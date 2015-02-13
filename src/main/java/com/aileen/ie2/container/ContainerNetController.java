package com.aileen.ie2.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import com.aileen.ie2.tileEntity.tileController;

/**
 * Created by Aileen on 12.01.14.
 */
public class ContainerNetController extends Container {
    private tileController tileController;
    private int lastPower;


    public ContainerNetController(InventoryPlayer inventoryPlayer, tileController tileController) {
        this.tileController = tileController;
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(tileController, x, 8 + 18 * x, 84));
        }
        //add inventory to container
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(inventoryPlayer, (x + y * 9) + 9, 8 + 18 * x, 105 + y * 18));
            }
        }

        //add hotbar to container
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(inventoryPlayer, x, 8 + 18 * x, 163));
        }


    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        //par1ICrafting.sendProgressBarUpdate(this, 0, this.tileController.getOverallEnergy());
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

//        for (int i = 0; i < this.crafters.size(); ++i) {
//            ICrafting icrafting = (ICrafting) this.crafters.get(i);
//
//            if (this.lastPower != this.tileController.getOverallEnergy()) {
//                icrafting.sendProgressBarUpdate(this, 0, this.tileController.getOverallEnergy());
//            }
//        }
//        this.lastPower = (int) this.tileController.getOverallEnergy();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            //this.tileController.bufferFill(par2);
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        return null;
       /* ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (par2 != 1 && par2 != 0) {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1.getItem().itemID) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (tileNetGenerator.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (par2 >= 3 && par2 < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;*/
    }
}

