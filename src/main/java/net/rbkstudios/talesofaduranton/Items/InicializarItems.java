package net.rbkstudios.talesofaduranton.Items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.Efectos.InicializarEfectos;
import net.rbkstudios.talesofaduranton.Entidades.InicializarEntidades;

import net.rbkstudios.talesofaduranton.Items.Custom.Crusher;
import net.rbkstudios.talesofaduranton.Items.Custom.FrogManStaff;

import net.rbkstudios.talesofaduranton.Items.Custom.geodaItem;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;

public class InicializarItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TalesOfAduranton.MODID);




    public static final RegistryObject<Item> FROG_MAN_SPAWN_EGG = ITEMS.register("frogman_spawn_egg",
            () -> new ForgeSpawnEggItem(InicializarEntidades.FROGMAN_ENTITY, 0x428430, 0xa8a84e, new Item.Properties()));

    public static final RegistryObject<Item> FROG_MAN_SKELETON_SPAWN_EGG = ITEMS.register("frogman_spawn_skeleton_egg",
            () -> new ForgeSpawnEggItem(InicializarEntidades.FROGMAN_SKELETON_ENTITY, 0x9c8d8b, 0xe0eac4, new Item.Properties()));

    public static final RegistryObject<Item> FROG_MAN_SHAMAN_SPAWN_EGG = ITEMS.register("frogman_spawn_shaman_egg",
            () -> new ForgeSpawnEggItem(InicializarEntidades.FROGMAN_SHAMAN_ENTITY, 0x428430, 0xb847ca, new Item.Properties()));

    public static final RegistryObject<Item> FROG_MAN_TRADER_SPAWN_EGG = ITEMS.register("frogman_spawn_trader_egg",
            () -> new ForgeSpawnEggItem(InicializarEntidades.FROGMAN_TRADER_ENTITY, 0x428430, 0x222621, new Item.Properties()));

    public static final RegistryObject<Item> FROG_MAN_TROPICAL_SPAWN_EGG = ITEMS.register("frogman_spawn_tropical_egg",
            () -> new ForgeSpawnEggItem(InicializarEntidades.FROGMAN_TROPICAL, 0x68bd11, 0x68bd11, new Item.Properties()));

    public static final RegistryObject<Item> FROG_MAN_ZOMBIE_SPAWN_EGG = ITEMS.register("frogman_spawn_zombie_egg",
            () -> new ForgeSpawnEggItem(InicializarEntidades.FROGMAN_ZOMBIE, 0x46632f, 0x93ae60, new Item.Properties()));

    public static final RegistryObject<Item> FROG_MAN_BEAST_SPAWN_EGG = ITEMS.register("frogman_spawn_beast_egg",
            () -> new ForgeSpawnEggItem(InicializarEntidades.FROGMAN_BEAST, 0x5c3f2a, 0x232c35, new Item.Properties()));

    public static final RegistryObject<Item> FROG_MAN_CRAWLER_SPAWN_EGG = ITEMS.register("frogman_spawn_crawler_egg",
            () -> new ForgeSpawnEggItem(InicializarEntidades.FROGMAN_CRAWLER, 0xb7b7b7, 0x0e0d0d, new Item.Properties()));


    public static final RegistryObject<Item> FROG_MAN_GHOST_SPAWN_EGG = ITEMS.register("frogman_spawn_ghost_egg",
            () -> new ForgeSpawnEggItem(InicializarEntidades.FROGMAN_GHOST, 0x8befa9, 0x4ce659, new Item.Properties()));





    public static final RegistryObject<Item> STAFF = ITEMS.register("staff",
            () -> new FrogManStaff(new Item.Properties().stacksTo(1).durability(250)));

    public static final RegistryObject<Item> GEODE = ITEMS.register("geode",
            () -> new geodaItem(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> RAW_FROG_MEAT = ITEMS.register("raw_frog_meat",
            () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(5).saturationMod(3).effect(new MobEffectInstance(MobEffects.POISON,100),1).build())));

    public static final RegistryObject<Item> COOKED_FROG_MEAT = ITEMS.register("cooked_frog_meat",
            () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(10).saturationMod(8).build())));


    public static final RegistryObject<Item> POISON_BALL = ITEMS.register("poison_ball",
            () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().saturationMod(0).fast().alwaysEat().nutrition(0).effect(new MobEffectInstance(InicializarEfectos.POISONOUS_EFFECT.get(),100),1).build())));

     public static    final RegistryObject<Item> COIN = ITEMS.register("coin",
            () -> new Item(new Item.Properties().stacksTo(64)));


    public static final RegistryObject<Item> RUSTIC_CRUSHER = ITEMS.register("rustic_crusher",
            () -> new Crusher(Tiers.STONE,1,-3.0f,new Item.Properties()));



    public static final RegistryObject<Item> ECTOPLASM = ITEMS.register("ectoplasm",
            () -> new Item(new Item.Properties().stacksTo(64)));








    public static void registrar(IEventBus bus){
        ITEMS.register(bus);
    }
}
