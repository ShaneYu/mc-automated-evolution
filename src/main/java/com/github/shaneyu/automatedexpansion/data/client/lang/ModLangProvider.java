package com.github.shaneyu.automatedexpansion.data.client.lang;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.ModLang;
import com.github.shaneyu.automatedexpansion.common.registries.ModBlocks;
import com.github.shaneyu.automatedexpansion.common.registries.ModItems;
import com.github.shaneyu.automatedexpansion.common.util.EnumUtils;
import com.github.shaneyu.automatedexpansion.common.lib.EnumColor;
import net.minecraft.data.DataGenerator;

public class ModLangProvider extends BaseLangProvider {

    public ModLangProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void addTranslations() {
        addItems();
        addBlocks();
        addMisc();
    }

    private void addItems() {
        // Dusts
        add(ModItems.DUST_COPPER, "Copper Dust");
        add(ModItems.DUST_IRON, "Iron Dust");
        add(ModItems.DUST_GOLD, "Gold Dust");
        add(ModItems.DUST_STEEL, "Steel Dust");
        add(ModItems.DUST_DIAMOND, "Diamond Dust");

        // Ingots
        add(ModItems.INGOT_STEEL, "Steel Ingot");

        // Sheets
        add(ModItems.SHEET_COPPER, "Copper Sheet");
        add(ModItems.SHEET_IRON, "Iron Sheet");
        add(ModItems.SHEET_GOLD, "Gold Sheet");
        add(ModItems.SHEET_STEEL, "Steel Sheet");

        // Cables
        add(ModItems.CABLE_COPPER, "Copper Cable");
        add(ModItems.CABLE_INSULATED_COPPER, "Insulated Copper Cable");

        // Tools
        add(ModItems.TOOL_HAMMER, "Hammer");
        add(ModItems.TOOL_WRENCH, "Wrench");
        add(ModItems.TOOL_WIRE_CUTTER, "Wire Cutter");
    }

    private void addBlocks() {
        // Storage blocks
        add(ModBlocks.STEEL_BLOCK, "Block of Steel");

        // Frames
        add(ModBlocks.IRON_FRAME, "Iron Frame");
        add(ModBlocks.STEEL_FRAME, "Steel Frame");
    }

    private void addMisc() {
        // Colors
        for (EnumColor color : EnumUtils.COLORS) {
            add(color.getLangEntry(), color.getEnglishName());
        }

        // Constants
        add(ModLang.AUTOMATED_EXPANSION, AutomatedExpansion.MOD_NAME);

        // Directions
        add(ModLang.DOWN, "Down");
        add(ModLang.UP, "Up");
        add(ModLang.NORTH, "North");
        add(ModLang.SOUTH, "South");
        add(ModLang.WEST, "West");
        add(ModLang.EAST, "East");

        // Relative sides
        add(ModLang.FRONT, "Front");
        add(ModLang.LEFT, "Left");
        add(ModLang.RIGHT, "Right");
        add(ModLang.BACK, "Back");
        add(ModLang.TOP, "Top");
        add(ModLang.BOTTOM, "Bottom");

        // Hold for
        add(ModLang.HOLD_FOR_DETAILS, "Hold %1$s for details.");
        add(ModLang.HOLD_FOR_DESCRIPTION, "Hold %1$s for a description.");

        // Gui stuff
        add(ModLang.TRUE_LOWER, "true");
        add(ModLang.FALSE_LOWER, "false");
    }
}
