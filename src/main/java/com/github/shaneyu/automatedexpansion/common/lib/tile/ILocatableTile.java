package com.github.shaneyu.automatedexpansion.common.lib.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface ILocatableTile {
    Level getLevel();

    BlockPos getPos();
}
