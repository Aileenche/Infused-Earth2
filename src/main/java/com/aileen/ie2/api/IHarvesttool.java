package com.aileen.ie2.api;

import com.aileen.ie2.libs.DigitalItemstack;
import net.minecraft.item.ItemStack;

/**
 * Created by Aileen on 31.01.2015.
 */
public abstract interface IHarvesttool {
    public abstract DigitalItemstack[] iInvToEx(ItemStack itemStack);

    public abstract boolean exInvToI(ItemStack item, DigitalItemstack[] digitalItemstack);

    public DigitalItemstack getItemstackInSlot(ItemStack item, int Slot);

    public boolean setItemstackInSlot(ItemStack item, int Slot, DigitalItemstack digitalItemstack);
    public boolean clearItemstackInSlot(ItemStack item, int Slot);
}
