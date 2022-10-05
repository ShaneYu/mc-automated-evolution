package com.github.shaneyu.automatedexpansion.common;

import com.github.shaneyu.automatedexpansion.common.lib.annotations.NothingNullByDefault;
import com.github.shaneyu.automatedexpansion.common.lib.text.ILangEntry;
import net.minecraft.Util;

@NothingNullByDefault
public enum ModLang implements ILangEntry {
    AUTOMATED_EXPANSION("constants", "mod_name"),

    // Boolean
    TRUE_LOWER("gui", "true_lower"),
    FALSE_LOWER("gui", "false_lower"),

    // Directions
    DOWN("direction", "down"),
    UP("direction", "up"),
    NORTH("direction", "north"),
    SOUTH("direction", "south"),
    WEST("direction", "west"),
    EAST("direction", "east"),

    // Relative Sides
    FRONT("side", "front"),
    LEFT("side", "left"),
    RIGHT("side", "right"),
    BACK("side", "back"),
    TOP("side", "top"),
    BOTTOM("side", "bottom"),

    // Colors
    COLOR_BLACK("color", "black"),
    COLOR_DARK_BLUE("color", "dark_blue"),
    COLOR_DARK_GREEN("color", "dark_green"),
    COLOR_DARK_AQUA("color", "dark_aqua"),
    COLOR_DARK_RED("color", "dark_red"),
    COLOR_PURPLE("color", "purple"),
    COLOR_ORANGE("color", "orange"),
    COLOR_GRAY("color", "gray"),
    COLOR_DARK_GRAY("color", "dark_gray"),
    COLOR_INDIGO("color", "indigo"),
    COLOR_BRIGHT_GREEN("color", "bright_green"),
    COLOR_AQUA("color", "aqua"),
    COLOR_RED("color", "red"),
    COLOR_PINK("color", "pink"),
    COLOR_YELLOW("color", "yellow"),
    COLOR_WHITE("color", "white"),
    COLOR_BROWN("color", "brown"),
    COLOR_BRIGHT_PINK("color", "bright_pink"),

    // Hold for
    HOLD_FOR_DETAILS("tooltip", "hold_for_details"),
    HOLD_FOR_DESCRIPTION("tooltip", "hold_for_description"),
    ;

    private final String key;

    ModLang(String type, String path) {
        this(Util.makeDescriptionId(type, AutomatedExpansion.rl(path)));
    }

    ModLang(String key) {
        this.key = key;
    }

    @Override
    public String getTranslationKey() {
        return key;
    }
}
