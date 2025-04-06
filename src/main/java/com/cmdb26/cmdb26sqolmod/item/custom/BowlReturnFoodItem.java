package com.cmdb26.cmdb26sqolmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class BowlReturnFoodItem extends Item {
    public BowlReturnFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack result = super.onItemUseFinish(stack, worldIn, entityLiving);

        // Check if the entity is a player
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            // Only give a bowl in survival mode
            if (!player.abilities.isCreativeMode) {
                // If the item was fully consumed, return a new bowl
                if (stack.isEmpty()) {
                    return new ItemStack(Items.BOWL);
                } else {
                    // If not fully consumed, try adding a bowl to the player's inventory
                    if (!worldIn.isRemote) {
                        ItemStack bowlStack = new ItemStack(Items.BOWL);
                        if (!player.inventory.addItemStackToInventory(bowlStack)) {
                            // If inventory is full, manually spawn the bowl item into the world.
                            ItemEntity bowlEntity = new ItemEntity(worldIn, player.getPosX(), player.getPosY(), player.getPosZ(), bowlStack);
                            worldIn.addEntity(bowlEntity);
                        }
                    }
                }
            }
        }

        // Always return the consumption result if no special handling was needed.
        return result;
    }}