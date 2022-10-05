package com.github.shaneyu.automatedexpansion.common.lib.registration.impl;

import com.github.shaneyu.automatedexpansion.common.lib.registration.WrappedRegistryObject;
import com.github.shaneyu.automatedexpansion.common.lib.providers.IItemProvider;
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
