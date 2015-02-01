package com.aileen.ie2.item;

import com.aileen.ie2.handler.DataHandler;
import com.aileen.ie2.item.tools.PickaxeIE;
import com.aileen.ie2.libs.Reference;
import com.aileen.ie2.material.Materials;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Aileen on 04.01.14.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
    public static void init() {

        DataHandler.item_dustSelenium = new ItemIE("item_dustSelenium");
        GameRegistry.registerItem(DataHandler.item_dustSelenium, "item_dustSelenium");

        DataHandler.item_gemTopas = new ItemIE("item_gemTopas");
        GameRegistry.registerItem(DataHandler.item_gemTopas, "item_gemTopas");
        OreDictionary.registerOre("gemTopas",DataHandler.item_gemTopas);

        DataHandler.item_ingotSelenium = new ItemIE("item_ingotSelenium");
        GameRegistry.registerItem(DataHandler.item_ingotSelenium, "item_ingotSelenium");
        OreDictionary.registerOre("ingotSelenium",DataHandler.item_ingotSelenium);

        DataHandler.item_gemAmethyst = new ItemIE("item_gemAmethyst");
        GameRegistry.registerItem(DataHandler.item_gemAmethyst, "item_gemAmethyst");
        OreDictionary.registerOre("gemAmethyst",DataHandler.item_gemAmethyst);

        DataHandler.item_gemSapphire = new ItemIE("item_gemSapphire");
        GameRegistry.registerItem(DataHandler.item_gemSapphire, "item_gemSapphire");
        OreDictionary.registerOre("gemSapphire",DataHandler.item_gemSapphire);

        DataHandler.item_gemRuby = new ItemIE("item_gemRuby");
        GameRegistry.registerItem(DataHandler.item_gemRuby, "item_gemRuby");
        OreDictionary.registerOre("gemRuby",DataHandler.item_gemRuby);

    }

    public static void init2() {

        DataHandler.item_pickaxeSelenium = new PickaxeIE(DataHandler.SELENIUM,"item_pickaxeSelenium");
        GameRegistry.registerItem(DataHandler.item_pickaxeSelenium, "item_pickaxeSelenium");

        DataHandler.item_pickaxeRuby = new PickaxeIE(DataHandler.RUBY,"item_pickaxeRuby");
        GameRegistry.registerItem(DataHandler.item_pickaxeRuby, "item_pickaxeRuby");

        DataHandler.item_pickaxeTopas = new PickaxeIE(DataHandler.TOPAS,"item_pickaxeTopas");
        GameRegistry.registerItem(DataHandler.item_pickaxeTopas, "item_pickaxeTopas");

        DataHandler.item_pickaxeSapphire = new PickaxeIE(DataHandler.SAPPHIRE,"item_pickaxeSapphire");
        GameRegistry.registerItem(DataHandler.item_pickaxeSapphire, "item_pickaxeSapphire");

        DataHandler.item_pickaxeAmethyst = new PickaxeIE(DataHandler.AMETHYST,"item_pickaxeAmethyst");
        GameRegistry.registerItem(DataHandler.item_pickaxeAmethyst, "item_pickaxeAmethyst");
//        MinecraftForge.EVENT_BUS.register(new VanillaMobDrops());
//        GameRegistry.registerFuelHandler(new FuelHandler());
    }
}
