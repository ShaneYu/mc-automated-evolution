package com.github.shaneyu.automatedexpansion.common.registries;

import com.github.shaneyu.automatedexpansion.common.item.ItemCraftingMultiUse;
import com.github.shaneyu.automatedexpansion.common.registration.impl.ItemDeferredRegister;
import com.github.shaneyu.automatedexpansion.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;

import java.util.function.Function;
import java.util.function.Supplier;

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

    public static final ItemRegistryObject<ItemCraftingMultiUse> TOOL_HAMMER = registerTool("hammer", () -> new ItemCraftingMultiUse(20));

    public static final ItemRegistryObject<Item> TOOL_WRENCH = registerTool("wrench", properties -> new Item(properties.setNoRepair().defaultDurability(0)));

    public static final ItemRegistryObject<ItemCraftingMultiUse> TOOL_WIRE_CUTTER = registerTool("wirecutter", () -> new ItemCraftingMultiUse(20));

    private static ItemRegistryObject<Item> registerDust(String name) {
        return ITEMS.register("dust_" + name);
    }

    private static ItemRegistryObject<Item> registerIngot(String name) {
        return ITEMS.register("ingot_" + name);
    }

    private static ItemRegistryObject<Item> registerSheet(String name) {
        return ITEMS.register("sheet_" + name);
    }

    private static ItemRegistryObject<Item> registerCable(String name) {
        return ITEMS.register("cable_" + name);
    }

    private static <ITEM extends Item> ItemRegistryObject<ITEM> registerTool(String name, Supplier<ITEM> supplier) {
        return ITEMS.register("tool_" + name, supplier);
    }

    private static <ITEM extends Item> ItemRegistryObject<ITEM> registerTool(String name, Function<Item.Properties, ITEM> supplier) {
        return ITEMS.register("tool_" + name, supplier);
    }
}
