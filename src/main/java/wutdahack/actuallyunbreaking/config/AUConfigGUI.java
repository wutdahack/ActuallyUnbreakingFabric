package wutdahack.actuallyunbreaking.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

public class AUConfigGUI {

    AUConfig config = ActuallyUnbreaking.instance.config;

    public Screen getConfigScreen(Screen parent, boolean isTransparent) {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TranslatableComponent("text.config.actuallyunbreaking.title"));
        builder.setDefaultBackgroundTexture(new ResourceLocation("minecraft:textures/block/dirt.png"));
        builder.setSavingRunnable(() -> ActuallyUnbreaking.instance.saveConfig());
        ConfigCategory general = builder.getOrCreateCategory(new TextComponent("general"));
        ConfigEntryBuilder configEntryBuilder = builder.entryBuilder();

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslatableComponent("text.config.actuallyunbreaking.option.maxLevelOnly"), config.maxLevelOnly)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> config.maxLevelOnly = newValue)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.maxLevelOnly"))
                        .build()
        );


        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslatableComponent("text.config.actuallyunbreaking.option.useUnbreakableAtLevel"), config.useUnbreakableAtLevel)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> config.useUnbreakableAtLevel = newValue)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.useUnbreakableAtLevel"))
                        .build()
        );


        general.addEntry(
                configEntryBuilder
                        .startIntField(new TranslatableComponent("text.config.actuallyunbreaking.option.unbreakableAtLevel"), config.unbreakableAtLevel)
                        .setDefaultValue(3)
                        .setSaveConsumer(newValue -> config.unbreakableAtLevel = newValue)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.unbreakableAtLevel"))
                        .build()
        );


        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslatableComponent("text.config.actuallyunbreaking.option.mendingIncompatibility"), config.mendingIncompatibility)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> config.mendingIncompatibility = newValue)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.mendingIncompatibility"))
                        .build()
        );


        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslatableComponent("text.config.actuallyunbreaking.option.useUnbreakableTag"), config.useUnbreakableTag)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> config.useUnbreakableTag = newValue)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.useUnbreakableTag"))
                        .build()
        );
        return builder.setTransparentBackground(isTransparent).build();

    }

}
