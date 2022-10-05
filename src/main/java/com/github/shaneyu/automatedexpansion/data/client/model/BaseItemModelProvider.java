package com.github.shaneyu.automatedexpansion.data.client.model;
import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.lib.providers.IItemProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public abstract class BaseItemModelProvider extends ItemModelProvider {

    protected BaseItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, AutomatedExpansion.MODID, existingFileHelper);
    }

    @NotNull
    @Override
    public String getName() {
        return "Item model provider: " + modid;
    }

    public boolean textureExists(ResourceLocation texture) {
        return existingFileHelper.exists(texture, PackType.CLIENT_RESOURCES, ".png", "textures");
    }

    protected ResourceLocation itemTexture(IItemProvider itemProvider) {
        return modLoc("item/" + itemProvider.getName());
    }

    protected ResourceLocation itemTexture(IItemProvider itemProvider, String type) {
        return modLoc("item/" + type + "/" + itemProvider.getName());
    }

    protected void registerGenerated(IItemProvider... itemProviders) {
        for (IItemProvider itemProvider : itemProviders) {
            generated(itemProvider);
        }
    }

    protected ItemModelBuilder generated(IItemProvider itemProvider) {
        return generated(itemProvider, itemTexture(itemProvider));
    }

    protected ItemModelBuilder generated(IItemProvider itemProvider, String type) {
        return generated(itemProvider, itemTexture(itemProvider, type));
    }

    protected ItemModelBuilder generated(IItemProvider itemProvider, ResourceLocation texture) {
        return withExistingParent(itemProvider.getName(), "item/generated").texture("layer0", texture);
    }

    protected ItemModelBuilder resource(IItemProvider itemProvider, String type) {
        ItemModelBuilder modelBuilder = generated(itemProvider, modLoc("item/empty")).texture("layer1", modLoc("item/" + type));
        ResourceLocation overlay = modLoc("item/" + type + "_overlay");

        if (textureExists(overlay)) {
            //If we have an overlay type for that resource type then add that as another layer
            modelBuilder = modelBuilder.texture("layer2", overlay);
        }

        return modelBuilder;
    }

    protected void registerHandheld(IItemProvider... itemProviders) {
        for (IItemProvider itemProvider : itemProviders) {
            handheld(itemProvider);
        }
    }

    protected ItemModelBuilder handheld(IItemProvider itemProvider) {
        return handheld(itemProvider, itemTexture(itemProvider));
    }

    protected ItemModelBuilder handheld(IItemProvider itemProvider, ResourceLocation texture) {
        return withExistingParent(itemProvider.getName(), "item/handheld").texture("layer0", texture);
    }
}
