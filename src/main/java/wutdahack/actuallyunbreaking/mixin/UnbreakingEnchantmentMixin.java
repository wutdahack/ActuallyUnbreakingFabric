package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(UnbreakingEnchantment.class)
public class UnbreakingEnchantmentMixin {

    // when preventing damage, sets the tool to be actually unbreakable
    @Inject(method = "shouldPreventDamage", cancellable = true, at = @At(value = "TAIL"))
    // (learnt my lesson and i'm not using overwrite ever again lol)
    private static void preventDamage(ItemStack item, int level, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (item.isDamageable() || item.isDamaged()) {
            item.setDamage(-2147483648); // setting the item to be unbreakable with this ridiculous negative number
        }
        cir.setReturnValue(true); // this did the same thing overwrite did! :D
    }

}
