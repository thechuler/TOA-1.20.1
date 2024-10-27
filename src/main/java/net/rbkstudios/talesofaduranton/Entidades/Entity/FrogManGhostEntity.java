package net.rbkstudios.talesofaduranton.Entidades.Entity;

import com.mojang.datafixers.optics.Prism;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.rbkstudios.talesofaduranton.Sonidos.InicializarSonidos;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class FrogManGhostEntity extends Monster {
    public FrogManGhostEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }


    public static final int TICKS_PER_FLAP = Mth.ceil(1.4959966F);
    public static EntityDataAccessor<Boolean> RUGIR = SynchedEntityData.defineId(FrogManGhostEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> ATACAR = SynchedEntityData.defineId(FrogManGhostEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState gruñirAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int  rugidoAnimationTimeout = 0;



    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RUGIR,false);
        this.entityData.define(ATACAR,false);
    }


    public void setData(EntityDataAccessor<Boolean> DATA, boolean bool ) {
        this.entityData.set(DATA, bool);
    }

    public boolean getData(EntityDataAccessor<Boolean> DATA){
        return this.entityData.get(DATA);
    }



    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1,new FloatGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        this.goalSelector.addGoal(2,new MeleeAttackGoal(this,1,true));

        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, AbstractGolem.class, false));
        this.goalSelector.addGoal(7 ,new BeeWanderGoal());
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 8.5)
                .add(Attributes.FOLLOW_RANGE,90)
                .add(Attributes.FLYING_SPEED,5);
    }

    @Override
    public boolean canBeCollidedWith() {
        return false; // Evitar colisiones con proyectiles
    }



    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if(pSource.is(DamageTypes.FALL) || pSource.is(DamageTypes.IN_WALL)){
            return false;
        }
        if (pSource.getDirectEntity() instanceof Projectile) {
            return false; // No recibir daño de proyectiles
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    public void tick() {

        if (this.level().isClientSide) {
            setUpAnimationStates();
            ManageRugido();
        }

        // Permitir que el fantasma atraviese bloques siempre que esté buscando su objetivo
        if (this.getTarget() != null) {
            this.noPhysics = true;  // Activar no-clip, permitiendo atravesar bloques

            // Obtener la posición del objetivo
            double targetX = this.getTarget().getX();
            double targetY = this.getTarget().getY();
            double targetZ = this.getTarget().getZ();

            // Mover la entidad en línea recta hacia el objetivo
            this.moveInStraightLineTo(targetX, targetY, targetZ, 0.2); // Velocidad = 0.3
        } else {
            // Desactivar no-clip cuando no esté buscando un objetivo
            this.noPhysics = false;
        }

        super.tick();
    }


    public float lerpRotation(float fromAngle, float toAngle, float maxStep) {
        // Normalizamos los ángulos para evitar problemas de interpolación cruzando 360 grados
        float f = Mth.wrapDegrees(toAngle - fromAngle);
        return fromAngle + Mth.clamp(f, -maxStep, maxStep);
    }


    public void moveInStraightLineTo(double targetX, double targetY, double targetZ, double speed) {
        // Obtener las coordenadas actuales de la entidad
        double currentX = this.getX();
        double currentY = this.getY();
        double currentZ = this.getZ();

        // Calcular la diferencia entre la posición actual y el objetivo
        double dx = targetX - currentX;
        double dy = targetY - currentY;  // Diferencia en Y
        double dz = targetZ - currentZ;

        // Calcular la distancia total al objetivo en 3D
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

        // Si la distancia es muy pequeña, detener el movimiento
        if (distance < 0.1) {
            this.getNavigation().stop();
            return;
        }

        // Calcular el movimiento en línea recta hacia el objetivo (normalizado)
        double moveX = (dx / distance) * speed;
        double moveY = (dy / distance) * speed;  // Movimiento en Y
        double moveZ = (dz / distance) * speed;

        // Aplicar el movimiento a la entidad
        this.setDeltaMovement(moveX, moveY, moveZ);  // Ahora también incluimos la Y

        // Calcular la rotación objetivo
        float targetYaw = (float) (Mth.atan2(dz, dx) * (180F / Math.PI)) - 90.0F;

        // Suavizar la rotación de la entidad con nuestro método `lerpRotation`
        this.setYRot(lerpRotation(this.getYRot(), targetYaw, 90.0F));

        // Ajustar la rotación de la cabeza
        this.yHeadRot = this.getYRot();
    }




    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING){
            f = Math.min(pPartialTick * 6f,1f);
        }else{
            f=0f;
        }

        this.walkAnimation.update(f,0.2f);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    //Setup Estados Animacion
    private void setUpAnimationStates(){

        if(idleAnimationTimeout<= 0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }


    }





    private void ManageRugido(){

        if(getData(RUGIR) && this.rugidoAnimationTimeout <= 0){
            if(this.level().isClientSide()){
                setData(RUGIR,false);
                this.rugidoAnimationTimeout = 60;
                this.gruñirAnimationState.start(this.tickCount);

                    this.level().playLocalSound(this.blockPosition(),InicializarSonidos.FROGMANGHOSTAMBIENT.get(), SoundSource.NEUTRAL,this.getSoundVolume(),this.getVoicePitch(),false);


            }
        }else{
            this.rugidoAnimationTimeout --;
        }

        if(getData(RUGIR) == false && this.rugidoAnimationTimeout <= 0){
            gruñirAnimationState.stop();
        }


    }






    class BeeWanderGoal extends Goal {

        BeeWanderGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            return FrogManGhostEntity.this.navigation.isDone() && FrogManGhostEntity.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return FrogManGhostEntity.this.navigation.isInProgress();
        }

        public void start() {
            Vec3 vec3 = this.findPos();
            if (vec3 != null) {
                FrogManGhostEntity.this.navigation.moveTo(FrogManGhostEntity.this.navigation.createPath(BlockPos.containing(vec3), 1), 1.0);
            }

        }

        @javax.annotation.Nullable
        private Vec3 findPos() {
            Vec3 vec3;

            vec3 = FrogManGhostEntity.this.getViewVector(0.0F);

            Vec3 vec32 = HoverRandomPos.getPos(FrogManGhostEntity.this, 8, 7, vec3.x, vec3.z, 1.5707964F, 3, 1);
            return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(FrogManGhostEntity.this, 8, 4, -2, vec3.x, vec3.z, 1.5707963705062866);
        }
    }

    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel) {
            public boolean isStableDestination(BlockPos p_27947_) {
                return !this.level.getBlockState(p_27947_.below()).isAir();
            }


        };

        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(false);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }


    public boolean isFlapping() {
        return this.isFlying() && this.tickCount % TICKS_PER_FLAP == 0;
    }

    public boolean isFlying() {
        return !this.onGround();
    }


    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        setData(RUGIR,true);
        return null;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return InicializarSonidos.FROGMANGHOSTHURT.get();
    }


    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return InicializarSonidos.FROGMANGHOSTDEATH.get();
    }



    protected static boolean getHeight(BlockAndTintGetter p_186210_, BlockPos p_186211_) {
        return p_186210_.getBlockFloorHeight(p_186211_) > 100;
    }


    public static boolean PuedeSpawnear(EntityType<FrogManGhostEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random) {


        return  checkAnyLightMonsterSpawnRules(entityType,level,spawnType,position,random) && !level.getServer().overworld().isDay();
    }



}