package com.github.shaneyu.automatedexpansion.data.common.tag;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.providers.IBlockProvider;
import com.github.shaneyu.automatedexpansion.common.registries.ModBlocks;
import com.github.shaneyu.automatedexpansion.common.registries.ModItems;
import com.github.shaneyu.automatedexpansion.common.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModTagsProvider extends BaseTagProvider {
    public ModTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, AutomatedExpansion.MODID, existingFileHelper);
    }

    @Override
    protected List<IBlockProvider> getAllBlocks() {
        return ModBlocks.BLOCKS.getAllBlocks();
    }

    @Override
    protected void registerTags() {
        addOres();
        addStorageBlocks();
        addIngots();
        addDusts();
        addGems();
        addCrops();
        addSeeds();
        addHarvestRequirements();
    }

    private void addOres() {
    }

    private void addStorageBlocks() {
        addToTags(ModTags.Items.STORAGE_BLOCKS_STEEL, ModTags.Blocks.STORAGE_BLOCKS_STEEL, ModBlocks.STEEL_BLOCK);

        getItemBuilder(Tags.Items.STORAGE_BLOCKS).add(
            ModTags.Items.STORAGE_BLOCKS_STEEL
        );

        getBlockBuilder(Tags.Blocks.STORAGE_BLOCKS).add(
            ModTags.Blocks.STORAGE_BLOCKS_STEEL
        );
    }

    private void addIngots() {
        addToTag(ModTags.Items.INGOTS_STEEL, ModItems.INGOT_STEEL.get());

        getItemBuilder(Tags.Items.INGOTS).add(
            ModTags.Items.INGOTS_STEEL
        );
    }

    private void addDusts() {
        addToTag(ModTags.Items.DUSTS_COPPER, ModItems.DUST_COPPER.get());
        addToTag(ModTags.Items.DUSTS_IRON, ModItems.DUST_IRON.get());
        addToTag(ModTags.Items.DUSTS_GOLD, ModItems.DUST_GOLD.get());
        addToTag(ModTags.Items.DUSTS_DIAMOND, ModItems.DUST_DIAMOND.get());
        addToTag(ModTags.Items.DUSTS_STEEL, ModItems.DUST_STEEL.get());

        getItemBuilder(Tags.Items.DUSTS).add(
            ModTags.Items.DUSTS_COPPER,
            ModTags.Items.DUSTS_IRON,
            ModTags.Items.DUSTS_GOLD,
            ModTags.Items.DUSTS_DIAMOND,
            ModTags.Items.DUSTS_STEEL
        );
    }

    private void addGems() {
    }

    private void addCrops() {
    }

    private void addSeeds() {
    }

    private void addHarvestRequirements() {
        addHarvestWithIronPickaxe(
            ModBlocks.STEEL_BLOCK
        );
    }

    private void addHarvestWithStonePickaxe(IBlockProvider... blocks) {
        addToHarvestTag(BlockTags.MINEABLE_WITH_PICKAXE, blocks);
        addToTag(BlockTags.NEEDS_STONE_TOOL, blocks);
    }

    private void addHarvestWithIronPickaxe(IBlockProvider... blocks) {
        addToHarvestTag(BlockTags.MINEABLE_WITH_PICKAXE, blocks);
        addToTag(BlockTags.NEEDS_IRON_TOOL, blocks);
    }

    // Helpers

    private void addOres(TagKey<Item> itemTag, TagKey<Block> blockTag, IBlockProvider... oreBlocks) {
        addToTags(itemTag, blockTag, oreBlocks);
        getItemBuilder(Tags.Items.ORES).add(itemTag);
        getBlockBuilder(Tags.Blocks.ORES).add(blockTag);
    }

    private void addOresWithSingularRate(TagKey<Item> itemTag, TagKey<Block> blockTag, IBlockProvider... oreBlocks) {
        addOres(itemTag, blockTag, oreBlocks);
        addToTags(Tags.Items.ORE_RATES_SINGULAR, Tags.Blocks.ORE_RATES_SINGULAR, oreBlocks);
    }

    private void addOresWithDenseRate(TagKey<Item> itemTag, TagKey<Block> blockTag, IBlockProvider... oreBlocks) {
        addOres(itemTag, blockTag, oreBlocks);
        addToTags(Tags.Items.ORE_RATES_DENSE, Tags.Blocks.ORE_RATES_DENSE, oreBlocks);
    }

    private void addOresInGroundDeepslate(IBlockProvider... oreBlocks) {
        addToTags(Tags.Items.ORES_IN_GROUND_DEEPSLATE, Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, oreBlocks);
    }

    private void addOresInGroundStone(IBlockProvider... oreBlocks) {
        addToTags(Tags.Items.ORES_IN_GROUND_STONE, Tags.Blocks.ORES_IN_GROUND_STONE, oreBlocks);
    }

    private void addOresInGroundNetherrack(IBlockProvider... oreBlocks) {
        addToTags(Tags.Items.ORES_IN_GROUND_NETHERRACK, Tags.Blocks.ORES_IN_GROUND_NETHERRACK, oreBlocks);
    }

}
