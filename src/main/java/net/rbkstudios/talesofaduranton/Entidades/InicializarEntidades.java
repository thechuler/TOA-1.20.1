package net.rbkstudios.talesofaduranton.Entidades;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.Entidades.Entity.*;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


@Mod.EventBusSubscriber(modid = TalesOfAduranton.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InicializarEntidades {

    public static final DeferredRegister<EntityType<?>> ENTIDADES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TalesOfAduranton.MODID);


    public static final RegistryObject<EntityType<FrogManEntity>> FROGMAN_ENTITY = ENTIDADES.register("frogman_entity",
            () -> EntityType.Builder.of(FrogManEntity::new, MobCategory.CREATURE).sized(1f,1.3f)
                    .build(new ResourceLocation(TalesOfAduranton.MODID,"frogman_entity").toString()));

    public static final RegistryObject<EntityType<FrogManSkeletonEntity>> FROGMAN_SKELETON_ENTITY = ENTIDADES.register("frogman_skeleton_entity",
            () -> EntityType.Builder.of(FrogManSkeletonEntity::new, MobCategory.CREATURE).sized(1f,1.3f)
                    .build(new ResourceLocation(TalesOfAduranton.MODID,"frogman_skeleton_entity").toString()));

    public static final RegistryObject<EntityType<FrogManShamanEntity>> FROGMAN_SHAMAN_ENTITY = ENTIDADES.register("frogman_shaman_entity",
            () -> EntityType.Builder.of(FrogManShamanEntity::new, MobCategory.CREATURE).sized(1f,1.3f)
                    .build(new ResourceLocation(TalesOfAduranton.MODID,"frogman_shaman_entity").toString()));

    public static final RegistryObject<EntityType<FrogManTraderEntity>> FROGMAN_TRADER_ENTITY = ENTIDADES.register("frogman_trader_entity",
            () -> EntityType.Builder.of(FrogManTraderEntity::new, MobCategory.CREATURE).sized(1f,1.3f)
                    .build(new ResourceLocation(TalesOfAduranton.MODID,"frogman_trader_entity").toString()));



    public static final RegistryObject<EntityType<FrogManTropicalEntity>> FROGMAN_TROPICAL = ENTIDADES.register("frogman_tropical",
            () -> EntityType.Builder.of(FrogManTropicalEntity::new, MobCategory.CREATURE).sized(1f,1.3f)
                    .build(new ResourceLocation(TalesOfAduranton.MODID,"frogman_tropical").toString()));


    public static final RegistryObject<EntityType<FrogManZombieEntity>> FROGMAN_ZOMBIE = ENTIDADES.register("frogman_zombie",
            () -> EntityType.Builder.of(FrogManZombieEntity::new, MobCategory.MONSTER).sized(1f,1.3f)
                    .build(new ResourceLocation(TalesOfAduranton.MODID,"frogman_zombie").toString()));


    public static final RegistryObject<EntityType<FrogManBeastEntity>> FROGMAN_BEAST = ENTIDADES.register("frogman_beast",
            () -> EntityType.Builder.of(FrogManBeastEntity::new, MobCategory.CREATURE).sized(1f,1.3f)
                    .build(new ResourceLocation(TalesOfAduranton.MODID,"frogman_beast").toString()));


    public static final RegistryObject<EntityType<FrogManCrawlerEntity>> FROGMAN_CRAWLER = ENTIDADES.register("frogman_crawler",
            () -> EntityType.Builder.of(FrogManCrawlerEntity::new, MobCategory.MONSTER).sized(1.5f,2f)
                    .build(new ResourceLocation(TalesOfAduranton.MODID,"frogman_crawler").toString()));



    public static final RegistryObject<EntityType<FrogManDeepEntity>> FROGMAN_DEEP = ENTIDADES.register("frogman_deep",
            () -> EntityType.Builder.of(FrogManDeepEntity::new, MobCategory.CREATURE).sized(1f,1.3f)
                    .build(new ResourceLocation(TalesOfAduranton.MODID,"frogman_deep").toString()));

    public static final RegistryObject<EntityType<FrogManGhostEntity>> FROGMAN_GHOST = ENTIDADES.register("frogman_ghost",
            () -> EntityType.Builder.of(FrogManGhostEntity::new, MobCategory.MONSTER).sized(1f,1.3f)
                    .build(new ResourceLocation(TalesOfAduranton.MODID,"frogman_ghost").toString()));





    public static void registrar(IEventBus eventBus) {
        ENTIDADES.register(eventBus);
    }


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(FROGMAN_ENTITY.get(), FrogManEntity.createAttributes().build());
        event.put(FROGMAN_SKELETON_ENTITY.get(), FrogManEntity.createAttributes().build());
        event.put(FROGMAN_SHAMAN_ENTITY.get(), FrogManSkeletonEntity.createAttributes().build());
        event.put(FROGMAN_TRADER_ENTITY.get(), FrogManTraderEntity.createAttributes().build());
        event.put(FROGMAN_TROPICAL.get(), FrogManTropicalEntity.createAttributes().build());
        event.put(FROGMAN_ZOMBIE.get(), FrogManZombieEntity.createAttributes().build());
        event.put(FROGMAN_BEAST.get(), FrogManBeastEntity.createAttributes().build());
        event.put(FROGMAN_CRAWLER.get(), FrogManCrawlerEntity.createAttributes().build());
        event.put(FROGMAN_DEEP.get(), FrogManDeepEntity.createAttributes().build());
        event.put(FROGMAN_GHOST.get(), FrogManGhostEntity.createAttributes().build());
    }

    @SubscribeEvent
    public  static void  RegistrarLugardeSpawn(SpawnPlacementRegisterEvent event){
        event.register(InicializarEntidades.FROGMAN_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE,FrogManEntity::PuedeSpawnear,SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(InicializarEntidades.FROGMAN_SHAMAN_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE,FrogManShamanEntity::PuedeSpawnear,SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(InicializarEntidades.FROGMAN_TRADER_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE,FrogManTraderEntity::PuedeSpawnear,SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(InicializarEntidades.FROGMAN_CRAWLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,FrogManCrawlerEntity::PuedeSpawnear,SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(InicializarEntidades.FROGMAN_GHOST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,FrogManGhostEntity::PuedeSpawnear,SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(InicializarEntidades.FROGMAN_TROPICAL.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING,FrogManTropicalEntity::PuedeSpawnear,SpawnPlacementRegisterEvent.Operation.REPLACE);

    }



}

