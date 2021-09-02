package wutdahack.actuallyunbreaking.config;

import me.shedaniel.autoconfig.ConfigData;

public class AUConfig implements ConfigData {

    public boolean maxLevelOnly = false;

    public boolean mendingIncompatibility = true;

    public void setMaxLevelOnly(boolean maxLevelOnly) {
        this.maxLevelOnly = maxLevelOnly;
    }

    public void setMendingIncompatibility(boolean mendingIncompatibility) {
        this.mendingIncompatibility = mendingIncompatibility;
    }
}
