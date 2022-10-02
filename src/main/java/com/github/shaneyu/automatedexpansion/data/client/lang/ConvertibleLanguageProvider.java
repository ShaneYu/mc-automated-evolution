package com.github.shaneyu.automatedexpansion.data.client.lang;

import com.github.shaneyu.automatedexpansion.common.AutomatedExpansion;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.List;

public abstract class ConvertibleLanguageProvider extends LanguageProvider {
    public ConvertibleLanguageProvider(DataGenerator gen, String locale) {
        super(gen, AutomatedExpansion.MODID, locale);
    }

    public abstract void convert(String key, List<FormatSplitter.Component> splitEnglish);

    @Override
    protected void addTranslations() {}
}
