package com.aileen.ie2.block;

import com.aileen.ie2.block.TileBlocks.blockTank;
import com.aileen.ie2.block.TileBlocks.blockToolExtractor;
import com.aileen.ie2.block.others.MiningLight;
import com.aileen.ie2.handler.DataHandler;
import com.aileen.ie2.libs.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Aileen on 04.01.14.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    public static void init() {

        DataHandler.block_Tank = new blockTank("block_Tank");
        GameRegistry.registerBlock(DataHandler.block_Tank, "block_Tank");

        DataHandler.block_ToolExtractor = new blockToolExtractor("block_ToolExtractor");
        GameRegistry.registerBlock(DataHandler.block_ToolExtractor, "block_ToolExtractor");

        DataHandler.block_MiningLight = new MiningLight("block_MiningLight");
        GameRegistry.registerBlock(DataHandler.block_MiningLight, "block_MiningLight");

        DataHandler.block_Selenium_Ore = new BlockIE("block_Selenium_Ore");
        DataHandler.block_Selenium_Ore.setHardness(15);
        DataHandler.block_Selenium_Ore.setHarvestLevel("pickaxe", 3);
        GameRegistry.registerBlock(DataHandler.block_Selenium_Ore, "block_Selenium_Ore");
        OreDictionary.registerOre("oreSelenium", DataHandler.block_Selenium_Ore);

        DataHandler.block_Topas_Ore = new BlockIE("block_Topas_Ore");
        DataHandler.block_Topas_Ore.setHardness(5);
        DataHandler.block_Topas_Ore.setHarvestLevel("pickaxe", 1);
        GameRegistry.registerBlock(DataHandler.block_Topas_Ore, "block_Topas_Ore");
        OreDictionary.registerOre("oreTopas", DataHandler.block_Topas_Ore);

        DataHandler.block_Amethyst_Ore = new BlockIE("block_Amethyst_Ore");
        DataHandler.block_Amethyst_Ore.setHardness(7.5F);
        DataHandler.block_Amethyst_Ore.setHarvestLevel("pickaxe", 1);
        GameRegistry.registerBlock(DataHandler.block_Amethyst_Ore, "block_Amethyst_Ore");
        OreDictionary.registerOre("oreAmethyst", DataHandler.block_Amethyst_Ore);

        DataHandler.block_Sapphire_Ore = new BlockIE("block_Sapphire_Ore");
        DataHandler.block_Sapphire_Ore.setHardness(10);
        DataHandler.block_Sapphire_Ore.setHarvestLevel("pickaxe", 2);
        GameRegistry.registerBlock(DataHandler.block_Sapphire_Ore, "block_Sapphire_Ore");
        OreDictionary.registerOre("oreSapphire", DataHandler.block_Sapphire_Ore);

        DataHandler.block_Ruby_Ore = new BlockIE("block_Ruby_Ore");
        DataHandler.block_Ruby_Ore.setHardness(12.5F);
        DataHandler.block_Ruby_Ore.setHarvestLevel("pickaxe", 2);
        GameRegistry.registerBlock(DataHandler.block_Ruby_Ore, "block_Ruby_Ore");
        OreDictionary.registerOre("oreRuby", DataHandler.block_Ruby_Ore);

        DataHandler.block_Selenium = new BlockIE("block_Selenium");
        DataHandler.block_Selenium_Ore.setHardness(10);
        DataHandler.block_Selenium_Ore.setResistance(1000);
        GameRegistry.registerBlock(DataHandler.block_Selenium, "block_Selenium");


//        seleniumOre = new BlockOreIE(ConfigurationHandler.getBlockValue("seleniumOre"), "seleniumOre", ConfigurationHandler.getBlockValue("seleniumOre"));
//        GameRegistry.registerBlock(seleniumOre, LangHelper.getLocalizedName("seleniumOre"));
//        LanguageRegistry.addName(seleniumOre, LangHelper.getLocalizedName("seleniumOre"));
//        OreDictionary.registerOre("oreSelenium", seleniumOre);
    }

    public static void init2() {

    }
}
