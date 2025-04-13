package com.cmdb26.cmdb26sqolmod.client;

import com.cmdb26.cmdb26sqolmod.client.renderer.WolfArmorLayer;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "cmdb26sqolmod", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityType.WOLF, renderManager -> {
            WolfRenderer renderer = new WolfRenderer(renderManager);
            renderer.addLayer(new WolfArmorLayer(renderer));
            return renderer;
        });
    }
}
