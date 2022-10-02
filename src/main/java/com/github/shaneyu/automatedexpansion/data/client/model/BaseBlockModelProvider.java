package com.github.shaneyu.automatedexpansion.data.client.model;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public abstract class BaseBlockModelProvider extends BlockModelProvider {

    public BaseBlockModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, AutomatedExpansion.MODID, existingFileHelper);
    }

    @NotNull
    @Override
    public String getName() {
        return "Block model provider: " + AutomatedExpansion.MODID;
    }

    public BlockModelBuilder sideBottomTop(String name, ResourceLocation parent, ResourceLocation texture) {
        return withExistingParent(name, parent)
            .texture("side", texture)
            .texture("bottom", texture)
            .texture("top", texture);
    }

    public boolean textureExists(ResourceLocation texture) {
        return existingFileHelper.exists(texture, PackType.CLIENT_RESOURCES, ".png", "textures");
    }
}
