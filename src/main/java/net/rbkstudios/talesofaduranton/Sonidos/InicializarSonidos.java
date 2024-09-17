package net.rbkstudios.talesofaduranton.Sonidos;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class InicializarSonidos {

    public static final DeferredRegister<SoundEvent> SONIDOS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TalesOfAduranton.MODID);




    public static final RegistryObject<SoundEvent> FROGMANAMBIENT =
            SONIDOS.register("frogmanambient", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"frogmanambient")));


    public static final RegistryObject<SoundEvent>  FROGMANDEATH =
            SONIDOS.register("frogmandeath", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"frogmandeath")));


    public static final RegistryObject<SoundEvent>  FROGMANHURT=
            SONIDOS.register("frogmanhurt", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"frogmanhurt")));


    public static final RegistryObject<SoundEvent>  FROGMANEASTEREGG=
            SONIDOS.register("frogmaneasteregg", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"frogmaneasteregg")));



    public static final RegistryObject<SoundEvent>  FROGMANTRADEUPDATE=
            SONIDOS.register("frogmantradeupdate", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"frogmantradeupdate")));


    public static final RegistryObject<SoundEvent>  FROGMANZOMBIEHURT=
            SONIDOS.register("frogmanzombiehurt", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"frogmanzombiehurt")));


    public static final RegistryObject<SoundEvent>  FROGMANZOMBIEDEAD=
            SONIDOS.register("frogmanzombiedead", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"frogmanzombiedead")));


    public static final RegistryObject<SoundEvent>  FROGMANZOMBIEAMBIENT=
            SONIDOS.register("frogmanzombieambient", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"frogmanzombieambient")));





    public static final RegistryObject<SoundEvent>  FROGMANBEASTHURT=
            SONIDOS.register("frogmanbeasthurt", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"frogmanbeasthurt")));





    public static void register(IEventBus eventBus) {
        SONIDOS.register(eventBus);
    }

}
