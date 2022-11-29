package dev.badbird.heroinmod.effect;

import dev.badbird.heroinmod.HeroinMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;

import java.awt.*;

public class LowEffect extends StatusEffect {
    public LowEffect() {
        super(
                StatusEffectCategory.HARMFUL, // whether beneficial or harmful for entities
                Color.RED.getRGB()); // color in RGB
    }

    // This method is called every tick to check whether it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }


    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);

    }

    private static final int DURATION = 20 * 60 * 15; // 15 minutes
    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, DURATION, 2));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, DURATION, 2));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, DURATION));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, DURATION));
    }


    public static StatusEffectInstance getEffectInstance() {
        return new StatusEffectInstance(HeroinMod.LOW_EFFECT, 60 * 5 * 20, 1);
    }

    @Override
    public Text getName() {
        return Text.of("Withdrawl");
    }
}
