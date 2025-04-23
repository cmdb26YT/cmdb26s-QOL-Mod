package com.cmdb26.cmdb26sqolmod.events;

import com.cmdb26.cmdb26sqolmod.item.ModItems;
import com.cmdb26.cmdb26sqolmod.network.WolfArmorNetwork;
import com.cmdb26.cmdb26sqolmod.network.WolfArmorSyncPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.PacketDistributor;

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

            if (!wolf.isTamed() || wolf.getOwner() != player) return;

            CompoundNBT persistentData = wolf.getPersistentData();
            CompoundNBT wolfArmorData = persistentData.getCompound("WolfArmor");

            // Shift + Right-click = Unequip armor (regardless of hand contents)
            if (player.isSneaking() && wolfArmorData.contains("ArmorItem")) {
                CompoundNBT itemTag = wolfArmorData.getCompound("ArmorItem");
                ItemStack storedArmor = ItemStack.read(itemTag);

                if (!player.isCreative()) {
                    // Return the item to player or drop it
                    if (!player.inventory.addItemStackToInventory(storedArmor)) {
                        wolf.entityDropItem(storedArmor, 0.5f);
                    }
                }

                // Clear armor data either way
                wolfArmorData.remove("ArmorItem");
                persistentData.put("WolfArmor", wolfArmorData);
                WOLF_ARMOR_MAP.remove(wolf.getUniqueID());

                WolfArmorNetwork.sendToTracking(wolf, new WolfArmorSyncPacket(wolf.getUniqueID(), ItemStack.EMPTY));



                wolf.world.playSound(null, wolf.getPosition(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1.0F, 1.0F);

                event.setCanceled(true);
                event.setCancellationResult(ActionResultType.SUCCESS);
                return;
            }

            //  Equip armor if not already armored
            if (isWolfArmor(heldItem) && !wolfArmorData.contains("ArmorItem")) {
                CompoundNBT itemTag = new CompoundNBT();
                heldItem.write(itemTag);
                wolfArmorData.put("ArmorItem", itemTag);
                persistentData.put("WolfArmor", wolfArmorData);

                WOLF_ARMOR_MAP.put(wolf.getUniqueID(), heldItem.copy());

                WolfArmorNetwork.sendToTracking(wolf, new WolfArmorSyncPacket(wolf.getUniqueID(), heldItem.copy()));


                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }

                wolf.world.playSound(null, wolf.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, SoundCategory.NEUTRAL, 1.0F, 1.0F);

                event.setCanceled(true);
                event.setCancellationResult(ActionResultType.SUCCESS);
            }
        }
    }

    private static boolean isWolfArmor(ItemStack stack) {
        return stack.getItem() == ModItems.WOLF_ARMOR.get();
    }


    @SubscribeEvent
    public static void onWolfJoinWorld(EntityJoinWorldEvent event) {
        if (!(event.getEntity() instanceof WolfEntity)) return;

        WolfEntity wolf = (WolfEntity) event.getEntity();
        CompoundNBT persistentData = wolf.getPersistentData();

        if (persistentData.contains("WolfArmor")) {
            CompoundNBT wolfArmorData = persistentData.getCompound("WolfArmor");

            if (wolfArmorData.contains("ArmorItem")) {
                CompoundNBT itemTag = wolfArmorData.getCompound("ArmorItem");
                ItemStack armorItem = ItemStack.read(itemTag);

                // Only send to clients; don't store in server map
                WolfArmorNetwork.sendToTracking(wolf, new WolfArmorSyncPacket(wolf.getUniqueID(), armorItem));
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
            reduced *= 0.10f; // 95% damage reduction
        }

        event.setAmount(reduced);
    }
    @SubscribeEvent
    public static void onWolfDeath(LivingDeathEvent event) {
        if (!(event.getEntityLiving() instanceof WolfEntity)) return;

        WolfEntity wolf = (WolfEntity) event.getEntityLiving();

        if (!wolf.isTamed()) return;

        ItemStack armor = WOLF_ARMOR_MAP.get(wolf.getUniqueID());
        if (armor == null || armor.isEmpty()) return;

        // Drop the armor in the world if on server side
        if (!wolf.world.isRemote) {
            ServerWorld world = (ServerWorld) wolf.world;
            ItemStack drop = armor.copy();
            ItemEntity entityItem = new ItemEntity(world,
                    wolf.getPosX(), wolf.getPosY(), wolf.getPosZ(), drop);
            world.addEntity(entityItem);
        }

        // Clean up from the tracking map
        WOLF_ARMOR_MAP.remove(wolf.getUniqueID());
    }
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayerEntity)) return;

        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        ServerWorld world = player.getServerWorld();

        for (WolfEntity wolf : world.getEntitiesWithinAABB(WolfEntity.class, player.getBoundingBox().grow(256))) {
            CompoundNBT persistentData = wolf.getPersistentData();

            if (persistentData.contains("WolfArmor")) {
                CompoundNBT wolfArmorData = persistentData.getCompound("WolfArmor");

                if (wolfArmorData.contains("ArmorItem")) {
                    CompoundNBT itemTag = wolfArmorData.getCompound("ArmorItem");
                    ItemStack armorItem = ItemStack.read(itemTag);

                    // Send packet to just this player
                    WolfArmorNetwork.INSTANCE.send(
                            PacketDistributor.PLAYER.with(() -> player),
                            new WolfArmorSyncPacket(wolf.getUniqueID(), armorItem)
                    );
                }
            }
        }
    }
}
