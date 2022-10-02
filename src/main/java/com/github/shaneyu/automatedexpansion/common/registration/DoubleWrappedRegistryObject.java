package com.github.shaneyu.automatedexpansion.common.registration;

import com.github.shaneyu.automatedexpansion.common.util.annotations.NothingNullByDefault;
import net.minecraftforge.registries.RegistryObject;

@NothingNullByDefault
public class DoubleWrappedRegistryObject<PRIMARY, SECONDARY> implements  INamedEntry {
    protected final RegistryObject<PRIMARY> primaryRegistryObject;
    protected final RegistryObject<SECONDARY> secondaryRegistryObject;

    public DoubleWrappedRegistryObject(RegistryObject<PRIMARY> primaryRegistryObject, RegistryObject<SECONDARY> secondaryRegistryObject) {
        this.primaryRegistryObject = primaryRegistryObject;
        this.secondaryRegistryObject = secondaryRegistryObject;
    }

    public PRIMARY getPrimary() {
        return primaryRegistryObject.get();
    }

    public SECONDARY getSecondary() {
        return secondaryRegistryObject.get();
    }

    @Override
    public String getInternalRegistryName() {
        return primaryRegistryObject.getId().getPath();
    }
}
