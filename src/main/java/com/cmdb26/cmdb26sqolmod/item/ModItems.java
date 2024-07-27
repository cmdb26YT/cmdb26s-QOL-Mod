package com.cmdb26.cmdb26sqolmod.item;

import com.cmdb26.cmdb26sqolmod.Cmdb26sQOLMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Cmdb26sQOLMod.MOD_ID);


    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties().group(ModItemGroup.mod_tab_1)));

//    public static final RegistryObject<Item>

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
