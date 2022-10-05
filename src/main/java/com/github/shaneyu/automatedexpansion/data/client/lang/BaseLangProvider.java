package com.github.shaneyu.automatedexpansion.data.client.lang;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import com.github.shaneyu.automatedexpansion.common.lib.text.IHasTranslationKey;
import com.github.shaneyu.automatedexpansion.data.client.lang.FormatSplitter.Component;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public abstract class BaseLangProvider extends LanguageProvider {
    private final ConvertibleLanguageProvider[] altProviders;

    public BaseLangProvider(DataGenerator dataGenerator) {
        super(dataGenerator, AutomatedExpansion.MODID, "en_us");

        altProviders = new ConvertibleLanguageProvider[]{
            new UpsideDownLanguageProvider(dataGenerator)
        };
    }

    @NotNull
    @Override
    public String getName() {
        return super.getName() + ": " + AutomatedExpansion.MODID;
    }

    protected void add(IHasTranslationKey key, String value) {
        add(key.getTranslationKey(), value);
    }

    @Override
    public void add(@NotNull String key, @NotNull String value) {
        if (value.contains("%s")) {
            throw new IllegalArgumentException("Values containing substitutions should use explicit numbered indices: "+key+" - "+value);
        }

        super.add(key, value);

        if (altProviders.length > 0) {
            List<Component> splitEnglish = FormatSplitter.split(value);

            for (ConvertibleLanguageProvider provider : altProviders) {
                provider.convert(key, splitEnglish);
            }
        }
    }

    @Override
    public void run(@NotNull CachedOutput cache) throws IOException {
        super.run(cache);

        if (altProviders.length > 0) {
            for (ConvertibleLanguageProvider provider : altProviders) {
                provider.run(cache);
            }
        }
    }
}
