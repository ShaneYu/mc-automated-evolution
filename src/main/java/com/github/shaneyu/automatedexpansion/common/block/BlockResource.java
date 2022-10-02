package com.github.shaneyu.automatedexpansion.common.block;

import org.jetbrains.annotations.NotNull;

public class BlockResource extends BlockBase {
    @NotNull
    private final String resourceName;

    public BlockResource(@NotNull String resourceName, Properties pProperties) {
        super(pProperties);
        this.resourceName = resourceName;
    }

    @NotNull
    public String getResourceName() {
        return resourceName;
    }
}
