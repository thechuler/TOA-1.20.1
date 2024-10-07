package net.rbkstudios.talesofaduranton.Entidades.Entity;

import com.mojang.datafixers.optics.Prism;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rbkstudios.talesofaduranton.Sonidos.InicializarSonidos;

public class FrogManGhostEntity extends Monster {
    public FrogManGhostEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }



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
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 8.5)
                .add(Attributes.FOLLOW_RANGE,90);
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
            this.moveInStraightLineTo(targetX, targetY, targetZ, 0.3); // Velocidad = 0.3
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
        double dz = targetZ - currentZ;

        // Calcular la distancia al objetivo en el plano XZ
        double distanceXZ = Math.sqrt(dx * dx + dz * dz);

        // Si la distancia es muy pequeña, detener el movimiento
        if (distanceXZ < 0.01) {
            this.getNavigation().stop();
            return;
        }

        // Calcular el movimiento en línea recta hacia el objetivo (normalizado)
        double moveX = (dx / distanceXZ) * speed;
        double moveZ = (dz / distanceXZ) * speed;

        // Aplicar el movimiento a la entidad sin detenerse por colisiones
        this.setDeltaMovement(moveX, 0, moveZ);  // Ignoramos la Y para evitar la gravedad

        // Calcular la rotación objetivo
        float targetYaw = (float) (Mth.atan2(dz, dx) * (180F / Math.PI)) - 90.0F;

        // Suavizar la rotación de la entidad con nuestro método `lerpRotation`
        this.setYRot(lerpRotation(this.getYRot(), targetYaw, 90.0F));

        // También ajustar la rotación de la cabeza si es necesario
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
                if (this.hasCustomName() && this.getCustomName() != null && this.getCustomName().getString().equals("Roman")) {
                    this.level().playLocalSound(this.blockPosition(), InicializarSonidos.FROGMANEASTEREGG.get(), SoundSource.NEUTRAL,this.getSoundVolume(),this.getVoicePitch(),false);
                }else{
                    this.level().playLocalSound(this.blockPosition(),InicializarSonidos.FROGMANAMBIENT.get(), SoundSource.NEUTRAL,this.getSoundVolume(),this.getVoicePitch(),false);
                }

            }
        }else{
            this.rugidoAnimationTimeout --;
        }

        if(getData(RUGIR) == false && this.rugidoAnimationTimeout <= 0){
            gruñirAnimationState.stop();
        }

    }




}
