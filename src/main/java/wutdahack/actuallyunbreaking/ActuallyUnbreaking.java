package wutdahack.actuallyunbreaking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wutdahack.actuallyunbreaking.config.AUConfig;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ActuallyUnbreaking implements ModInitializer {

    public static Logger LOGGER = LoggerFactory.getLogger("actuallyunbreaking");
    public AUConfig config;
    public static ActuallyUnbreaking instance;

    @Override
    public void onInitialize() {
        loadConfig();
        instance = this;
    }

    // config code based bedrockify config code
    // https://github.com/juancarloscp52/BedrockIfy/blob/1.17.x/src/main/java/me/juancarloscp52/bedrockify/Bedrockify.java
    public void loadConfig() {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "actuallyunbreaking.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (configFile.exists()) {
            try {
                FileReader fileReader = new FileReader(configFile);
                config = gson.fromJson(fileReader, AUConfig.class);
                fileReader.close();
            } catch (IOException e) {
                LOGGER.warn("could not load actuallyunbreaking config options: " + e.getLocalizedMessage());
            }
        } else {
            config = new AUConfig();
            saveConfig();
        }
    }

    public void saveConfig() {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "actuallyunbreaking.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (!configFile.getParentFile().exists()) {
            configFile.getParentFile().mkdir();
        }
        try {
            FileWriter fileWriter = new FileWriter(configFile);
            fileWriter.write(gson.toJson(config));
            fileWriter.close();
        } catch (IOException e) {
            LOGGER.warn("could not save actuallyunbreaking config options: " + e.getLocalizedMessage());
        }
    }
}
