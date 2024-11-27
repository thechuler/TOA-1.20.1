package net.rbkstudios.talesofaduranton.Entidades.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.rbkstudios.talesofaduranton.Sonidos.InicializarSonidos;
import net.rbkstudios.talesofaduranton.Utilidades;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.world.entity.monster.Monster.checkAnyLightMonsterSpawnRules;

public class FrogManZombieEntity extends Animal implements Enemy {


    private int textureVariant = -1;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    public FrogManZombieEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        if (textureVariant == -1) { // Solo asigna un valor aleatorio si no se ha cargado aún
            textureVariant = Utilidades.GenerarNumeroAleatorio(0, 3);
        }
    }


    public int getTextureVariant() {
        return textureVariant;
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
                .add(Attributes.ATTACK_DAMAGE, 8.5)
                .add(Attributes.FOLLOW_RANGE,100);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new FloatGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        this.goalSelector.addGoal(2,new MeleeAttackGoal(this,1,true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false, (entity) -> {
            return !(entity instanceof FrogManZombieEntity) && !(entity instanceof WaterAnimal);
        }));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
    }








    @Override
    public boolean doHurtTarget(Entity pEntity) {
        if(!this.level().isClientSide()&& pEntity instanceof LivingEntity){
            ((LivingEntity) pEntity).addEffect(new MobEffectInstance(MobEffects.WEAKNESS,200));
        }

        return super.doHurtTarget(pEntity);
    }





    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return InicializarSonidos.FROGMANZOMBIEHURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return InicializarSonidos.FROGMANZOMBIEAMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return InicializarSonidos.FROGMANZOMBIEDEAD.get();
    }

    @Override
    public void tick() {
        if(this.level().isClientSide()){
            setUpAnimationStates();
        }else{


        }





        super.tick();
    }





    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.FALL) ) {
            return false;
        }
        return super.hurt(source, amount);
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



    public static boolean PuedeSpawnear(EntityType<FrogManZombieEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random) {


        return  checkAnyLightMonsterSpawnRules(entityType,level,spawnType,position,random) && !level.getServer().overworld().isDay();
    }


    public static boolean checkAnyLightMonsterSpawnRules(EntityType<? extends FrogManZombieEntity> pType, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(pType, pLevel, pSpawnType, pPos, pRandom);
    }

}
