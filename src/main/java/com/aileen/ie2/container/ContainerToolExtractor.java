package com.aileen.ie2.container;

import com.aileen.ie2.helper.LogHelper;
import com.aileen.ie2.tileEntity.tileToolExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Aileen on 13.01.14.
 */
public class ContainerToolExtractor extends Container {

    private tileToolExtractor tileToolExtractor;
    private int lastCookTime;


    public ContainerToolExtractor(InventoryPlayer inventoryPlayer, tileToolExtractor tileToolExtractor) {
        this.tileToolExtractor = tileToolExtractor;

        //add furnace inventory to container
        addSlotToContainer(new Slot(tileToolExtractor, 27, 8, 16)); //Smelting Slot

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(tileToolExtractor, (x + y * 9), 8 + 18 * x, 38 + y * 18));
            }
        }

        //add inventory to container
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(inventoryPlayer, 9 + (x + y * 9), 8 + 18 * x, 97 + y * 18));
            }
        }

        //add hotbar to container
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(inventoryPlayer, x, 8 + 18 * x, 155));
        }


    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return tileToolExtractor.isUseableByPlayer(entityPlayer);
    }

    @Override
    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        if (par1 > 0 && par1 < 28) {
            if (this.getSlot(0).getHasStack()) {
                if (par4EntityPlayer.inventory.getItemStack() != null) {
                    return null;
                }
                return super.slotClick(par1, par2, par3, par4EntityPlayer);
            } else {
                return null;
            }
        }
        return super.slotClick(par1, par2, par3, par4EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack;
        Slot slot = (Slot) this.inventorySlots.get(par2);
        LogHelper.debug("Clicked: " + par2);

//        if (par2 >= 1 && par2 <= 27) {
//            ItemStack transferstack = tileToolExtractor.getStackInSlot(par2-1);
//            if (transferstack != null) {
//                ItemStack preparedStack = tileToolExtractor.decrStackSize(par2-1,transferstack.stackSize);
//                mergeItemStack(preparedStack, 28, 63, false);
//            }
//        }
        return new ItemStack(Blocks.diamond_ore, 63);
    }
}
