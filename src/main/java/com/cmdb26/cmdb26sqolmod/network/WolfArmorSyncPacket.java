package com.cmdb26.cmdb26sqolmod.network;

import com.cmdb26.cmdb26sqolmod.events.WolfArmorEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class WolfArmorSyncPacket {

    private final UUID wolfId;
    private final ItemStack armorStack;

    public WolfArmorSyncPacket(UUID wolfId, ItemStack armorStack) {
        this.wolfId = wolfId;
        this.armorStack = armorStack;
    }

    public static void encode(WolfArmorSyncPacket msg, PacketBuffer buf) {
        buf.writeUniqueId(msg.wolfId);
        buf.writeItemStack(msg.armorStack);
    }

    public static WolfArmorSyncPacket decode(PacketBuffer buf) {
        UUID id = buf.readUniqueId();
        ItemStack stack = buf.readItemStack();
        return new WolfArmorSyncPacket(id, stack);
    }

    public static void handle(WolfArmorSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (Minecraft.getInstance().world == null || Minecraft.getInstance().player == null) return;

            WolfEntity wolf = null;

            for (WolfEntity w : Minecraft.getInstance().world.getEntitiesWithinAABB(WolfEntity.class,
                    Minecraft.getInstance().player.getBoundingBox().grow(64))) {
                if (w.getUniqueID().equals(msg.wolfId)) {
                    wolf = w;
                    break;
                }
            }

            if (wolf != null) {
                WolfArmorEventHandler.WOLF_ARMOR_MAP.put(msg.wolfId, msg.armorStack);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
