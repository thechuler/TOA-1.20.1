package net.rbkstudios.talesofaduranton.Entidades.Entity;


import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;


import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;

import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;


import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.WaterAnimal;

import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Enemy;


import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;


public class FrogManDeepEntity extends WaterAnimal implements Enemy {
    protected final WaterBoundPathNavigation waterNavigation;
    protected final GroundPathNavigation groundNavigation;
    boolean searchingForLand;



    public FrogManDeepEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new DrownedMoveControl(this);
        this.waterNavigation = new WaterBoundPathNavigation(this, pLevel);
        this.groundNavigation =new GroundPathNavigation(this, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 8.5)
                .add(Attributes.FOLLOW_RANGE,90);
    }



    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new DrownedGoToWaterGoal(this, 1.0));
        this.goalSelector.addGoal(1,new DragToDeepGoal(this,2));
        this.goalSelector.addGoal(5, new DrownedGoToBeachGoal(this, 1.0));
        this.goalSelector.addGoal(6, new DrownedSwimUpGoal(this, 1.0, this.level().getSeaLevel()));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[]{Drowned.class})).setAlertOthers(new Class[]{ZombifiedPiglin.class}));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, false));

    }





















    public class DragToDeepGoal extends Goal {
        private final Mob mob;
        private LivingEntity target;
        private final double speed;
        private int cooldown;

        public DragToDeepGoal(Mob mob, double speed) {
            this.mob = mob;
            this.speed = speed;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            // Busca un objetivo cercano en un rango definido
            this.target = this.mob.getTarget();
            return this.target != null && this.target.isAlive() && this.isInRangeToDrag(target);
        }

        @Override
        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive() && this.isInRangeToDrag(target);
        }

        @Override
        public void start() {
            this.cooldown = 0;
        }

        @Override
        public void stop() {
            this.target = null;
        }

        @Override
        public void tick() {
            // Moverse hacia el objetivo
            if (this.cooldown > 0) {
                this.cooldown--;
                return;
            }

            if (this.mob.distanceToSqr(this.target) < 2.0D) {
                // Si la entidad ha alcanzado el objetivo, lo arrastra al fondo
                this.dragTargetToDeep(target);
            } else {
                // Si no ha alcanzado al objetivo, sigue moviéndose hacia él
                this.mob.getNavigation().moveTo(this.target, this.speed);
            }
        }

        private boolean isInRangeToDrag(LivingEntity target) {
            return this.mob.distanceToSqr(target) < 64.0D; // Define un rango de detección para empezar a arrastrar
        }

        private void dragTargetToDeep(LivingEntity target) {
            // Aplica movimiento descendente al objetivo (simulando arrastre hacia el fondo del agua)
            if (target.isInWater()) {
                Vec3 movement = new Vec3(0, -0.3, 0); // Aplica un movimiento hacia abajo
                target.setDeltaMovement(target.getDeltaMovement().add(movement));
            } else {
                // Si no está en el agua, simplemente lo empuja hacia la entidad
                Vec3 direction = new Vec3(
                        this.mob.getX() - target.getX(),
                        this.mob.getY() - target.getY(),
                        this.mob.getZ() - target.getZ()
                ).normalize();
                target.setDeltaMovement(target.getDeltaMovement().add(direction.scale(0.1)));
            }

            // Reinicia el cooldown para evitar arrastrar continuamente
            this.cooldown = 20; // 20 ticks (1 segundo)
        }
    }




    boolean wantsToSwim() {
        if (this.searchingForLand) {
            return true;
        } else {
            LivingEntity $$0 = this.getTarget();
            return $$0 != null && $$0.isInWater();
        }
    }

    private static class DrownedGoToWaterGoal extends Goal {
        private final PathfinderMob mob;
        private double wantedX;
        private double wantedY;
        private double wantedZ;
        private final double speedModifier;
        private final Level level;

        public DrownedGoToWaterGoal(PathfinderMob pMob, double pSpeedModifier) {
            this.mob = pMob;
            this.speedModifier = pSpeedModifier;
            this.level = pMob.level();
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            if (!this.level.isDay()) {
                return false;
            } else if (this.mob.isInWater()) {
                return false;
            } else {
                Vec3 $$0 = this.getWaterPos();
                if ($$0 == null) {
                    return false;
                } else {
                    this.wantedX = $$0.x;
                    this.wantedY = $$0.y;
                    this.wantedZ = $$0.z;
                    return true;
                }
            }
        }

        public boolean canContinueToUse() {
            return !this.mob.getNavigation().isDone();
        }

        public void start() {
            this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
        }

        @Nullable
        private Vec3 getWaterPos() {
            RandomSource $$0 = this.mob.getRandom();
            BlockPos $$1 = this.mob.blockPosition();

            for(int $$2 = 0; $$2 < 10; ++$$2) {
                BlockPos $$3 = $$1.offset($$0.nextInt(20) - 10, 2 - $$0.nextInt(8), $$0.nextInt(20) - 10);
                if (this.level.getBlockState($$3).is(Blocks.WATER)) {
                    return Vec3.atBottomCenterOf($$3);
                }
            }

            return null;
        }
    }

    private static class DrownedGoToBeachGoal extends MoveToBlockGoal {
        private final FrogManDeepEntity drowned;

        public DrownedGoToBeachGoal(FrogManDeepEntity pDrowned, double pSpeedModifier) {
            super(pDrowned, pSpeedModifier, 8, 2);
            this.drowned = pDrowned;
        }

        public boolean canUse() {
            return super.canUse() && !this.drowned.level().isDay() && this.drowned.isInWater() && this.drowned.getY() >= (double)(this.drowned.level().getSeaLevel() - 3);
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockPos $$2 = pPos.above();
            return pLevel.isEmptyBlock($$2) && pLevel.isEmptyBlock($$2.above()) ? pLevel.getBlockState(pPos).entityCanStandOn(pLevel, pPos, this.drowned) : false;
        }

        public void start() {
            this.drowned.setSearchingForLand(false);
            this.drowned.navigation = this.drowned.groundNavigation;
            super.start();
        }

        public void stop() {
            super.stop();
        }
    }

    private static class DrownedMoveControl extends MoveControl {
        private final FrogManDeepEntity drowned;

        public DrownedMoveControl(FrogManDeepEntity pDrowned) {
            super(pDrowned);
            this.drowned = pDrowned;
        }

        public void tick() {
            LivingEntity $$0 = this.drowned.getTarget();
            if (this.drowned.wantsToSwim() && this.drowned.isInWater()) {
                if ($$0 != null && $$0.getY() > this.drowned.getY() || this.drowned.searchingForLand) {
                    this.drowned.setDeltaMovement(this.drowned.getDeltaMovement().add(0.0, 0.002, 0.0));
                }

                if (this.operation != Operation.MOVE_TO || this.drowned.getNavigation().isDone()) {
                    this.drowned.setSpeed(0.0F);
                    return;
                }

                double $$1 = this.wantedX - this.drowned.getX();
                double $$2 = this.wantedY - this.drowned.getY();
                double $$3 = this.wantedZ - this.drowned.getZ();
                double $$4 = Math.sqrt($$1 * $$1 + $$2 * $$2 + $$3 * $$3);
                $$2 /= $$4;
                float $$5 = (float)(Mth.atan2($$3, $$1) * 57.2957763671875) - 90.0F;
                this.drowned.setYRot(this.rotlerp(this.drowned.getYRot(), $$5, 90.0F));
                this.drowned.yBodyRot = this.drowned.getYRot();
                float $$6 = (float)(this.speedModifier * this.drowned.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float $$7 = Mth.lerp(0.125F, this.drowned.getSpeed(), $$6);
                this.drowned.setSpeed($$7);
                this.drowned.setDeltaMovement(this.drowned.getDeltaMovement().add((double)$$7 * $$1 * 0.005, (double)$$7 * $$2 * 0.1, (double)$$7 * $$3 * 0.005));
            } else {
                if (!this.drowned.onGround()) {
                    this.drowned.setDeltaMovement(this.drowned.getDeltaMovement().add(0.0, -0.008, 0.0));
                }

                super.tick();
            }

        }
    }

    public void setSearchingForLand(boolean pSearchingForLand) {
        this.searchingForLand = pSearchingForLand;
    }

    protected boolean closeToNextPos() {
        Path $$0 = this.getNavigation().getPath();
        if ($$0 != null) {
            BlockPos $$1 = $$0.getTarget();
            if ($$1 != null) {
                double $$2 = this.distanceToSqr((double)$$1.getX(), (double)$$1.getY(), (double)$$1.getZ());
                if ($$2 < 4.0) {
                    return true;
                }
            }
        }

        return false;
    }

    static class DrownedSwimUpGoal extends Goal {
        private final FrogManDeepEntity drowned;
        private final double speedModifier;
        private final int seaLevel;
        private boolean stuck;

        public DrownedSwimUpGoal(FrogManDeepEntity pDrowned, double pSpeedModifier, int pSeaLevel) {
            this.drowned = pDrowned;
            this.speedModifier = pSpeedModifier;
            this.seaLevel = pSeaLevel;
        }

        public boolean canUse() {
            return !this.drowned.level().isDay() && this.drowned.isInWater() && this.drowned.getY() < (double)(this.seaLevel - 2);
        }

        public boolean canContinueToUse() {
            return this.canUse() && !this.stuck;
        }

        public void tick() {
            if (this.drowned.getY() < (double)(this.seaLevel - 1) && (this.drowned.getNavigation().isDone() || this.drowned.closeToNextPos())) {
                Vec3 $$0 = DefaultRandomPos.getPosTowards(this.drowned, 4, 8, new Vec3(this.drowned.getX(), (double)(this.seaLevel - 1), this.drowned.getZ()), 1.5707963705062866);
                if ($$0 == null) {
                    this.stuck = true;
                    return;
                }

                this.drowned.getNavigation().moveTo($$0.x, $$0.y, $$0.z, this.speedModifier);
            }

        }

        public void start() {
            this.drowned.setSearchingForLand(true);
            this.stuck = false;
        }

        public void stop() {
            this.drowned.setSearchingForLand(false);
        }
    }


}
