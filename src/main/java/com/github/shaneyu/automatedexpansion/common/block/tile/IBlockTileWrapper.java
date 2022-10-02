package com.github.shaneyu.automatedexpansion.common.block.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface IBlockTileWrapper {
    BlockPos getTilePos();

    Level getTileWorld();
}
