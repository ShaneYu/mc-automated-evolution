package com.github.shaneyu.automatedexpansion.data.common.recipe;

import com.github.shaneyu.automatedexpansion.common.util.annotations.NothingNullByDefault;
import com.github.shaneyu.automatedexpansion.data.common.recipe.impl.BasicResourceProcessingRecipeProvider;
import com.github.shaneyu.automatedexpansion.data.common.recipe.impl.StorageRecipeProvider;
import com.github.shaneyu.automatedexpansion.data.common.recipe.pattern.RecipePattern;
import com.github.shaneyu.automatedexpansion.data.common.recipe.pattern.RecipePattern.TripleLine;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
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
    }

    @Override
    protected List<ISubRecipeProvider> getSubRecipeProviders() {
        return List.of(
            new BasicResourceProcessingRecipeProvider(),
            new StorageRecipeProvider()
        );
    }
}
