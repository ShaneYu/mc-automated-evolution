package com.github.shaneyu.automatedexpansion.data.common.recipe;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.lib.annotations.NothingNullByDefault;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@NothingNullByDefault
public abstract class BaseRecipeProvider extends RecipeProvider {
    private final ExistingFileHelper existingFileHelper;

    protected BaseRecipeProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
        super(pGenerator);
        this.existingFileHelper = existingFileHelper;
    }

    @NotNull
    @Override
    public String getName() {
        return super.getName() + ": " + AutomatedExpansion.MODID;
    }

    @Override
    protected final void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        Consumer<FinishedRecipe> trackingConsumer = consumer.andThen(recipe ->
            existingFileHelper.trackGenerated(recipe.getId(), PackType.SERVER_DATA, ".json", "recipes"));

        addRecipes(trackingConsumer);
        getSubRecipeProviders().forEach(subRecipeProvider -> subRecipeProvider.addRecipes(trackingConsumer));
    }

    protected abstract void addRecipes(Consumer<FinishedRecipe> consumer);

    /**
     * Gets all the sub/offloaded recipe providers that this recipe provider has.
     */
    protected List<ISubRecipeProvider> getSubRecipeProviders() {
        return Collections.emptyList();
    }
}
