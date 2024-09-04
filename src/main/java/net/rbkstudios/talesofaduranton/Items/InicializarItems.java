package net.rbkstudios.talesofaduranton.Items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.Efectos.InicializarEfectos;
import net.rbkstudios.talesofaduranton.Entidades.InicializarEntidades;
import net.rbkstudios.talesofaduranton.Items.Custom.Breaker;
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

    public static final RegistryObject<Item> STAFF = ITEMS.register("staff",
            () -> new FrogManStaff(new Item.Properties().stacksTo(1).durability(250)));

    public static final RegistryObject<Item> GEODE = ITEMS.register("geode",
            () -> new geodaItem(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> POISON_BALL = ITEMS.register("poison_ball",
            () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().saturationMod(0).fast().alwaysEat().nutrition(0).effect(new MobEffectInstance(InicializarEfectos.POISONOUS_EFFECT.get(),100),1).build())));

    public static final RegistryObject<Item> STONE_BREAKER = ITEMS.register("stone_breaker",
            () -> new Breaker(Tiers.STONE,6,-1.5f,new Item.Properties()));

    public static final RegistryObject<Item> IRON_BREAKER = ITEMS.register("iron_breaker",
            () -> new Breaker(Tiers.STONE,7,-2.0f,new Item.Properties()));

    public static final RegistryObject<Item> GOLD_BREAKER = ITEMS.register("gold_breaker",
            () -> new Breaker(Tiers.STONE,6,-2.5f,new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_BREAKER = ITEMS.register("diamond_breaker",
            () -> new Breaker(Tiers.STONE,8,-3.0f,new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_BREAKER = ITEMS.register("netherite_breaker",
            () -> new Breaker(Tiers.STONE,10,-3.5f,new Item.Properties()));





    public static void registrar(IEventBus bus){
        ITEMS.register(bus);
    }
}
