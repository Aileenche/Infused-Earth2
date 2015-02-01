package com.aileen.ie2.libs;

import com.aileen.ie2.block.ModBlocks;
import com.aileen.ie2.handler.DataHandler;
import com.aileen.ie2.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;

/**
 * Void Magic
 * <p/>
 * com.aileen.ie2.libs
 *
 * @author Cheriekeks
 *         <p/>
 *         12.01.14 20:56
 */
public class EnergyFunctions {
    public static int getEnergyForItem(ItemStack itemStack) {
        if (itemStack == null) {
            return 0;
        } else {
            Item item = itemStack.getItem();

//            if (i < 256 && Block.blocksList[i] != null) {
//                Block block = Block.blocksList[i];
//
//                if (block == Block.woodSingleSlab) {
//                    return 2;
//                }
//                if (block instanceof BlockLog) {
//                    return 16;
//                }
//                if (block.blockMaterial == Material.wood) {
//                    return 16;
//                }
//                if (block.blockID == Block.planks.blockID) {
//                    return 4;
//                }
//                if (block == Block.coalBlock) {
//                    return 144;
//                }
//            }
//            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 10;
//            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 10;
//            if (item instanceof ItemHoe && ((ItemHoe) item).getMaterialName().equals("WOOD")) return 10;
//            if (i == Item.stick.itemID) return 2;
//            if (i == Item.coal.itemID) return 16;
//            if (i == Item.bucketLava.itemID) return 50;
//            if (i == Block.sapling.blockID) return 16;
//            if (i == Item.blazeRod.itemID) return 150;
//            if (i == Block.blockRedstone.blockID) return 45;
//            if (i == Block.bookShelf.blockID) return 30;
//            if (i == Block.fence.blockID) return 6;
//            if (i == Block.fenceGate.blockID) return 16;
//            if (i == Block.chest.blockID) return 32;
//            if (i == Block.workbench.blockID) return 16;
//            if (i == Block.jukebox.blockID) return 32;
//            if (i == Block.trapdoor.blockID) return 12;
//            if (i == Block.pressurePlatePlanks.blockID) return 8;
//            if (i == Block.woodenButton.blockID) return 4;
//            if (i == Block.netherrack.blockID) return 1;
//            if (i == Block.stairsWoodBirch.blockID) return 6;
//            if (i == Block.stairsWoodJungle.blockID) return 6;
//            if (i == Block.stairsWoodOak.blockID) return 6;
//            if (i == Block.stairsWoodSpruce.blockID) return 6;
//            if (i == Block.cloth.blockID) return 1;
//            if (i == Block.carpet.blockID) return 1;
//            if (i == Item.redstone.itemID) return 5;
//            if (i == Item.itemFrame.itemID) return 16;
//            if (i == Item.book.itemID) return 2;
            if (item == DataHandler.item_ingotSelenium) return 500;
            if (item == Item.getItemFromBlock(DataHandler.block_Selenium)) return 4500;
            return GameRegistry.getFuelValue(itemStack);
        }
    }
}
