package com.cmdb26.cmdb26sqolmod.item;

import com.cmdb26.cmdb26sqolmod.Cmdb26sQOLMod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {

    RUBY("ruby", new float[] {327.0f, 87.5f, 71.166666f, 90.66666f},
            new int[] { 2, 5, 6, 2 }, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 6f, 0.0f, () -> {
        return Ingredient.fromItems(ModItems.RUBY.get());
    }),

    EMERALD("emerald", new float[] {455.0f, 131.25f, 92.5f, 128.4f}, new int[] { 3, 6, 8, 3 }, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f, 0.2f, () -> {
        return Ingredient.fromItems(Items.EMERALD);
    }),

    NETHER_STAR("nether_star", new float[] {782.0f, 218.75f, 166.72f, 219.0f}, new int[] { 3, 6, 8, 3 }, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 6f, 0.2f, () -> {
        return Ingredient.fromItems(Items.NETHER_STAR);
    });

    private static final int[] BASE_DURABILITY = new int[]{1, 4, 6, 3};
    private final String name;
    private final float[] maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;

    private ModArmorMaterial(String name, float[] maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue<>(() -> Ingredient.fromItems(ModItems.RUBY.get()));
    }

    public int getDurability(EquipmentSlotType slot) {
        return (int)(BASE_DURABILITY[slot.getIndex()] * this.maxDamageFactor[slot.getIndex()]);
    }

    public int getDamageReductionAmount(EquipmentSlotType slot) {
        return this.damageReductionAmountArray[slot.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public Ingredient getRepairMaterial() {
        return (Ingredient)this.repairMaterial.getValue();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return Cmdb26sQOLMod.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}