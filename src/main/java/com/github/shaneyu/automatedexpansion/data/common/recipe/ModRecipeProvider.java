package com.github.shaneyu.automatedexpansion.data.common.recipe;

import com.github.shaneyu.automatedexpansion.common.providers.IItemProvider;
import com.github.shaneyu.automatedexpansion.common.registries.ModBlocks;
import com.github.shaneyu.automatedexpansion.common.registries.ModItems;
import com.github.shaneyu.automatedexpansion.common.util.annotations.NothingNullByDefault;
import com.github.shaneyu.automatedexpansion.data.common.recipe.builder.impl.ExtendedShapedRecipeBuilder;
import com.github.shaneyu.automatedexpansion.data.common.recipe.builder.impl.ExtendedShapelessRecipeBuilder;
import com.github.shaneyu.automatedexpansion.data.common.recipe.impl.BasicResourceProcessingRecipeProvider;
import com.github.shaneyu.automatedexpansion.data.common.recipe.impl.StorageRecipeProvider;
import com.github.shaneyu.automatedexpansion.data.common.recipe.pattern.RecipePattern;
import com.github.shaneyu.automatedexpansion.data.common.recipe.pattern.RecipePattern.TripleLine;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Consumer;

@NothingNullByDefault
public class ModRecipeProvider extends BaseRecipeProvider {
    public static final RecipePattern STORAGE_PATTERN = RecipePattern.createPattern(
        TripleLine.of('#', '#', '#'),
        TripleLine.of('#', '#', '#'),
        TripleLine.of('#', '#', '#'));

    public static final RecipePattern BOX_PATTERN = RecipePattern.createPattern(
        TripleLine.of('#', '#', '#'),
        TripleLine.of('#', ' ', '#'),
        TripleLine.of('#', '#', '#'));

    public static final RecipePattern BOX_PATTERN_WITH_MIDDLE = RecipePattern.createPattern(
        TripleLine.of('#', '#', '#'),
        TripleLine.of('#', 'M', '#'),
        TripleLine.of('#', '#', '#'));

    public ModRecipeProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
        super(pGenerator, existingFileHelper);
    }

    @Override
    protected void addRecipes(Consumer<FinishedRecipe> consumer) {
        // Hammer
        ExtendedShapedRecipeBuilder.shapedRecipe(ModItems.TOOL_HAMMER, 1)
            .pattern(RecipePattern.createPattern(
                TripleLine.of(' ', 'I', ' '),
                TripleLine.of(' ', 'L', 'I'),
                TripleLine.of('L', ' ', ' ')
            ))
            .key('I', Items.IRON_INGOT)
            .key('L', Items.LAPIS_LAZULI)
            .build(consumer);

        // Wrench
        ExtendedShapedRecipeBuilder.shapedRecipe(ModItems.TOOL_WRENCH, 1)
            .pattern(RecipePattern.createPattern(
                TripleLine.of(' ', 'I', ' '),
                TripleLine.of(' ', 'I', 'I'),
                TripleLine.of('L', ' ', ' ')
            ))
            .key('I', Items.IRON_INGOT)
            .key('L', Items.LAPIS_LAZULI)
            .build(consumer);

        // Wire Cutter
        ExtendedShapedRecipeBuilder.shapedRecipe(ModItems.TOOL_WIRE_CUTTER, 1)
            .pattern(RecipePattern.createPattern(
                TripleLine.of('I', 'L', ' '),
                TripleLine.of('L', ' ', ' '),
                TripleLine.of(' ', ' ', ' ')
            ))
            .key('I', Items.IRON_INGOT)
            .key('L', Items.LAPIS_LAZULI)
            .build(consumer);

        // Sheet Recipes
        addSheetRecipe(consumer, Items.COPPER_INGOT, ModItems.SHEET_COPPER);
        addSheetRecipe(consumer, Items.IRON_INGOT, ModItems.SHEET_IRON);
        addSheetRecipe(consumer, Items.GOLD_INGOT, ModItems.SHEET_GOLD);
        addSheetRecipe(consumer, ModItems.INGOT_STEEL, ModItems.SHEET_STEEL);

        // Cable Recipes
        addCableRecipe(consumer, ModItems.SHEET_COPPER, ModItems.CABLE_COPPER);

        // Iron Frame
        ExtendedShapedRecipeBuilder.shapedRecipe(ModBlocks.IRON_FRAME, 1)
            .pattern(BOX_PATTERN)
            .key('#', ModItems.SHEET_IRON)
            .build(consumer);

        // Steel Frame
        ExtendedShapedRecipeBuilder.shapedRecipe(ModBlocks.STEEL_FRAME, 1)
            .pattern(BOX_PATTERN_WITH_MIDDLE)
            .key('#', ModItems.SHEET_STEEL)
            .key('M', ModBlocks.IRON_FRAME)
            .build(consumer);
    }

    @Override
    protected List<ISubRecipeProvider> getSubRecipeProviders() {
        return List.of(
            new BasicResourceProcessingRecipeProvider(),
            new StorageRecipeProvider()
        );
    }

    private void addSheetRecipe(Consumer<FinishedRecipe> consumer, IItemProvider ingotItem, IItemProvider sheetItem) {
        addSheetRecipe(consumer, ingotItem.asItem(), sheetItem);
    }

    private void addSheetRecipe(Consumer<FinishedRecipe> consumer, Item ingotItem, IItemProvider sheetItem) {
        ExtendedShapedRecipeBuilder.shapedRecipe(sheetItem, 1)
            .pattern(RecipePattern.createPattern('H', 'I'))
            .key('H', ModItems.TOOL_HAMMER)
            .key('I', ingotItem)
            .build(consumer);
    }

    private void addCableRecipe(Consumer<FinishedRecipe> consumer, IItemProvider sheetItem, IItemProvider cableItem) {
        ExtendedShapelessRecipeBuilder.shapelessRecipe(cableItem, 2)
            .addIngredient(ModItems.TOOL_WIRE_CUTTER)
            .addIngredient(sheetItem)
            .build(consumer);
    }
}
