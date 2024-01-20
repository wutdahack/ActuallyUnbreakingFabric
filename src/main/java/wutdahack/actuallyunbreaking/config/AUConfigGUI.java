package wutdahack.actuallyunbreaking.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

public class AUConfigGUI {

    AUConfig config = ActuallyUnbreaking.instance.config;

    public Screen getConfigScreen(Screen parent, boolean isTransparent) {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Component.translatable("text.config.actuallyunbreaking.title"));
        builder.setDefaultBackgroundTexture(new ResourceLocation("minecraft:textures/block/dirt.png"));
        builder.setSavingRunnable(() -> ActuallyUnbreaking.instance.saveConfig());
        ConfigCategory general = builder.getOrCreateCategory(Component.literal("general"));
        ConfigEntryBuilder configEntryBuilder = builder.entryBuilder();

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(Component.translatable("text.config.actuallyunbreaking.option.maxLevelOnly"), config.maxLevelOnly)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> config.maxLevelOnly = newValue)
                    .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.maxLevelOnly"))
                    .build()
        );

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(Component.translatable("text.config.actuallyunbreaking.option.useUnbreakableAtLevel"), config.useUnbreakableAtLevel)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> config.useUnbreakableAtLevel = newValue)
                    .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.useUnbreakableAtLevel"))
                    .build()
        );

        general.addEntry(
                configEntryBuilder
                    .startIntField(Component.translatable("text.config.actuallyunbreaking.option.unbreakableAtLevel"), config.unbreakableAtLevel)
                    .setDefaultValue(3)
                    .setSaveConsumer(newValue -> config.unbreakableAtLevel = newValue)
                    .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.unbreakableAtLevel"))
                    .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(Component.translatable("text.config.actuallyunbreaking.option.useOnlyUnbreakableAtLevel"), config.useOnlyUnbreakableAtLevel)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> config.useOnlyUnbreakableAtLevel = newValue)
                        .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.useOnlyUnbreakableAtLevel"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startIntField(Component.translatable("text.config.actuallyunbreaking.option.onlyUnbreakableAtLevel"), config.onlyUnbreakableAtLevel)
                        .setDefaultValue(3)
                        .setSaveConsumer(newValue -> config.onlyUnbreakableAtLevel = newValue)
                        .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.onlyUnbreakableAtLevel"))
                        .build()

        );

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(Component.translatable("text.config.actuallyunbreaking.option.mendingIncompatibility"), config.mendingIncompatibility)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> config.mendingIncompatibility = newValue)
                    .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.mendingIncompatibility"))
                    .build()
        );

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(Component.translatable("text.config.actuallyunbreaking.option.useUnbreakableTag"), config.useUnbreakableTag)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> config.useUnbreakableTag = newValue)
                    .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.useUnbreakableTag"))
                    .build()
        );
        return builder.setTransparentBackground(isTransparent).build();

    }

}
