package net.rbkstudios.talesofaduranton.Efectos.Custom;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.rbkstudios.talesofaduranton.Utilidades;

import java.util.List;

public class Poisonouus extends MobEffect {

     private int Contador = 0;

    public Poisonouus(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
       if(Contador >=  60){

           Utilidades.spawnParticlesEnArea(pLivingEntity.level(), ParticleTypes.ENTITY_EFFECT,pLivingEntity.getOnPos(),5,40);
         List<LivingEntity> entidades =  Utilidades.ObtenerEntidadesEnArea(pLivingEntity.level(),pLivingEntity.getOnPos(),5);
           for (LivingEntity entidad : entidades){
               if(entidad != pLivingEntity){
                   int poisonDuration = 100 + (pAmplifier * 20);
                   if (entidad.getEffect(MobEffects.POISON) == null) {
                       entidad.addEffect(new MobEffectInstance(MobEffects.POISON, poisonDuration));
                   }
               }

           }

       }else{
           Contador++;
      }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }
}
