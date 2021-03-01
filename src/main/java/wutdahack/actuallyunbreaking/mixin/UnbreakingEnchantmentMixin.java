package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(UnbreakingEnchantment.class)
public class UnbreakingEnchantmentMixin {

    // when preventing damage, sets the tool to be actually unbreakable
    @Overwrite
    // thanks to overwrite now it doesn't take 1 durability away and then add it back on - overwrite should be avoided but it worked better than inject??
    public static boolean shouldPreventDamage(ItemStack item, int level, Random random) {
        if (item.isDamageable() || item.isDamaged()) {
            item.setDamage(-2147483648);
        }
        return true;
    }
    
}
