package com.cmdb26.cmdb26sqolmod.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class WolfArmorNetwork {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("cmdb26sqolmod", "wolf_sync"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int id = 0;

    public static void register() {
        CHANNEL.registerMessage(id++, WolfArmorSyncPacket.class,
                WolfArmorSyncPacket::encode,
                WolfArmorSyncPacket::decode,
                WolfArmorSyncPacket::handle);
    }
}

