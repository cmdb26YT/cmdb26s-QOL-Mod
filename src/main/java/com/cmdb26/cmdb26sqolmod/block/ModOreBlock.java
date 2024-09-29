package com.cmdb26.cmdb26sqolmod.block;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import static com.cmdb26.cmdb26sqolmod.block.ModBlocks.RUBY_ORE;

public class ModOreBlock extends Block {
    public ModOreBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        if (silktouch == 0) {  // If Silk Touch is not applied
            Random rand = new Random();
            if (this == RUBY_ORE.get()) {  // Check if block is Ruby Ore
                int baseXp = MathHelper.nextInt(rand, 2, 5);  // Generate random XP between 2 and 5
                return baseXp;
            }
        }
        return 0;  // No XP drop if Silk Touch is applied
    }
}










