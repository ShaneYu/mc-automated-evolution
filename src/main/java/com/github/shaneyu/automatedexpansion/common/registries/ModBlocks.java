package com.github.shaneyu.automatedexpansion.common.registries;

import com.github.shaneyu.automatedexpansion.common.block.BlockResource;
import com.github.shaneyu.automatedexpansion.common.block.IHasDescription;
import com.github.shaneyu.automatedexpansion.common.item.block.ItemBlockBase;
import com.github.shaneyu.automatedexpansion.common.item.block.ItemBlockTooltip;
import com.github.shaneyu.automatedexpansion.common.registration.impl.BlockDeferredRegister;
import com.github.shaneyu.automatedexpansion.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class ModBlocks {
    private ModBlocks() {}

    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister();

    public static final BlockRegistryObject<BlockResource, ItemBlockBase> STEEL_BLOCK = registerResourceBlock("steel",
        BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3f, 3f));

    public static final BlockRegistryObject<Block, ItemBlockBase> IRON_FRAME = registerFrameBlock("iron",
        BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(2f, 2f));

    public static final BlockRegistryObject<Block, ItemBlockBase> STEEL_FRAME = registerFrameBlock("steel",
        BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3f, 3f));

    private static BlockRegistryObject<Block, ItemBlockBase> registerFrameBlock(String name, BlockBehaviour.Properties blockProperties) {
        return BLOCKS.registerDefaultProperties("block_frame_" + name, () -> new Block(blockProperties), ItemBlockBase::new);
    }

    private static BlockRegistryObject<BlockResource, ItemBlockBase> registerResourceBlock(String name, BlockBehaviour.Properties blockProperties) {
        return BLOCKS.registerDefaultProperties("block_" + name, () -> new BlockResource(name, blockProperties), ItemBlockBase::new);
    }

    private static <BLOCK extends Block & IHasDescription> BlockRegistryObject<BLOCK, ItemBlockTooltip<BLOCK>> registerBlock(
        String name, Supplier<? extends BLOCK> blockSupplier) {

        return BLOCKS.registerDefaultProperties(name, blockSupplier, ItemBlockTooltip::new);
    }
}
