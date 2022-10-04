package com.github.shaneyu.automatedexpansion.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static void init() {
        Items.init();
        Blocks.init();
    }

    private ModTags() {}

    public static class Items {
        private static void init() {}

        private Items() {}

        public static final TagKey<Item> DUSTS_COPPER = forgeTag("dusts/copper");
        public static final TagKey<Item> DUSTS_IRON = forgeTag("dusts/iron");
        public static final TagKey<Item> DUSTS_GOLD = forgeTag("dusts/gold");
        public static final TagKey<Item> DUSTS_STEEL = forgeTag("dusts/steel");
        public static final TagKey<Item> DUSTS_DIAMOND = forgeTag("dusts/diamond");


        public static final TagKey<Item> INGOTS_STEEL = forgeTag("ingots/steel");


        public static final TagKey<Item> SHEETS = forgeTag("sheets");
        public static final TagKey<Item> SHEETS_COPPER = forgeTag("sheets/copper");
        public static final TagKey<Item> SHEETS_IRON = forgeTag("sheets/iron");
        public static final TagKey<Item> SHEETS_GOLD = forgeTag("sheets/gold");
        public static final TagKey<Item> SHEETS_STEEL = forgeTag("sheets/steel");


        public static final TagKey<Item> FRAMES = forgeTag("frames");
        public static final TagKey<Item> FRAMES_IRON = forgeTag("frames/iron");
        public static final TagKey<Item> FRAMES_STEEL = forgeTag("frames/steel");


        public static final TagKey<Item> CABLES = tag("cables");
        public static final TagKey<Item> CABLES_COPPER = tag("cables/copper");

        public static final TagKey<Item> CABLES_INSULATED = tag("cables_insulated");
        public static final TagKey<Item> CABLES_INSULATED_COPPER = tag("cables_insulated/copper");


        public static final TagKey<Item> STORAGE_BLOCKS_STEEL = forgeTag("storage_blocks/steel");


        public static final TagKey<Item> TOOLS_WRENCH = forgeTag("tools/wrench");
        public static final TagKey<Item> TOOLS_HAMMER = forgeTag("tools/hammer");
        public static final TagKey<Item> TOOLS_WIRE_CUTTER = forgeTag("tools/wirecutter");

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(AutomatedExpansion.rl(name));
        }
    }

    public static class Blocks {
        private static void init() {}

        private Blocks() {}

        public static final TagKey<Block> FRAMES = forgeTag("frames");
        public static final TagKey<Block> FRAMES_IRON = forgeTag("frames/iron");
        public static final TagKey<Block> FRAMES_STEEL = forgeTag("frames/steel");

        public static final TagKey<Block> STORAGE_BLOCKS_STEEL = forgeTag("storage_blocks/steel");

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(AutomatedExpansion.rl(name));
        }
    }
}
