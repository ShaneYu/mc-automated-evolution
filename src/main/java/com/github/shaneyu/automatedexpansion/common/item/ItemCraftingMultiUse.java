package com.github.shaneyu.automatedexpansion.common.item;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.util.annotations.NothingNullByDefault;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Vanishable;

@NothingNullByDefault
public class ItemCraftingMultiUse extends Item implements Vanishable {
    private static final int DEFAULT_DURABILITY_PER_CRAFT = 20;

    private final int durabilityPerCraft;

    public ItemCraftingMultiUse(int defaultUses) {
        this(AutomatedExpansion.getModBaseProperties(), defaultUses);
    }

    public ItemCraftingMultiUse(Properties pProperties, int defaultUses) {
        this(AutomatedExpansion.getModBaseProperties(), defaultUses * DEFAULT_DURABILITY_PER_CRAFT, DEFAULT_DURABILITY_PER_CRAFT);
    }

    public ItemCraftingMultiUse(int defaultDurability, int durabilityPerCraft) {
        this(AutomatedExpansion.getModBaseProperties(), defaultDurability, durabilityPerCraft);
    }

    public ItemCraftingMultiUse(Properties pProperties, int defaultDurability, int durabilityPerCraft) {
        super(pProperties.defaultDurability(defaultDurability).setNoRepair());
        this.durabilityPerCraft = durabilityPerCraft;
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        return false;
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage() - getDurabilityPerCraft();
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        int updatedDamage = Math.max(itemStack.getDamageValue() + getDurabilityPerCraft(), 0);

        if (updatedDamage < itemStack.getMaxDamage()) {
            ItemStack updatedItemStack = itemStack.copy();
            updatedItemStack.setDamageValue(updatedDamage);

            return updatedItemStack;
        }

        return ItemStack.EMPTY;
    }

    public int getDurabilityPerCraft() {
        return this.durabilityPerCraft;
    }
}
