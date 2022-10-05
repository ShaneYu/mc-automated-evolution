package com.github.shaneyu.automatedexpansion.common.lib.registration.impl;

import com.github.shaneyu.automatedexpansion.common.lib.registration.DoubleWrappedRegistryObject;
import com.github.shaneyu.automatedexpansion.common.lib.providers.IBlockProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class BlockRegistryObject<BLOCK extends Block, ITEM extends Item> extends DoubleWrappedRegistryObject<BLOCK, ITEM> implements IBlockProvider {
    public BlockRegistryObject(RegistryObject<BLOCK> blockRegistryObject, RegistryObject<ITEM> itemRegistryObject) {
        super(blockRegistryObject, itemRegistryObject);
    }

    @NotNull
    @Override
    public BLOCK getBlock() {
        return getPrimary();
    }

    @NotNull
    @Override
    public ITEM asItem() {
        return getSecondary();
    }
}
