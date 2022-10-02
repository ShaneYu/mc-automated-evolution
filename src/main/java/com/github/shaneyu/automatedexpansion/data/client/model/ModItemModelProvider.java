package com.github.shaneyu.automatedexpansion.data.client.model;

import com.github.shaneyu.automatedexpansion.common.registries.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider  extends BaseItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generated(ModItems.DUST_COPPER);
        generated(ModItems.DUST_IRON);
        generated(ModItems.DUST_GOLD);
        generated(ModItems.DUST_DIAMOND);
        generated(ModItems.DUST_STEEL);

        generated(ModItems.INGOT_STEEL);
    }
}
