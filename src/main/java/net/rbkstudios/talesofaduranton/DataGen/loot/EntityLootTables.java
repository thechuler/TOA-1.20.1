package net.rbkstudios.talesofaduranton.DataGen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.rbkstudios.talesofaduranton.Entidades.InicializarEntidades;
import net.rbkstudios.talesofaduranton.Items.InicializarItems;

import java.util.stream.Stream;

public class EntityLootTables extends EntityLootSubProvider {


    public EntityLootTables() {
        super(FeatureFlags.REGISTRY.allFlags());
    }



    @Override
    public void generate() {

        add(InicializarEntidades.FROGMAN_TRADER_ENTITY.get(), LootTable.lootTable());

        add(InicializarEntidades.FROGMAN_CRAWLER.get(), LootTable.lootTable());
        add(InicializarEntidades.FROGMAN_GHOST.get(), LootTable.lootTable());



        add(InicializarEntidades.FROGMAN_BEAST.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.BROWN_WOOL))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                        .when(LootItemRandomChanceCondition.randomChance(0.2f)) // Probabilidad de 50%

                )


        );





        add(InicializarEntidades.FROGMAN_TROPICAL.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(InicializarItems.POISON_BALL.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                        .when(LootItemRandomChanceCondition.randomChance(0.5f)) // Probabilidad de 50%

                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(InicializarItems.RAW_FROG_MEAT.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                        .when(LootItemRandomChanceCondition.randomChance(0.7f)) // Probabilidad de 50%

                )
        );



        add(InicializarEntidades.FROGMAN_ENTITY.get(), LootTable.lootTable()
               .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                       .add(LootItem.lootTableItem(InicializarItems.POISON_BALL.get()))
                       .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                       .when(LootItemRandomChanceCondition.randomChance(0.5f)) // Probabilidad de 50%

               )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(InicializarItems.RAW_FROG_MEAT.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                        .when(LootItemRandomChanceCondition.randomChance(0.7f)) // Probabilidad de 50%

                )


       );


        add(InicializarEntidades.FROGMAN_SKELETON_ENTITY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.BONE))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                        .when(LootItemRandomChanceCondition.randomChance(0.2f)) // Probabilidad de 50%

                )


        );

        add(InicializarEntidades.FROGMAN_SHAMAN_ENTITY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(InicializarItems.STAFF.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                        .when(LootItemRandomChanceCondition.randomChance(0.1f)) 

                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(InicializarItems.POISON_BALL.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                        .when(LootItemRandomChanceCondition.randomChance(0.5f))

                )

        );

        add(InicializarEntidades.FROGMAN_ZOMBIE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                        .when(LootItemRandomChanceCondition.randomChance(0.75f)) // Probabilidad de 50%

                ));

    }


    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return InicializarEntidades.ENTIDADES.getEntries().stream().map(RegistryObject::get);
    }
}
