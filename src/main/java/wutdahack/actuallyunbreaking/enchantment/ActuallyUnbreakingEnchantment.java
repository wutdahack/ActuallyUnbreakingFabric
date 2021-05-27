package wutdahack.actuallyunbreaking.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

// this enchantment is an UnbreakingEnchantment like enchantment
public class ActuallyUnbreakingEnchantment extends Enchantment {

    public ActuallyUnbreakingEnchantment(Rarity rarityIn, EnchantmentTarget typeIn, EquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }

    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    public int getMaxLevel() {
        return 1;
    }

    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isDamageable() || super.isAcceptableItem(stack);
    }


}
