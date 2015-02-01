package com.aileen.ie2.handler;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

public class FuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {

        if (fuel.isItemEqual(new ItemStack(DataHandler.item_ingotSelenium))) {
            return 8000000;
        }
        return 0;
    }

}
