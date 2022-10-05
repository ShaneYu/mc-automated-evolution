package com.github.shaneyu.automatedexpansion.data.common.recipe;

import com.github.shaneyu.automatedexpansion.common.lib.annotations.NothingNullByDefault;
import com.github.shaneyu.automatedexpansion.data.common.recipe.builder.impl.ExtendedCookingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

@NothingNullByDefault
public class RecipeProviderUtil {
    private RecipeProviderUtil() {}

    public static void addSmeltingBlastingRecipes(Consumer<FinishedRecipe> consumer, Ingredient smeltingInput, ItemLike output, float experience, int smeltingTime,
                                                  ResourceLocation blastingLocation, ResourceLocation smeltingLocation, RecipeCriterion... criteria) {
        ExtendedCookingRecipeBuilder blastingRecipe = ExtendedCookingRecipeBuilder.blasting(output, smeltingInput, smeltingTime / 2).experience(experience);
        ExtendedCookingRecipeBuilder smeltingRecipe = ExtendedCookingRecipeBuilder.smelting(output, smeltingInput, smeltingTime).experience(experience);

        // If there are any criteria add them
        for (RecipeCriterion criterion : criteria) {
            blastingRecipe.addCriterion(criterion);
            smeltingRecipe.addCriterion(criterion);
        }

        blastingRecipe.build(consumer, blastingLocation);
        smeltingRecipe.build(consumer, smeltingLocation);
    }
}
