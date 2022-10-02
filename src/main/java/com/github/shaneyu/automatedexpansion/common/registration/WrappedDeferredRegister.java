package com.github.shaneyu.automatedexpansion.common.registration;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class WrappedDeferredRegister<T> {
    protected final DeferredRegister<T> internalRegistry;

    protected WrappedDeferredRegister(DeferredRegister<T> internalRegistry) {
        this.internalRegistry = internalRegistry;
    }

    protected WrappedDeferredRegister(IForgeRegistry<T> registry) {
        this(DeferredRegister.create(registry, AutomatedExpansion.MODID));
    }

    protected WrappedDeferredRegister(ResourceKey<? extends Registry<T>> registryName) {
        this(DeferredRegister.create(registryName, AutomatedExpansion.MODID));
    }

    protected <I extends T, W extends WrappedRegistryObject<I>> W register(String name, Supplier<? extends I> sup, Function<RegistryObject<I>, W> objectWrapper) {
        return objectWrapper.apply(internalRegistry.register(name, sup));
    }

    public void register(IEventBus bus) {
        internalRegistry.register(bus);
    }

    /**
     * Only call this from automated expansion and for custom registries
     */
    public void createAndRegister(IEventBus bus) {
        createAndRegister(bus, UnaryOperator.identity());
    }

    /**
     * Only call this from automated expansion and for custom registries
     */
    public void createAndRegister(IEventBus bus, UnaryOperator<RegistryBuilder<T>> builder) {
        internalRegistry.makeRegistry(() -> builder.apply(new RegistryBuilder<>()));
        register(bus);
    }
}
