package dev.badbird.heroinmod.mixin;

import dev.badbird.heroinmod.HeroinMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TemptGoal.class)
public class TemptGoalMixin {
    @Inject(
            method = "isTemptedBy",
            at = @At("HEAD"),
            cancellable = true
    )
    public void isTemptedBy(LivingEntity entity, CallbackInfoReturnable<Boolean> cir){
        ItemStack stack = entity.getMainHandStack();
        if (stack.getItem() == HeroinMod.HEROIN) {
            cir.setReturnValue(true);
        }
    }

}
