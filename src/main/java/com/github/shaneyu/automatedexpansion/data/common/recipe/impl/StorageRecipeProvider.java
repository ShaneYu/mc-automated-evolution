package com.github.shaneyu.automatedexpansion.data.common.recipe.impl;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.ModTags;
import com.github.shaneyu.automatedexpansion.common.block.BlockResource;
import com.github.shaneyu.automatedexpansion.common.registration.impl.BlockRegistryObject;
import com.github.shaneyu.automatedexpansion.common.registries.ModBlocks;
import com.github.shaneyu.automatedexpansion.data.common.recipe.ISubRecipeProvider;
import com.github.shaneyu.automatedexpansion.data.common.recipe.ModRecipeProvider;
import com.github.shaneyu.automatedexpansion.data.common.recipe.builder.impl.ExtendedShapedRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class StorageRecipeProvider implements ISubRecipeProvider {
    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        addStorageBlockRecipes(consumer);
    }

    private void addStorageBlockRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "storage_blocks/";

        addStorageBlockRecipe(consumer, ModBlocks.STEEL_BLOCK, ModTags.Items.INGOTS_STEEL, basePath);
    }

    private void addStorageBlockRecipe(Consumer<FinishedRecipe> consumer, BlockRegistryObject<BlockResource, ?> block, TagKey<Item> ingotTag, String basePath) {
        ExtendedShapedRecipeBuilder.shapedRecipe(block)
            .pattern(ModRecipeProvider.STORAGE_PATTERN)
            .key('#', ingotTag)
            .build(consumer, AutomatedExpansion.rl(basePath + block.getBlock().getResourceName()));
    }
}
