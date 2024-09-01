package net.rbkstudios.talesofaduranton.GeneracionDeMundo;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.rbkstudios.talesofaduranton.Bloques.InicializarBloques;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


import java.util.List;

public class ModConfiguredFeatures {


    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PIEDRA_CARGADA = registerKey("piedra_cargada");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldPiedraCargada = List.of(OreConfiguration.target(stoneReplaceable,
                        InicializarBloques.GEODE_BLOCK.get().defaultBlockState()));


        register(context, OVERWORLD_PIEDRA_CARGADA, Feature.ORE, new OreConfiguration(overworldPiedraCargada, 9));
    }





    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(TalesOfAduranton.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
