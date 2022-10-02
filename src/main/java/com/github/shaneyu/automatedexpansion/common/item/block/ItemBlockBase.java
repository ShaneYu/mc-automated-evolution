package com.github.shaneyu.automatedexpansion.common.item.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class ItemBlockBase<BLOCK extends Block> extends BlockItem {
    @NotNull
    private final BLOCK block;

    public ItemBlockBase(@NotNull BLOCK pBlock, Properties pProperties) {
        super(pBlock, pProperties);
        this.block = pBlock;
    }

    @NotNull
    @Override
    public BLOCK getBlock() {
        return block;
    }
}
