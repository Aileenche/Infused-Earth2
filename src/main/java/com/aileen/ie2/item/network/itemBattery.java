package com.aileen.ie2.item.network;

import com.aileen.ie2.api.IIEBattery;
import com.aileen.ie2.item.ItemIE;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

/**
 * Created by Aileen on 08.02.2015.
 */
public class itemBattery extends ItemIE implements IIEBattery {

    private int capacity;
    private boolean creative;

    public itemBattery(String unlocalizedName, int maxbuffer) {
        this(unlocalizedName, maxbuffer, false);
    }

    public itemBattery(String unlocalizedName, int maxbuffer, boolean creative) {
        super(unlocalizedName);
        this.setMaxStackSize(1);
        this.capacity = maxbuffer;
        this.creative = creative;
    }

    public int getFree(ItemStack container) {
        if (creative) {
            return 0;
        }
        if (container.stackTagCompound == null) {
            container.stackTagCompound = new NBTTagCompound();
        }
        int energy = container.stackTagCompound.getInteger("Energy");

        return (this.capacity - energy);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int load(ItemStack container, int submitted) {
        if (creative) {
            return 0;
        }
        if (container.stackTagCompound == null) {
            container.stackTagCompound = new NBTTagCompound();
        }
        int energy = container.stackTagCompound.getInteger("Energy");
        int free = getFree(container);
        if (submitted > 500) {
            if (500 > free) {
                energy = this.capacity;
                container.stackTagCompound.setInteger("Energy", energy);
                return free;
            } else {
                energy += 500;
                container.stackTagCompound.setInteger("Energy", energy);
                return 500;
            }
        } else {
            if (submitted > free) {
                energy = this.capacity;
                container.stackTagCompound.setInteger("Energy", energy);
                return free;
            } else {
                energy += submitted;
                container.stackTagCompound.setInteger("Energy", energy);
                return submitted;
            }
        }
    }

    public int drain(ItemStack container, int todrain) {
        if (creative) {
            return 500;
        }

        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
            return 0;
        }
        int energy = container.stackTagCompound.getInteger("Energy");

        if (todrain >= 500) {
            if (500 >= energy) {
                container.stackTagCompound.setInteger("Energy", 0);
                return energy;
            } else {
                energy -= 500;
                container.stackTagCompound.setInteger("Energy", energy);
                return 500;
            }
        } else {
            if (todrain >= energy) {
                container.stackTagCompound.setInteger("Energy", 0);
                return energy;
            } else {
                energy -= todrain;
                container.stackTagCompound.setInteger("Energy", energy);
                return todrain;
            }
        }
    }

    public int getEnergyStored(ItemStack container) {
        if (creative) {
            return (capacity / 2);
        }

        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
            return 0;
        }
        return container.stackTagCompound.getInteger("Energy");
    }

    public int getMaxEnergyStored(ItemStack container) {
        if (creative) {
            return (capacity);
        }
        return this.capacity;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List info, boolean useExtraInformation) {
        if (creative) {
            info.add(EnumChatFormatting.LIGHT_PURPLE + "CREATIVE");
            info.add(EnumChatFormatting.GREEN + "Buffer: " + (this.capacity/2));
            info.add(EnumChatFormatting.GREEN + "MaxBuffer: " + this.capacity);
        } else {
            if (itemstack.stackTagCompound == null || !itemstack.stackTagCompound.hasKey("Energy")) {
                itemstack.stackTagCompound = new NBTTagCompound();
                itemstack.stackTagCompound.setInteger("Energy", 0);
            }
            info.add(EnumChatFormatting.GREEN + "Buffer: " + itemstack.stackTagCompound.getInteger("Energy"));
            info.add(EnumChatFormatting.GREEN + "MaxBuffer: " + this.capacity);
        }
    }
}

