package com.github.shaneyu.automatedexpansion.common.providers;

import com.github.shaneyu.automatedexpansion.common.util.text.IHasTextComponent;
import com.github.shaneyu.automatedexpansion.common.util.text.IHasTranslationKey;
import com.github.shaneyu.automatedexpansion.common.util.text.TextComponentUtil;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

@MethodsReturnNonnullByDefault
public interface IBaseProvider extends IHasTextComponent, IHasTranslationKey {
    /**
     * Gets the registry name of the element represented by this provider.
     *
     * @return Registry name.
     */
    ResourceLocation getRegistryName();

    /**
     * Gets the "name" or "path" of the registry name.
     */
    default String getName() {
        return getRegistryName().getPath();
    }

    @Override
    default Component getTextComponent() {
        return TextComponentUtil.translate(getTranslationKey());
    }
}
