package wutdahack.actuallyunbreaking;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "actuallyunbreaking")
@Config.Gui.Background("minecraft:textures/block/dirt.png")
public class AUConfig implements ConfigData {

    @ConfigEntry.Gui.Excluded
    public static transient AUConfig instance;

    @ConfigEntry.Gui.RequiresRestart(value = false)
    @Comment("only the last level of unbreaking will set the tool\nto be unbreakable if this is true. default = false")
    public boolean maxLevelOnly = false;

}
