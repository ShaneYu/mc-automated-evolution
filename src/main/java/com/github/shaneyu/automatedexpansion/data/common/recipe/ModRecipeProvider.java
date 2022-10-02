package com.github.shaneyu.automatedexpansion.data.common.recipe;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.providers.IBlockProvider;
import com.github.shaneyu.automatedexpansion.common.providers.IItemProvider;
import com.github.shaneyu.automatedexpansion.common.registries.ModBlocks;
import com.github.shaneyu.automatedexpansion.common.registries.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        itemToStorageBlock(ModBlocks.STEEL_BLOCK, ModItems.INGOT_STEEL)
            .unlockedBy("has_ingot_steel", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.INGOT_STEEL).build()))
            .save(pFinishedRecipeConsumer, AutomatedExpansion.rl("block_steel_from_ingots"));

        storageBlockToItem(ModItems.INGOT_STEEL, ModBlocks.STEEL_BLOCK)
            .unlockedBy("has_block_steel", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.STEEL_BLOCK).build()))
            .save(pFinishedRecipeConsumer, AutomatedExpansion.rl("ingot_steel_from_block_steel"));
    }

    private ShapelessRecipeBuilder storageBlockToItem(IItemProvider itemProvider, IBlockProvider fromBlockProvider) {
        return ShapelessRecipeBuilder.shapeless(itemProvider.asItem(), 9)
            .requires(fromBlockProvider.getBlock(), 1);
    }

    private ShapedRecipeBuilder itemToStorageBlock(IBlockProvider storageBlockProvider, IItemProvider fromItemProvider) {
        return ShapedRecipeBuilder.shaped(storageBlockProvider.getBlock(), 1)
            .define('#', fromItemProvider.asItem())
            .pattern("###")
            .pattern("###")
            .pattern("###");
    }
}
