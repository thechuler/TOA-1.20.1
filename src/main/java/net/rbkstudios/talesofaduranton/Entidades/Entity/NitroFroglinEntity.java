package net.rbkstudios.talesofaduranton.Entidades.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.rbkstudios.talesofaduranton.Entidades.InicializarEntidades;
import net.rbkstudios.talesofaduranton.Sonidos.InicializarSonidos;
import net.rbkstudios.talesofaduranton.Utilidades;
import org.checkerframework.common.returnsreceiver.qual.This;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NitroFroglinEntity extends Animal {
    public NitroFroglinEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static EntityDataAccessor<Boolean> RUGIR = SynchedEntityData.defineId(NitroFroglinEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> ENCENDIDO = SynchedEntityData.defineId(NitroFroglinEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> ESTORNUDO = SynchedEntityData.defineId(NitroFroglinEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> ESTORNUDOEFECTO = SynchedEntityData.defineId(NitroFroglinEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> GENERARPOLVORA = SynchedEntityData.defineId(NitroFroglinEntity.class, EntityDataSerializers.BOOLEAN);



    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState gruñirAnimationState = new AnimationState();
    public final AnimationState estornudoAnimationState = new AnimationState();


    private int idleAnimationTimeout = 0;
    private int  rugidoAnimationTimeout = 0;
    private int  estornudoAnimationTimeout = 0;
    private  int tiempoestornudo = 60;
    public int TiempoEntreEstornudos = 0;
    public int variacionDeTiempo = 0;




    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RUGIR,false);
        this.entityData.define(ENCENDIDO,true);
        this.entityData.define(ESTORNUDO,false  );
        this.entityData.define(ESTORNUDOEFECTO,false  );
        this.entityData.define(GENERARPOLVORA,false  );

    }
    public void setData(EntityDataAccessor<Boolean> DATA,boolean bool ) {
        this.entityData.set(DATA, bool);
    }

    public boolean getData(EntityDataAccessor<Boolean> DATA){
        return this.entityData.get(DATA);
    }


    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {
        ItemStack item = pPlayer.getItemInHand(pHand);
        if(!this.level().isClientSide())
          if(getData(ENCENDIDO) && item.is(Items.BUCKET)) {// Solo reducir ítems si el jugador no está en creativo
              setData(ENCENDIDO,false);
              item.shrink(1);
              pPlayer.addItem(new ItemStack(Items.LAVA_BUCKET)); // Darle un balde de lava
          }
        return InteractionResult.SUCCESS;
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






    private int sneezeInterval = 0;

    @Override
    public void tick() {

        if (this.level().isClientSide) {
            setUpAnimationStates();
            if (!getData(ENCENDIDO)) {
                ManageRugido();
            }
            ManageEstornudo();
        } else {
            if (!this.isBaby() && !getData(ENCENDIDO)) {

                if (sneezeInterval == 0) {
                    sneezeInterval = Utilidades.GenerarNumeroAleatorio(3000,5000);
                }


                if (TiempoEntreEstornudos >= sneezeInterval) {
                    System.out.print("SE ACTIVO" + "\n");
                    setData(ESTORNUDO, true);
                    setData(GENERARPOLVORA, true);
                    TiempoEntreEstornudos = 0;
                    sneezeInterval = Utilidades.GenerarNumeroAleatorio(3000,5000);
                } else {
                    TiempoEntreEstornudos++;
                }


                if (TiempoEntreEstornudos >= 60 && tiempoestornudo <= 120) {
                    setData(ESTORNUDO, false);
                }

                System.out.print(TiempoEntreEstornudos + "\n");
            }

            entregarPolvora();
            if (this.level().dimension() == Level.NETHER && !getData(ENCENDIDO)) {
                setData(ENCENDIDO, true);
            }
        }

        super.tick();
    }



    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 8.5)
                .add(Attributes.FOLLOW_RANGE,20);

    }




    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.FALL) || source.is(DamageTypes.ON_FIRE) || source.is(DamageTypes.LAVA)  ) {
            return false;
        }
        return super.hurt(source, amount);
    }




    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new FloatGoal(this));
        this.goalSelector.addGoal(2,new MeleeAttackGoal(this,1,true));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1, Ingredient.of(new ItemLike[]{Items.COAL}), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, FrogManZombieEntity.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, FrogManEntity.class, false));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
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

        if(!getData(RUGIR) && this.rugidoAnimationTimeout <= 0){
            gruñirAnimationState.stop();
        }

    }










    //---------------------ESTORNUDO-----------------------------




    public void entregarPolvora() {
        if (getData(GENERARPOLVORA)) {
            if (tiempoestornudo > 0) {
                tiempoestornudo--;
            } else {
                ItemEntity polvora = new ItemEntity(
                        this.level(), this.getX(), this.getY() + 0.5, this.getZ(),
                        new ItemStack(Items.GUNPOWDER)
                );
                float yaw = (float) Math.toRadians(this.getYRot());
                double launchSpeed = 0.3;
                double xVel = -Math.sin(yaw) * launchSpeed;
                double zVel = Math.cos(yaw) * launchSpeed;
                polvora.setDeltaMovement(xVel, 0.2, zVel);

                this.level().addFreshEntity(polvora);

                setData(GENERARPOLVORA, false);
            }
        } else {
            tiempoestornudo = 60;
        }
    }





    private boolean sneezeTriggered = false;

    public void ManageEstornudo() {

        if (getData(ESTORNUDO) && !sneezeTriggered && this.estornudoAnimationTimeout <= 0) {
            this.estornudoAnimationTimeout = 60;
            this.estornudoAnimationState.start(this.tickCount);
            sneezeTriggered = true;
        } else {
            this.estornudoAnimationTimeout--;
        }

        // Cuando el timer baje a 25 (entre 25 y 0) y el NitroFroglin no esté encendido, lanzamos las partículas.
        if (this.estornudoAnimationTimeout <= 25 && this.estornudoAnimationTimeout > 0 && !getData(ENCENDIDO)) {
            sneeze();
        }

        // Una vez finalizada la animación, detenemos y reseteamos el flag interno para permitir futuros estornudos.
        if (this.estornudoAnimationTimeout <= 0) {
            estornudoAnimationState.stop();
            sneezeTriggered = false;
        }
    }



    public void sneeze() {
        if (this.level().isClientSide) {
            for (int i = 0; i < 10; i++) { // 10 partículas para un mejor efecto
                double spread = 0.2;
                double speed = 0.3;

                float yaw = (float) Math.toRadians(this.getYRot());
                float pitch = (float) Math.toRadians(this.getXRot());

                double xDir = -Math.sin(yaw) * Math.cos(pitch) + (Math.random() - 0.5) * spread;
                double yDir = -Math.sin(pitch) + (Math.random() - 0.5) * spread;
                double zDir = Math.cos(yaw) * Math.cos(pitch) + (Math.random() - 0.5) * spread;

                double x = this.getX() + xDir * 0.5;
                double y = this.getY() + this.getEyeHeight() * 0.9;
                double z = this.getZ() + zDir * 0.5;

                this.level().addParticle(ParticleTypes.SMOKE, x, y-0.1f, z, xDir * speed, yDir * speed, zDir * speed);
            }

        }
    }

















    @Override
    public boolean isFood(ItemStack pStack) {
        if(pStack.is(Items.COAL)){
            return true;
        }else{
            return false;
        }

    }




    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return new NitroFroglinEntity(InicializarEntidades.NITROFROGLIN.get(),serverLevel);
    }





    // Los bools se recuperan y van directo a la sincronizacion server- cliente
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("encendido", getData(ENCENDIDO));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        setData(ENCENDIDO, pCompound.getBoolean("encendido"));
    }




    public static boolean PuedeSpawnear(EntityType<NitroFroglinEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random) {
return true;
    }
    }
