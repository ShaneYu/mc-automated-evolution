package com.github.shaneyu.automatedexpansion.data.client.state;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.providers.IBlockProvider;
import com.github.shaneyu.automatedexpansion.data.client.model.BaseBlockModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class BaseBlockStateProvider<PROVIDER extends BaseBlockModelProvider> extends BlockStateProvider {
    private final PROVIDER modelProvider;

    public BaseBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper,
                                  BiFunction<DataGenerator, ExistingFileHelper, PROVIDER> providerCreator) {
        super(generator, AutomatedExpansion.MODID, existingFileHelper);
        modelProvider = providerCreator.apply(generator, existingFileHelper);
    }

    @NotNull
    @Override
    public String getName() {
        return "Block state provider: " + AutomatedExpansion.MODID;
    }

    @Override
    public PROVIDER models() {
        return modelProvider;
    }

    protected VariantBlockStateBuilder getVariantBuilder(IBlockProvider blockProvider) {
        return getVariantBuilder(blockProvider.getBlock());
    }

    /**
     * Like directionalBlock but allows us to skip specific properties
     */
    protected void directionalBlock(Block block, Function<BlockState, ModelFile> modelFunc, int angleOffset, Property<?>... toSkip) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            Direction dir = state.getValue(BlockStateProperties.FACING);
            return ConfiguredModel.builder()
                .modelFile(modelFunc.apply(state))
                .rotationX(dir == Direction.DOWN ? 180 : dir.getAxis().isHorizontal() ? 90 : 0)
                .rotationY(dir.getAxis().isVertical() ? 0 : (((int) dir.toYRot()) + angleOffset) % 360)
                .build();
        }, toSkip);
    }

    protected void simpleBlockItem(IBlockProvider block, ModelFile model) {
        super.simpleBlockItem(block.getBlock(), model);
    }
}
