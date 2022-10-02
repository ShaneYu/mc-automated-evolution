package com.github.shaneyu.automatedexpansion.common.registration.impl;

import com.github.shaneyu.automatedexpansion.common.providers.IItemProvider;
import com.github.shaneyu.automatedexpansion.common.registration.WrappedRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ItemRegistryObject<ITEM extends Item> extends WrappedRegistryObject<ITEM> implements IItemProvider {

    public ItemRegistryObject(RegistryObject<ITEM> registryObject) {
        super(registryObject);
    }

    @NotNull
    @Override
    public ITEM asItem() {
        return get();
    }
}
