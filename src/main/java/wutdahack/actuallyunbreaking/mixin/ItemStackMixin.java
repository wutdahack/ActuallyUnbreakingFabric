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
    public void makeUnbreakable(int amount, Random random, ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {

        int unbreakingLevel = EnchantmentHelper.getLevel(Enchantments.UNBREAKING, (ItemStack) (Object) this); // get unbreaking level
        int mendingLevel = EnchantmentHelper.getLevel(Enchantments.MENDING, (ItemStack) (Object) this); // get mending level

        if (ActuallyUnbreaking.getInstance().config.useUnbreakableTag) {

            if (ActuallyUnbreaking.getInstance().config.maxLevelOnly ? unbreakingLevel >= Enchantments.UNBREAKING.getMaxLevel() : unbreakingLevel > 0) { // if tool has unbreaking

                ((ItemStack) (Object) this).getOrCreateTag().putBoolean("Unbreakable", true); // add the unbreakable tag
                ((ItemStack) (Object) this).setDamage(0); // set item damage to 0 to remove the tool's durability bar

                Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.get((ItemStack) (Object) this).entrySet().stream()
                        .filter(entry -> entry.getKey() != Enchantments.UNBREAKING) // remove unbreaking from the map
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                if (mendingLevel > 0) { // if tool has mending
                    enchantmentMap = enchantmentMap.entrySet().stream()
                            .filter(entry -> entry.getKey() != Enchantments.MENDING) // remove mending from the map
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                }

                EnchantmentHelper.set(
                        enchantmentMap,
                        (ItemStack) (Object) this
                ); // use the enchantment map on the tool
            }
        }
    }
}

