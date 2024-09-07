package net.rbkstudios.talesofaduranton.DataGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import net.rbkstudios.talesofaduranton.Bloques.InicializarBloques;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ProveedorBlockTag extends BlockTagsProvider {

    public ProveedorBlockTag(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TalesOfAduranton.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(InicializarBloques.GEODE_BLOCK.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(InicializarBloques.GEODE_BLOCK.get());


    }


}
