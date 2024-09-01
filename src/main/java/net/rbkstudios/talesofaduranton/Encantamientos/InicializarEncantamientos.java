package net.rbkstudios.talesofaduranton.Encantamientos;

import net.minecraft.world.entity.EquipmentSlot;
import net.rbkstudios.talesofaduranton.Encantamientos.Custom.MultiSoul;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;

public class InicializarEncantamientos {
    public static final DeferredRegister<Enchantment> ENCANTAMIENTOS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TalesOfAduranton.MODID);


    public static RegistryObject<Enchantment> MULTISOUL =
            ENCANTAMIENTOS.register("multisoul", () -> new MultiSoul(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));



    public static RegistryObject<Enchantment> SUMMONSPEED =
            ENCANTAMIENTOS.register("summonspeed", () -> new MultiSoul(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));


    public static void registrar(IEventBus bus){
        ENCANTAMIENTOS.register(bus);
    }
}
