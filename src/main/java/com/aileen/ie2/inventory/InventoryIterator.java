package com.aileen.ie2.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Aileen on 18.01.14.
 */
public class InventoryIterator {
    public static Iterable<IInvSlot> getIterable(IInventory inv, ForgeDirection side) {
        if ((inv instanceof ISidedInventory)) {
            return new InventoryIteratorSided((ISidedInventory) inv, side);
        }
        return new InventoryIteratorSimple(inv);
    }

    public static abstract interface IInvSlot {
        public abstract int getIndex();

        public abstract boolean canPutStackInSlot(ItemStack paramItemStack);

        public abstract boolean canTakeStackFromSlot(ItemStack paramItemStack);

        public abstract ItemStack decreaseStackInSlot();

        public abstract ItemStack getStackInSlot();

        public abstract void setStackInSlot(ItemStack paramItemStack);
    }
}
