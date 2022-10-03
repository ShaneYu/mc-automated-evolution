package com.github.shaneyu.automatedexpansion.data.common.recipe;

import com.github.shaneyu.automatedexpansion.common.registries.ModItems;
import com.github.shaneyu.automatedexpansion.common.util.annotations.NothingNullByDefault;
import com.github.shaneyu.automatedexpansion.data.common.recipe.builder.impl.ExtendedShapedRecipeBuilder;
import com.github.shaneyu.automatedexpansion.data.common.recipe.impl.BasicResourceProcessingRecipeProvider;
import com.github.shaneyu.automatedexpansion.data.common.recipe.impl.StorageRecipeProvider;
import com.github.shaneyu.automatedexpansion.data.common.recipe.pattern.RecipePattern;
import com.github.shaneyu.automatedexpansion.data.common.recipe.pattern.RecipePattern.TripleLine;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
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
    }

    @Override
    protected List<ISubRecipeProvider> getSubRecipeProviders() {
        return List.of(
            new BasicResourceProcessingRecipeProvider(),
            new StorageRecipeProvider()
        );
    }
}
