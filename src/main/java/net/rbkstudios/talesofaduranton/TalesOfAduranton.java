package net.rbkstudios.talesofaduranton;


import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManShamanModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManSkeletonModel;
import net.rbkstudios.talesofaduranton.Entidades.Renders.FrogManRender;
import net.rbkstudios.talesofaduranton.Entidades.Renders.FrogManShamanRender;
import net.rbkstudios.talesofaduranton.Entidades.Renders.FrogManSkeletonRender;
import net.rbkstudios.talesofaduranton.Items.InicializarCreativeTab;
import net.rbkstudios.talesofaduranton.Items.InicializarItems;
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




     /*
        InicializarParticulas.register(modEventBus);
        InicializarSonidos.register(modEventBus);
        InicializarCreativeTab.registrar(modEventBus);
*/

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
        public  static  void  registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
            event.registerLayerDefinition(FrogManModel.LAYER_LOCATION,FrogManModel::createBodyLayer);
            event.registerLayerDefinition(FrogManSkeletonModel.LAYER_LOCATION,FrogManSkeletonModel::createBodyLayer);
            event.registerLayerDefinition(FrogManShamanModel.LAYER_LOCATION,FrogManShamanModel::createBodyLayer);


        }

        @SubscribeEvent
        public static void registerRender(EntityRenderersEvent.RegisterRenderers event){
           event.registerEntityRenderer(InicializarEntidades.FROGMAN_ENTITY.get(), FrogManRender::new);
            event.registerEntityRenderer(InicializarEntidades.FROGMAN_SKELETON_ENTITY.get(), FrogManSkeletonRender::new);
            event.registerEntityRenderer(InicializarEntidades.FROGMAN_SHAMAN_ENTITY.get(), FrogManShamanRender::new);


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
