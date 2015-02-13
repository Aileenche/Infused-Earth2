package com.aileen.ie2.tileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import com.aileen.ie2.item.network.itemBattery;
import com.aileen.ie2.libs.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.UUID;

/**
 * Created by Aileen on 10.02.2015.
 */
public class tileController extends TileEntity implements IEnergyHandler, ISidedInventory {
    public ItemStack[] internalItems = new ItemStack[9];
    private UUID owner = null;
    private ItemStack itembuffer;
    private EnergyStorage buffer = new EnergyStorage(1000000000);


    public tileController() {
    }


    public ItemStack add2storage(ItemStack stack) {
        stack.stackSize -= (Utils.addToRandomInventoryAround(worldObj, xCoord, yCoord, zCoord, stack));
        if (stack.stackSize > 0) {
            if (itembuffer == null) {
                this.itembuffer = stack;
                return null;
            } else {
                return stack;
            }
        }
        return null;
    }

    public int getOverallEnergy() {
        return buffer.getEnergyStored();
    }

    public int getOverallMaxEnergy() {
        return buffer.getMaxEnergyStored();
    }

    public int getFreeEnergy() {
        return getOverallMaxEnergy() - getOverallEnergy();
    }

    public void updateEntity() {
        super.updateEntity();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        if (!worldObj.isRemote) {
            for (int i = 0; i < internalItems.length; i++) {
                if (internalItems[i] != null && internalItems[i].getItem() instanceof itemBattery) {
                    itemBattery bat = (itemBattery) internalItems[i].getItem();
                    buffer.extractEnergy(bat.load(internalItems[i], buffer.getEnergyStored()), false);
                }
            }
            if (itembuffer != null) {
                itembuffer.stackSize -= Utils.addToRandomInventoryAround(worldObj, xCoord, yCoord, zCoord, itembuffer);
                if (itembuffer.stackSize < 1) {
                    itembuffer = null;
                }
            }
        }
    }


    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        buffer.readFromNBT(nbtTagCompound);
        if (nbtTagCompound.hasKey("owner")) {
            owner = UUID.fromString(nbtTagCompound.getString("owner"));
        }
        NBTTagList nbttaglist = nbtTagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        this.internalItems = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.internalItems.length) {
                this.internalItems[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        buffer.writeToNBT(nbtTagCompound);
        if (owner != null) {
            nbtTagCompound.setString("owner", owner.toString());
        }
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.internalItems.length; ++i) {
            if (this.internalItems[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.internalItems[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        nbtTagCompound.setTag("Items", nbttaglist);
    }

    public int getEnergyPercentage() {
        return (int) (((float) getOverallEnergy() / (float) getOverallMaxEnergy()) * 100);
    }

    public int getEnergyLevelScaled(int par1) {
        float state = ((par1 / (float) getOverallMaxEnergy()) * (float) getOverallEnergy());
        return (int) state;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return buffer.receiveEnergy(maxReceive, false);
    }


    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return buffer.extractEnergy(maxExtract, false);
    }


    @Override
    public int getEnergyStored(ForgeDirection from) {
        return getOverallEnergy();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return getOverallMaxEnergy();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false;
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return this.internalItems.length;
    }

    @Override
    public ItemStack getStackInSlot(int par1) {
        return this.internalItems[par1];
    }

    @Override

    public ItemStack decrStackSize(int par1, int par2) {
        ItemStack itemstack;
        if (this.internalItems[par1].stackSize <= par2) {
            itemstack = this.internalItems[par1];
            this.internalItems[par1] = null;
            return itemstack;
        } else {
            itemstack = this.internalItems[par1].splitStack(par2);

            if (this.internalItems[par1].stackSize == 0) {
                this.internalItems[par1] = null;
            }

            return itemstack;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.internalItems[par1] != null) {
            ItemStack itemstack = this.internalItems[par1];
            this.internalItems[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.internalItems[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }

    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }
}
