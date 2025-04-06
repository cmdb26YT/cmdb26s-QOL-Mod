package com.cmdb26.cmdb26sqolmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.server.MinecraftServer;
import com.cmdb26.cmdb26sqolmod.world.dimension.QuarryTeleporter;
import com.cmdb26.cmdb26sqolmod.world.dimension.ModDimensions;

public class QuarryBlock extends Block {
    public QuarryBlock(Properties properties) {
        super(properties);
    }
    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            if (!player.isCrouching()) {
                MinecraftServer server = worldIn.getServer();

                if (server != null) {
                    if (worldIn.getDimensionKey() == ModDimensions.QUARRY) {
                        ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                        if (overWorld != null) {
                            player.changeDimension(overWorld, new QuarryTeleporter(pos, false));
                        }
                    } else {
                        ServerWorld quarryWorld = server.getWorld(ModDimensions.QUARRY);
                        if (quarryWorld != null) {
                            player.changeDimension(quarryWorld, new QuarryTeleporter(pos, true));
                        }
                    }
                    return ActionResultType.SUCCESS;
                }
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
