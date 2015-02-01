package com.aileen.ie2.inventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Aileen on 18.01.14.
 */
public interface ITransactor {
    ItemStack add(ItemStack stack, ForgeDirection orientation, boolean doAdd);

    ItemStack remove(IStackFilter filter, ForgeDirection orientation, boolean doRemove);
}
