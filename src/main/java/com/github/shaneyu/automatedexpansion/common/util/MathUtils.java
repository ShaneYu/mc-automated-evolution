package com.github.shaneyu.automatedexpansion.common.util;

import java.util.List;

public class MathUtils {
    private MathUtils() {}

    /**
     * Gets an element in an array by index, taking the mod (or floored mod if negative).
     *
     * @param elements Elements.
     * @param index    Index.
     *
     * @return Element at the given index.
     */
    public static <TYPE> TYPE getByIndexMod(TYPE[] elements, int index) {
        if (index < 0) {
            return elements[Math.floorMod(index, elements.length)];
        }
        return elements[index % elements.length];
    }

    /**
     * Gets an element in a list by index, taking the mod (or floored mod if negative).
     *
     * @param elements Elements.
     * @param index    Index.
     *
     * @return Element at the given index.
     */
    public static <TYPE> TYPE getByIndexMod(List<TYPE> elements, int index) {
        if (index < 0) {
            return elements.get(Math.floorMod(index, elements.size()));
        }
        return elements.get(index % elements.size());
    }
}
