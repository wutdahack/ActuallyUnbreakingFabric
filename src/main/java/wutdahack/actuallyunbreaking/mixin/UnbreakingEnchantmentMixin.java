package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(UnbreakingEnchantment.class)
public class UnbreakingEnchantmentMixin {

    @Inject(method = "shouldPreventDamage", at = @At(value = "INVOKE"))
    private static void preventDamage(ItemStack item, int level, Random random, CallbackInfoReturnable<Boolean> cir) {
        cir.isCancelled(); // i can't remember if this mixin worked without this line so i'm keeping it because it seems to work with it
         if (item.isDamaged() || item.isDamageable()) {
             item.setDamage(-2147483648);
         }
    }

}
