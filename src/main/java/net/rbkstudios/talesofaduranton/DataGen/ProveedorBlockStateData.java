package net.rbkstudios.talesofaduranton.DataGen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.Bloques.InicializarBloques;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;

public class ProveedorBlockStateData extends BlockStateProvider {

    public ProveedorBlockStateData(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TalesOfAduranton.MODID, exFileHelper);
    }



    // Aca se van a registrar los modelos de los bloques.
    @Override
    protected void registerStatesAndModels() {
        blockWithItem(InicializarBloques.GEODE_BLOCK);


    }



    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }


}
