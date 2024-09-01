package net.rbkstudios.talesofaduranton.DataGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.rbkstudios.talesofaduranton.GeneracionDeMundo.ModBiomeModifiers;
import net.rbkstudios.talesofaduranton.GeneracionDeMundo.ModConfiguredFeatures;
import net.rbkstudios.talesofaduranton.GeneracionDeMundo.ModPlacedFeatures;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ProveedorGeneracionMundo extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);


    public ProveedorGeneracionMundo(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TalesOfAduranton.MODID));
    }
}
