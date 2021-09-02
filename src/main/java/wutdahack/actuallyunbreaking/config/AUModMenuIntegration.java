package wutdahack.actuallyunbreaking.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class AUModMenuIntegration implements ModMenuApi {

    AUConfigGUI configGUI = new AUConfigGUI();

    // generating a config gui if modmenu is installed
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> configGUI.getConfigScreen(parent, MinecraftClient.getInstance().world != null);
    }

}
