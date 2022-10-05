package com.github.shaneyu.automatedexpansion.common.lib.registration.impl;

import com.github.shaneyu.automatedexpansion.common.tile.UpdatableTileEntity;
import com.github.shaneyu.automatedexpansion.common.lib.registration.WrappedDeferredRegister;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class TileEntityTypeDeferredRegister extends WrappedDeferredRegister<BlockEntityType<?>> {

    public TileEntityTypeDeferredRegister() {
        super(ForgeRegistries.BLOCK_ENTITY_TYPES);
    }

    public <BE extends UpdatableTileEntity> TileEntityTypeRegistryObject<BE> register(BlockRegistryObject<?, ?> block, BlockEntitySupplier<? extends BE> factory) {
        return this.<BE>builder(block, factory).clientTicker(UpdatableTileEntity::tickClient).serverTicker(UpdatableTileEntity::tickServer).build();
    }

    public <BE extends BlockEntity> BlockEntityTypeBuilder<BE> builder(BlockRegistryObject<?, ?> block, BlockEntitySupplier<? extends BE> factory) {
        return new BlockEntityTypeBuilder<>(block, factory);
    }

    public class BlockEntityTypeBuilder<BE extends BlockEntity> {

        private final BlockRegistryObject<?, ?> block;
        private final BlockEntityType.BlockEntitySupplier<? extends BE> factory;
        @Nullable
        private BlockEntityTicker<BE> clientTicker;
        @Nullable
        private BlockEntityTicker<BE> serverTicker;

        private BlockEntityTypeBuilder(BlockRegistryObject<?, ?> block, BlockEntityType.BlockEntitySupplier<? extends BE> factory) {
            this.block = block;
            this.factory = factory;
        }

        public BlockEntityTypeBuilder<BE> clientTicker(BlockEntityTicker<BE> ticker) {
            if (clientTicker != null) {
                throw new IllegalStateException("Client ticker may only be set once.");
            }
            this.clientTicker = ticker;
            return this;
        }

        public BlockEntityTypeBuilder<BE> serverTicker(BlockEntityTicker<BE> ticker) {
            if (serverTicker != null) {
                throw new IllegalStateException("Server ticker may only be set once.");
            }
            this.serverTicker = ticker;
            return this;
        }

        public BlockEntityTypeBuilder<BE> commonTicker(BlockEntityTicker<BE> ticker) {
            return clientTicker(ticker).serverTicker(ticker);
        }

        @SuppressWarnings("ConstantConditions")
        public TileEntityTypeRegistryObject<BE> build() {
            TileEntityTypeRegistryObject<BE> registryObject = new TileEntityTypeRegistryObject<>(null);
            registryObject.clientTicker(clientTicker).serverTicker(serverTicker);

            return register(block.getInternalRegistryName(), () -> BlockEntityType.Builder.<BE>of(factory, block.getBlock()).build(null),
                registryObject::setRegistryObject);
        }
    }
}
