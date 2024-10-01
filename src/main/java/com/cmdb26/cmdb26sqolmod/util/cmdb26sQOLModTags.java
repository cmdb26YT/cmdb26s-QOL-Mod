package com.cmdb26.cmdb26sqolmod.util;

import com.cmdb26.cmdb26sqolmod.Cmdb26sQOLMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class cmdb26sQOLModTags {

    public static class Blocks {

        public static final Tags.IOptionalNamedTag<Block> RUBY_ORE = createTag("ruby_ore");

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Cmdb26sQOLMod.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }

    }
    public static class Items {

        public static final Tags.IOptionalNamedTag<Item> RUBY = createForgeTag("gems/ruby");

        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Cmdb26sQOLMod.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}
