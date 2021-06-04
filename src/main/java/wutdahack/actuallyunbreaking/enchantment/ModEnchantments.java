package wutdahack.actuallyunbreaking.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {

    public static final Enchantment UNBREAKING = new ActuallyUnbreakingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.BREAKABLE, EquipmentSlot.values() //this may be the fix to the armour problem
    );

    // overriding minecraft's enchantment because the UnbreakingEnchantment constructor is protected
    public static void registerEnchantments() {
        Registry.register(Registry.ENCHANTMENT, Registry.ENCHANTMENT.getRawId(Enchantments.UNBREAKING), "unbreaking", UNBREAKING);
    }


}
