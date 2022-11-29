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

public class HeroinEffect extends StatusEffect {
    private int fovNormal;
    public HeroinEffect() {
        super(
                StatusEffectCategory.BENEFICIAL, // whether beneficial or harmful for entities
                Color.WHITE.getRGB()); // color in RGB
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
        entity.removeStatusEffect(StatusEffects.STRENGTH);
        entity.removeStatusEffect(StatusEffects.SPEED);
        entity.removeStatusEffect(StatusEffects.JUMP_BOOST);
        entity.removeStatusEffect(StatusEffects.RESISTANCE);

        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 60));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20 * 60));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 60));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 20 * 60));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20 * 60));

        if (!entity.hasStatusEffect(HeroinMod.HEROIN_EFFECT)) {
            // Add LowEffect
            entity.addStatusEffect(LowEffect.getEffectInstance());
        }
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        this.fovNormal = MinecraftClient.getInstance().options.getFov().getValue();
        // remove LowEffect
        entity.removeStatusEffect(HeroinMod.LOW_EFFECT);
        // set fov to max
        MinecraftClient.getInstance().options.getFov().setValue(110);
        entity.removeStatusEffect(StatusEffects.SLOWNESS);
        entity.removeStatusEffect(StatusEffects.MINING_FATIGUE);
        entity.removeStatusEffect(StatusEffects.NAUSEA);
        entity.removeStatusEffect(StatusEffects.HUNGER);
        entity.removeStatusEffect(StatusEffects.WEAKNESS);

        entity.removeStatusEffect(StatusEffects.STRENGTH);
        entity.removeStatusEffect(StatusEffects.SPEED);
        entity.removeStatusEffect(StatusEffects.JUMP_BOOST);
        entity.removeStatusEffect(StatusEffects.RESISTANCE);

        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 10, 2));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 10));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20 * 10, 2));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * 10, 4));

        HeroinMod.onDose();
    }

    @Override
    public Text getName() {
        return Text.of("High");
    }
}
