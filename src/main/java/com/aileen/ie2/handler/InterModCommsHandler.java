package com.aileen.ie2.handler;

import com.aileen.ie2.helper.LogHelper;
import com.aileen.ie2.recipe.MacerateableOresHandler;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Sunny on 14.02.14.
 */
public class InterModCommsHandler {
    public static InterModCommsHandler instance = new InterModCommsHandler();

    public void handleIMC(FMLInterModComms.IMCEvent theIMC) {
        for (FMLInterModComms.IMCMessage theMessage : theIMC.getMessages())
            try {
                if (theMessage.isNBTMessage()) {
                    NBTTagCompound nbt = theMessage.getNBTValue();

                    /*
                        NBTTagCompound nbt = new NBTTagCompound();
                        nbt.setInteger("input", -Item oder Block ID-);
                        nbt.setCompoundTag("output", new NBTTagCompound());
                        YourOutputItemStack.writeToNBT(nbt.getCompoundTag("output"));
                        FMLInterModComms.sendMessage("IE2", "MaceratorRecipe", nbt);
                    */
                    if (theMessage.key.equalsIgnoreCase("MaceratorRecipe")) {
                        MacerateableOresHandler.addMaceratorRecipe(nbt.getInteger("input"), ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("output")));
                        LogHelper.info("Added IMC Recipe for Macerator from " + theMessage.getSender());
                        continue;
                    }

                    /*
                        NBTTagCompound nbt = new NBTTagCompound();
                        nbt.setInteger("networkID", -the Network ID you wish to assign-);
                        nbt.setInteger("blockID", -Block ID-);
                        FMLInterModComms.sendMessage("IE2", "AddNetworkBlock", nbt);
                    */
                    if (theMessage.key.equalsIgnoreCase("AddNetworkBlock")) {
                        //Netfuncs.addNetBlock(nbt.getInteger("networkID"), nbt.getInteger("blockID"));
                        LogHelper.info("Added IMC Networkblock to ValidList from " + theMessage.getSender());
                        continue;
                    }

                    LogHelper.warning("Received an invalid IMC from " + theMessage.getSender() + "! Key was " + theMessage.key);
                }
            } catch (Exception e) {
                LogHelper.warning("Received an broken IMC from " + theMessage.getSender() + "!");
                e.printStackTrace();
            }
    }
}