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
        registerDusts();
        registerIngots();
        registerSheets();
        registerCables();
        registerTools();
    }

    private void registerDusts() {
        generated(ModItems.DUST_COPPER, "dust");
        generated(ModItems.DUST_IRON, "dust");
        generated(ModItems.DUST_GOLD, "dust");
        generated(ModItems.DUST_STEEL, "dust");
        generated(ModItems.DUST_DIAMOND, "dust");
    }

    private void registerSheets() {
        generated(ModItems.SHEET_COPPER, "sheet");
        generated(ModItems.SHEET_IRON, "sheet");
        generated(ModItems.SHEET_GOLD, "sheet");
        generated(ModItems.SHEET_STEEL, "sheet");
    }

    private void registerIngots() {
        generated(ModItems.INGOT_STEEL, "ingot");
    }

    private void registerCables() {
        generated(ModItems.CABLE_COPPER, "cable");
        generated(ModItems.CABLE_INSULATED_COPPER, "cable");
    }

    private void registerTools() {
        generated(ModItems.TOOL_HAMMER, "tool");
        generated(ModItems.TOOL_WRENCH, "tool");
        generated(ModItems.TOOL_WIRE_CUTTER, "tool");
    }
}
