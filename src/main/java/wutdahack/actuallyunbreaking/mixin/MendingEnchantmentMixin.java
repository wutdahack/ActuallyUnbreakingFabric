package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

@Mixin(MendingEnchantment.class)
public abstract class MendingEnchantmentMixin extends Enchantment {

    private MendingEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        if (ActuallyUnbreaking.instance.config.useUnbreakableTag) {
            return stack.getNbt() != null && !stack.getNbt().getBoolean("Unbreakable"); // item is only acceptable if it doesn't have the unbreakable tag
        } else {
            return super.isAcceptableItem(stack);
        }
    }
}
