package com.github.shaneyu.automatedexpansion.common.registration.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.github.shaneyu.automatedexpansion.common.ModCreativeTab;
import com.github.shaneyu.automatedexpansion.common.providers.IItemProvider;
import com.github.shaneyu.automatedexpansion.common.registration.WrappedDeferredRegister;
import com.github.shaneyu.automatedexpansion.common.util.text.EnumColor;
import com.github.shaneyu.automatedexpansion.common.util.text.TextComponentUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class ItemDeferredRegister extends WrappedDeferredRegister<Item> {

    private static final ModCreativeTab tabAutomatedExpansion = new ModCreativeTab();

    private final List<IItemProvider> allItems = new ArrayList<>();

    public ItemDeferredRegister() {
        super(ForgeRegistries.ITEMS);
    }

    public static Item.Properties getModBaseProperties() {
        return new Item.Properties().tab(tabAutomatedExpansion);
    }

    public ItemRegistryObject<Item> register(String name) {
        return register(name, Item::new);
    }

    public ItemRegistryObject<Item> registerUnburnable(String name) {
        return registerUnburnable(name, Item::new);
    }

    public ItemRegistryObject<Item> register(String name, Rarity rarity) {
        return register(name, properties -> new Item(properties.rarity(rarity)));
    }

    public ItemRegistryObject<Item> register(String name, EnumColor color) {
        return register(name, properties -> new Item(properties) {
            @NotNull
            @Override
            public Component getName(@NotNull ItemStack stack) {
                return TextComponentUtil.build(color, super.getName(stack));
            }
        });
    }

    public <ITEM extends Item> ItemRegistryObject<ITEM> register(String name, Function<Item.Properties, ITEM> sup) {
        return register(name, () -> sup.apply(getModBaseProperties()));
    }

    public <ITEM extends Item> ItemRegistryObject<ITEM> registerUnburnable(String name, Function<Item.Properties, ITEM> sup) {
        return register(name, () -> sup.apply(getModBaseProperties().fireResistant()));
    }

    public <ITEM extends Item> ItemRegistryObject<ITEM> register(String name, Supplier<? extends ITEM> sup) {
        ItemRegistryObject<ITEM> registeredItem = register(name, sup, ItemRegistryObject::new);
        allItems.add(registeredItem);
        return registeredItem;
    }

    public List<IItemProvider> getAllItems() {
        return Collections.unmodifiableList(allItems);
    }
}
