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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.rbkstudios.talesofaduranton.Entidades.IA.ShamanSummonGoal;
import net.rbkstudios.talesofaduranton.Sonidos.InicializarSonidos;
import org.jetbrains.annotations.Nullable;

public class FrogManShamanEntity extends Animal implements Enemy {
    public FrogManShamanEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }



    public static EntityDataAccessor<Boolean> RUGIR = SynchedEntityData.defineId(FrogManShamanEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> ATACAR = SynchedEntityData.defineId(FrogManShamanEntity.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState gruñirAnimationState = new AnimationState();
    public final AnimationState atacarAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int  rugidoAnimationTimeout = 0;
    private int  atacarAnimationTimeOut = 0;
    public int  castTimeOut = 0;

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal(this, Player.class, 8.0F, 0.8, 1.0));
        this.goalSelector.addGoal(2, new AvoidEntityGoal(this, AbstractGolem.class, 8.0F, 0.8, 1.0));

        this.goalSelector.addGoal(4, new ShamanSummonGoal(this));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal(this, Player.class, true)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, AbstractGolem.class, false));
        this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal(this, AbstractVillager.class, false)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, false));
    }







    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RUGIR,false);
        this.entityData.define(ATACAR,false);
    }


    public void setData(EntityDataAccessor<Boolean> DATA,boolean bool ) {
        this.entityData.set(DATA, bool);
    }

    public boolean getData(EntityDataAccessor<Boolean> DATA){
        return this.entityData.get(DATA);
    }



    @Override
    public void tick() {

        if(this.level().isClientSide()){
            setUpAnimationStates();
            ManageRugido();
        }else{

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



    @Override
    public boolean addEffect(MobEffectInstance pEffectInstance, @Nullable Entity pEntity) {
        if(pEffectInstance.getEffect() == MobEffects.POISON ||pEffectInstance.getEffect() == MobEffects.WITHER ){
            return false;
        }
        return super.addEffect(pEffectInstance, pEntity);
    }












    public static boolean PuedeSpawnear(EntityType<FrogManShamanEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random) {
        return true ;
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

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        setData(RUGIR,true);
        return null;
    }




    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 8.5);
    }



    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
