package net.rbkstudios.talesofaduranton.Eventos;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rbkstudios.talesofaduranton.Efectos.InicializarEfectos;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManSkeletonEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManZombieEntity;
import net.rbkstudios.talesofaduranton.Entidades.InicializarEntidades;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;
import net.rbkstudios.talesofaduranton.Utilidades;
import org.joml.Vector3f;

@Mod.EventBusSubscriber(modid = TalesOfAduranton.MODID)
public class EventosCustom {

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        if (player.hasEffect(InicializarEfectos.GUARD_BREACH.get())) {
            event.setCanceled(true);
        }
    }


    @SubscribeEvent
    public static void onLivingDeathEvent(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof FrogManZombieEntity) {
            FrogManZombieEntity frogman = (FrogManZombieEntity) event.getSource().getEntity();
            LivingEntity entityKilled = event.getEntity();
            FrogManZombieEntity newEntity = InicializarEntidades.FROGMAN_ZOMBIE.get().create(entityKilled.level());
            if (newEntity != null) {
                newEntity.moveTo(entityKilled.blockPosition(), entityKilled.getYRot(), entityKilled.getXRot());
                entityKilled.level().addFreshEntity(newEntity);
                DustParticleOptions redParticle = new DustParticleOptions(new Vector3f(1F, 0F, 0F), 1.0F); // RGB (1, 0, 0), tamaño 1
                Utilidades.spawnearParticulas(newEntity,15, redParticle);
            }


        }

    }



}
