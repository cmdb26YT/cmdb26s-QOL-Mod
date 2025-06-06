package com.cmdb26.cmdb26sqolmod.block;

import com.cmdb26.cmdb26sqolmod.Cmdb26sQOLMod;
import com.cmdb26.cmdb26sqolmod.block.custom.QuarryBlock;
import com.cmdb26.cmdb26sqolmod.item.ModItemGroup;
import com.cmdb26.cmdb26sqolmod.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, Cmdb26sQOLMod.MOD_ID);

    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore",
            () -> new ModOreBlock(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE)
                    .setRequiresTool().hardnessAndResistance(3f)));

    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON)
                    .sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)
                    .setRequiresTool().hardnessAndResistance(5f)));

    public static final RegistryObject<Block> NETHER_STAR_BLOCK = registerBlock("nether_star_block",
            () -> new Block(AbstractBlock.Properties.create(Material.ICE)
                    .sound(SoundType.GLASS).harvestLevel(2).harvestTool(ToolType.PICKAXE)
                    .setRequiresTool().hardnessAndResistance(7.5f)));

    public static final RegistryObject<Block> QUARRY_BLOCK = registerBlock("quarry_block",
            () -> new QuarryBlock(AbstractBlock.Properties.create(Material.IRON)
                    .sound(SoundType.BONE).harvestLevel(0).harvestTool(ToolType.PICKAXE)
                    .hardnessAndResistance(0.5f)));



    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.mod_tab_1)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
