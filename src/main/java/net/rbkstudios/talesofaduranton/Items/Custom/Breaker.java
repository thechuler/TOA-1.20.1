package net.rbkstudios.talesofaduranton.Items.Custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.rbkstudios.talesofaduranton.Efectos.InicializarEfectos;
import net.rbkstudios.talesofaduranton.Utilidades;

public class Breaker extends SwordItem {

 int probabilidad ;
 int duracion;
    public Breaker(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        if(this.getTier() == Tiers.STONE){
            probabilidad = 30;
            duracion = 40;
        }

        if(this.getTier() == Tiers.GOLD){
            probabilidad = 35;
            duracion = 50;
        }

        if(this.getTier() == Tiers.IRON){
            probabilidad = 40;
            duracion = 60;
        }

        if(this.getTier() == Tiers.DIAMOND){
            probabilidad = 50;
            duracion = 80;
        }

        if(this.getTier() == Tiers.NETHERITE){
            probabilidad = 100;
            duracion = 160;
        }

    }


    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
       int num = Utilidades.GenerarNumeroAleatorio(0,100);
       if(num <= probabilidad ){
           System.out.print(probabilidad);
           System.out.print(probabilidad);

           pTarget.addEffect(new MobEffectInstance(InicializarEfectos.GUARD_BREACH.get(),duracion));
       }

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
