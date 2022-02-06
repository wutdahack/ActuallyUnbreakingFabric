package wutdahack.actuallyunbreaking.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

public class AUConfigGUI {

    AUConfig config = ActuallyUnbreaking.getInstance().config;

    public Screen getConfigScreen(Screen parent, boolean isTransparent) {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TranslatableText("text.config.actuallyunbreaking.title"));
        builder.setDefaultBackgroundTexture(new Identifier("minecraft:textures/block/dirt.png"));
        builder.setSavingRunnable(() -> ActuallyUnbreaking.getInstance().saveConfig());
        ConfigCategory general = builder.getOrCreateCategory(Text.of("general"));
        ConfigEntryBuilder configEntryBuilder = builder.entryBuilder();

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(new TranslatableText("text.config.actuallyunbreaking.option.maxLevelOnly"), config.maxLevelOnly)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> config.setMaxLevelOnly(newValue))
                    .setTooltip(Text.of("only the last level of unbreaking will set the tool\nto be unbreakable if this is true. default = false"))
                    .build()
        );

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(new TranslatableText("text.config.actuallyunbreaking.option.mendingIncompatibility"), config.mendingIncompatibility)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> config.setMendingIncompatibility(newValue))
                    .setTooltip(Text.of("unbreaking will be incompatible with mending\nif this is true. default = true"))
                    .build()
        );

        general.addEntry(
                configEntryBuilder
                    .startBooleanToggle(new TranslatableText("text.config.actuallyunbreaking.option.editEnchantedLootGeneration"), config.editEnchantedLootGeneration)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> config.setEditEnchantedLootGeneration(newValue))
                    .setTooltip(Text.of("enchanted loot will not generate with both\nmending and unbreaking if this is true. default = true"))
                    .build()
        );
        return builder.setTransparentBackground(isTransparent).build();

    }

}
