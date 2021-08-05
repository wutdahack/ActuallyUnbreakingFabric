package wutdahack.actuallyunbreaking;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantments;

public class ActuallyUnbreaking implements ModInitializer {

    public static boolean isUnbreakable(int level) {
        return AUConfig.instance.maxLevelOnly ? level >= Enchantments.UNBREAKING.getMaxLevel() : level > 0;
    }

    @Override
    public void onInitialize() {

        // registering config
        AUConfig.instance = AutoConfig.register(AUConfig.class, JanksonConfigSerializer::new).get();

    }
}
