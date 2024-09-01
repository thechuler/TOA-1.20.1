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
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManShamanEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManSkeletonEntity;
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








    public static void registrar(IEventBus eventBus) {
        ENTIDADES.register(eventBus);
    }


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(FROGMAN_ENTITY.get(), FrogManEntity.createAttributes().build());
        event.put(FROGMAN_SKELETON_ENTITY.get(), FrogManEntity.createAttributes().build());
        event.put(FROGMAN_SHAMAN_ENTITY.get(), FrogManSkeletonEntity.createAttributes().build());
    }

    @SubscribeEvent
    public  static void  RegistrarLugardeSpawn(SpawnPlacementRegisterEvent event){
        event.register(InicializarEntidades.FROGMAN_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE,FrogManEntity::PuedeSpawnear,SpawnPlacementRegisterEvent.Operation.OR);

    }



}

