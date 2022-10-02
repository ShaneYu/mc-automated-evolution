package com.github.shaneyu.automatedexpansion.data.client.state;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.providers.IBlockProvider;
import com.github.shaneyu.automatedexpansion.common.registries.ModBlocks;
import com.github.shaneyu.automatedexpansion.data.client.model.ModBlockModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockStateProvider extends BaseBlockStateProvider<ModBlockModelProvider> {

    public ModBlockStateProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, existingFileHelper, ModBlockModelProvider::new);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.STEEL_BLOCK);
    }

    private void horizontalBlockWithItem(Block block) {
        String blockName = ForgeRegistries.BLOCKS.getKey(block).getPath();

        horizontalBlock(block, models().getExistingFile(AutomatedExpansion.rl("block/" + blockName)));
        itemModels().withExistingParent(blockName, AutomatedExpansion.rl("block/" + blockName));
    }

    private void simpleBlockWithItem(IBlockProvider blockProvider) {
        simpleBlockWithItem(blockProvider.getBlock());
    }

    private void simpleBlockWithItem(Block block) {
        String blockName = ForgeRegistries.BLOCKS.getKey(block).getPath();

        simpleBlock(block);
        itemModels().withExistingParent(blockName, AutomatedExpansion.rl("block/" + blockName));
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
