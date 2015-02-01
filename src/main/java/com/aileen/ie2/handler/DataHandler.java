package com.aileen.ie2.handler;

import com.aileen.ie2.configuration.ConfigurationHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

/**
 * Created by Aileen on 25.01.2015.
 */
public class DataHandler {

    //Blocks
    public static Block block_Selenium_Ore;
    public static Block block_Topas_Ore;
    public static Block block_Amethyst_Ore;
    public static Block block_Sapphire_Ore;
    public static Block block_Ruby_Ore;
    public static Block block_Selenium;
    public static Block block_Tank;
    public static Block block_MiningLight;
    public static Block block_ToolExtractor;

    //Items
    public static Item item_ingotSelenium;
    public static Item item_dustSelenium;
    public static Item item_gemRuby;
    public static Item item_gemSapphire;
    public static Item item_gemTopas;
    public static Item item_gemAmethyst;
    public static Item item_pickaxeSelenium;
    public static Item item_pickaxeTopas;
    public static Item item_pickaxeSapphire;
    public static Item item_pickaxeRuby;
    public static Item item_pickaxeAmethyst;

    //Materials

    public static Item.ToolMaterial SELENIUM;
    public static Item.ToolMaterial TOPAS;
    public static Item.ToolMaterial AMETHYST;
    public static Item.ToolMaterial SAPPHIRE;
    public static Item.ToolMaterial RUBY;
    public static Item.ToolMaterial TITANIUM;

    //Icons
    public static IIcon torchFX;
    public static IIcon miningLight;

    //Worldgen
    public static int worldgen_ore_multiplier = ConfigurationHandler.getOreValue("worldgen_ore_multiplier", 5,"Ore Generation Multiplier -> (multiplier * ore_vpc) = max generated veins per Chunk. Default 5");

    public static int worldgen_block_Selenium_Ore_maxheight = ConfigurationHandler.getOreValue("worldgen_block_Selenium_Ore_maxheight", 15,"Which should be the highest Level to find the Ore? Default 15");
    public static int worldgen_block_Selenium_Ore_vpc = ConfigurationHandler.getOreValue("worldgen_block_Selenium_Ore_vpc", 3, "Max Veins per Chunk -> is multiplied with the Ore Multiplier. To dissable an ore world generation just set this to 0. Default 3");
    public static int worldgen_block_Selenium_Ore_vs = ConfigurationHandler.getOreValue("worldgen_block_Selenium_Ore_vs", 3, "Veinsize. How much res should be in a single Vein? Default 3");

    public static int worldgen_block_Topas_Ore_maxheight = ConfigurationHandler.getOreValue("worldgen_block_Topas_Ore_maxheight", 64,"Which should be the highest Level to find the Ore? Default 64");
    public static int worldgen_block_Topas_Ore_vpc = ConfigurationHandler.getOreValue("worldgen_block_Topas_Ore_vpc", 20, "Max Veins per Chunk -> is multiplied with the Ore Multiplier. To dissable an ore world generation just set this to 0. Default 20");
    public static int worldgen_block_Topas_Ore_vs = ConfigurationHandler.getOreValue("worldgen_block_Topas_Ore_vs", 5, "Veinsize. How much ores should be in a single Vein? Default 5");

    public static int worldgen_block_Amethyst_Ore_maxheight = ConfigurationHandler.getOreValue("worldgen_block_Amethyst_Ore_maxheight", 40,"Which should be the highest Level to find the Ore? Default 40");
    public static int worldgen_block_Amethyst_Ore_vpc = ConfigurationHandler.getOreValue("worldgen_block_Amethyst_Ore_vpc", 10, "Max Veins per Chunk -> is multiplied with the Ore Multiplier. To dissable an ore world generation just set this to 0. Default 10");
    public static int worldgen_block_Amethyst_Ore_vs = ConfigurationHandler.getOreValue("worldgen_block_Amethyst_Ore_vs", 4, "Veinsize. How much ores should be in a single Vein? Default 4");

    public static int worldgen_block_Sapphire_Ore_maxheight = ConfigurationHandler.getOreValue("worldgen_block_Sapphire_Ore_maxheight", 25,"Which should be the highest Level to find the Ore? Default 25");
    public static int worldgen_block_Sapphire_Ore_vpc = ConfigurationHandler.getOreValue("worldgen_block_Sapphire_Ore_vpc", 8, "Max Veins per Chunk -> is multiplied with the Ore Multiplier. To dissable an ore world generation just set this to 0. Default 8");
    public static int worldgen_block_Sapphire_Ore_vs = ConfigurationHandler.getOreValue("worldgen_block_Sapphire_Ore_vs", 3, "Veinsize. How much ores should be in a single Vein? Default 3");

    public static int worldgen_block_Ruby_Ore_maxheight = ConfigurationHandler.getOreValue("worldgen_block_Ruby_Ore_maxheight", 20,"Which should be the highest Level to find the Ore? Default 20");
    public static int worldgen_block_Ruby_Ore_vpc = ConfigurationHandler.getOreValue("worldgen_block_Ruby_Ore_vpc", 6, "Max Veins per Chunk -> is multiplied with the Ore Multiplier. To dissable an ore world generation just set this to 0. Default 6");
    public static int worldgen_block_Ruby_Ore_vs = ConfigurationHandler.getOreValue("worldgen_block_Ruby_Ore_vs", 4, "Veinsize. How much ores should be in a single Vein? Default 4");

    public static void init() {

    }
}
