package com.github.shaneyu.automatedexpansion.common.tile;

import com.github.shaneyu.automatedexpansion.common.lib.block.IHasTileEntity;
import com.github.shaneyu.automatedexpansion.common.lib.providers.IBlockProvider;
import com.github.shaneyu.automatedexpansion.common.lib.tile.ILocatableTile;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public abstract class UpdatableTileEntity extends BlockEntity implements ILocatableTile {

    protected final IBlockProvider blockProvider;

    public UpdatableTileEntity(IBlockProvider blockProvider, BlockPos pPos, BlockState pBlockState) {
        super(((IHasTileEntity<? extends BlockEntity>) blockProvider.getBlock()).getTileType().get(), pPos, pBlockState);

        this.blockProvider = blockProvider;
    }

    public Block getBlock() {
        return blockProvider.getBlock();
    }

    public ResourceLocation getBlockRegistryName() {
        return blockProvider.getRegistryName();
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public BlockPos getPos() {
        return worldPosition;
    }

    public static void tickClient(Level level, BlockPos pos, BlockState state, UpdatableTileEntity tile) {
        tile.onUpdateClient();
    }

    public static void tickServer(Level level, BlockPos pos, BlockState state, UpdatableTileEntity tile) {
        tile.onUpdateServer();
    }

    /**
     * Update call for machines. Use instead of updateEntity -- it's called every tick on the client side.
     */
    protected void onUpdateClient() {
    }

    /**
     * Update call for machines. Use instead of updateEntity -- it's called every tick on the server side.
     */
    protected void onUpdateServer() {
    }
}
