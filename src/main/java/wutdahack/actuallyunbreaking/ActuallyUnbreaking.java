package wutdahack.actuallyunbreaking;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class ActuallyUnbreaking implements ModInitializer {

    @Override
    public void onInitialize() {

        // registering config
        AUConfig.instance = AutoConfig.register(AUConfig.class, JanksonConfigSerializer::new).get();

    }
}
