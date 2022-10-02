package com.github.shaneyu.automatedexpansion.common.registries;

import com.github.shaneyu.automatedexpansion.common.registration.impl.TileEntityTypeDeferredRegister;

public class ModTileEntityTypes {
    private ModTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister();

    //public static final TileEntityTypeRegistryObject<TileEntitySolarGenerator> SOLAR_GENERATOR = TILE_ENTITY_TYPES.register(ModBlocks.SOLAR_GENERATOR, TileEntitySolarGenerator::new);
    //public static final TileEntityTypeRegistryObject<TileEntityCrusher> CRUSHER = TILE_ENTITY_TYPES.register(ModBlocks.CRUSHER, TileEntityCrusher::new);
}
