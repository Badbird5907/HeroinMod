package dev.badbird.heroinmod.items;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HeroinItem extends Item {
    private static final Text NAME = Text.of("Heroin");
    public HeroinItem(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName() {
        return NAME;
    }

    @Override
    public Text getName(ItemStack stack) {
        return NAME;
    }

    @Override
    public SoundEvent getEatSound() {
        return super.getEatSound();
    }

    @Override
    public boolean isFood() {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
}
