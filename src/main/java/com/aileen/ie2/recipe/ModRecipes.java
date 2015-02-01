package com.aileen.ie2.recipe;

/**
 * Created by Aileen on 04.01.14.
 */
public class ModRecipes {
    public static void init() {
        initSmeltingRecipies();
        initCraftingRecepies();
    }

    private static void initSmeltingRecipies() {
//        GameRegistry.addSmelting(ModBlocks.seleniumOre.blockID, new ItemStack(ModItems.seleniumIngot), 0.8F);
//        GameRegistry.addSmelting(ModBlocks.amethystOre.blockID, new ItemStack(ModItems.gemAmethyst), 0.8F);
//        GameRegistry.addSmelting(ModBlocks.rubyOre.blockID, new ItemStack(ModItems.gemRuby), 1.8F);
//        GameRegistry.addSmelting(ModBlocks.topasOre.blockID, new ItemStack(ModItems.gemTopas), 0.4F);
//        GameRegistry.addSmelting(ModBlocks.sapphireOre.blockID, new ItemStack(ModItems.gemSapphire), 1.4F);
//        GameRegistry.addSmelting(ModItems.dustGold.itemID, new ItemStack(Item.ingotGold), 0.8F);
//        GameRegistry.addSmelting(ModItems.dustIron.itemID, new ItemStack(Item.ingotIron), 0.8F);
//        GameRegistry.addSmelting(ModItems.dustSelenium.itemID, new ItemStack(ModItems.seleniumIngot), 0.8F);
//        GameRegistry.addSmelting(Item.rottenFlesh.itemID, new ItemStack(Item.leather), 0.5F);
    }

    private static void initCraftingRecepies() {
//        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 15), new ItemStack(ModItems.bone_tiny));
//        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 3, 15), new ItemStack(ModItems.bone_small));
//        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 5, 15), new ItemStack(ModItems.bone_medium));
//        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 8, 15), new ItemStack(ModItems.bone_large));
//        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.gemAmethyst, 9), new ItemStack(ModBlocks.amethystBlock));
//        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.gemRuby, 9), new ItemStack(ModBlocks.rubyBlock));
//        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.gemSapphire, 9), new ItemStack(ModBlocks.sapphireBlock));
//        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.gemTopas, 9), new ItemStack(ModBlocks.topasBlock));
//        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.seleniumIngot, 9), new ItemStack(ModBlocks.seleniumBlock));
//        GameRegistry.addRecipe(new ItemStack(ModItems.seleniumstick, 4),
//                "X ",
//                "X ",
//                'X', ModItems.seleniumIngot);
//        GameRegistry.addRecipe(new ItemStack(ModItems.eLinkCard, 1),
//                "C ",
//                "P ",
//                'P', Item.paper,
//                'C', Item.redstone);
//        GameRegistry.addRecipe(new ItemStack(ModItems.colorCard, 1),
//                "C ",
//                "P ",
//                'P', Item.paper,
//                'C', Item.dyePowder);
//        GameRegistry.addRecipe(new ItemStack(ModItems.glassBowl, 10),
//                "   ",
//                "XXX",
//                " X ",
//                'X', Block.thinGlass);
//        GameRegistry.addRecipe(new ItemStack(ModItems.appleSorbet, 1),
//                " Y ",
//                "XXX",
//                " Z ",
//                'X', Item.appleRed,
//                'Y', Item.snowball,
//                'Z', ModItems.glassBowl);
//        GameRegistry.addRecipe(new ItemStack(ModItems.seleniumPickaxe),
//                "XXX",
//                " I ",
//                " I ",
//                'X', ModItems.seleniumIngot,
//                'I', ModItems.seleniumstick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.seleniumAxe),
//                "XX ",
//                "XI ",
//                " I ",
//                'X', ModItems.seleniumIngot,
//                'I', ModItems.seleniumstick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.seleniumShovel),
//                " X ",
//                " I ",
//                " I ",
//                'X', ModItems.seleniumIngot,
//                'I', ModItems.seleniumstick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.seleniumSword),
//                " X ",
//                " X ",
//                " I ",
//                'X', ModItems.seleniumIngot,
//                'I', ModItems.seleniumstick);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.seleniumBlock),
//                "XXX",
//                "XXX",
//                "XXX",
//                'X', ModItems.seleniumIngot);
//        GameRegistry.addRecipe(new ItemStack(ModItems.axeAmethyst, 1, 0),
//                "XX ",
//                "XI ",
//                " I ",
//                'X', ModItems.gemAmethyst,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.netSolarGen1, 1, 0),
//                "XXX",
//                "CRC",
//                "III",
//                'X', ModItems.dustCoal,
//                'I', Item.ingotIron,
//                'C', ModBlocks.netCable,
//                'R', ModBlocks.netGenerator);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.netSolarGen2, 1, 0),
//                "XXX",
//                "CBC",
//                "XXX",
//                'X', ModBlocks.netSolarGen1,
//                'B', ModBlocks.amethystBlock,
//                'C', ModBlocks.netCable);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.netSolarGen3, 1, 0),
//                "XXX",
//                "CBC",
//                "XXX",
//                'X', ModBlocks.netSolarGen2,
//                'B', ModBlocks.sapphireBlock,
//                'C', ModBlocks.netCable);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.netSolarGen4, 1, 0),
//                "XXX",
//                "CBC",
//                "XXX",
//                'X', ModBlocks.netSolarGen3,
//                'B', ModBlocks.rubyBlock,
//                'C', ModBlocks.netCable);
//        GameRegistry.addRecipe(new ItemStack(ModItems.axeRuby, 1, 0),
//                "XX ",
//                "XI ",
//                " I ",
//                'X', ModItems.gemRuby,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.axeSapphire, 1, 0),
//                "XX ",
//                "XI ",
//                " I ",
//                'X', ModItems.gemSapphire,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.axeTopas, 1, 0),
//                "XX ",
//                "XI ",
//                " I ",
//                'X', ModItems.gemTopas,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.pickaxeAmethyst, 1, 0),
//                "XXX",
//                " I ",
//                " I ",
//                'X', ModItems.gemAmethyst,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.toolExcavator, 1, 0),
//                "IRI",
//                "IHI",
//                "ICI",
//                'R', Item.redstone,
//                'H', Block.hopperBlock,
//                'C', Block.chest,
//                'I', Item.ingotIron);
//        GameRegistry.addRecipe(new ItemStack(ModItems.pickaxeRuby, 1, 0),
//                "XXX",
//                " I ",
//                " I ",
//                'X', ModItems.gemRuby,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.pickaxeSapphire, 1, 0),
//                "XXX",
//                " I ",
//                " I ",
//                'X', ModItems.gemSapphire,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.pickaxeTopas, 1, 0),
//                "XXX",
//                " I ",
//                " I ",
//                'X', ModItems.gemTopas,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.shovelAmethyst, 1, 0),
//                " X ",
//                " I ",
//                " I ",
//                'X', ModItems.gemAmethyst,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.shovelRuby, 1, 0),
//                " X ",
//                " I ",
//                " I ",
//                'X', ModItems.gemRuby,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.shovelSapphire, 1, 0),
//                " X ",
//                " I ",
//                " I ",
//                'X', ModItems.gemSapphire,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.shovelTopas, 1, 0),
//                " X ",
//                " I ",
//                " I ",
//                'X', ModItems.gemTopas,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.swordAmethyst, 1, 0),
//                " X ",
//                " X ",
//                " I ",
//                'X', ModItems.gemAmethyst,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.swordGlass, 1, 0),
//                " X ",
//                " X ",
//                " I ",
//                'X', Block.glass,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.swordRuby, 1, 0),
//                " X ",
//                " X ",
//                " I ",
//                'X', ModItems.gemRuby,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.swordSapphire, 1, 0),
//                " X ",
//                " X ",
//                " I ",
//                'X', ModItems.gemSapphire,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModItems.swordTopas, 1, 0),
//                " X ",
//                " X ",
//                " I ",
//                'X', ModItems.gemTopas,
//                'I', Item.stick);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.amethystBlock, 1, 0),
//                "XXX",
//                "XXX",
//                "XXX",
//                'X', ModItems.gemAmethyst);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.rubyBlock, 1, 0),
//                "XXX",
//                "XXX",
//                "XXX",
//                'X', ModItems.gemRuby);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.sapphireBlock, 1, 0),
//                "XXX",
//                "XXX",
//                "XXX",
//                'X', ModItems.gemSapphire);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.topasBlock, 1, 0),
//                "XXX",
//                "XXX",
//                "XXX",
//                'X', ModItems.gemTopas);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.netCable, 8, 0),
//                "XXX",
//                "sgs",
//                "XXX",
//                'X', Block.glass,
//                's', ModItems.seleniumIngot,
//                'g', Block.glowStone);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.netGenerator, 1, 0),
//                "sgs",
//                "gXg",
//                "sgs",
//                'X', Item.magmaCream,
//                's', Item.ingotIron,
//                'g', Block.glass);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.netController, 1, 0),
//                "tga",
//                "gXg",
//                "sgr",
//                'X', ModItems.smallStorageUpgrade,
//                's', ModBlocks.sapphireBlock,
//                't', ModBlocks.topasBlock,
//                'r', ModBlocks.rubyBlock,
//                'a', ModBlocks.amethystBlock,
//                'g', Block.glass);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.digger, 1, 0),
//                "sCs",
//                "sHs",
//                "sPs",
//                's', ModItems.seleniumIngot,
//                'C', Block.pistonStickyBase,
//                'H', ModItems.seleniumShovel,
//                'P', ModItems.seleniumPickaxe);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.highspeedFurnace, 1, 0),
//                "ccc",
//                "cfc",
//                "ccc",
//                'c', ModBlocks.netCable,
//                'f', Block.furnaceIdle);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.macerator, 1, 0),
//                "ccc",
//                "chc",
//                "ccc",
//                'c', ModBlocks.netCable,
//                'h', ModBlocks.highspeedFurnace);
//        GameRegistry.addRecipe(new ItemStack(ModItems.smallStorageUpgrade, 1, 0),
//                "ccc",
//                "cpc",
//                "ccc",
//                'c', ModBlocks.netCable,
//                'p', Item.potato);
//        GameRegistry.addRecipe(new ItemStack(ModItems.mediumStorageUpgrade, 1, 0),
//                "ccc",
//                "crc",
//                "ccc",
//                'c', ModBlocks.netCable,
//                'r', Block.blockRedstone);
//        GameRegistry.addRecipe(new ItemStack(ModItems.largeStorageUpgrade, 1, 0),
//                "rcr",
//                "csc",
//                "rcr",
//                'c', ModBlocks.netCable,
//                'r', Block.blockRedstone,
//                's', ModBlocks.seleniumBlock);
//        GameRegistry.addRecipe(new ItemStack(ModItems.diggerrangeupgrade, 1, 0),
//                "rcr",
//                "csc",
//                "rcr",
//                'c', ModBlocks.netCable,
//                'r', Item.redstone,
//                's', Item.pickaxeDiamond);
//        GameRegistry.addRecipe(new ItemStack(ModBlocks.toolRepairstation, 1, 0),
//                "rcr",
//                "csc",
//                "rcr",
//                'c', ModBlocks.netCable,
//                'r', Item.ingotIron,
//                's', ModItems.mediumStorageUpgrade);
    }
}
