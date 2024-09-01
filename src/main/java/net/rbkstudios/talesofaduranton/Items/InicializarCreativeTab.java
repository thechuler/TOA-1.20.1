package net.rbkstudios.talesofaduranton.Items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class InicializarCreativeTab {


    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TalesOfAduranton.MODID);



    public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_TABS.register("main_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(InicializarItems.FROG_MAN_SPAWN_EGG.get()))
                    .title(Component.translatable("creativetab.main_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        for (int i = 0; i < InicializarItems.ITEMS.getEntries().size(); i++) {
                            pOutput.accept(InicializarItems.ITEMS.getEntries().stream().toList().get(i).get().asItem());
                        }
                    })
                    .build());


    public static void registrar(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }


}

