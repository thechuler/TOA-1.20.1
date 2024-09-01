package net.rbkstudios.talesofaduranton.DataGen.loot;

import net.minecraft.core.HolderLookup;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

import net.minecraft.world.level.block.Block;

import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.Bloques.InicializarBloques;
import net.rbkstudios.talesofaduranton.Items.InicializarItems;


import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(InicializarBloques.GEODE_BLOCK.get(),
               block -> createOreDrop(InicializarBloques.GEODE_BLOCK.get(), InicializarItems.GEODE.get())
                );
    }





    @Override
    protected Iterable<Block> getKnownBlocks() {
        return InicializarBloques.BLOQUES.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}