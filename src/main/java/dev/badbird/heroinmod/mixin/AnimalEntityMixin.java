package dev.badbird.heroinmod.mixin;

import dev.badbird.heroinmod.HeroinMod;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnimalEntity.class)
public class AnimalEntityMixin {
    @Inject(at = @At("HEAD"), method = "isBreedingItem", cancellable = true)
    public void isBreedingItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(HeroinMod.HEROIN)) {
            cir.setReturnValue(true);
        }
    }

}
