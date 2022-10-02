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
        public static final TagKey<Item> DUSTS_DIAMOND = forgeTag("dusts/diamond");
        public static final TagKey<Item> DUSTS_STEEL = forgeTag("dusts/steel");

        public static final TagKey<Item> INGOTS_STEEL = forgeTag("ingots/steel");

        public static final TagKey<Item> STORAGE_BLOCKS_STEEL = forgeTag("storage_blocks/steel");

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

        public static final TagKey<Block> STORAGE_BLOCKS_STEEL = forgeTag("storage_blocks/steel");

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(AutomatedExpansion.rl(name));
        }
    }
}
