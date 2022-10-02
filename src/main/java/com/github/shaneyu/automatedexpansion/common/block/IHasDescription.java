package com.github.shaneyu.automatedexpansion.common.block;

import com.github.shaneyu.automatedexpansion.common.util.text.ILangEntry;
import org.jetbrains.annotations.NotNull;

public interface IHasDescription {
    @NotNull
    ILangEntry getDescription();
}
