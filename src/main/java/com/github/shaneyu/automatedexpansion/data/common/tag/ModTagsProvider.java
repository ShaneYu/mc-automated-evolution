package com.github.shaneyu.automatedexpansion.data.common.tag;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.lib.providers.IBlockProvider;
import com.github.shaneyu.automatedexpansion.common.registries.ModBlocks;
import com.github.shaneyu.automatedexpansion.common.registries.ModItems;
import com.github.shaneyu.automatedexpansion.common.tags.ModTags;
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
        addSheets();
        addFrames();
        addCables();
        addCablesInsulated();
        addGems();
        addCrops();
        addSeeds();
        addHarvestRequirements();
        addTools();
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
        addToTag(ModTags.Items.INGOTS_STEEL, ModItems.INGOT_STEEL);

        getItemBuilder(Tags.Items.INGOTS).add(
            ModTags.Items.INGOTS_STEEL
        );
    }

    private void addDusts() {
        addToTag(ModTags.Items.DUSTS_COPPER, ModItems.DUST_COPPER);
        addToTag(ModTags.Items.DUSTS_IRON, ModItems.DUST_IRON);
        addToTag(ModTags.Items.DUSTS_GOLD, ModItems.DUST_GOLD);
        addToTag(ModTags.Items.DUSTS_DIAMOND, ModItems.DUST_DIAMOND);
        addToTag(ModTags.Items.DUSTS_STEEL, ModItems.DUST_STEEL);

        getItemBuilder(Tags.Items.DUSTS).add(
            ModTags.Items.DUSTS_COPPER,
            ModTags.Items.DUSTS_IRON,
            ModTags.Items.DUSTS_GOLD,
            ModTags.Items.DUSTS_DIAMOND,
            ModTags.Items.DUSTS_STEEL
        );
    }

    private void addSheets() {
        addToTag(ModTags.Items.SHEETS_COPPER, ModItems.SHEET_COPPER);
        addToTag(ModTags.Items.SHEETS_IRON, ModItems.SHEET_IRON);
        addToTag(ModTags.Items.SHEETS_GOLD, ModItems.SHEET_GOLD);
        addToTag(ModTags.Items.SHEETS_STEEL, ModItems.SHEET_STEEL);

        getItemBuilder(ModTags.Items.SHEETS).add(
            ModTags.Items.SHEETS_COPPER,
            ModTags.Items.SHEETS_IRON,
            ModTags.Items.SHEETS_GOLD,
            ModTags.Items.SHEETS_STEEL
        );
    }

    private void addFrames() {
        addToTags(ModTags.Items.FRAMES_IRON, ModTags.Blocks.FRAMES_IRON, ModBlocks.IRON_FRAME);
        addToTags(ModTags.Items.FRAMES_STEEL, ModTags.Blocks.FRAMES_STEEL, ModBlocks.STEEL_FRAME);

        getItemBuilder(ModTags.Items.FRAMES).add(
            ModTags.Items.FRAMES_IRON,
            ModTags.Items.FRAMES_STEEL
        );

        getBlockBuilder(ModTags.Blocks.FRAMES).add(
            ModTags.Blocks.FRAMES_IRON,
            ModTags.Blocks.FRAMES_STEEL
        );
    }

    private void addCables() {
        addToTag(ModTags.Items.CABLES_COPPER, ModItems.CABLE_COPPER);

        getItemBuilder(ModTags.Items.CABLES).add(
            ModTags.Items.CABLES_COPPER
        );
    }

    private void addCablesInsulated() {
        addToTag(ModTags.Items.CABLES_INSULATED_COPPER, ModItems.CABLE_INSULATED_COPPER);

        getItemBuilder(ModTags.Items.CABLES_INSULATED).add(
            ModTags.Items.CABLES_INSULATED_COPPER
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
            ModBlocks.STEEL_BLOCK,
            ModBlocks.IRON_FRAME,
            ModBlocks.STEEL_FRAME
        );
    }

    private void addTools() {
        addToTag(ModTags.Items.TOOLS_HAMMER, ModItems.TOOL_HAMMER);
        getItemBuilder(Tags.Items.TOOLS).add(ModTags.Items.TOOLS_HAMMER);

        addToTag(ModTags.Items.TOOLS_WRENCH, ModItems.TOOL_WRENCH);
        getItemBuilder(Tags.Items.TOOLS).add(ModTags.Items.TOOLS_WRENCH);

        addToTag(ModTags.Items.TOOLS_WIRE_CUTTER, ModItems.TOOL_WIRE_CUTTER);
        getItemBuilder(Tags.Items.TOOLS).add(ModTags.Items.TOOLS_WIRE_CUTTER);
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
