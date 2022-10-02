package com.github.shaneyu.automatedexpansion.common.util;

import com.github.shaneyu.automatedexpansion.common.util.text.EnumColor;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EquipmentSlot;

public class EnumUtils {
    private EnumUtils() {}

    /**
     * Cached collection of armor slot positions from EquipmentSlotType. DO NOT MODIFY THIS LIST.
     */
    public static final EquipmentSlot[] ARMOR_SLOTS = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    /**
     * Cached collection of hand slot positions from EquipmentSlotType. DO NOT MODIFY THIS LIST.
     */
    public static final EquipmentSlot[] HAND_SLOTS = {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};

    /**
     * Cached value of {@link Direction#values()}. DO NOT MODIFY THIS LIST.
     */
    public static final Direction[] DIRECTIONS = Direction.values();

    /**
     * Cached value of the horizontal directions. DO NOT MODIFY THIS LIST.
     *
     * @implNote Index is ordinal() - 2, as the first two elements of {@link Direction} are {@link Direction#DOWN} and {@link Direction#UP}
     */
    public static final Direction[] HORIZONTAL_DIRECTIONS = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

    /**
     * Cached value of {@link RelativeSide#values()}. DO NOT MODIFY THIS LIST.
     */
    public static final RelativeSide[] SIDES = RelativeSide.values();

    /**
     * Cached value of {@link EnumColor#values()}. DO NOT MODIFY THIS LIST.
     */
    public static final EnumColor[] COLORS = EnumColor.values();
}
