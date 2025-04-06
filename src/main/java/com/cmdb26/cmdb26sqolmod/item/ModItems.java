package com.cmdb26.cmdb26sqolmod.item;

import com.cmdb26.cmdb26sqolmod.Cmdb26sQOLMod;
import com.cmdb26.cmdb26sqolmod.item.custom.BowlReturnFoodItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Cmdb26sQOLMod.MOD_ID);


    //  Materials
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> APPLE_PIE = ITEMS.register("apple_pie",
            () -> new Item(new Item.Properties()
                    .food(new Food.Builder().hunger(8).saturation(0.3f).build())
                    .group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> ROASTED_CARROT = ITEMS.register("roasted_carrot",
            () -> new Item(new Item.Properties()
                    .food(new Food.Builder().hunger(7).saturation(0.57142857142f).build())
                    .group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> MASHED_POTATO = ITEMS.register("mashed_potato",
            () -> new BowlReturnFoodItem(new Item.Properties()
                    .food(new Food.Builder().hunger(6).saturation(0.4f).build())
                    .group(ItemGroup.FOOD)));

    //  Tools

    //      Ruby Tools
    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword",
            () -> new SwordItem(ModItemTier.RUBY, -1, -2f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe",
            () -> new PickaxeItem(ModItemTier.RUBY, -3, -2.8f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe",
            () -> new AxeItem(ModItemTier.RUBY, 1, -2.5f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel",
            () -> new ShovelItem(ModItemTier.RUBY, -2.5f, -3f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe",
            () -> new HoeItem(ModItemTier.RUBY, -6, 0f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    //      Emerald Tools
    public static final RegistryObject<Item> EMERALD_SWORD = ITEMS.register("emerald_sword",
            () -> new SwordItem(ModItemTier.EMERALD, 3, -2.8f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe",
            () -> new PickaxeItem(ModItemTier.EMERALD, -2, -2.8f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> EMERALD_AXE = ITEMS.register("emerald_axe",
            () -> new AxeItem(ModItemTier.EMERALD, 5.5f, -3.2f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> EMERALD_SHOVEL = ITEMS.register("emerald_shovel",
            () -> new ShovelItem(ModItemTier.EMERALD, -0.5f, -3f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> EMERALD_HOE = ITEMS.register("emerald_hoe",
            () -> new HoeItem(ModItemTier.EMERALD, -6, 0f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    //      Nether Star Tools
    public static final RegistryObject<Item> NETHER_STAR_SWORD = ITEMS.register("nether_star_sword",
            () -> new SwordItem(ModItemTier.NETHER_STAR, 3, -2f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> NETHER_STAR_PICKAXE = ITEMS.register("nether_star_pickaxe",
            () -> new PickaxeItem(ModItemTier.NETHER_STAR, -2, -2.8f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> NETHER_STAR_AXE = ITEMS.register("nether_star_axe",
            () -> new AxeItem(ModItemTier.NETHER_STAR, 5.5f, -2.5f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> NETHER_STAR_SHOVEL = ITEMS.register("nether_star_shovel",
            () -> new ShovelItem(ModItemTier.NETHER_STAR, -0.5f, -3f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> NETHER_STAR_HOE = ITEMS.register("nether_star_hoe",
            () -> new HoeItem(ModItemTier.NETHER_STAR, -6, 0f,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));


    //  Armor

    //      Ruby Armor
    public static final RegistryObject<Item> RUBY_HELMET = ITEMS.register("ruby_helmet",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.HEAD,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> RUBY_LEGGINGS = ITEMS.register("ruby_leggings",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.LEGS,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> RUBY_BOOTS = ITEMS.register("ruby_boots",
            () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.FEET,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    //      Emerald Armor
    public static final RegistryObject<Item> EMERALD_HELMET = ITEMS.register("emerald_helmet",
            () -> new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlotType.HEAD,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate",
            () -> new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings",
            () -> new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlotType.LEGS,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> EMERALD_BOOTS = ITEMS.register("emerald_boots",
            () -> new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlotType.FEET,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    //      Nether Star Armor
    public static final RegistryObject<Item> NETHER_STAR_HELMET = ITEMS.register("nether_star_helmet",
            () -> new ArmorItem(ModArmorMaterial.NETHER_STAR, EquipmentSlotType.HEAD,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> NETHER_STAR_CHESTPLATE = ITEMS.register("nether_star_chestplate",
            () -> new ArmorItem(ModArmorMaterial.NETHER_STAR, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> NETHER_STAR_LEGGINGS = ITEMS.register("nether_star_leggings",
            () -> new ArmorItem(ModArmorMaterial.NETHER_STAR, EquipmentSlotType.LEGS,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));

    public static final RegistryObject<Item> NETHER_STAR_BOOTS = ITEMS.register("nether_star_boots",
            () -> new ArmorItem(ModArmorMaterial.NETHER_STAR, EquipmentSlotType.FEET,
                    new Item.Properties().group(ModItemGroup.mod_tab_1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}