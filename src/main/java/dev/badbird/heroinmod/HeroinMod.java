package dev.badbird.heroinmod;

import dev.badbird.heroinmod.effect.HeroinEffect;
import dev.badbird.heroinmod.effect.LowEffect;
import dev.badbird.heroinmod.effect.OverdoseEffect;
import dev.badbird.heroinmod.items.HeroinItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HeroinMod implements ModInitializer {
    public static final HeroinEffect HEROIN_EFFECT = Registry.register(Registry.STATUS_EFFECT, new Identifier("heroinmod", "heroin_effect"), new HeroinEffect());
    public static final Item HEROIN = Registry.register(Registry.ITEM, new Identifier("heroinmod", "heroin"),
            new HeroinItem(new FabricItemSettings().group(ItemGroup.FOOD)
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(10f).snack()
                            .alwaysEdible()
                            .meat()
                            .statusEffect(
                                    new StatusEffectInstance(
                                            HEROIN_EFFECT,
                                            20 * 10
                                    ),
                                    1.0f
                            )
                            .build())));
    public static final LowEffect LOW_EFFECT = Registry.register(Registry.STATUS_EFFECT, new Identifier("heroinmod", "low_effect"), new LowEffect());
    public static final OverdoseEffect OVERDOSE_EFFECT = Registry.register(Registry.STATUS_EFFECT, new Identifier("heroinmod", "overdose_effect"), new OverdoseEffect());

    private static long firstDose = -1;
    private static int doses = 0;

    @Override
    public void onInitialize() {
    }

    public static void onDose() {
        // if they take 5 doses in 1 minute, they overdose
        if (firstDose == -1) {
            firstDose = System.currentTimeMillis();
        }
        doses++;
        if (System.currentTimeMillis() - firstDose < 60 * 1000) {
            System.out.println("Dosage: " + doses + " in " + (System.currentTimeMillis() - firstDose) / 1000 + " seconds");
            if (doses >= 5) {
                System.out.println("Overdose!");
                MinecraftClient.getInstance().player.addStatusEffect(OverdoseEffect.getEffectInstance());
                firstDose = -1;
                doses = 0;
            }
        } else {
            firstDose = -1;
            doses = 0;
        }
    }
}
