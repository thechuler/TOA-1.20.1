package net.rbkstudios.talesofaduranton.Entidades.Entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.rbkstudios.talesofaduranton.Sonidos.InicializarSonidos;
import net.rbkstudios.talesofaduranton.Utilidades;
import org.jetbrains.annotations.Nullable;

public class FrogManSkeletonEntity extends FrogManEntity {




    public static EntityDataAccessor<Boolean> RUGIR = SynchedEntityData.defineId(FrogManEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> ATACAR = SynchedEntityData.defineId(FrogManEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState gruñirAnimationState = new AnimationState();
    public final AnimationState spawnAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int  rugidoAnimationTimeout = 0;


    public FrogManSkeletonEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        spawnAnimationState.start(this.tickCount);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

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

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        setData(RUGIR,true);
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return InicializarSonidos.FROGMANHURT.get();
    }


    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return InicializarSonidos.FROGMANDEATH.get();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new FloatGoal(this));
        this.goalSelector.addGoal(2,new MeleeAttackGoal(this,1,true));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
    }


    @Override
    public void tick() {



            if (this.level().isClientSide()) {
                setUpAnimationStates();
                ManageRugido();
            }
            if (!this.level().isClientSide()) {
                ManageOwnDead();
            }




        super.tick();
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
                    this.level().playLocalSound(this.blockPosition(),InicializarSonidos.FROGMANEASTEREGG.get(), SoundSource.NEUTRAL,this.getSoundVolume(),this.getVoicePitch(),false);
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

















    private void ManageOwnDead(){
        if(this.getTarget() == null || !this.getTarget().isAlive()){
            Utilidades.spawnearParticulas(this,15, ParticleTypes.CLOUD);
         this.discard();
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 4.5);
    }




}
