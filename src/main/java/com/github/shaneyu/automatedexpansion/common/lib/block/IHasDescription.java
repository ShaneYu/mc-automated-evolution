package com.github.shaneyu.automatedexpansion.common.lib.block;

import com.github.shaneyu.automatedexpansion.common.lib.text.ILangEntry;
import org.jetbrains.annotations.NotNull;

public interface IHasDescription {
    @NotNull
    ILangEntry getDescription();
}
