package com.cmdb26.cmdb26sqolmod.world;

import com.cmdb26.cmdb26sqolmod.world.gen.ModOreGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.cmdb26.cmdb26sqolmod.Cmdb26sQOLMod;

@Mod.EventBusSubscriber(modid = Cmdb26sQOLMod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModOreGeneration.generateOres(event);
//      ModTreeGeneration.generateTrees(event);
    }
}
