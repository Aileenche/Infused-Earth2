package com.aileen.ie2.inventory;

/**
 * Created by Aileen on 18.01.14.
 */

import net.minecraft.item.ItemStack;

public abstract interface IStackFilter {
    public abstract boolean matches(ItemStack paramItemStack);
}
