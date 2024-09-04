package net.rbkstudios.talesofaduranton.Efectos;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.Efectos.Custom.GuardBreach;
import net.rbkstudios.talesofaduranton.Efectos.Custom.Poisonouus;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;

public class InicializarEfectos {
    public static final DeferredRegister<MobEffect> EFECTOS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TalesOfAduranton.MODID);


    public static final RegistryObject<MobEffect> POISONOUS_EFFECT = EFECTOS.register("poisonous",
            () -> new Poisonouus(MobEffectCategory.BENEFICIAL, 5149489));


    public static final RegistryObject<MobEffect> GUARD_BREACH = EFECTOS.register("guard_breach",
            () -> new GuardBreach(MobEffectCategory.HARMFUL, 5139489));



    public static void registrar(IEventBus bus){
        EFECTOS.register(bus);
    }
}
