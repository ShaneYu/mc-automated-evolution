package com.github.shaneyu.automatedexpansion.data;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.data.client.lang.ModLangProvider;
import com.github.shaneyu.automatedexpansion.data.client.model.ModItemModelProvider;
import com.github.shaneyu.automatedexpansion.data.client.state.ModBlockStateProvider;
import com.github.shaneyu.automatedexpansion.data.common.ModLootTableProvider;
import com.github.shaneyu.automatedexpansion.data.common.recipe.ModRecipeProvider;
import com.github.shaneyu.automatedexpansion.data.common.tag.ModTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AutomatedExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {
    private ModDataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Client side
        generator.addProvider(event.includeClient(), new ModLangProvider(generator));
        ModItemModelProvider itemModelProvider = new ModItemModelProvider(generator, existingFileHelper);
        generator.addProvider(event.includeClient(), itemModelProvider);
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(generator, itemModelProvider.existingFileHelper));

        // Server size
        generator.addProvider(event.includeServer(), new ModRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new ModLootTableProvider(generator));
        generator.addProvider(event.includeServer(), new ModTagsProvider(generator, existingFileHelper));
    }
}
