package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.util.math.random.Random;
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
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

@Mixin(UnbreakingEnchantment.class)
public abstract class UnbreakingEnchantmentMixin extends Enchantment {

    private UnbreakingEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    // items can't have mending and unbreaking together if enabled in the config
    @Override
    protected boolean canAccept(Enchantment other) {
        if (ActuallyUnbreaking.instance.config.mendingIncompatibility) {
            return !(other instanceof MendingEnchantment) && super.canAccept(other);
        } else {
            return super.canAccept(other);
        }
    }

    @Inject(method = "isAcceptableItem", at = @At(value = "HEAD"), cancellable = true)
    private void dontAcceptUnbreakableItems(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (ActuallyUnbreaking.instance.config.useUnbreakableTag) {
            cir.setReturnValue(stack.getNbt() != null && !stack.getNbt().getBoolean("Unbreakable")); // item is only acceptable if it doesn't have the unbreakable tag
        }
    }

    @Inject(method = "shouldPreventDamage", at = @At(value = "HEAD"), cancellable = true)
    private static void makeUnbreakable(ItemStack item, int level, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (!ActuallyUnbreaking.instance.config.useUnbreakableTag) {
            if (ActuallyUnbreaking.instance.config.useUnbreakableAtLevel && level >= ActuallyUnbreaking.instance.config.unbreakableAtLevel) {
                item.setDamage(0); // set item damage to 0 to remove the tool's durability bar
                cir.setReturnValue(true);
            }
            else if (ActuallyUnbreaking.instance.config.maxLevelOnly && level >= Enchantments.UNBREAKING.getMaxLevel()) {
                item.setDamage(0);
                cir.setReturnValue(true);
            }
            else if (!(ActuallyUnbreaking.instance.config.maxLevelOnly || ActuallyUnbreaking.instance.config.useUnbreakableAtLevel) && level > 0) {
                item.setDamage(0);
                cir.setReturnValue(true);
            }
        }
    }
}
