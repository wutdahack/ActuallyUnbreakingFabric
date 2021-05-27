package wutdahack.actuallyunbreaking;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import wutdahack.actuallyunbreaking.enchantment.ModEnchantments;

public class ActuallyUnbreaking implements ModInitializer {

    @Override
    public void onInitialize() {

        ModEnchantments.registerEnchantments();

    }
}
