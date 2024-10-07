package net.rbkstudios.talesofaduranton.Items.Custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class Crusher extends PickaxeItem {
    public Crusher(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }




    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {

        MinarBloques3x3(pPos, pLevel, pStack, pEntityLiving);
        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }







    public void MinarBloques3x3(BlockPos pos, Level level, ItemStack tool, LivingEntity entity) {
    // Obtén el yaw y pitch del jugador (rotación horizontal y vertical)
    float yaw = entity.getYRot();
    float pitch = entity.getXRot();  // Esto determina si está mirando hacia arriba o hacia abajo

    // Variables para determinar cómo se ajustará el área de minería
    int[][] offsets;

    if (pitch < -45) {
        // Mirando hacia arriba
        offsets = new int[][]{
                {-1, 0, -1}, {0, 0, -1}, {1, 0, -1},
                {-1, 0, 0}, {0, 0, 0}, {1, 0, 0},
                {-1, 0, 1}, {0, 0, 1}, {1, 0, 1}
        };
    } else if (pitch > 45) {
        // Mirando hacia abajo
        offsets = new int[][]{
                {-1, 0, -1}, {0, 0, -1}, {1, 0, -1},
                {-1, 0, 0}, {0, 0, 0}, {1, 0, 0},
                {-1, 0, 1}, {0, 0, 1}, {1, 0, 1}
        };
    } else {
        // Mirando horizontalmente, ajustar la matriz según la dirección
        if (yaw >= -135 && yaw < -45) {
            // Mirando hacia el este
            offsets = new int[][]{
                    {0, 1, -1}, {0, 1, 0}, {0, 1, 1},
                    {0, 0, -1}, {0, 0, 0}, {0, 0, 1},
                    {0, -1, -1}, {0, -1, 0}, {0, -1, 1}
            };
        } else if (yaw >= -45 && yaw < 45) {
            // Mirando hacia el sur
            offsets = new int[][]{
                    {-1, 1, 0}, {0, 1, 0}, {1, 1, 0},
                    {-1, 0, 0}, {0, 0, 0}, {1, 0, 0},
                    {-1, -1, 0}, {0, -1, 0}, {1, -1, 0}
            };
        } else if (yaw >= 45 && yaw < 135) {
            // Mirando hacia el oeste
            offsets = new int[][]{
                    {0, 1, -1}, {0, 1, 0}, {0, 1, 1},
                    {0, 0, -1}, {0, 0, 0}, {0, 0, 1},
                    {0, -1, -1}, {0, -1, 0}, {0, -1, 1}
            };
        } else {
            // Mirando hacia el norte
            offsets = new int[][]{
                    {-1, 1, 0}, {0, 1, 0}, {1, 1, 0},
                    {-1, 0, 0}, {0, 0, 0}, {1, 0, 0},
                    {-1, -1, 0}, {0, -1, 0}, {1, -1, 0}
            };
        }
    }

    // Minar los bloques según el área y la orientación del jugador
    for (int[] offset : offsets) {
        BlockPos targetPos = pos.offset(offset[0], offset[1], offset[2]); // Ajusta según X, Y y Z
        BlockState blockState = level.getBlockState(targetPos);

        // Destruir bloque si es minable
        if (blockState.getDestroySpeed(level, targetPos) >= 0) {
            level.destroyBlock(targetPos, true);
            tool.hurtAndBreak(1, entity, (e) -> e.broadcastBreakEvent(entity.getUsedItemHand()));
        }
    }
}

}




