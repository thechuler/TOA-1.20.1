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



    public static final RegistryObject<SoundEvent>  EMPTY_CANNON=
            SONIDOS.register("empty_cannon", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"empty_cannon")));


    public static final RegistryObject<SoundEvent> CANNON_RELOAD=
            SONIDOS.register("cannon_reload", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"cannon_reload")));



    public static final RegistryObject<SoundEvent>  CANNON_CLICK=
            SONIDOS.register("cannon_click", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"cannon_click")));


    public static final RegistryObject<SoundEvent>  CANNON_SHOOT=
            SONIDOS.register("cannon_shoot", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"cannon_shoot")));



    public static final RegistryObject<SoundEvent>  NITRO_FLUID_SPLASH=
            SONIDOS.register("nitro_fluid_splash", ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation
                    (TalesOfAduranton.MODID,"nitro_fluid_splash")));






    public static void register(IEventBus eventBus) {
        SONIDOS.register(eventBus);
    }

}
