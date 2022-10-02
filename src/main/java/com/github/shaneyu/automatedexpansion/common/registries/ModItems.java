package com.github.shaneyu.automatedexpansion.common.registries;

import com.github.shaneyu.automatedexpansion.common.registration.impl.ItemDeferredRegister;
import com.github.shaneyu.automatedexpansion.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;

public class ModItems {
    private ModItems() {}

    public static ItemDeferredRegister ITEMS = new ItemDeferredRegister();

    public static final ItemRegistryObject<Item> DUST_COPPER = registerDust("copper");
    public static final ItemRegistryObject<Item> DUST_IRON = registerDust("iron");
    public static final ItemRegistryObject<Item> DUST_GOLD = registerDust("gold");
    public static final ItemRegistryObject<Item> DUST_DIAMOND = registerDust("diamond");
    public static final ItemRegistryObject<Item> DUST_STEEL = registerDust("steel");

    public static final ItemRegistryObject<Item> INGOT_STEEL = registerIngot("steel");

    private static ItemRegistryObject<Item> registerDust(String name) {
        return ITEMS.register("dust_" + name);
    }

    private static ItemRegistryObject<Item> registerIngot(String name) {
        return ITEMS.register("ingot_" + name);
    }
}
