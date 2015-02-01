package com.aileen.ie2;

/**
 * Created by Aileen on 04.01.14.
 */

import com.aileen.ie2.block.ModBlocks;
import com.aileen.ie2.configuration.ConfigurationHandler;
import com.aileen.ie2.configuration.initialConfiguration;
import com.aileen.ie2.handler.DataHandler;
import com.aileen.ie2.handler.GuiHandler;
import com.aileen.ie2.handler.InterModCommsHandler;
import com.aileen.ie2.helper.LogHelper;
import com.aileen.ie2.item.ModItems;
import com.aileen.ie2.libs.Reference;
import com.aileen.ie2.material.Materials;
import com.aileen.ie2.proxy.CommonProxy;
import com.aileen.ie2.proxy.IProxy;
import com.aileen.ie2.recipe.MacerateableOresHandler;
import com.aileen.ie2.recipe.ModRecipes;
import com.aileen.ie2.worldgen.Biome.ModBiome;
import com.aileen.ie2.worldgen.WorldGen;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;


@Mod(modid = Reference.MOD_ID, version = Reference.VERSION_NUMBER, name = Reference.MOD_NAME, dependencies = Reference.DEPENDENCIES)
public class InfusedEarth {

    @Mod.Instance(Reference.MOD_ID)
    public static InfusedEarth instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {

        //CommandHandler.initCommands(event);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        initialConfiguration.init();

        //Initialize LogHelper
        LogHelper.init();

        //Initialize Config Handler
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        //Datahandler
        DataHandler.init();

        //Initialize Fluids
        //ModFluids.init();

        //Initialize Materials
        Materials.init();

        //Initialize Blocks
        ModBlocks.init();

        //Initialize Items
        ModItems.init();

        //Initialize Blocks Stage 2
        ModBlocks.init2();

        //Initialize Items Stage 2
        ModItems.init2();

        //Initialize Biome
        ModBiome.init();

        //Initialize MaceratorRecipes
        MacerateableOresHandler.init();

        GameRegistry.registerWorldGenerator(new WorldGen(), 1);
        //Initialize Entities
        //ModEntitys.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        //GameRegistry.registerPlayerTracker(new PlayerHandler());

        //Register the GuiHandler
        //NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());

        //initialize localization helper
        //LocalizationHelper.init();

        //Initialize more entity stuff
        //ModEntitys.init();

        //Initialize Recipes
        ModRecipes.init();

        //localize Fluids
        //ModFluids.addNamesAndProperties();

        //Initialize Renderer
        //proxy.registerRenderer();

        //initialize EventHandlers
        //proxy.registerEvents();

        // Initialize mod tile entities
         proxy.registerTileEntities();

        ConfigurationHandler.save();

        //Netfuncs.init();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        for (String ores: OreDictionary.getOreNames()) {
            LogHelper.debug(ores);
        }
    }

    @Mod.EventHandler
    public void handleIMC(FMLInterModComms.IMCEvent theIMC) {
        InterModCommsHandler.instance.handleIMC(theIMC);
    }

    //TODO MATERIALIDEE PICKAXE - TITAN

}
