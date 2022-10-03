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

    public static final ItemRegistryObject<Item> SHEET_COPPER = registerSheet("copper");
    public static final ItemRegistryObject<Item> SHEET_IRON = registerSheet("iron");
    public static final ItemRegistryObject<Item> SHEET_GOLD = registerSheet("gold");
    public static final ItemRegistryObject<Item> SHEET_STEEL = registerSheet("steel");

    public static final ItemRegistryObject<Item> CABLE_COPPER = registerCable("copper");
    public static final ItemRegistryObject<Item> CABLE_INSULATED_COPPER = registerCable("insulated_copper");

    public static final ItemRegistryObject<Item> TOOL_HAMMER = registerTool("hammer");
    public static final ItemRegistryObject<Item> TOOL_WRENCH = registerTool("wrench");
    public static final ItemRegistryObject<Item> TOOL_WIRE_CUTTER = registerTool("wirecutter");

    private static ItemRegistryObject<Item> registerDust(String name) {
        return ITEMS.register("dust_" + name);
    }

    private static ItemRegistryObject<Item> registerIngot(String name) {
        return ITEMS.register("ingot_" + name);
    }

    private static ItemRegistryObject<Item> registerSheet(String name) {
        return ITEMS.register("sheet_" + name);
    }

    private static ItemRegistryObject<Item> registerTool(String name) {
        return ITEMS.register("tool_" + name);
    }

    private static ItemRegistryObject<Item> registerCable(String name) {
        return ITEMS.register("cable_" + name);
    }
}
