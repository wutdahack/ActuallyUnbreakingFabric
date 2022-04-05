package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "damage(ILjava/util/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z", at = @At(value = "HEAD"))
    private void makeUnbreakable(int amount, Random random, ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {

        if (ActuallyUnbreaking.instance.config.useUnbreakableTag) {

            int unbreakingLevel = EnchantmentHelper.getLevel(Enchantments.UNBREAKING, (ItemStack) (Object) this); // get unbreaking level

            if (ActuallyUnbreaking.instance.config.useUnbreakableAtLevel) { // if tool has unbreaking
                if (unbreakingLevel >= ActuallyUnbreaking.instance.config.unbreakableAtLevel) {
                    addUnbreakableTag((ItemStack) (Object) this);
                }
            } else if (ActuallyUnbreaking.instance.config.maxLevelOnly) {
                if (unbreakingLevel >= Enchantments.UNBREAKING.getMaxLevel()) {
                    addUnbreakableTag((ItemStack) (Object) this);
                }
            } else if (unbreakingLevel > 0) {
                addUnbreakableTag((ItemStack)(Object) this);
            }
        }
    }

    private void addUnbreakableTag(ItemStack item) {

        int mendingLevel = EnchantmentHelper.getLevel(Enchantments.MENDING, (ItemStack) (Object) this); // get mending level

        item.getOrCreateTag().putBoolean("Unbreakable", true); // add the unbreakable tag
        item.setDamage(0); // set item damage to 0 to remove the tool's durability bar

        Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.get(item).entrySet().stream()
                .filter(entry -> entry.getKey() != Enchantments.UNBREAKING) // remove unbreaking from the map
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (mendingLevel > 0) { // if tool has mending
            enchantmentMap = enchantmentMap.entrySet().stream()
                    .filter(entry -> entry.getKey() != Enchantments.MENDING) // remove mending from the map
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                }

        EnchantmentHelper.set(
                enchantmentMap,
                item
        ); // use the enchantment map on the tool
    }
}