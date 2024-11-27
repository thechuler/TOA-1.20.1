package net.rbkstudios.talesofaduranton.GeneracionDeMundo;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.rbkstudios.talesofaduranton.Entidades.InicializarEntidades;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;

import java.util.Arrays;
import java.util.Collections;


public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> AGREGAR_PIEDRA_CARGADA = registerKey("agregar_piedra_cargada");
    public static final ResourceKey<BiomeModifier> SPAWNEAR_FROGMAN = registerKey("spawn_frogman");
    public static final ResourceKey<BiomeModifier> SPAWNEAR_GHOSTFROGMAN = registerKey("spawn_frogmanghost");
    public static final ResourceKey<BiomeModifier> SPAWNEAR_TRADERFROGMAN = registerKey("spawn_frogmantrader");
    public static final ResourceKey<BiomeModifier> SPAWNEAR_FROGMAN_CRAWLER = registerKey("spawn_frogman_crawler");
    public static final ResourceKey<BiomeModifier> SPAWNEAR_FROGMAN_TROPICAL = registerKey("spawn_frogman_tropical");
    public static final ResourceKey<BiomeModifier> SPAWNEAR_FROGMAN_BESTIA = registerKey("spawn_frogman_bestia");




    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        HolderSet<Biome> TODOS_LOS_BIOMAS = biomes.getOrThrow(BiomeTags.IS_OVERWORLD);
        HolderSet<Biome> BIOMASFROGMAN = HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP));
        HolderSet<Biome> BIOMASFROGMANTRADER = HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP));
        HolderSet<Biome> BIOMASFROGMANTROPICAL = HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.SPARSE_JUNGLE));
        HolderSet<Biome> BIOMASFROGMANBESTIA = HolderSet.direct(biomes.getOrThrow(Biomes.TAIGA), biomes.getOrThrow(Biomes.SNOWY_TAIGA),biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA),biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA));



        context.register(AGREGAR_PIEDRA_CARGADA, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PIEDRA_CARGADA_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));


        context.register(SPAWNEAR_GHOSTFROGMAN, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(TODOS_LOS_BIOMAS,
                Arrays.asList(
                        new MobSpawnSettings.SpawnerData(InicializarEntidades.FROGMAN_GHOST.get(),
                                70, 1, 1
                        ),
                        new MobSpawnSettings.SpawnerData(InicializarEntidades.FROGMAN_ZOMBIE.get(),
                                30, 2, 2
                        ))));




        context.register(SPAWNEAR_FROGMAN_TROPICAL, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(BIOMASFROGMANTROPICAL,
                Collections.singletonList(new MobSpawnSettings.SpawnerData(InicializarEntidades.FROGMAN_TROPICAL.get(),
                        100, 2, 4
                ))));

        context.register(SPAWNEAR_FROGMAN_BESTIA, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(BIOMASFROGMANBESTIA,
                Collections.singletonList(new MobSpawnSettings.SpawnerData(InicializarEntidades.FROGMAN_BEAST.get(),
                        100, 2, 4
                ))));




        context.register(SPAWNEAR_TRADERFROGMAN, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(TODOS_LOS_BIOMAS,
                Collections.singletonList(new MobSpawnSettings.SpawnerData(InicializarEntidades.FROGMAN_TRADER_ENTITY.get(),
                        10, 1, 1
                ))));


        context.register(SPAWNEAR_FROGMAN_CRAWLER, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(TODOS_LOS_BIOMAS,
                Collections.singletonList(new MobSpawnSettings.SpawnerData(InicializarEntidades.FROGMAN_CRAWLER.get(),
                        40, 1, 1
                ))));



        context.register(SPAWNEAR_FROGMAN, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(BIOMASFROGMAN,
                Arrays.asList(
                        new MobSpawnSettings.SpawnerData(InicializarEntidades.FROGMAN_ENTITY.get(),
                                100, 4, 6
                        ),
                        new MobSpawnSettings.SpawnerData(InicializarEntidades.FROGMAN_SHAMAN_ENTITY.get(),
                                30, 1, 1
                        ))));



    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(TalesOfAduranton.MODID, name));
    }

}
