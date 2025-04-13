package com.cmdb26.cmdb26sqolmod.events;

import com.cmdb26.cmdb26sqolmod.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.util.SoundEvents;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(modid = "cmdb26sqolmod")
public class WolfArmorEventHandler {
    public static final Map<UUID, ItemStack> WOLF_ARMOR_MAP = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onWolfInteract(PlayerInteractEvent.EntityInteract event) {
        Entity entity = event.getTarget();
        PlayerEntity player = event.getPlayer();
        ItemStack heldItem = event.getItemStack();

        if (!player.world.isRemote && entity instanceof WolfEntity) {
            WolfEntity wolf = (WolfEntity) entity;

            if (wolf.isTamed() && wolf.getOwner() == player) {

                if (isWolfArmor(heldItem)) {
                    // Check if the wolf already has armor
                    CompoundNBT persistentData = wolf.getPersistentData();
                    CompoundNBT wolfArmorData = persistentData.getCompound("WolfArmor");

                    if (wolfArmorData.contains("ArmorItem")) {
                        // Already armored, do nothing
                        return;
                    }

                    // Otherwise, apply the armor
                    CompoundNBT itemTag = new CompoundNBT();
                    heldItem.write(itemTag);
                    wolfArmorData.put("ArmorItem", itemTag);
                    persistentData.put("WolfArmor", wolfArmorData);

                    WOLF_ARMOR_MAP.put(wolf.getUniqueID(), heldItem.copy());

                    if (!player.isCreative()) {
                        heldItem.shrink(1);
                    }

                    wolf.world.playSound(null, wolf.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, SoundCategory.NEUTRAL, 1.0F, 1.0F);

                    event.setCanceled(true);
                    event.setCancellationResult(ActionResultType.SUCCESS);
                }
            }
        }
    }
    private static boolean isWolfArmor(ItemStack stack) {
        return stack.getItem() == ModItems.WOLF_ARMOR.get();
    }


    @SubscribeEvent
    public static void onWolfJoinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();

        if (!entity.world.isRemote && entity instanceof WolfEntity) {
            WolfEntity wolf = (WolfEntity) entity;
            CompoundNBT persistentData = wolf.getPersistentData();

            if (persistentData.contains("WolfArmor")) {
                CompoundNBT wolfArmorData = persistentData.getCompound("WolfArmor");

                if (wolfArmorData.contains("ArmorItem")) {
                    CompoundNBT itemTag = wolfArmorData.getCompound("ArmorItem");
                    ItemStack armorItem = ItemStack.read(itemTag);

                    // Store it in the tracking map
                    WOLF_ARMOR_MAP.put(wolf.getUniqueID(), armorItem);
                }
            }
        }
    }
    @SubscribeEvent
    public static void onWolfDamaged(LivingHurtEvent event) {
        if (!(event.getEntityLiving() instanceof WolfEntity)) return;

        WolfEntity wolf = (WolfEntity) event.getEntityLiving();

        // Check if this wolf has armor equipped
        ItemStack armor = WOLF_ARMOR_MAP.get(wolf.getUniqueID());
        if (armor == null || armor.isEmpty()) return;

        // Simple protection logic
        float damage = event.getAmount();
        float reduced = damage;

        if (armor.getItem() == ModItems.WOLF_ARMOR.get()) {
            reduced *= 0.05f; // 95% damage reduction
        }

        event.setAmount(reduced);
    }
}
