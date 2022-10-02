package com.github.shaneyu.automatedexpansion.data.common.recipe.impl;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.ModTags;
import com.github.shaneyu.automatedexpansion.common.registries.ModItems;
import com.github.shaneyu.automatedexpansion.data.common.recipe.ISubRecipeProvider;
import com.github.shaneyu.automatedexpansion.data.common.recipe.RecipeProviderUtil;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class BasicResourceProcessingRecipeProvider implements ISubRecipeProvider {
    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "processing/";

        addBasicProcessingRecipes(consumer, basePath + "copper/", ModTags.Items.DUSTS_COPPER, Items.COPPER_INGOT);
        addBasicProcessingRecipes(consumer, basePath + "iron/", ModTags.Items.DUSTS_IRON, Items.IRON_INGOT);
        addBasicProcessingRecipes(consumer, basePath + "gold/", ModTags.Items.DUSTS_GOLD, Items.GOLD_INGOT);
        addSteelProcessingRecipes(consumer, basePath + "steel/");

        // TODO: Add diamond dust recipe here when we have a crushing machine
    }

    private void addSteelProcessingRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        addBasicProcessingRecipes(consumer, basePath, ModTags.Items.DUSTS_STEEL, ModItems.STEEL_INGOT, 0.4F, 200);

        // TODO: We don't want steel to just be a simple resource, we want to combine iron and coal to make it; add those recipes here later when we have them.
    }

    private void addBasicProcessingRecipes(Consumer<FinishedRecipe> consumer, String basePath, TagKey<Item> dustItem, ItemLike ingotItem) {
        addBasicProcessingRecipes(consumer, basePath, dustItem, ingotItem, 0.4F, 200);
    }

    private void addBasicProcessingRecipes(Consumer<FinishedRecipe> consumer, String basePath, TagKey<Item> dustItem, ItemLike ingotItem, float experience, int smeltingTime) {
        // Ingot from dust
        RecipeProviderUtil.addSmeltingBlastingRecipes(consumer, Ingredient.of(dustItem), ingotItem, experience, smeltingTime,
            AutomatedExpansion.rl(basePath + "ingot/from_dust_blasting"), AutomatedExpansion.rl(basePath + "ingot/from_dust_smelting"));

        // Dust from ingot
        // TODO: Add dust from ingot recipe when crushing machine has been added!
    }
}
