package net.rbkstudios.talesofaduranton;


import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.rbkstudios.talesofaduranton.Bloques.InicializarBloques;
import net.rbkstudios.talesofaduranton.Efectos.InicializarEfectos;
import net.rbkstudios.talesofaduranton.Encantamientos.InicializarEncantamientos;
import net.rbkstudios.talesofaduranton.Entidades.InicializarEntidades;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.*;
import net.rbkstudios.talesofaduranton.Entidades.Renders.*;
import net.rbkstudios.talesofaduranton.Items.InicializarCreativeTab;
import net.rbkstudios.talesofaduranton.Items.InicializarItems;
import net.rbkstudios.talesofaduranton.Particulas.Custom.BloodParticle;
import net.rbkstudios.talesofaduranton.Particulas.Custom.PhantomParticle;
import net.rbkstudios.talesofaduranton.Particulas.InicializarParticulas;
import net.rbkstudios.talesofaduranton.Sonidos.InicializarSonidos;
import org.slf4j.Logger;

@Mod(TalesOfAduranton.MODID)
public class TalesOfAduranton
{

    public static final String MODID = "talesofaduranton";

    private static final Logger LOGGER = LogUtils.getLogger();

    public TalesOfAduranton()
    {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InicializarEntidades.registrar(modEventBus);
        InicializarSonidos.register(modEventBus);
        InicializarItems.registrar(modEventBus);
        InicializarCreativeTab.registrar(modEventBus);
        InicializarBloques.registrar(modEventBus);
        InicializarEncantamientos.registrar(modEventBus);
        InicializarEfectos.registrar(modEventBus);
        InicializarParticulas.register(modEventBus);


        modEventBus.addListener(this::commonSetup);



        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);



    }


    private void commonSetup(final FMLCommonSetupEvent event) {

    }









    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }



    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {


    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
            Minecraft.getInstance().particleEngine.register(InicializarParticulas.BLOOD_PARTICLE.get(), BloodParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(InicializarParticulas.PHANTOM_PARTICLE.get(), PhantomParticle.Provider::new);
        }


        @SubscribeEvent
        public  static  void  registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
            event.registerLayerDefinition(FrogManModel.LAYER_LOCATION,FrogManModel::createBodyLayer);
            event.registerLayerDefinition(FrogManSkeletonModel.LAYER_LOCATION,FrogManSkeletonModel::createBodyLayer);
            event.registerLayerDefinition(FrogManShamanModel.LAYER_LOCATION,FrogManShamanModel::createBodyLayer);
            event.registerLayerDefinition(FrogManTraderModel.LAYER_LOCATION,FrogManTraderModel::createBodyLayer);
            event.registerLayerDefinition(FrogManTropicalModel.LAYER_LOCATION, FrogManTropicalModel::createBodyLayer);
            event.registerLayerDefinition(FrogManZombieModel.LAYER_LOCATION, FrogManZombieModel::createBodyLayer);
            event.registerLayerDefinition(FrogManBeastModel.LAYER_LOCATION, FrogManBeastModel::createBodyLayer);
            event.registerLayerDefinition(FrogManCrawlerModel.LAYER_LOCATION, FrogManCrawlerModel::createBodyLayer);
            event.registerLayerDefinition(FrogManGhostModel.LAYER_LOCATION, FrogManGhostModel::createBodyLayer);
            event.registerLayerDefinition(NitroFroglinModel.LAYER_LOCATION, NitroFroglinModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerRender(EntityRenderersEvent.RegisterRenderers event){
           event.registerEntityRenderer(InicializarEntidades.FROGMAN_ENTITY.get(), FrogManRender::new);
            event.registerEntityRenderer(InicializarEntidades.FROGMAN_SKELETON_ENTITY.get(), FrogManSkeletonRender::new);
            event.registerEntityRenderer(InicializarEntidades.FROGMAN_SHAMAN_ENTITY.get(), FrogManShamanRender::new);
            event.registerEntityRenderer(InicializarEntidades.FROGMAN_TRADER_ENTITY.get(), FrogManTraderRender::new);
            event.registerEntityRenderer(InicializarEntidades.FROGMAN_TROPICAL.get(), FrogManTropicalRender::new);
            event.registerEntityRenderer(InicializarEntidades.FROGMAN_ZOMBIE.get(), FrogManZombieRender::new);
            event.registerEntityRenderer(InicializarEntidades.FROGMAN_BEAST.get(), FrogManBeastRender::new);
            event.registerEntityRenderer(InicializarEntidades.FROGMAN_CRAWLER.get(), FrogManCrawlerRender::new);

            event.registerEntityRenderer(InicializarEntidades.FROGMAN_GHOST.get(), FrogManGhostRender::new);
            event.registerEntityRenderer(InicializarEntidades.NITROFROGLIN.get(), NitroFroglinRender::new);
        }




        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
      /*      ItemBlockRenderTypes.setRenderLayer(InicializarBloques.BLOQUE_NITRO_FLUIDO.get(), RenderType.translucent());
            EntityRenderers.register(InicializarEntidades.BAG_OF_FLIES_ENTITY.get(), ThrownItemRenderer::new);
            EntityRenderers.register(InicializarEntidades.BALA_DE_CANNON_ENTITY.get(),ThrownItemRenderer::new);
            EntityRenderers.register(InicializarEntidades.BALA_DE_CANNON_PUTREFACTA_ENTITY.get(),ThrownItemRenderer::new);
        */}
    }
}
