package wutdahack.actuallyunbreaking.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

public class AUConfigGUI {

    AUConfig config = ActuallyUnbreaking.instance.config;

    public Screen getConfigScreen(Screen parent, boolean isTransparent) {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Text.translatable("text.config.actuallyunbreaking.title"));
        builder.setDefaultBackgroundTexture(new Identifier("minecraft:textures/block/dirt.png"));
        builder.setSavingRunnable(() -> ActuallyUnbreaking.instance.saveConfig());
        ConfigCategory general = builder.getOrCreateCategory(Text.of("general"));
        ConfigEntryBuilder configEntryBuilder = builder.entryBuilder();

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(Text.translatable("text.config.actuallyunbreaking.option.maxLevelOnly"), config.maxLevelOnly)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> config.maxLevelOnly = newValue)
                    .setTooltip(Text.of("only the last level of unbreaking will set the tool\nto be unbreakable if this is true. default = false"))
                    .build()
        );

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(Text.translatable("text.config.actuallyunbreaking.option.useUnbreakableAtLevel"), config.useUnbreakableAtLevel)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> config.useUnbreakableAtLevel = newValue)
                    .setTooltip(Text.of("if this is true, the tool will only be\nunbreakable at a specified level\n(overrides max level option). default = false"))
                    .build()
        );

        general.addEntry(
                configEntryBuilder
                    .startIntField(Text.translatable("text.config.actuallyunbreaking.option.unbreakableAtLevel"), config.unbreakableAtLevel)
                    .setDefaultValue(3)
                    .setSaveConsumer(newValue -> config.unbreakableAtLevel = newValue)
                    .setTooltip(Text.of("the specified level the tool will be unbreakable at. default = 3"))
                    .build()
        );

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(Text.translatable("text.config.actuallyunbreaking.option.mendingIncompatibility"), config.mendingIncompatibility)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> config.mendingIncompatibility = newValue)
                    .setTooltip(Text.of("unbreaking will be incompatible with mending\nif this is true. default = true"))
                    .build()
        );

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(Text.translatable("text.config.actuallyunbreaking.option.editEnchantedLootGeneration"), config.editEnchantedLootGeneration)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> config.editEnchantedLootGeneration = newValue)
                    .setTooltip(Text.of("enchanted loot will not generate with both\nmending and unbreaking if this is true. default = true"))
                    .build()
        );

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(Text.translatable("text.config.actuallyunbreaking.option.useUnbreakableTag"), config.useUnbreakableTag)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> config.useUnbreakableTag = newValue)
                    .setTooltip(Text.of("when the tool takes damage, the unbreakable\nnbt tag will be added and unbreaking (and mending) will be\nremoved from the tool (you will no longer be able\nto add unbreaking and mending to the tool) if this is true.\ndefault = true"))
                    .build()
        );
        return builder.setTransparentBackground(isTransparent).build();

    }

}
