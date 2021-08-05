package wutdahack.actuallyunbreaking.mixin;

import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

@Mixin(ItemStack.class)
abstract class ItemStackMixin {
    @ModifyVariable(method = "damage(ILjava/util/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z",
                    at = @At(value = "STORE", ordinal = 0, opcode = Opcodes.ISTORE), ordinal = 1) // this injects after `i = EnchantmentHelper.getLevel(Enchantments.UNBREAKING, this);`
    public int repairUnbreakableItem(int level, int amount, Random random, ServerPlayerEntity player) {
        if (ActuallyUnbreaking.isUnbreakable(level)) {
            ((ItemStack) (Object) this).setDamage(0);
        }

        return level;
    }
}
