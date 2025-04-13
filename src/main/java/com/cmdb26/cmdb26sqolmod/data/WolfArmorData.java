package com.cmdb26.cmdb26sqolmod.data;

import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WolfArmorData {
    private static final Map<UUID, ItemStack> armorMap = new HashMap<>();

    public static void setArmor(WolfEntity wolf, ItemStack stack) {
        if (stack.isEmpty()) {
            armorMap.remove(wolf.getUniqueID());
        } else {
            armorMap.put(wolf.getUniqueID(), stack.copy());
        }
    }

    public static ItemStack getArmor(WolfEntity wolf) {
        return armorMap.getOrDefault(wolf.getUniqueID(), ItemStack.EMPTY);
    }
}

