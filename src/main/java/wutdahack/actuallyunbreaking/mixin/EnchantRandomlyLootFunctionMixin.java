package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(EnchantRandomlyLootFunction.class)
public abstract class EnchantRandomlyLootFunctionMixin {

    boolean isBook;
    ItemStack lootStack;

    @ModifyVariable(method = "process", name = "list", at = @At(value = "STORE"))
    private List<Enchantment> filterMending(List<Enchantment> list) {
        list = Registry.ENCHANTMENT.stream().filter(Enchantment::isAvailableForRandomSelection).filter(enchantment -> isBook || enchantment.isAcceptableItem(lootStack)).filter(
                enchantment -> {
                    if (ActuallyUnbreaking.getInstance().config.editEnchantedLootGeneration) {
                        if (EnchantmentHelper.getLevel(Enchantments.UNBREAKING, lootStack) > 0) {
                            return enchantment != Enchantments.MENDING;
                        } else if (EnchantmentHelper.getLevel(Enchantments.MENDING, lootStack) > 0) {
                            return enchantment != Enchantments.UNBREAKING;
                        }
                    }
                    return true;
                }
        ).collect(Collectors.toList());
        return list;
    }

    @ModifyVariable(method = "process", name = "bl", at = @At(value = "LOAD"))
    private boolean setIsBook(boolean value) {
        return isBook = value;
    }

    @ModifyVariable(method = "process", ordinal = 0, at = @At(value = "HEAD"), argsOnly = true)
    private ItemStack setLootStack(ItemStack stack) {
        return lootStack = stack;
    }
}