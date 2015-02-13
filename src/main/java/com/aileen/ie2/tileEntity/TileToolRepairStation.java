package com.aileen.ie2.tileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import com.aileen.ie2.api.IEEnergyNet;
import com.aileen.ie2.api.IHarvesttool;
import com.aileen.ie2.api.IIEBattery;
import com.aileen.ie2.item.network.itemBattery;
import com.aileen.ie2.libs.Coords;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Aileen on 07.02.2015.
 */
public class TileToolRepairStation extends TileEntity implements IEnergyHandler, ISidedInventory, IEEnergyNet {
    public EnergyStorage storage = new EnergyStorage(32000);
    public ItemStack[] internalItems = new ItemStack[20];
    protected ForgeDirection orientation;
    protected byte state;
    protected String customName;
    private int repairrate = 2000;
    public Coords controller = new Coords(0,0,0,0);

    public TileToolRepairStation() {
        orientation = ForgeDirection.SOUTH;
        state = 0;
        customName = "Tool Repair Station";
    }

    public void setConPos(int x, int y, int z, int w) {
        this.controller.set(x, y, z, w);
    }

    @Override
    public boolean hasValidController() {
        if (controller != null) {
            if(controller.posX == 0 && controller.posY == 0 && controller.posZ == 0){
                return false;
            }
            if (controller.posX != this.xCoord && controller.posY != this.yCoord && controller.posZ != this.zCoord){
                World world = MinecraftServer.getServer().worldServerForDimension(controller.worldId);
                TileEntity tileEntity = world.getTileEntity(controller.posX,controller.posY,controller.posZ);
                if(tileEntity != null && tileEntity instanceof tileController){
                    return true;
                }
            }
        }
        controller = new Coords(0, 0, 0, 0);
        return false;
    }

    public boolean hasCustomName() {
        return customName != null && customName.length() > 0;
    }

    public int getSizeInventory() {
        return this.internalItems.length;
    }

    public ItemStack getStackInSlot(int par1) {
        return this.internalItems[par1];
    }

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

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.internalItems[par1] != null) {
            ItemStack itemstack = this.internalItems[par1];
            this.internalItems[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

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

    public String getInvName() {
        return this.isInvNameLocalized() ? this.customName : "container.furnace";
    }

    public boolean isInvNameLocalized() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        storage.readFromNBT(nbtTagCompound);
        controller.readFromNBT(nbtTagCompound);
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

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        storage.writeToNBT(nbtTagCompound);
        controller.writeToNBT(nbtTagCompound);
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

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean hasEnergy() {

        if (internalItems[19] != null && internalItems[19].getItem() instanceof IIEBattery) {
            storage.modifyEnergyStored(((itemBattery) internalItems[19].getItem()).drain(internalItems[19], this.getFreeEnergy()));
        }
        if (hasValidController()) {
            World world = MinecraftServer.getServer().worldServerForDimension(controller.worldId);
            TileEntity tilecontroller = world.getTileEntity(controller.posX, controller.posY, controller.posZ);
            if (tilecontroller != null && tilecontroller instanceof tileController) {
                tileController te = (tileController) tilecontroller;
                storage.receiveEnergy(te.extractEnergy(ForgeDirection.DOWN, getFreeEnergy(), false), false);
            }
        }
        return this.storage.getEnergyStored() >= repairrate;
    }

    public void updateEntity() {
        if (!this.worldObj.isRemote) {
            if (this.hasEnergy()) {
                if (internalItems[0] == null) {
                    for (int i = 1; i < 10; i++) {
                        if (internalItems[i] != null && (internalItems[i].getItem() instanceof IHarvesttool || internalItems[i].getItem() instanceof ItemTool || internalItems[i].getItem() instanceof ItemHoe || internalItems[i].getItem() instanceof ItemSword || internalItems[i].getItem() instanceof ItemArmor)) {
                            internalItems[0] = internalItems[i].copy();
                            internalItems[i] = null;
                            return;
                        }
                    }
                } else {
                    if (internalItems[0].getItemDamage() > 0 && (internalItems[0].getItem() instanceof IHarvesttool || internalItems[0].getItem() instanceof ItemTool || internalItems[0].getItem() instanceof ItemHoe || internalItems[0].getItem() instanceof ItemSword || internalItems[0].getItem() instanceof ItemArmor)) {
                        internalItems[0].setItemDamage(internalItems[0].getItemDamage() - 10);
                        storage.extractEnergy(repairrate, false);
                    } else {
                        for (int i = 10; i <= 18; i++) {
                            if (internalItems[i] == null) {
                                internalItems[i] = internalItems[0].copy();
                                internalItems[0] = null;
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    public void openChest() {
    }

    public void closeChest() {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return false;
    }

    public int getEnergyLevelScaled(int par1) {
        float state = ((par1 / (float) this.storage.getMaxEnergyStored()) * (float) this.storage.getEnergyStored());
        return (int) state;
    }

    public int getEnergyPercentage() {
        return (int) (((float) storage.getEnergyStored() / (float) storage.getMaxEnergyStored()) * 100);
    }

    public int getFreeEnergy() {
        return storage.getMaxEnergyStored() - storage.getEnergyStored();
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int i) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int i, ItemStack stack, int i2) {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack stack, int i2) {
        return false;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {

        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {

        return storage.extractEnergy(maxExtract, simulate);
    }


    @Override
    public int getEnergyStored(ForgeDirection from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {

        return storage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }
}
