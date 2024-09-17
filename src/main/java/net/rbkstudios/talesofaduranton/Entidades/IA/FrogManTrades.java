package net.rbkstudios.talesofaduranton.Entidades.IA;

import net.minecraft.Util;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.rbkstudios.talesofaduranton.Items.InicializarItems;
import net.rbkstudios.talesofaduranton.Utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FrogManTrades {

    public static List<ItemStack> Lingotes() {
        return new ArrayList<>(List.of(
                new ItemStack(Items.IRON_INGOT, 3),
                new ItemStack(Items.GOLD_INGOT, 3),
                new ItemStack(Items.COPPER_INGOT, 6),
                new ItemStack(Items.GLOWSTONE_DUST, 8),
                new ItemStack(Items.CHARCOAL, 6),
                new ItemStack(Items.COAL, 6),
                new ItemStack(Items.EMERALD, 2),
                new ItemStack(Items.DIAMOND, 2),
                new ItemStack(Items.AMETHYST_SHARD, 4),
                new ItemStack(Items.REDSTONE, 12)
        ));
    }

    public static List<ItemStack> Consumibles() {
        RandomSource random = RandomSource.create();

        // Lista de efectos de pociones disponibles
        List<Potion> efectosDePocion = List.of(
                Potions.HEALING,
                Potions.STRENGTH,
                Potions.SWIFTNESS,
                Potions.REGENERATION,
                Potions.INVISIBILITY,
                Potions.FIRE_RESISTANCE,
                Potions.POISON,
                Potions.WATER_BREATHING,
                Potions.LONG_REGENERATION,
                Potions.LONG_NIGHT_VISION,
                Potions.LONG_POISON,
                Potions.LONG_STRENGTH,
                Potions.LONG_TURTLE_MASTER,
                Potions.LONG_WEAKNESS,
                Potions.LONG_LEAPING,
                Potions.HEALING,
                Potions.STRONG_STRENGTH,
                Potions.STRONG_HEALING,
                Potions.STRONG_POISON,
                Potions.STRONG_SLOWNESS,
                Potions.LONG_INVISIBILITY,
                Potions.LONG_FIRE_RESISTANCE,
                Potions.THICK
        );
        Potion efectoAleatorio = efectosDePocion.get(Utilidades.GenerarNumeroAleatorio(0,efectosDePocion.size()-1));
        ItemStack pocionConEfectoAleatorio = new ItemStack(Items.POTION);


        PotionUtils.setPotion(pocionConEfectoAleatorio, efectoAleatorio);

      return  new ArrayList<>(List.of(
                new ItemStack(Items.COOKED_BEEF, 10),
                new ItemStack(Items.BREAD, 10),
                new ItemStack(Items.GOLDEN_APPLE, 1),
                new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
                pocionConEfectoAleatorio,

                new ItemStack(Items.CHORUS_FRUIT, 3),
                new ItemStack(Items.COOKED_CHICKEN, 10),
                new ItemStack(Items.PUMPKIN_PIE, 2),
                new ItemStack(Items.CAKE, 1),
                new ItemStack(Items.COOKED_CHICKEN, 10),
                new ItemStack(Items.BAKED_POTATO, 10),
                new ItemStack(Items.COOKED_SALMON, 10),
                new ItemStack(Items.COOKED_PORKCHOP, 10),
                new ItemStack(Items.COOKED_RABBIT, 10),
                new ItemStack(InicializarItems.COOKED_FROG_MEAT.get(), 10),
                new ItemStack(Items.BEEF, 15),
                new ItemStack(Items.CHICKEN, 15),
               new ItemStack(Items.CARROT, 8),
              new ItemStack(Items.POTATO, 8),
              new ItemStack(Items.PORKCHOP, 15),
              new ItemStack(Items.RABBIT, 15),
              new ItemStack(Items.SALMON, 15),
              new ItemStack(Items.BEEF, 15),
                new ItemStack(Items.GOLDEN_CARROT, 3)

        ));}

    public static List<ItemStack> Herramientas() {

        List<ItemStack> herramientas = new ArrayList<>(List.of(
                new ItemStack(Items.WOODEN_SHOVEL, 1),
                new ItemStack(Items.DIAMOND_SHOVEL, 1),
                new ItemStack(Items.IRON_SHOVEL, 1),
                new ItemStack(Items.WOODEN_AXE, 1),
                new ItemStack(Items.WOODEN_PICKAXE, 1),
                new ItemStack(Items.WOODEN_SWORD, 1),
                new ItemStack(Items.IRON_SWORD, 1),
                new ItemStack(Items.IRON_PICKAXE, 1),
                new ItemStack(Items.IRON_AXE, 1),
                new ItemStack(Items.DIAMOND_SWORD, 1),
                new ItemStack(Items.DIAMOND_AXE, 1),
                new ItemStack(Items.DIAMOND_PICKAXE, 1),
                new ItemStack(Items.IRON_BOOTS, 1),
                new ItemStack(Items.IRON_CHESTPLATE, 1),
                new ItemStack(Items.IRON_HELMET, 1),
                new ItemStack(Items.IRON_LEGGINGS, 1),
                new ItemStack(Items.DIAMOND_BOOTS, 1),
                new ItemStack(Items.DIAMOND_CHESTPLATE, 1),
                new ItemStack(Items.DIAMOND_HELMET, 1),
                new ItemStack(Items.DIAMOND_LEGGINGS, 1)

        ));

        // Posibilidad de añadir encantamientos
        Random random = new Random();
        RandomSource randomSource = RandomSource.create();
        for (ItemStack herramienta : herramientas) {
            if (random.nextBoolean()) {
                EnchantmentHelper.enchantItem(randomSource, herramienta, 30, true);
            }
        }

        return herramientas;
    }

    public static List<ItemStack> Materiales() {
        return new ArrayList<>(List.of(
                new ItemStack(Items.STRING, 4),
                new ItemStack(Items.BAMBOO, 1),
                new ItemStack(Items.SUGAR_CANE, 12),
                new ItemStack(Items.CLAY, 24),
                new ItemStack(Items.STICK, 14),
                new ItemStack(Items.KELP, 10),
                new ItemStack(Items.BLAZE_POWDER, 2),
                new ItemStack(Items.SLIME_BALL, 2),
                new ItemStack(Items.NETHER_WART, 4),
                new ItemStack(Items.HONEY_BOTTLE, 1),
                new ItemStack(Items.ENDER_PEARL, 1),
                new ItemStack(Items.GHAST_TEAR, 1),
                new ItemStack(Items.GUNPOWDER, 4)

        ));
    }

    public static List<ItemStack> Bloques() {
        return new ArrayList<>(List.of(
                new ItemStack(Items.STONE, 64),
                new ItemStack(Items.OAK_PLANKS, 64),
                new ItemStack(Items.DEEPSLATE, 64),
                new ItemStack(Items.BLACKSTONE, 32),
                new ItemStack(Items.NETHER_BRICK, 32),
                new ItemStack(Items.NETHER_WART_BLOCK, 12),
                new ItemStack(Items.BONE_BLOCK, 4),
                new ItemStack(Items.PURPUR_BLOCK, 16),
                new ItemStack(Items.SPONGE, 1),
                new ItemStack(Items.TERRACOTTA, 32),
                new ItemStack(Items.WHITE_WOOL, 32),
                new ItemStack(Items.BLACK_WOOL, 32),
                new ItemStack(Items.BLUE_WOOL, 32),
                new ItemStack(Items.CYAN_WOOL, 32),
                new ItemStack(Items.GRAY_WOOL, 32),
                new ItemStack(Items.YELLOW_WOOL, 32),
                new ItemStack(Items.PINK_WOOL, 32),
                new ItemStack(Items.LIGHT_BLUE_WOOL, 32),
                new ItemStack(Items.WHITE_WOOL, 32)

        ));
    }


public static List<ItemStack> CoinLow(){
    return new ArrayList<>(List.of(
            new ItemStack(Items.BONE, 20),
            new ItemStack(Items.ROTTEN_FLESH, 25),
            new ItemStack(Items.PHANTOM_MEMBRANE, 5),
            new ItemStack(InicializarItems.RAW_FROG_MEAT.get(), 24),
            new ItemStack(Items.SPIDER_EYE, 14),
            new ItemStack(Items.STRING, 16),
            new ItemStack(Items.RED_MUSHROOM, 12),
            new ItemStack(Items.BROWN_MUSHROOM, 12),
            new ItemStack(Items.NETHER_WART, 10),
            new ItemStack(Items.SWEET_BERRIES, 10),
            new ItemStack(Items.GLOW_BERRIES, 5),
            new ItemStack(Items.LEATHER, 8),
            new ItemStack(Items.GUNPOWDER, 12),
            new ItemStack(Items.CARROT, 20),
            new ItemStack(Items.POTATO, 20)

    ));
}

    public static List<ItemStack> CoinHigh(){
        return new ArrayList<>(List.of(
                new ItemStack(Items.DRAGON_HEAD, 1),
                new ItemStack(Items.BLAZE_ROD, 10),
                new ItemStack(Items.NETHERITE_SCRAP, 2),
                new ItemStack(InicializarItems.GEODE.get(), 20),
                new ItemStack(Items.DIAMOND_CHESTPLATE, 1),
                new ItemStack(Items.FLINT, 44),
                new ItemStack(Items.BOOK, 40),
                new ItemStack(Items.GOLDEN_CARROT, 32),
                new ItemStack(Items.TOTEM_OF_UNDYING, 1),
                new ItemStack(Items.SWEET_BERRIES, 10)
        ));
    }




    public static void GenerarTrades(MerchantOffers Ofertas) {
        Ofertas.clear();

        Ofertas.add(new MerchantOffer(
                CoinLow().get(Utilidades.GenerarNumeroAleatorio(0,CoinLow().size()-1)),
                new ItemStack(InicializarItems.COIN.get(), 5 + Utilidades.GenerarNumeroAleatorio(0,2)),
                20,
                10,
                0
        ));


       Ofertas.add(new MerchantOffer(
               CoinLow().get(Utilidades.GenerarNumeroAleatorio(0,CoinLow().size()-1)),
               CoinLow().get(Utilidades.GenerarNumeroAleatorio(0,CoinLow().size()-1)),
               new ItemStack(InicializarItems.COIN.get(), 10 + Utilidades.GenerarNumeroAleatorio(0,5)),
               20,
                10,
               0
       ));
        Ofertas.add(new MerchantOffer(
                CoinHigh().get(Utilidades.GenerarNumeroAleatorio(0,CoinHigh().size()-1)),
                new ItemStack(InicializarItems.COIN.get(), 30 + Utilidades.GenerarNumeroAleatorio(0,15)),
                20,
                10,
                0
        ));



        Ofertas.add(new MerchantOffer(
                new ItemStack(InicializarItems.COIN.get(), 30 + Utilidades.GenerarNumeroAleatorio(5,20)),
                Herramientas().get(Utilidades.GenerarNumeroAleatorio(0, Herramientas().size()-1)),
                10,
                1,
                0.05f
        ));


        Ofertas.add(new MerchantOffer(
                new ItemStack(InicializarItems.COIN.get(), 8 + Utilidades.GenerarNumeroAleatorio(0,15)),
                Consumibles().get(Utilidades.GenerarNumeroAleatorio(0, Consumibles().size()-1)),
                10,
                1,
                0.05f
        ));


        Ofertas.add(new MerchantOffer(
                new ItemStack(InicializarItems.COIN.get(), 15 + Utilidades.GenerarNumeroAleatorio(0,20)),
                Lingotes().get(Utilidades.GenerarNumeroAleatorio(0, Lingotes().size()-1)),
                10,
                1,
                0.05f
        ));

        Ofertas.add(new MerchantOffer(
                new ItemStack(InicializarItems.COIN.get(), 10 + Utilidades.GenerarNumeroAleatorio(0,10)),
                Materiales().get(Utilidades.GenerarNumeroAleatorio(0, Materiales().size()-1)),
                10,
                1,
                0.05f
        ));

        Ofertas.add(new MerchantOffer(
                new ItemStack(InicializarItems.COIN.get(), 15 + Utilidades.GenerarNumeroAleatorio(0,10)),
                Bloques().get(Utilidades.GenerarNumeroAleatorio(0, Bloques().size()-1)),
                10,
                1,
                0.05f
        ));

        // Una categoría al azar nuevamente
        List<ItemStack> todasLasCategorias = new ArrayList<>();
        todasLasCategorias.addAll(Herramientas());
        todasLasCategorias.addAll(Lingotes());
        todasLasCategorias.addAll(Materiales());
        todasLasCategorias.addAll(Bloques());
        todasLasCategorias.addAll(Consumibles());



        Ofertas.add(new MerchantOffer(
                new ItemStack(InicializarItems.COIN.get(), 10 + Utilidades.GenerarNumeroAleatorio(0,40)),
                todasLasCategorias.get(Utilidades.GenerarNumeroAleatorio(0, todasLasCategorias.size()-1)),
                10,
                1,
                0.05f
        ));



    }
}
