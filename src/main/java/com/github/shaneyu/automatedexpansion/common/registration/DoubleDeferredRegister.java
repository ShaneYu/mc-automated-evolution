package com.github.shaneyu.automatedexpansion.common.registration;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class DoubleDeferredRegister<PRIMARY, SECONDARY> {
    private final DeferredRegister<PRIMARY> primaryRegister;
    private final DeferredRegister<SECONDARY> secondaryRegister;

    public DoubleDeferredRegister(DeferredRegister<PRIMARY> primaryRegistry, DeferredRegister<SECONDARY> secondaryRegistry) {
        this.primaryRegister = primaryRegistry;
        this.secondaryRegister = secondaryRegistry;
    }

    public DoubleDeferredRegister(IForgeRegistry<PRIMARY> primaryRegistry, IForgeRegistry<SECONDARY> secondaryRegistry) {
        this(DeferredRegister.create(primaryRegistry, AutomatedExpansion.MODID), DeferredRegister.create(secondaryRegistry, AutomatedExpansion.MODID));
    }

    protected DoubleDeferredRegister(ResourceKey<? extends Registry<PRIMARY>> primaryRegistryName,
                                     ResourceKey<? extends Registry<SECONDARY>> secondaryRegistryName) {
        this(DeferredRegister.create(primaryRegistryName, AutomatedExpansion.MODID), DeferredRegister.create(secondaryRegistryName, AutomatedExpansion.MODID));
    }

    public <P extends PRIMARY, S extends SECONDARY, W extends DoubleWrappedRegistryObject<P, S>> W register(String name, Supplier<? extends P> primarySupplier,
                                                                                                            Supplier<? extends S> secondarySupplier, BiFunction<RegistryObject<P>, RegistryObject<S>, W> objectWrapper) {
        return objectWrapper.apply(primaryRegister.register(name, primarySupplier), secondaryRegister.register(name, secondarySupplier));
    }

    public <P extends PRIMARY, S extends SECONDARY, W extends DoubleWrappedRegistryObject<P, S>> W register(String name, Supplier<? extends P> primarySupplier,
                                                                                                            Function<P, S> secondarySupplier, BiFunction<RegistryObject<P>, RegistryObject<S>, W> objectWrapper) {
        return registerAdvanced(name, primarySupplier, secondarySupplier.compose(RegistryObject::get), objectWrapper);
    }

    public <P extends PRIMARY, S extends SECONDARY, W extends DoubleWrappedRegistryObject<P, S>> W registerAdvanced(String name, Supplier<? extends P> primarySupplier,
                                                                                                                    Function<RegistryObject<P>, S> secondarySupplier, BiFunction<RegistryObject<P>, RegistryObject<S>, W> objectWrapper) {
        RegistryObject<P> primaryObject = primaryRegister.register(name, primarySupplier);

        return objectWrapper.apply(primaryObject, secondaryRegister.register(name, () -> secondarySupplier.apply(primaryObject)));
    }

    public void register(IEventBus bus) {
        primaryRegister.register(bus);
        secondaryRegister.register(bus);
    }
}
