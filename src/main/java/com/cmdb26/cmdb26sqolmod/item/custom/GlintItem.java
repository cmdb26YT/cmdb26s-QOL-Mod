package com.cmdb26.cmdb26sqolmod.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GlintItem extends Item {
    public GlintItem(Properties props) {
        super(props);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;  // always render the enchantment glint
    }
}
