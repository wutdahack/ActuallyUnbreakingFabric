package wutdahack.actuallyunbreaking.config;

public class AUConfig {

    public boolean maxLevelOnly = false;

    public boolean mendingIncompatibility = true;

    public boolean editEnchantedLootGeneration = true;

    public boolean useUnbreakableTag = true;

    public void setMaxLevelOnly(boolean maxLevelOnly) {
      this.maxLevelOnly = maxLevelOnly;
    }

    public void setMendingIncompatibility(boolean mendingIncompatibility) {
      this.mendingIncompatibility = mendingIncompatibility;
    }

    public void setEditEnchantedLootGeneration(boolean editEnchantedLootGeneration) {
        this.editEnchantedLootGeneration = editEnchantedLootGeneration;
    }

    public void setUseUnbreakableTag(boolean useUnbreakableTag) {
        this.useUnbreakableTag = useUnbreakableTag;
    }
}
