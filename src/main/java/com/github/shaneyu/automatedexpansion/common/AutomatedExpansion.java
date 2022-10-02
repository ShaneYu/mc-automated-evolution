package com.github.shaneyu.automatedexpansion.common;

import com.github.shaneyu.automatedexpansion.common.registries.ModBlocks;
import com.github.shaneyu.automatedexpansion.common.registries.ModItems;
import com.github.shaneyu.automatedexpansion.common.registries.ModTileEntityTypes;
import com.github.shaneyu.automatedexpansion.common.util.Version;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AutomatedExpansion.MODID)
public class AutomatedExpansion {
    public static final String MODID = "automatedexpansion";
    public static final String MOD_NAME = "Automated Expansion";
    public static final Logger LOGGER = LogUtils.getLogger();

    public final Version versionNumber;

    public AutomatedExpansion() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        versionNumber = new Version(ModLoadingContext.get().getActiveContainer());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Version {} initializing...", versionNumber);

         event.enqueueWork(() -> {
             // ModNetwork.register();
             ModTags.init();
         });

        LOGGER.info("Mod loaded.");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }
}
