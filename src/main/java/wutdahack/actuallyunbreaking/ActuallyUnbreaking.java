package wutdahack.actuallyunbreaking;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import wutdahack.actuallyunbreaking.enchantment.ModEnchantments;

public class ActuallyUnbreaking implements ModInitializer {

    @Override
    public void onInitialize() {

        // registering config
        AutoConfig.register(AUConfig.class, JanksonConfigSerializer::new);
        // registering enchantments
        ModEnchantments.registerEnchantments();

    }
}
