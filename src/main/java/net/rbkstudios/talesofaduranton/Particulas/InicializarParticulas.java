package net.rbkstudios.talesofaduranton.Particulas;


import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;

public class InicializarParticulas {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TalesOfAduranton.MODID);

    public static final RegistryObject<SimpleParticleType> BLOOD_PARTICLE =
            PARTICLE_TYPES.register("blood_particle", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}