package net.rbkstudios.talesofaduranton.Entidades.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;

import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.rbkstudios.talesofaduranton.Sonidos.InicializarSonidos;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FrogManBeastEntity extends Animal implements Enemy {
    public FrogManBeastEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    public static EntityDataAccessor<Boolean> RUGIR = SynchedEntityData.defineId(FrogManBeastEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> CARGAR = SynchedEntityData.defineId(FrogManBeastEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> ATACAR = SynchedEntityData.defineId(FrogManBeastEntity.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CARGAR,false);
        this.entityData.define(ATACAR,false);
        this.entityData.define(RUGIR,false);
    }



    public void setData(EntityDataAccessor<Boolean> DATA,boolean bool ) {
        this.entityData.set(DATA, bool);
    }
    public boolean getData(EntityDataAccessor<Boolean> DATA){return this.entityData.get(DATA);}
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState gruñirAnimationState = new AnimationState();
    public final AnimationState chargeAnimationState = new AnimationState();
    public final AnimationState atacarAnimationState = new AnimationState();
    private boolean chargeAnimationStarted = false;
    private boolean attackAnimationStarted = false;
    private int tiempoCarga= 0;
    private int tiempoAtascado = 0;
    private int  rugidoAnimationTimeout = 0;
    private int idleAnimationTimeout = 0;

    private BlockPos posicion;

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 8.5)
                .add(Attributes.FOLLOW_RANGE,20);

    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if(!getData(CARGAR)){
            setData(RUGIR,true);
        }
        return null;
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
    private void setUpAnimationStates(){

        if(idleAnimationTimeout<= 0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }


    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            ManageAnimations();
            setUpAnimationStates();
            ManageRugido();
        }

        if (!this.level().isClientSide) {
            if (getTarget() != null && getTarget().isAlive()) {
                performRaycast();
                lookAtTarget(getTarget());
                if(!getData(ATACAR) && !getData(CARGAR)) {
                    if(tiempoCarga <= 0){
                        setData(CARGAR, true);
                        tiempoCarga = 30;
                    }else{
                        tiempoCarga--;
                    }

                }

            Cargar();
                Atacar();
            }


        }



    }






    public void Cargar(){
 if(getData(CARGAR)){
     if(tiempoCarga >= 50){
     setData(CARGAR,false);

     if(this.getTarget() !=null && this.getTarget().isAlive()){

         setData(ATACAR,true);
         tiempoAtascado = 200;
         posicion = this.getTarget().getOnPos();
     }

 }else{
         tiempoCarga++;
     }
    }}



    public void Atacar(){
        if(getData(ATACAR)){
            performRaycast();
            moveTowardsBlockPos(posicion,0.8);

        }
    }

    private void lookAtTarget(LivingEntity target) {
        Vec3 targetPosition = target.position();
        Vec3 currentPosition = this.position();
        Vec3 direction = targetPosition.subtract(currentPosition);
        double yaw = Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90;
        double pitch = Math.toDegrees(-Math.atan2(direction.y, direction.horizontalDistance()));
        this.setYRot((float) yaw);
        this.setXRot((float) pitch);
        this.yHeadRot = (float) yaw;
        this.yBodyRot = (float) yaw;
    }



    public void performRaycast() {
        double distance = 2.0;
        Vec3 viewDirection = this.getViewVector(1.0F).normalize();

        // Modificar el vector de dirección para que se mueva hacia abajo en el eje Y
        Vec3 modifiedViewDirection = new Vec3(viewDirection.x, viewDirection.y-1, viewDirection.z).normalize();

        Vec3 eyePosition = this.getEyePosition();
        Vec3 endPosition = eyePosition.add(modifiedViewDirection.scale(distance));
        BlockHitResult blockHitResult = this.level().clip(new ClipContext(
                eyePosition,
                endPosition,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                this
        ));

        if (blockHitResult.getType() == HitResult.Type.BLOCK && getData(ATACAR)) {
            BlockPos blockPos = blockHitResult.getBlockPos();
            System.out.println("Bloque detectado en: " + blockPos);

            // Destruir bloques en un área de 3 bloques de ancho y 2 bloques de alto
            destroyBlocksInArea(blockPos);
        }

        AABB boundingBox = this.getBoundingBox().expandTowards(modifiedViewDirection.scale(distance)).inflate(1.0);
        List<Entity> entitiesInPath = this.level().getEntities(this, boundingBox, entity -> {
            return entity != this && entity.isAlive();
        });

        for (Entity entity : entitiesInPath) {
            Vec3 entityPosition = entity.position();
            if (entityPosition.distanceTo(eyePosition) <= distance && getData(ATACAR)) {
                // Aplicar daño y empuje a la entidad
                if (entity instanceof LivingEntity livingEntity && !(entity instanceof FrogManBeastEntity)) {
                    livingEntity.hurt(damageSources().generic(), 15.0F); // Aplica 10 puntos de daño
                    Vec3 pushDirection = entityPosition.subtract(eyePosition).normalize().scale(6.0); // Empujar la entidad
                    entity.setDeltaMovement(pushDirection);

                }

            }
        }
    }
    private void destroyBlocksInArea(BlockPos center) {
        int width = 3;
        int height = 2;


        int entityY = (int) this.position().y;

        for (int x = -width / 2; x <= width / 2; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = -width / 2; z <= width / 2; z++) {
                    BlockPos pos = center.offset(x, y, z);


                    if (pos.getY() >= entityY && this.level().getBlockState(pos).getBlock() != Blocks.BEDROCK) {
                        this.level().destroyBlock(pos, false);
                    }
                }
            }
        }
    }







    private void moveTowardsBlockPos(BlockPos targetPos, double speed) {
        Vec3 currentPosition = this.position();
        Vec3 targetPosition = new Vec3(targetPos.getX() + 0.5, targetPos.getY(), targetPos.getZ() + 0.5);
        Vec3 direction = targetPosition.subtract(currentPosition).normalize(); // Normaliza la dirección para mantener una velocidad constante

        double distanceToTarget = currentPosition.distanceTo(targetPosition);



        if (distanceToTarget < 1.5) { // Si estamos cerca del objetivo
            this.setDeltaMovement(Vec3.ZERO);
            setData(ATACAR,false);
            this.posicion = null; // Optional: detener el movimiento cuando se alcance el objetivo
        } else {
            if(tiempoAtascado>=0) {
                this.setDeltaMovement(direction.scale(speed)); // Mueve la entidad en la dirección del objetivo
               tiempoAtascado--;
            }else {
                this.setDeltaMovement(Vec3.ZERO);
                setData(ATACAR,false);
                this.posicion = null;
            }
        }


    }






    public void ManageAnimations() {
        if (getData(CARGAR)) {
            if (!chargeAnimationStarted) {
                chargeAnimationStarted = true;
                this.chargeAnimationState.start(this.tickCount);
            }
        } else {
            if (chargeAnimationStarted) {
                this.chargeAnimationState.stop();
                chargeAnimationStarted = false;
            }
        }

        if (getData(ATACAR)) {
            if (!attackAnimationStarted) {
                attackAnimationStarted = true;
                this.atacarAnimationState.start(this.tickCount);
            }
        } else {
            if (attackAnimationStarted) {
                this.atacarAnimationState.stop();
                attackAnimationStarted = false;
            }
        }
    }



    private void ManageRugido(){

        if(getData(RUGIR) && this.rugidoAnimationTimeout <= 0){
            if(this.level().isClientSide()){
                setData(RUGIR,false);
                this.rugidoAnimationTimeout = 60;
                this.gruñirAnimationState.start(this.tickCount);
                    this.level().playLocalSound(this.blockPosition(),InicializarSonidos.FROGMANBEASTHURT.get(), SoundSource.NEUTRAL,this.getSoundVolume(),this.getVoicePitch(),false);


            }
        }else{
            this.rugidoAnimationTimeout --;
        }

        if(getData(RUGIR) == false && this.rugidoAnimationTimeout <= 0){
            gruñirAnimationState.stop();
        }

    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new FloatGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
       this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, AbstractGolem.class, false));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
