package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

import java.util.Map;
import java.util.stream.Collectors;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "hurt", at = @At(value = "HEAD"))
    private void makeUnbreakable(int amount, RandomSource random, ServerPlayer user, CallbackInfoReturnable<Boolean> cir) {

        if (ActuallyUnbreaking.instance.config.useUnbreakableTag) {

            int unbreakingLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, (ItemStack) (Object) this);

            if (ActuallyUnbreaking.instance.config.useUnbreakableAtLevel) {
                if (unbreakingLevel >= ActuallyUnbreaking.instance.config.unbreakableAtLevel) {
                    actuallyUnbreaking$addUnbreakableTag((ItemStack) (Object) this);
                }
            } else if (ActuallyUnbreaking.instance.config.maxLevelOnly) {
                if (unbreakingLevel >= Enchantments.UNBREAKING.getMaxLevel()) {
                    actuallyUnbreaking$addUnbreakableTag((ItemStack) (Object) this);
                }
            } else if (unbreakingLevel > 0) {
                actuallyUnbreaking$addUnbreakableTag((ItemStack)(Object) this);
            }
        }
    }

    @Unique
    private void actuallyUnbreaking$addUnbreakableTag(ItemStack item) {

        int mendingLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MENDING, (ItemStack) (Object) this);

        item.getOrCreateTag().putBoolean("Unbreakable", true); // add the unbreakable tag
        item.setDamageValue(0); // set item damage to 0 to remove the tool's durability bar

        Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.getEnchantments(item).entrySet().stream()
                .filter(entry -> entry.getKey() != Enchantments.UNBREAKING) // remove unbreaking from the map
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (mendingLevel > 0) { // if tool has mending
            enchantmentMap = enchantmentMap.entrySet().stream()
                    .filter(entry -> entry.getKey() != Enchantments.MENDING) // remove mending from the map
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                }

        EnchantmentHelper.setEnchantments(
                enchantmentMap,
                item
        ); // apply enchantment map to the tool
    }
}
