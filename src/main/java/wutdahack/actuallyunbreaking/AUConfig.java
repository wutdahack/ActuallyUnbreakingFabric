package wutdahack.actuallyunbreaking;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "actuallyunbreaking")
@Config.Gui.Background("minecraft:textures/block/dirt.png")
public class AUConfig implements ConfigData {

    @ConfigEntry.Gui.RequiresRestart(value = false)
    @Comment("only level 3 of unbreaking will set the tool\nto be unbreakable if this is true. default = false")
    public boolean level3Only = false;

}
