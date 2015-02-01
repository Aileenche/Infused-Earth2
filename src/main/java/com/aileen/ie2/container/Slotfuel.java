package com.aileen.ie2.container;

import com.aileen.ie2.libs.EnergyFunctions;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Void Magic
 * <p/>
 * com.aileen.ie2.container
 *
 * @author Cheriekeks
 *         <p/>
 *         12.01.14 20:53
 */
public class Slotfuel extends Slot {
    public Slotfuel(IInventory inventory, int id, int x, int y) {
        super(inventory, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (EnergyFunctions.getEnergyForItem(stack) > 0) {
            return true;
        }
        return false;
    }
}
