package net.rbkstudios.talesofaduranton.Entidades.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.rbkstudios.talesofaduranton.Sonidos.InicializarSonidos;
import org.jetbrains.annotations.Nullable;

public class FrogManTropicalEntity extends Animal implements Enemy {
    public FrogManTropicalEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(FrogManTropicalEntity.class, EntityDataSerializers.BYTE);
    public static EntityDataAccessor<Boolean> RUGIR = SynchedEntityData.defineId(FrogManTropicalEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> ATACAR = SynchedEntityData.defineId(FrogManTropicalEntity.class, EntityDataSerializers.BOOLEAN);



    private boolean estaTrepando = false;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState gruñirAnimationState = new AnimationState();
    public final AnimationState climbAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int  rugidoAnimationTimeout = 0;
    private int  atacarAnimationTimeOut = 0;



    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RUGIR,false);
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);
    }


    public void setData(EntityDataAccessor<Boolean> DATA, boolean bool ) {
        this.entityData.set(DATA, bool);
    }

    public boolean getData(EntityDataAccessor<Boolean> DATA){
        return this.entityData.get(DATA);
    }



    protected PathNavigation createNavigation(Level pLevel) {
        return new WallClimberNavigation(this, pLevel);
    }


    public boolean onClimbable() {
        return this.isClimbing();
    }

    public void setClimbing(boolean climbing) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (climbing) {
            b0 |= 1;
        } else {
            b0 &= -2;
        }
        this.entityData.set(DATA_FLAGS_ID, b0);
    }
    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }



















    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }


    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.FALL) ) {
            return false;
        }
        if(source.getDirectEntity() instanceof LivingEntity){
            ((LivingEntity) source.getDirectEntity()).addEffect(new MobEffectInstance(MobEffects.POISON,200));
        }

        return super.hurt(source, amount);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 8.5);
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




    @Override
    public void tick() {


        if(this.level().isClientSide()){
            setUpAnimationStates();
            ManageRugido();
            if(this.isClimbing() && !estaTrepando){
                climbAnimationState.start(this.tickCount);
                estaTrepando = true;
            }else if(!this.isClimbing() && estaTrepando){
                climbAnimationState.stop();
                estaTrepando = false;
            }


        }
        if (!this.level().isClientSide) {
            this.setClimbing(this.horizontalCollision);
        }

        super.tick();
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

    //Setup Estados Animacion
    private void setUpAnimationStates(){

        if(idleAnimationTimeout<= 0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }


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


    public static boolean PuedeSpawnear(EntityType<FrogManEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random) {
        return true ;
    }
}
