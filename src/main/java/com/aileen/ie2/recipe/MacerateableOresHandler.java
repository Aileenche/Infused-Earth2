package com.aileen.ie2.recipe;

import com.aileen.ie2.block.ModBlocks;
import com.aileen.ie2.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.LinkedHashMap;

/**
 * Created by Aileen on 13.01.14.
 */
public class MacerateableOresHandler {

    private static LinkedHashMap<Integer, ItemStack> resultItem = new LinkedHashMap<Integer, ItemStack>();

    public static void init() {
//        addMaceratorRecipe((ModBlocks.amethystOre.blockID), new ItemStack(ModItems.gemAmethyst, 2));
//        addMaceratorRecipe((ModBlocks.rubyOre.blockID), new ItemStack(ModItems.gemRuby, 2));
//        addMaceratorRecipe((ModBlocks.sapphireOre.blockID), new ItemStack(ModItems.gemSapphire, 2));
//        addMaceratorRecipe((ModBlocks.topasOre.blockID), new ItemStack(ModItems.gemTopas, 2));
//        addMaceratorRecipe((ModBlocks.seleniumOre.blockID), new ItemStack(ModItems.dustSelenium, 2));
//        addMaceratorRecipe((ModItems.bone_tiny.itemID), new ItemStack(Item.dyePowder, 2, 15));
//        addMaceratorRecipe((ModItems.bone_small.itemID), new ItemStack(Item.dyePowder, 5, 15));
//        addMaceratorRecipe((ModItems.bone_tiny.itemID), new ItemStack(Item.dyePowder, 10, 15));
//        addMaceratorRecipe((ModItems.bone_tiny.itemID), new ItemStack(Item.dyePowder, 16, 15));
//
//
//        //todo OreDictionary.getOreID("oreCopper")
//
//        addMaceratorRecipe((Block.oreCoal.blockID), new ItemStack(ModItems.dustCoal, 12));
//        addMaceratorRecipe((Item.coal.itemID), new ItemStack(ModItems.dustCoal, 4));
//        addMaceratorRecipe((Block.oreDiamond.blockID), new ItemStack(Item.diamond, 2));
//        addMaceratorRecipe((Block.oreEmerald.blockID), new ItemStack(Item.emerald, 2));
//        addMaceratorRecipe((Block.oreGold.blockID), new ItemStack(ModItems.dustGold, 2));
//        addMaceratorRecipe((Block.oreIron.blockID), new ItemStack(ModItems.dustIron, 2));
//        addMaceratorRecipe((Block.oreLapis.blockID), new ItemStack(Item.dyePowder, 8, 4));
//        addMaceratorRecipe((Block.oreNetherQuartz.blockID), new ItemStack(Item.netherQuartz, 4));
//        addMaceratorRecipe((Block.oreRedstone.blockID), new ItemStack(Item.redstone, 16));
//        addMaceratorRecipe((Block.glowStone.blockID), new ItemStack(Item.glowstone, 4));

    }

    public static boolean addMaceratorRecipe(int item2macerate, ItemStack itemResult) {
        if (resultItem.containsKey(item2macerate)) {
            return false;
        } else {
            resultItem.put(item2macerate, itemResult);
            return true;

        }
    }

    public static ItemStack getMaceratorResultItem(ItemStack item2macerate) {
        if (resultItem.containsKey(item2macerate)) {
            return resultItem.get(item2macerate);
        }
        return null;
    }

}
