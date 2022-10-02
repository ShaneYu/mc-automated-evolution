package com.github.shaneyu.automatedexpansion.common.item.block;

import com.github.shaneyu.automatedexpansion.common.block.IHasDescription;
import com.github.shaneyu.automatedexpansion.common.registration.impl.ItemDeferredRegister;
import net.minecraft.world.level.block.Block;

public class ItemBlockTooltip<BLOCK extends Block & IHasDescription> extends ItemBlockBase<BLOCK> {
    private final boolean hasDetails;

    public ItemBlockTooltip(BLOCK block, Properties properties) {
        this(block, false, properties);
    }

    public ItemBlockTooltip(BLOCK block) {
        this(block, true, ItemDeferredRegister.getModBaseProperties().stacksTo(1));
    }

    protected ItemBlockTooltip(BLOCK block, boolean hasDetails, Properties properties) {
        super(block, properties);
        this.hasDetails = hasDetails;
    }
}