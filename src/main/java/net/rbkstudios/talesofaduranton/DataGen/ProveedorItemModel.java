package net.rbkstudios.talesofaduranton.DataGen;


import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.Items.InicializarItems;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class ProveedorItemModel extends ItemModelProvider {

    public ProveedorItemModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TalesOfAduranton.MODID, existingFileHelper);
    }


    @Override
    protected void registerModels() {
        simpleItem(InicializarItems.GEODE);
        withExistingParent(InicializarItems.FROG_MAN_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
        withExistingParent(InicializarItems.FROG_MAN_SKELETON_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
        withExistingParent(InicializarItems.FROG_MAN_SHAMAN_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
        withExistingParent(InicializarItems.FROG_MAN_ZOMBIE_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
        withExistingParent(InicializarItems.FROG_MAN_TRADER_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
        withExistingParent(InicializarItems.FROG_MAN_TROPICAL_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
        withExistingParent(InicializarItems.FROG_MAN_BEAST_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));


        simpleItem(InicializarItems.POISON_BALL);
        handheldItem(InicializarItems.STONE_BREAKER);
        handheldItem(InicializarItems.IRON_BREAKER);
        handheldItem(InicializarItems.GOLD_BREAKER);
        handheldItem(InicializarItems.DIAMOND_BREAKER);
        handheldItem(InicializarItems.NETHERITE_BREAKER);
        simpleItem(InicializarItems.COIN);
        simpleItem(InicializarItems.COOKED_FROG_MEAT);

        simpleItem(InicializarItems.RAW_FROG_MEAT);
    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("minecraft", "item/generated"))
                .texture("layer0",
                        new ResourceLocation(TalesOfAduranton.MODID, "item/" + item.getId().getPath()));
    }


    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(TalesOfAduranton.MODID,"item/" + item.getId().getPath()));
    }
}