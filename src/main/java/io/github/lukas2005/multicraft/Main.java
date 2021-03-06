package io.github.lukas2005.multicraft;

import io.github.lukas2005.multicraft.blocks.ModBlocks;
import io.github.lukas2005.multicraft.items.ModItems;
import io.github.lukas2005.multicraft.proxy.ClientProxy;
import io.github.lukas2005.multicraft.proxy.CommonProxy;
import io.github.lukas2005.multicraft.proxy.IProxy;
import io.github.lukas2005.multicraft.world.gen.OreGen;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.Random;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main {
    public static final String MODID = "multic";
    public static final String NAME = "MultiCraft";
    public static final String VERSION = "0.1.3";
    public static final String SERVER_PROXY = "io.github.lukas2005.multicraft.proxy.CommonProxy";
    public static final String CLIENT_PROXY = "io.github.lukas2005.multicraft.proxy.ClientProxy";
/*
    public static CreativeTabs tab_blocks = new CreativeTabs("mod_blocks") {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModBlocks.ADOBE_BRICK);
        }
    };

    public static CreativeTabs tab_food = new CreativeTabs("mod_food") {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.EMERALD_APPLE);
        }
    };

    public static CreativeTabs tab_tools = new CreativeTabs("mod_tools") {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.IRON_SHIELD);
        }
    };
*/
    public static CreativeTabs tab = new CreativeTabs("mod_all") {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.TAB_ICON);
        }
    };

    @SidedProxy(serverSide = SERVER_PROXY, clientSide = CLIENT_PROXY)
    public static ClientProxy proxy;
    public static IProxy proxy2;

    public static final Random random = new Random(); // "random" > final, lol
    public static ArrayList<Entity[]> attachedMinecarts = new ArrayList<>();

    @Mod.Instance(MODID)
    public static Main instance;
    private static NonNullList<Ingredient> ingredients;
    private static ItemStack output;
    private static String group;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent e) {
        GameRegistry.registerWorldGenerator(new OreGen(), 3);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent e) {
        ModRecipes.init();
        ColoredWoodRecipe.init();
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent e) {
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModBlocks.registerItems(event.getRegistry());
            ModItems.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }
    }

    public static final ItemArmor.ArmorMaterial furCostume = EnumHelper.addArmorMaterial("FUR_COSTUME", MODID + ":fur_costume", 6, new int[]{2, 4, 3, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f);
    public static final ItemArmor.ArmorMaterial polarHood = EnumHelper.addArmorMaterial("POLAR_HOOD", MODID + ":polar_bear_hood", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f);
}
