package com.github.shaneyu.automatedexpansion.data.client.state;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.block.BlockResource;
import com.github.shaneyu.automatedexpansion.common.lib.registration.impl.BlockRegistryObject;
import com.github.shaneyu.automatedexpansion.common.registries.ModBlocks;
import com.github.shaneyu.automatedexpansion.data.client.model.ModBlockModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockStateProvider extends BaseBlockStateProvider<ModBlockModelProvider> {

    public ModBlockStateProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, existingFileHelper, ModBlockModelProvider::new);
    }

    @Override
    protected void registerStatesAndModels() {
        // Storage Blocks
        storageBlock(ModBlocks.STEEL_BLOCK);

        // Frames
        frameBlock(ModBlocks.IRON_FRAME);
        frameBlock(ModBlocks.STEEL_FRAME);
    }

    private void horizontalBlockWithItem(Block block) {
        String blockName = ForgeRegistries.BLOCKS.getKey(block).getPath();

        horizontalBlock(block, models().getExistingFile(AutomatedExpansion.rl("block/" + blockName)));
        itemModels().withExistingParent(blockName, AutomatedExpansion.rl("block/" + blockName));
    }

    private void storageBlock(BlockRegistryObject<BlockResource, ?> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject, "storage");
    }

    private void frameBlock(BlockRegistryObject<Block, ?> blockRegistryObject) {
        String name = blockRegistryObject.getName();
        Block block = blockRegistryObject.getBlock();

        ModelFile file = models()
            .cubeAll("block/frame/" + name, AutomatedExpansion.rl("block/frame/" + name))
            .renderType("cutout");

        simpleBlock(block, file);
        simpleBlockItem(block, file);;
    }

    private void simpleBlockWithItem(BlockRegistryObject<?, ?> blockRegistryObject, String type) {
        String name = blockRegistryObject.getName();
        Block block = blockRegistryObject.getBlock();
        ModelFile file = models().cubeAll("block/" + type + "/" + name, AutomatedExpansion.rl("block/" + type + "/" + name));
        simpleBlock(block, file);
        simpleBlockItem(block, file);
    }

//    private void lampBlock(Block block) {
//        String blockName = ForgeRegistries.BLOCKS.getKey(block).getPath();
//        ModelFile defaultModelFile = models().cubeAll(blockName, AutomatedExpansion.rl("block/" + blockName));
//        ModelFile onModelFile = models().cubeAll(blockName + "_on", AutomatedExpansion.rl("block/" + blockName + "_on"));
//
//        getVariantBuilder(block).forAllStates(state -> new ConfiguredModel[]{new ConfiguredModel(state.getValue(BlockStateProperties.LIT) ? onModelFile : defaultModelFile)});
//        itemModels().withExistingParent(blockName, AutomatedExpansion.rl("block/" + blockName));
//    }
}
