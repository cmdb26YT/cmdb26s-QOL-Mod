package com.cmdb26.cmdb26sqolmod.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;

public class WolfArmorNetwork {

        private static final String PROTOCOL_VERSION = "1";
        public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("cmdb26sqolmod", "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        private static int packetId = 0;

        private static int nextId() {
            return packetId++;
        }

        public static void register() {
            INSTANCE.registerMessage(
                    nextId(),
                    WolfArmorSyncPacket.class,
                    WolfArmorSyncPacket::encode,
                    WolfArmorSyncPacket::decode,
                    WolfArmorSyncPacket::handle,
                    Optional.of(NetworkDirection.PLAY_TO_CLIENT)
            );
        }
    public static void sendToTracking(Entity entity, Object message) {
        if (entity.world instanceof ServerWorld) {
            ServerWorld world = (ServerWorld) entity.world;
            for (ServerPlayerEntity player : world.getPlayers()) {
                if (player.getDistanceSq(entity) < 128 * 128) { // within 128 blocks
                    INSTANCE.sendTo(message, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
                }
            }
        }
    }
}


