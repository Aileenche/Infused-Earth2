package com.aileen.ie2.recipe;

import com.aileen.ie2.handler.DataHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by Aileen on 04.01.14.
 */
public class ModRecipes {
    public static void init() {
        initSmeltingRecipies();
        initCraftingRecepies();
    }

    private static void initSmeltingRecipies() {
        GameRegistry.addSmelting(DataHandler.block_Selenium_Ore, new ItemStack(DataHandler.item_ingotSelenium), 2.5F);
        GameRegistry.addSmelting(DataHandler.block_Titanium_Ore, new ItemStack(DataHandler.item_ingotTitanium), 2.8F);
        GameRegistry.addSmelting(DataHandler.block_Amethyst_Ore, new ItemStack(DataHandler.item_gemAmethyst), 0.8F);
        GameRegistry.addSmelting(DataHandler.block_Ruby_Ore, new ItemStack(DataHandler.item_gemRuby), 1.8F);
        GameRegistry.addSmelting(DataHandler.block_Topas_Ore, new ItemStack(DataHandler.item_gemTopas), 0.4F);
        GameRegistry.addSmelting(DataHandler.block_Sapphire_Ore, new ItemStack(DataHandler.item_gemSapphire), 1.4F);
        GameRegistry.addSmelting(DataHandler.item_dustSelenium, new ItemStack(DataHandler.item_ingotSelenium), 0.8F);
        GameRegistry.addSmelting(Items.rotten_flesh, new ItemStack(Items.leather), 0.5F);
    }

    private static void initCraftingRecepies() {
        GameRegistry.addShapelessRecipe(new ItemStack(DataHandler.item_gemAmethyst, 9), new ItemStack(DataHandler.block_Amethyst));
        GameRegistry.addShapelessRecipe(new ItemStack(DataHandler.item_gemRuby, 9), new ItemStack(DataHandler.block_Ruby));
        GameRegistry.addShapelessRecipe(new ItemStack(DataHandler.item_gemSapphire, 9), new ItemStack(DataHandler.block_Sapphire));
        GameRegistry.addShapelessRecipe(new ItemStack(DataHandler.item_gemTopas, 9), new ItemStack(DataHandler.block_Topas));
        GameRegistry.addShapelessRecipe(new ItemStack(DataHandler.item_ingotSelenium, 9), new ItemStack(DataHandler.block_Selenium));
        GameRegistry.addShapelessRecipe(new ItemStack(DataHandler.item_ingotTitanium, 9), new ItemStack(DataHandler.block_Titanium));

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_stickSelenium, 4),
                "X",
                "X",
                'X', DataHandler.item_ingotSelenium);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_stickTitanium, 4),
                "X",
                "X",
                'X', DataHandler.item_ingotTitanium);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_pickaxeSelenium),
                "XXX",
                " I ",
                " I ",
                'X', DataHandler.item_ingotSelenium,
                'I', DataHandler.item_stickSelenium);

        GameRegistry.addRecipe(new ItemStack(DataHandler.block_Selenium),
                "XXX",
                "XXX",
                "XXX",
                'X', DataHandler.item_ingotSelenium);
        GameRegistry.addRecipe(new ItemStack(DataHandler.block_Titanium),
                "XXX",
                "XXX",
                "XXX",
                'X', DataHandler.item_ingotTitanium);
        GameRegistry.addRecipe(new ItemStack(DataHandler.block_Sapphire),
                "XXX",
                "XXX",
                "XXX",
                'X', DataHandler.item_gemSapphire);
        GameRegistry.addRecipe(new ItemStack(DataHandler.block_Amethyst),
                "XXX",
                "XXX",
                "XXX",
                'X', DataHandler.item_gemAmethyst);
        GameRegistry.addRecipe(new ItemStack(DataHandler.block_Ruby),
                "XXX",
                "XXX",
                "XXX",
                'X', DataHandler.item_gemRuby);
        GameRegistry.addRecipe(new ItemStack(DataHandler.block_Topas),
                "XXX",
                "XXX",
                "XXX",
                'X', DataHandler.item_gemTopas);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_pickaxeTitanium),
                "XXX",
                " I ",
                " I ",
                'X', DataHandler.item_ingotTitanium,
                'I', DataHandler.item_stickTitanium);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_pickaxeAmethyst, 1, 0),
                "XXX",
                " I ",
                " I ",
                'X', DataHandler.item_gemAmethyst,
                'I', Items.stick);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_pickaxeTopas, 1, 0),
                "XXX",
                " I ",
                " I ",
                'X', DataHandler.item_gemTopas,
                'I', Items.stick);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_pickaxeSapphire, 1, 0),
                "XXX",
                " I ",
                " I ",
                'X', DataHandler.item_gemSapphire,
                'I', Items.stick);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_pickaxeRuby, 1, 0),
                "XXX",
                " I ",
                " I ",
                'X', DataHandler.item_gemRuby,
                'I', Items.stick);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_axeSelenium),
                "XX ",
                "XI ",
                " I ",
                'X', DataHandler.item_ingotSelenium,
                'I', DataHandler.item_stickSelenium);

//        GameRegistry.addRecipe(new ItemStack(DataHandler.item_axeTitanium),
//                "XX ",
//                "XI ",
//                " I ",
//                'X', DataHandler.item_ingotTitanium,
//                'I', DataHandler.item_stickTitanium);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_axeAmethyst, 1, 0),
                "XX ",
                "XI ",
                " I ",
                'X', DataHandler.item_gemAmethyst,
                'I', Items.stick);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_axeTopas, 1, 0),
                "XX ",
                "XI ",
                " I ",
                'X', DataHandler.item_gemTopas,
                'I', Items.stick);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_axeSapphire, 1, 0),
                "XX ",
                "XI ",
                " I ",
                'X', DataHandler.item_gemSapphire,
                'I', Items.stick);

        GameRegistry.addRecipe(new ItemStack(DataHandler.item_axeRuby, 1, 0),
                "XX ",
                "XI ",
                " I ",
                'X', DataHandler.item_gemRuby,
                'I', Items.stick);

        GameRegistry.addRecipe(new ItemStack(DataHandler.block_ToolExtractor, 1, 0),
                "IRI",
                "IHI",
                "ICI",
                'R', Items.redstone,
                'H', Blocks.hopper,
                'C', Blocks.chest,
                'I', Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(DataHandler.block_Tank, 1, 0),
                "III",
                "ITI",
                "III",
                'T', DataHandler.item_ingotTitanium,
                'I', DataHandler.item_ingotSelenium);

        GameRegistry.addRecipe(new ItemStack(DataHandler.block_ToolRepairStation, 1, 0),
                "ITI",
                "IAI",
                "ISI",
                'T', DataHandler.block_Topas,
                'A', DataHandler.block_Amethyst,
                'S', DataHandler.block_Sapphire,
                'I', DataHandler.item_ingotSelenium);
    }
}
