package com.github.shaneyu.automatedexpansion.common.registries;

import com.github.shaneyu.automatedexpansion.common.lib.registration.impl.RecipeSerializerDeferredRegister;

public class ModRecipeSerializers {
    private ModRecipeSerializers() {}

    public static final RecipeSerializerDeferredRegister RECIPE_SERIALIZERS = new RecipeSerializerDeferredRegister();

    // public static final RecipeSerializerRegistryObject<ItemStackToItemStackRecipe> CRUSHING = RECIPE_SERIALIZERS.register("crushing", () -> new ItemStackToItemStackRecipeSerializer<>(CrushingIRecipe::new));
}
