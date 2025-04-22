package com.cmdb26.cmdb26sqolmod.network;

import com.cmdb26.cmdb26sqolmod.events.WolfArmorEventHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class WolfArmorSyncPacket {
    private final UUID wolfId;
    private final ItemStack armor;

    public WolfArmorSyncPacket(UUID wolfId, ItemStack armor) {
        this.wolfId = wolfId;
        this.armor = armor;
    }

    public static void encode(WolfArmorSyncPacket msg, PacketBuffer buf) {
        buf.writeUniqueId(msg.wolfId);
        buf.writeItemStack(msg.armor);
    }

    public static WolfArmorSyncPacket decode(PacketBuffer buf) {
        return new WolfArmorSyncPacket(buf.readUniqueId(), buf.readItemStack());
    }

    public static void handle(WolfArmorSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> {
                WolfArmorEventHandler.WOLF_ARMOR_MAP.put(msg.wolfId, msg.armor);
                System.out.println(">>> Synced wolf armor to client: " + msg.armor.getItem().getRegistryName());
            });
        });
        ctx.get().setPacketHandled(true);
    }
}
