package com.github.shaneyu.automatedexpansion.data.common.tag;

import com.github.shaneyu.automatedexpansion.common.lib.providers.IBlockProvider;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;

public abstract class BaseTagProvider implements DataProvider {
    private final Map<TagType<?>, Map<TagKey<?>, TagBuilder>> supportedTagTypes = new Object2ObjectLinkedOpenHashMap<>();
    private final Set<Block> knownHarvestRequirements = new HashSet<>();
    private final ExistingFileHelper existingFileHelper;
    private final DataGenerator gen;
    private final String modid;

    protected BaseTagProvider(DataGenerator gen, String modid, @Nullable ExistingFileHelper existingFileHelper) {
        this.gen = gen;
        this.modid = modid;
        this.existingFileHelper = existingFileHelper;

        addTagType(TagType.ITEM);
        addTagType(TagType.BLOCK);
        addTagType(TagType.ENTITY_TYPE);
        addTagType(TagType.FLUID);
        addTagType(TagType.BLOCK_ENTITY_TYPE);
        addTagType(TagType.GAME_EVENT);
    }

    @NotNull
    @Override
    public String getName() {
        return "Tags: " + modid;
    }

    protected <TYPE> void addTagType(TagType<TYPE> tagType) {
        supportedTagTypes.computeIfAbsent(tagType, type -> new Object2ObjectLinkedOpenHashMap<>());
    }

    protected abstract void registerTags();

    protected List<IBlockProvider> getAllBlocks() {
        return Collections.emptyList();
    }

    protected void hasHarvestData(Block block) {
        knownHarvestRequirements.add(block);
    }

    @Override
    public void run(@NotNull CachedOutput cache) {
        supportedTagTypes.values().forEach(Map::clear);
        registerTags();

        for (IBlockProvider blockProvider : getAllBlocks()) {
            Block block = blockProvider.getBlock();

            if (block.defaultBlockState().requiresCorrectToolForDrops() && !knownHarvestRequirements.contains(block)) {
                throw new IllegalStateException("Missing harvest tool type for block '" + ForgeRegistries.BLOCKS.getKey(block).getPath() + "' that requires the correct tool for drops.");
            }
        }

        supportedTagTypes.forEach((tagType, tagTypeMap) -> act(cache, tagType, tagTypeMap));
    }

    private <TYPE> void act(@NotNull CachedOutput cache, TagType<TYPE> tagType, Map<TagKey<?>, TagBuilder> tagTypeMap) {
        if (!tagTypeMap.isEmpty()) {
            // Create a dummy provider and pass all our collected data through to it
            tagType.getRegistry().map(forgeRegistry -> new ForgeRegistryTagsProvider<>(gen, forgeRegistry, modid, existingFileHelper) {
                @Override
                protected void addTags() {
                    //Add each tag builder to the wrapped provider's builder
                    tagTypeMap.forEach((tag, tagBuilder) -> builders.put(tag.location(), tagBuilder));
                }

                @NotNull
                @Override
                public String getName() {
                    return tagType.name() + " Tags: " + modid;
                }
            }, vanillaRegistry -> new TagsProvider<>(gen, vanillaRegistry, modid, existingFileHelper) {
                @Override
                protected void addTags() {
                    //Add each tag builder to the wrapped provider's builder
                    tagTypeMap.forEach((tag, tagBuilder) -> builders.put(tag.location(), tagBuilder));
                }

                @NotNull
                @Override
                public String getName() {
                    return tagType.name() + " Tags: " + modid;
                }
            }).run(cache);
        }
    }

    protected <TYPE> ForgeRegistryTagBuilder<TYPE> getBuilder(TagType<TYPE> tagType, TagKey<TYPE> tag) {
        return new ForgeRegistryTagBuilder<>(tagType.getRegistry(), supportedTagTypes.get(tagType).computeIfAbsent(tag, ignored -> TagBuilder.create()), modid);
    }

    protected ForgeRegistryTagBuilder<Item> getItemBuilder(TagKey<Item> tag) {
        return getBuilder(TagType.ITEM, tag);
    }

    protected ForgeRegistryTagBuilder<Block> getBlockBuilder(TagKey<Block> tag) {
        return getBuilder(TagType.BLOCK, tag);
    }

    protected ForgeRegistryTagBuilder<EntityType<?>> getEntityTypeBuilder(TagKey<EntityType<?>> tag) {
        return getBuilder(TagType.ENTITY_TYPE, tag);
    }

    protected ForgeRegistryTagBuilder<Fluid> getFluidBuilder(TagKey<Fluid> tag) {
        return getBuilder(TagType.FLUID, tag);
    }

    protected ForgeRegistryTagBuilder<BlockEntityType<?>> getTileEntityTypeBuilder(TagKey<BlockEntityType<?>> tag) {
        return getBuilder(TagType.BLOCK_ENTITY_TYPE, tag);
    }

    protected ForgeRegistryTagBuilder<GameEvent> getGameEventBuilder(TagKey<GameEvent> tag) {
        return getBuilder(TagType.GAME_EVENT, tag);
    }

    protected void addToTag(TagKey<Item> tag, ItemLike... itemProviders) {
        getItemBuilder(tag).addTyped(ItemLike::asItem, itemProviders);
    }

    protected void addToTag(TagKey<Block> tag, IBlockProvider... blockProviders) {
        getBlockBuilder(tag).addTyped(IBlockProvider::getBlock, blockProviders);
    }

    @SafeVarargs
    protected final void addToTag(TagKey<Block> blockTag, Map<?, ? extends IBlockProvider>... blockProviders) {
        ForgeRegistryTagBuilder<Block> tagBuilder = getBlockBuilder(blockTag);
        for (Map<?, ? extends IBlockProvider> blockProvider : blockProviders) {
            for (IBlockProvider value : blockProvider.values()) {
                tagBuilder.add(value.getBlock());
            }
        }
    }

    protected void addToHarvestTag(TagKey<Block> tag, IBlockProvider... blockProviders) {
        ForgeRegistryTagBuilder<Block> tagBuilder = getBlockBuilder(tag);
        for (IBlockProvider blockProvider : blockProviders) {
            Block block = blockProvider.getBlock();
            tagBuilder.add(block);
            hasHarvestData(block);
        }
    }

    @SafeVarargs
    protected final void addToHarvestTag(TagKey<Block> blockTag, Map<?, ? extends IBlockProvider>... blockProviders) {
        ForgeRegistryTagBuilder<Block> tagBuilder = getBlockBuilder(blockTag);
        for (Map<?, ? extends IBlockProvider> blockProvider : blockProviders) {
            for (IBlockProvider value : blockProvider.values()) {
                Block block = value.getBlock();
                tagBuilder.add(block);
                hasHarvestData(block);
            }
        }
    }

    protected void addToTags(TagKey<Item> itemTag, TagKey<Block> blockTag, IBlockProvider... blockProviders) {
        ForgeRegistryTagBuilder<Item> itemTagBuilder = getItemBuilder(itemTag);
        ForgeRegistryTagBuilder<Block> blockTagBuilder = getBlockBuilder(blockTag);
        for (IBlockProvider blockProvider : blockProviders) {
            itemTagBuilder.add(blockProvider.asItem());
            blockTagBuilder.add(blockProvider.getBlock());
        }
    }
}
