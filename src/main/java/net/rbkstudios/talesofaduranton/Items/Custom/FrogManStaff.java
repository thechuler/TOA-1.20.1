package net.rbkstudios.talesofaduranton.Items.Custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.rbkstudios.talesofaduranton.Encantamientos.InicializarEncantamientos;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManSkeletonEntity;
import net.rbkstudios.talesofaduranton.Entidades.InicializarEntidades;
import net.rbkstudios.talesofaduranton.Utilidades;

public class FrogManStaff extends Item {
    public FrogManStaff(Properties pProperties) {
        super(pProperties);
    }




    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
         Entity entidad =Utilidades.getEntityPlayerIsLookingAt(pPlayer,35);
        if(entidad instanceof  LivingEntity){
            SpawnSkeletons(pPlayer, (LivingEntity) entidad,itemStack);

            itemStack.hurtAndBreak(1, pPlayer, player -> player.broadcastBreakEvent(player.getUsedItemHand()));

            Enchantment enchantment = InicializarEncantamientos.SUMMONSPEED.get();
            int level = EnchantmentHelper.getEnchantments(itemStack).getOrDefault(enchantment, 0);
            switch (level){
                case 0:
                    pPlayer.getCooldowns().addCooldown(this, 200);
                    break;
                case 1:
                    pPlayer.getCooldowns().addCooldown(this, 180);
                    break;
                case 2:
                    pPlayer.getCooldowns().addCooldown(this, 140);
                    break;
                case 3:
                    pPlayer.getCooldowns().addCooldown(this, 30);
                    break;
            }


        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }


    private void SpawnSkeletons(Player player , LivingEntity Target, ItemStack item){
        Enchantment enchantment = InicializarEncantamientos.MULTISOUL.get();
        int level = EnchantmentHelper.getEnchantments(item).getOrDefault(enchantment, 0);
        for (int i = 0; i < Utilidades.GenerarNumeroAleatorio(1,3 + level); i++) {
            BlockPos spawnPos = Utilidades.GenerarPosicionAleatoriaEnArea(player.getOnPos(),3);

            FrogManSkeletonEntity newEntity = InicializarEntidades.FROGMAN_SKELETON_ENTITY.get().create(player.level());

            if (newEntity != null) {
                newEntity.moveTo(spawnPos, player.getYRot(), player.getXRot());
                player.level().addFreshEntity(newEntity);
                player.level().playSound(null, spawnPos, SoundEvents.ZOMBIE_VILLAGER_CONVERTED, SoundSource.HOSTILE, 1.0F, 1.0F); // Sonido
                newEntity.setTarget(Target);
                Utilidades.spawnearParticulas(newEntity,15, ParticleTypes.CLOUD);
            }


        }
    }
}
