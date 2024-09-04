package net.rbkstudios.talesofaduranton.Eventos;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rbkstudios.talesofaduranton.Efectos.InicializarEfectos;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;

@Mod.EventBusSubscriber(modid = TalesOfAduranton.MODID)
public class EventosCustom {

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        if (player.hasEffect(InicializarEfectos.GUARD_BREACH.get())) {
            event.setCanceled(true);
        }
    }
/*
    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent event) {
        Player player = event.getEntity();
        if (player.hasEffect(InicializarEfectos.GUARD_BREACH.get())) {
            event.setCanceled(true);
        }
    }
*/


}
