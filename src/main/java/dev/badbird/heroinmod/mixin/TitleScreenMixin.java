package dev.badbird.heroinmod.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin{
    /*
    change the splash text to "Heroin!!"
     */
    @Inject(
            method = "init()V",
            at = @At("TAIL")
    )
    public void init(CallbackInfo ci) {
        ((TitleScreenAccessor) this).setSplashText("Heroin!!!");
    }
}
