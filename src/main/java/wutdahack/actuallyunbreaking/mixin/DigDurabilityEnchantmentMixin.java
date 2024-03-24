package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DigDurabilityEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.MendingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

@Mixin(DigDurabilityEnchantment.class)
public abstract class DigDurabilityEnchantmentMixin extends Enchantment {


    private DigDurabilityEnchantmentMixin(Rarity rarity, EnchantmentCategory enchantmentCategory, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentCategory, equipmentSlots);
    }

    @Override
    protected boolean checkCompatibility(Enchantment other) {

        if (ActuallyUnbreaking.instance.config.mendingIncompatibility) {
            return !(other instanceof MendingEnchantment) && super.checkCompatibility(other); // mending with unbreaking is redundant
        } else {
            return super.checkCompatibility(other);
        }

    }

    @Inject(method = "canEnchant", at = @At(value = "HEAD"), cancellable = true)
    private void dontAcceptUnbreakableItems(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {

        if (ActuallyUnbreaking.instance.config.useUnbreakableTag) {
            cir.setReturnValue(stack.getTag() != null && !stack.getTag().getBoolean("Unbreakable")); // item can't have unbreaking if it has the unbreaking tag as that's also redundant
        }

    }

    @Inject(method = "shouldIgnoreDurabilityDrop", at = @At(value = "HEAD"), cancellable = true)
    private static void makeUnbreakable(ItemStack stack, int level, RandomSource random, CallbackInfoReturnable<Boolean> cir) {

        if (!ActuallyUnbreaking.instance.config.useUnbreakableTag) {
            if (ActuallyUnbreaking.instance.config.useOnlyUnbreakableAtLevel) {
                if (level == ActuallyUnbreaking.instance.config.onlyUnbreakableAtLevel) {
                    stack.setDamageValue(0); // set item damage to 0 to remove the tool's durability bar
                    cir.setReturnValue(true);
                }
            }
            else if (ActuallyUnbreaking.instance.config.useUnbreakableAtLevel) {
                if (level >= ActuallyUnbreaking.instance.config.unbreakableAtLevel) {
                    stack.setDamageValue(0);
                    cir.setReturnValue(true);
                }
            }
            else if (ActuallyUnbreaking.instance.config.maxLevelOnly) {
                if (level >= Enchantments.UNBREAKING.getMaxLevel()) {
                    stack.setDamageValue(0);
                    cir.setReturnValue(true);
                }
            }
            else if (level > 0) {
                stack.setDamageValue(0);
                cir.setReturnValue(true);
            }
        }

    }
}
