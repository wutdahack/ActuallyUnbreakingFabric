package wutdahack.actuallyunbreaking.enchantment;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import wutdahack.actuallyunbreaking.AUConfig;

import java.util.Random;

// this enchantment is an UnbreakingEnchantment like enchantment
public class ActuallyUnbreakingEnchantment extends Enchantment {

    static AUConfig config = AutoConfig.getConfigHolder(AUConfig.class).getConfig();

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
        if (config.level3Only) {
            return 3;
        } else if (!config.level3Only) {
            return 1;
        }
        return 1;
    }

    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isDamageable() || super.isAcceptableItem(stack);
    }

    // items can't have mending and unbreaking together
    public boolean canAccept(Enchantment other) {
        return !(other instanceof MendingEnchantment) && super.canAccept(other);
    }

    public static boolean preventDamage(ItemStack stack, Random random) {

        int level = EnchantmentHelper.getLevel(ModEnchantments.UNBREAKING, stack);

        if (config.level3Only) {
            if (level == 3) {
                return true;
            } else if (level < 3) {
                UnbreakingEnchantment.shouldPreventDamage(stack, level, random);
            }

        }  else if (!config.level3Only && level > 0) {
            return true;
        }

        return false;
    }


}
