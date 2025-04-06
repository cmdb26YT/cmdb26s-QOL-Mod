package com.cmdb26.cmdb26sqolmod.item;

import com.cmdb26.cmdb26sqolmod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ModItemGroup {

    public static final ItemGroup mod_tab_1 = new ItemGroup("modtab1") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.RUBY.get());
        }

        @Override
        public void fill(NonNullList<ItemStack> items) {

            items.clear();

            items.add(new ItemStack(ModBlocks.RUBY_ORE.get()));
            items.add(new ItemStack(ModBlocks.RUBY_BLOCK.get()));
            items.add(new ItemStack(ModBlocks.NETHER_STAR_BLOCK.get()));
            items.add(new ItemStack(ModBlocks.QUARRY_BLOCK.get()));
            items.add(new ItemStack(ModItems.RUBY.get()));
            items.add(new ItemStack(ModItems.RUBY_SWORD.get()));
            items.add(new ItemStack(ModItems.RUBY_PICKAXE.get()));
            items.add(new ItemStack(ModItems.RUBY_AXE.get()));
            items.add(new ItemStack(ModItems.RUBY_SHOVEL.get()));
            items.add(new ItemStack(ModItems.RUBY_HOE.get()));
            items.add(new ItemStack(ModItems.RUBY_HELMET.get()));
            items.add(new ItemStack(ModItems.RUBY_CHESTPLATE.get()));
            items.add(new ItemStack(ModItems.RUBY_LEGGINGS.get()));
            items.add(new ItemStack(ModItems.RUBY_BOOTS.get()));
            items.add(new ItemStack(ModItems.EMERALD_SWORD.get()));
            items.add(new ItemStack(ModItems.EMERALD_PICKAXE.get()));
            items.add(new ItemStack(ModItems.EMERALD_AXE.get()));
            items.add(new ItemStack(ModItems.EMERALD_SHOVEL.get()));
            items.add(new ItemStack(ModItems.EMERALD_HOE.get()));
            items.add(new ItemStack(ModItems.EMERALD_HELMET.get()));
            items.add(new ItemStack(ModItems.EMERALD_CHESTPLATE.get()));
            items.add(new ItemStack(ModItems.EMERALD_LEGGINGS.get()));
            items.add(new ItemStack(ModItems.EMERALD_BOOTS.get()));
            items.add(new ItemStack(ModItems.NETHER_STAR_SWORD.get()));
            items.add(new ItemStack(ModItems.NETHER_STAR_PICKAXE.get()));
            items.add(new ItemStack(ModItems.NETHER_STAR_AXE.get()));
            items.add(new ItemStack(ModItems.NETHER_STAR_SHOVEL.get()));
            items.add(new ItemStack(ModItems.NETHER_STAR_HOE.get()));
            items.add(new ItemStack(ModItems.NETHER_STAR_HELMET.get()));
            items.add(new ItemStack(ModItems.NETHER_STAR_CHESTPLATE.get()));
            items.add(new ItemStack(ModItems.NETHER_STAR_LEGGINGS.get()));
            items.add(new ItemStack(ModItems.NETHER_STAR_BOOTS.get()));
        }
    };
}