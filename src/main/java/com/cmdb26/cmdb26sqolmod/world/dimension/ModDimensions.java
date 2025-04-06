package com.cmdb26.cmdb26sqolmod.world.dimension;

import com.cmdb26.cmdb26sqolmod.Cmdb26sQOLMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ModDimensions {
    public static RegistryKey<World> QUARRY = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
            new ResourceLocation(Cmdb26sQOLMod.MOD_ID, "quarry"));
}
