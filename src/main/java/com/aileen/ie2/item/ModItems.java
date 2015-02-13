package com.aileen.ie2.item;

import com.aileen.ie2.handler.DataHandler;
import com.aileen.ie2.item.network.itemBattery;
import com.aileen.ie2.item.network.item_eLinkCard;
import com.aileen.ie2.item.tools.AxeIE;
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

        DataHandler.item_stickSelenium = new ItemIE("item_stickSelenium");
        GameRegistry.registerItem(DataHandler.item_stickSelenium, "item_stickSelenium");

        DataHandler.item_stickTitanium = new ItemIE("item_stickTitanium");
        GameRegistry.registerItem(DataHandler.item_stickTitanium, "item_stickTitanium");

        DataHandler.item_gemTopas = new ItemIE("item_gemTopas");
        GameRegistry.registerItem(DataHandler.item_gemTopas, "item_gemTopas");
        OreDictionary.registerOre("gemTopas",DataHandler.item_gemTopas);

        DataHandler.item_ingotSelenium = new ItemIE("item_ingotSelenium");
        GameRegistry.registerItem(DataHandler.item_ingotSelenium, "item_ingotSelenium");
        OreDictionary.registerOre("ingotSelenium",DataHandler.item_ingotSelenium);

        DataHandler.item_ingotTitanium = new ItemIE("item_ingotTitanium");
        GameRegistry.registerItem(DataHandler.item_ingotTitanium, "item_ingotTitanium");
        OreDictionary.registerOre("ingotTitanium",DataHandler.item_ingotTitanium);

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
        DataHandler.item_EnergyLinkCard = new item_eLinkCard("item_EnergyLinkCard");
        GameRegistry.registerItem(DataHandler.item_EnergyLinkCard, "item_EnergyLinkCard");

        DataHandler.item_batterySmall = new itemBattery("item_batterySmall",32000);
        GameRegistry.registerItem(DataHandler.item_batterySmall, "item_batterySmall");

        DataHandler.item_batteryMiddle = new itemBattery("item_batteryMiddle",64000);
        GameRegistry.registerItem(DataHandler.item_batteryMiddle, "item_batteryMiddle");

        DataHandler.item_batteryLarge = new itemBattery("item_batteryLarge",128000);
        GameRegistry.registerItem(DataHandler.item_batteryLarge, "item_batteryLarge");

        DataHandler.item_batteryCreative = new itemBattery("item_batteryCreative",10000,true);
        GameRegistry.registerItem(DataHandler.item_batteryCreative, "item_batteryCreative");

        DataHandler.item_pickaxeSelenium = new PickaxeIE(DataHandler.SELENIUM,"item_pickaxeSelenium");
        GameRegistry.registerItem(DataHandler.item_pickaxeSelenium, "item_pickaxeSelenium");

        DataHandler.item_pickaxeTitanium = new PickaxeIE(DataHandler.TITANIUM,"item_pickaxeTitanium");
        GameRegistry.registerItem(DataHandler.item_pickaxeTitanium, "item_pickaxeTitanium");

        DataHandler.item_pickaxeRuby = new PickaxeIE(DataHandler.RUBY,"item_pickaxeRuby");
        GameRegistry.registerItem(DataHandler.item_pickaxeRuby, "item_pickaxeRuby");

        DataHandler.item_pickaxeTopas = new PickaxeIE(DataHandler.TOPAS,"item_pickaxeTopas");
        GameRegistry.registerItem(DataHandler.item_pickaxeTopas, "item_pickaxeTopas");

        DataHandler.item_pickaxeSapphire = new PickaxeIE(DataHandler.SAPPHIRE,"item_pickaxeSapphire");
        GameRegistry.registerItem(DataHandler.item_pickaxeSapphire, "item_pickaxeSapphire");

        DataHandler.item_pickaxeAmethyst = new PickaxeIE(DataHandler.AMETHYST,"item_pickaxeAmethyst");
        GameRegistry.registerItem(DataHandler.item_pickaxeAmethyst, "item_pickaxeAmethyst");

        //DataHandler.item_axeTitanium = new AxeIE(DataHandler.TITANIUM,"item_axeTitanium");
        //GameRegistry.registerItem(DataHandler.item_axeTitanium, "item_axeTitanium");

        DataHandler.item_axeSelenium = new AxeIE(DataHandler.SELENIUM,"item_axeSelenium");
        GameRegistry.registerItem(DataHandler.item_axeSelenium, "item_axeSelenium");

        DataHandler.item_axeRuby = new AxeIE(DataHandler.RUBY,"item_axeRuby");
        GameRegistry.registerItem(DataHandler.item_axeRuby, "item_axeRuby");

        DataHandler.item_axeTopas = new AxeIE(DataHandler.TOPAS,"item_axeTopas");
        GameRegistry.registerItem(DataHandler.item_axeTopas, "item_axeTopas");

        DataHandler.item_axeSapphire = new AxeIE(DataHandler.SAPPHIRE,"item_axeSapphire");
        GameRegistry.registerItem(DataHandler.item_axeSapphire, "item_axeSapphire");

        DataHandler.item_axeAmethyst = new AxeIE(DataHandler.AMETHYST,"item_axeAmethyst");
        GameRegistry.registerItem(DataHandler.item_axeAmethyst, "item_axeAmethyst");
//        MinecraftForge.EVENT_BUS.register(new VanillaMobDrops());
//        GameRegistry.registerFuelHandler(new FuelHandler());
    }
}
