package com.github.shaneyu.automatedexpansion.common.util;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import net.minecraft.resources.ResourceLocation;

public class ModResourceLocation {
    public static ResourceLocation path(String path) {
        return new ResourceLocation(AutomatedExpansion.MODID, path);
    }
}
