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

        public static final Tags.IOptionalNamedTag<Block> RUBY_ORE = createForgeTag("ores/ruby");

        public static final Tags.IOptionalNamedTag<Block> RUBY_BLOCK = createForgeTag("storage_blocks/ruby");

        public static final Tags.IOptionalNamedTag<Block> NETHER_STAR_BLOCK = createForgeTag("storage_blocks/nether_star");

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Cmdb26sQOLMod.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }

    }
    public static class Items {

        public static final Tags.IOptionalNamedTag<Item> RUBY = createForgeTag("gems/ruby");

        public static final Tags.IOptionalNamedTag<Item> APPLE_PIE = createForgeTag("foods/pie");

        public static final Tags.IOptionalNamedTag<Item> ROASTED_CARROT = createForgeTag("foods/vegetable");

        public static final Tags.IOptionalNamedTag<Item> MASHED_POTATO = createForgeTag("foods/vegetable");



        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Cmdb26sQOLMod.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}
