package wutdahack.actuallyunbreaking.mixin;

import amymialee.noenchantcap.NoEnchantCap;
import java.util.Random;
import net.fabricmc.loader.api.FabricLoader;
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

@Mixin(UnbreakingEnchantment.class)
abstract class UnbreakingEnchantmentMixin extends Enchantment {

    // items can't have mending and unbreaking together
    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof MendingEnchantment) && super.canAccept(other);
    }

    @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
    public void getMaxLevel(CallbackInfoReturnable<Integer> ret) {
        if (AUConfig.instance.maxLevelOnly) {
            if (FabricLoader.getInstance().isModLoaded("noenchantcap")) {
                ret.setReturnValue(NoEnchantCap.config.unbreakingCap);
            }
        } else {
            ret.setReturnValue(1);
        }
    }

    @Inject(method = "shouldPreventDamage", at = @At("HEAD"), cancellable = true)
    private static void makeUnbreakable(ItemStack item, int level, Random random, CallbackInfoReturnable<Boolean> ret) {
        if (AUConfig.instance.maxLevelOnly ? level >= Enchantments.UNBREAKING.getMaxLevel() : level > 0) {
            item.setDamage(0);
            ret.setReturnValue(true);
        }
    }

    protected UnbreakingEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

}
