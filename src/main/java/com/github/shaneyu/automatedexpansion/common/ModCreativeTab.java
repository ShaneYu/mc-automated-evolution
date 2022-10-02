package com.github.shaneyu.automatedexpansion.common;

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
        return new ItemStack(Items.DIAMOND);
    }

    @Override
    public Component getDisplayName() {
        return ModLang.AUTOMATED_EXPANSION.translate();
    }
}
