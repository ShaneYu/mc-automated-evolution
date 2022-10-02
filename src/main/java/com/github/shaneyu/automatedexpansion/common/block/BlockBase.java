package com.github.shaneyu.automatedexpansion.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

public abstract class BlockBase extends Block {
    protected BlockBase(Properties pProperties) {
        super(pProperties);
        // TODO: Register default state?
    }

    @NotNull
    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        if (pState.hasBlockEntity()) {
            return PushReaction.BLOCK;
        }

        return super.getPistonPushReaction(pState);
    }

    @Override
    public boolean triggerEvent(BlockState pState, Level pLevel, BlockPos pPos, int pId, int pParam) {
        boolean triggered = super.triggerEvent(pState, pLevel, pPos, pId, pParam);

        if (this instanceof IHasTileEntity<?> hasTileEntity) {
            return hasTileEntity.triggerBlockEntityEvent(pState, pLevel, pPos, pId, pParam);
        }

        return triggered;
    }
}
