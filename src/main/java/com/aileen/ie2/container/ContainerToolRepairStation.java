package com.aileen.ie2.container;

import com.aileen.ie2.tileEntity.TileToolRepairStation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Sunny on 13.02.14.
 */
public class ContainerToolRepairStation extends Container {
    private TileToolRepairStation tileToolRepairStation;
    private int lastPower;


    public ContainerToolRepairStation(InventoryPlayer inventoryPlayer, TileToolRepairStation tileToolRepairStation) {
        this.tileToolRepairStation = tileToolRepairStation;

        addSlotToContainer(new Slot(tileToolRepairStation, 0, 88, 25));
        addSlotToContainer(new Slot(tileToolRepairStation, 19, 30, 64));

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                addSlotToContainer(new Slot(tileToolRepairStation, (x + y * 3) + 1, 30 + 18 * x, 7 + y * 18));
            }
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                addSlotToContainer(new Slot(tileToolRepairStation, (x + y * 3) + 10, 110 + 18 * x, 7 + y * 18));
            }
        }
        //add inventory to container
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(inventoryPlayer, (x + y * 9) + 9, 8 + 18 * x, 86 + y * 18));
            }
        }

        //add hotbar to container
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(inventoryPlayer, x, 8 + 18 * x, 144));
        }


    }


    @Override
    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        if (par1 == 0) {
            return null;
        }
        if (par1 == 1) {
            if (par4EntityPlayer.inventory.getItemStack() == null) {
                return super.slotClick(par1, par2, par3, par4EntityPlayer);
            }
//            if (par4EntityPlayer.inventory.getItemStack().getItem() instanceof network_battery) {
//                return super.slotClick(par1, par2, par3, par4EntityPlayer);
//            } else {
//                return null;
//            }
        }
        if ((par1 > 10 && par1 <= 19)) {
            if (getSlot(par1).getHasStack() && par4EntityPlayer.inventory.getItemStack() == null) {
                return super.slotClick(par1, par2, par3, par4EntityPlayer);
            } else {
                return null;
            }
        }
        return super.slotClick(par1, par2, par3, par4EntityPlayer);
    }


    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return tileToolRepairStation.isUseableByPlayer(entityPlayer);
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tileToolRepairStation.storage.getEnergyStored());
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastPower != this.tileToolRepairStation.storage.getEnergyStored()) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileToolRepairStation.storage.getEnergyStored());
            }
        }
        this.lastPower = (int) this.tileToolRepairStation.storage.getEnergyStored();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.tileToolRepairStation.storage.setEnergyStored(par2);
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
//        Slot slot = (Slot) this.inventorySlots.get(par2);
//
//        Slotfuel slotty = (Slotfuel) this.inventorySlots.get(0);
//
//        if (slot != null && slot.getHasStack()) {
//            ItemStack itemstack1 = slot.getStack();
//            itemstack = itemstack1.copy();
//            LogHelper.debug("Clicked Slot: " + par2);
//            if (par2 >= 0 && par2 <= 7) {
//                if (!mergeItemStack(itemstack1, 8, 34, false)) {
//                    return null;
//                }
//            }
//            if (par2 >= 8 && par2 <= 34) {
//                if (slotty.isItemValid(itemstack1)) {
//                    if (!mergeItemStack(itemstack1, 0, 7, false)) {
//                        return null;
//                    }
//                } else {
//                    return null;
//                }
//            }
//            if (par2 >= 35 && par2 <= 43) {
//                if (!mergeItemStack(itemstack1, 8, 34, false)) {
//                    return null;
//                }
//            }
//
//            slot.onSlotChange(itemstack1, itemstack);
//
//            if (itemstack1.stackSize == 0) {
//                slot.putStack(null);
//            } else {
//                slot.onSlotChanged();
//            }
//
//            if (itemstack1.stackSize == itemstack.stackSize) {
//                return null;
//            }
//
//            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
//        }
        return itemstack;
    }
}