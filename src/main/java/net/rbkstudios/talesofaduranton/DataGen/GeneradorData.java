package net.rbkstudios.talesofaduranton.DataGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TalesOfAduranton.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GeneradorData {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ProveedorRecipe(packOutput));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput,lookupProvider));
        generator.addProvider(event.includeClient(), new ProveedorBlockStateData(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ProveedorItemModel(packOutput, existingFileHelper));
        ProveedorBlockTag proveedorBlockTag = generator.addProvider(event.includeServer(), new ProveedorBlockTag(packOutput, lookupProvider, existingFileHelper));
       // generator.addProvider(event.includeServer(), new ProveedorItemTag(packOutput, lookupProvider, proveedorBlockTag.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new ProveedorGeneracionMundo(packOutput, lookupProvider));



    }


}
