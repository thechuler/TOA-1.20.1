package net.rbkstudios.talesofaduranton.Entidades.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.rbkstudios.talesofaduranton.Entidades.IA.FrogManTrades;

import net.rbkstudios.talesofaduranton.Sonidos.InicializarSonidos;
import net.rbkstudios.talesofaduranton.Utilidades;
import org.jetbrains.annotations.Nullable;

public class FrogManTraderEntity extends WanderingTrader {
    public FrogManTraderEntity(EntityType<? extends AbstractVillager> pEntityType, Level pLevel) {
        super((EntityType<? extends WanderingTrader>) pEntityType, pLevel);
    }
    private boolean yaActualizo;
    public static EntityDataAccessor<Boolean> RUGIR = SynchedEntityData.defineId(FrogManTraderEntity.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState gruñirAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int  rugidoAnimationTimeout = 0;



    @Override
    protected void updateTrades() {
        MerchantOffers offers = this.getOffers();

        offers.clear();

            FrogManTrades.GenerarTrades(offers);





    }


    public void GenerarOfertas(MerchantOffers ofertas ){
        offers.clear();

    }




    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RUGIR,false);
    }

    public void setData(EntityDataAccessor<Boolean> DATA,boolean bool ) {
        this.entityData.set(DATA, bool);
    }
    public boolean getData(EntityDataAccessor<Boolean> DATA){
        return this.entityData.get(DATA);
    }


    @Override
    protected void rewardTradeXp(MerchantOffer merchantOffer) {
    }



    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 8.5);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.FALL) ) {
            return false;
        }
        return super.hurt(source, amount);
    }



    @Override
    public void tick() {
        if(this.level().isClientSide()){
            setUpAnimationStates();
            ManageRugido();
        }else{
            if(!this.level().isNight()){
                this.yaActualizo = false;
            }else if (!this.yaActualizo){
                FrogManTrades.GenerarTrades(this.getOffers());
                Utilidades.spawnearParticulas(this,20, ParticleTypes.HAPPY_VILLAGER);
              this.playSound(InicializarSonidos.FROGMANTRADEUPDATE.get(),3,1);
             this.yaActualizo = true;
            }



        }


        super.tick();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal(this, Zombie.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal(this, Evoker.class, 12.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal(this, Vindicator.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal(this, Vex.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal(this, Pillager.class, 15.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal(this, Illusioner.class, 12.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal(this, Zoglin.class, 10.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.5));
        this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 0.35));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.35));
        this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }


    private void setUpAnimationStates(){

        if(idleAnimationTimeout<= 0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }


    }


    public static boolean PuedeSpawnear(EntityType<FrogManTraderEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random) {
        return true ;
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


    @Override
    protected SoundEvent getTradeUpdatedSound(boolean pGetYesSound) {
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
}
