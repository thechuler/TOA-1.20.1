package net.rbkstudios.talesofaduranton.Efectos.Custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class GuardBreach extends MobEffect {

    public GuardBreach(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }




    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);

        if (pLivingEntity instanceof Player) {
            Player player = (Player) pLivingEntity;
            if (player.getEffect(MobEffects.BLINDNESS) == null) {
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,200 , pAmplifier));
            }
        }
        if (!pLivingEntity.level().isClientSide()) {
            Double x = pLivingEntity.getX();
            Double y = pLivingEntity.getY();
            Double z = pLivingEntity.getZ();

            pLivingEntity.teleportTo(x, y, z);
            pLivingEntity.setDeltaMovement(0, 0, 0);
        }
    }





    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }
}
