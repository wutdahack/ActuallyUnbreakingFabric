package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.MendingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

@Mixin(MendingEnchantment.class)
public abstract class MendingEnchantmentMixin extends Enchantment {

    private MendingEnchantmentMixin(Rarity rarity, EnchantmentCategory enchantmentCategory, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentCategory, equipmentSlots);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        if (ActuallyUnbreaking.instance.config.useUnbreakableTag) {
            return stack.getTag() != null && !stack.getTag().getBoolean("Unbreakable"); // item is only acceptable if it doesn't have the unbreakable tag
        } else {
            return super.canEnchant(stack);
        }
    }
}
