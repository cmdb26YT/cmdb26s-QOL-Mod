package com.cmdb26.cmdb26sqolmod.item.custom;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModFoodEffects {
    public static final Food ENCHANTED_GOLDEN_CARROT_EFFECTS = new Food.Builder()
            // restores 4 hunger points (2 hunger icons) and 0 saturation
            .hunger(4)
            .saturation(0.0F)
            // allow eating at full hunger
            .setAlwaysEdible()

            // (S secs × 20 ticks = T ticks)

            // Saturation I for 5 seconds
            .effect(() -> new EffectInstance(Effects.SATURATION, 100, 0), 1.0F)
            .build();


}
