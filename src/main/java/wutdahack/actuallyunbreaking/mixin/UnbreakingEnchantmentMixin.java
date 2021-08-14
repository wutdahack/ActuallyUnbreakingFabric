package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.AUConfig;

import java.util.Random;

@Mixin(UnbreakingEnchantment.class)
public abstract class UnbreakingEnchantmentMixin extends Enchantment {

    private UnbreakingEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    // items can't have mending and unbreaking together
    @Override
    protected boolean canAccept(Enchantment other) {
        if (AUConfig.instance.mendingIncompatibility) {
            return !(other instanceof MendingEnchantment) && super.canAccept(other);
        } else {
            return super.canAccept(other);
        }
    }


    @Inject(method = "shouldPreventDamage", at = @At(value = "HEAD"), cancellable = true)
    private static void makeUnbreakable(ItemStack item, int level, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (AUConfig.instance.maxLevelOnly ? level >= Enchantments.UNBREAKING.getMaxLevel() : level > 0) {
            item.setDamage(0);
            cir.setReturnValue(true);
        }
    }
}