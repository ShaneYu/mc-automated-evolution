package com.github.shaneyu.automatedexpansion.common;

import com.github.shaneyu.automatedexpansion.common.registries.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeTab extends CreativeModeTab {
    public ModCreativeTab() {
        super(AutomatedExpansion.MODID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItems.TOOL_WIRE_CUTTER);
    }

    @Override
    public Component getDisplayName() {
        return ModLang.AUTOMATED_EXPANSION.translate();
    }
}
