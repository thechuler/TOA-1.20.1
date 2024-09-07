package net.rbkstudios.talesofaduranton.DataGen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.rbkstudios.talesofaduranton.Items.InicializarItems;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;

import java.util.List;
import java.util.function.Consumer;

public class ProveedorRecipe extends RecipeProvider implements IConditionBuilder {


    public ProveedorRecipe(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
      Smelting(pWriter, InicializarItems.RAW_FROG_MEAT.get(),RecipeCategory.MISC,InicializarItems.COOKED_FROG_MEAT.get(),0.25f,200,"frog_meat");
      CampfireCooking(pWriter, InicializarItems.RAW_FROG_MEAT.get(),RecipeCategory.MISC,InicializarItems.COOKED_FROG_MEAT.get(),0.25f,200,"frog_meat");
        Smoking(pWriter, InicializarItems.RAW_FROG_MEAT.get(),RecipeCategory.MISC,InicializarItems.COOKED_FROG_MEAT.get(),0.25f,100,"frog_meat");



    }






    protected static void Smelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void Blasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void CampfireCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_campfire_cooking");
    }


    protected static void Smoking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMOKING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smoking");
    }




    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, ItemLike pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(pIngredients), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(pIngredients), has(pIngredients))
                    .save(pFinishedRecipeConsumer,  TalesOfAduranton.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(pIngredients));

    }







}