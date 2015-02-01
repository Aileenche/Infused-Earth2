package com.aileen.ie2.tileEntity;

import com.aileen.ie2.api.IHarvesttool;
import com.aileen.ie2.helper.LogHelper;
import com.aileen.ie2.libs.DigitalItemstack;
import com.aileen.ie2.libs.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

/**
 * Created by Aileen on 31.01.2015.
 */
public class tileToolExtractor extends TileEntity implements ISidedInventory {
    private static final int[] slots_top = new int[]{27};
    private static final int[] slots_sides = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};

    private ItemStack[] furnaceItemStacks = new ItemStack[28];

    private int ttu = 0;


    public tileToolExtractor() {

    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory() {
        return this.furnaceItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1) {
        ItemStack stack = null;
        if (par1 == 27) {
            return this.furnaceItemStacks[par1];
        }
        if (this.furnaceItemStacks[27] != null && this.furnaceItemStacks[27].getItem() instanceof IHarvesttool) {
            IHarvesttool tool = (IHarvesttool) this.furnaceItemStacks[27].getItem();
            DigitalItemstack harvester = tool.getItemstackInSlot(this.furnaceItemStacks[27], par1);
            if (harvester != null) {
                DigitalItemstack dstack = harvester;
                if (dstack.stackSize > 64) {
                    stack = new ItemStack(dstack.getItem(), 64, dstack.getItemDamage());
                    return stack;
                } else {
                    stack = new ItemStack(dstack.getItem(), dstack.stackSize, dstack.getItemDamage());
                    return stack;
                }
            }
        }
        return stack;
    }

    public int getStacksizeInSlot(int slot) {
        if (this.furnaceItemStacks[27] != null && this.furnaceItemStacks[27].getItem() instanceof IHarvesttool) {
            IHarvesttool tool = (IHarvesttool) this.furnaceItemStacks[27].getItem();

            DigitalItemstack harvester = tool.getItemstackInSlot(this.furnaceItemStacks[27], slot);

            if (harvester != null) {
                return harvester.stackSize;
            }
        }
        return -1;
    }

    public ItemStack decrStackSize(int par1, int par2) {
        ItemStack itemstack;
        if (par1 == 27) {
            if (this.furnaceItemStacks[par1].stackSize <= par2) {
                itemstack = this.furnaceItemStacks[par1];
                this.furnaceItemStacks[par1] = null;
                return itemstack;
            } else {
                itemstack = this.furnaceItemStacks[par1].splitStack(par2);

                if (this.furnaceItemStacks[par1].stackSize == 0) {
                    this.furnaceItemStacks[par1] = null;
                }

                return itemstack;
            }
        } else {
            if (this.furnaceItemStacks[27] != null && this.furnaceItemStacks[27].getItem() instanceof IHarvesttool) {
                IHarvesttool tool = (IHarvesttool) this.furnaceItemStacks[27].getItem();
                DigitalItemstack harvester = tool.getItemstackInSlot(this.furnaceItemStacks[27], par1);
                if (harvester != null) {
                    ItemStack userstack;
                    DigitalItemstack toolstack;
                    toolstack = new DigitalItemstack(harvester.getItem(), (harvester.stackSize - par2), harvester.getItemDamage());
                    if (toolstack.stackSize <= 0) {
                        if (tool.clearItemstackInSlot(this.furnaceItemStacks[27], par1)) {
                            userstack = new ItemStack(harvester.getItem(), par2, harvester.getItemDamage());
                            return userstack;
                        }
                    } else {
                        if (tool.setItemstackInSlot(this.furnaceItemStacks[27], par1, toolstack)) {
                            userstack = new ItemStack(harvester.getItem(), par2, harvester.getItemDamage());
                            return userstack;
                        }
                    }
                }
            }
        }
        return new ItemStack(Blocks.diamond_ore, 62);
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.furnaceItemStacks[par1] != null) {
            ItemStack itemstack = this.furnaceItemStacks[par1];
            this.furnaceItemStacks[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.furnaceItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "Tool Extractor";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        NBTTagList nbttaglist = nbtTagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.furnaceItemStacks.length) {
                this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }


    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
            if (this.furnaceItemStacks[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbtTagCompound.setTag("Items", nbttaglist);
    }

    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (this.furnaceItemStacks[27] != null && this.furnaceItemStacks[27].getItem() instanceof IHarvesttool) {
                IHarvesttool tool = (IHarvesttool) this.furnaceItemStacks[27].getItem();
                ttu++;
                if (ttu >= 1) {
                    for (int i = 0; i < 27; i++) {
                        if (tool.getItemstackInSlot(this.furnaceItemStacks[27], i) != null) {
                            DigitalItemstack stack = tool.getItemstackInSlot(this.furnaceItemStacks[27], i);
                            ItemStack tempStack;
                            if (stack.stackSize > 64) {
                                tempStack = new ItemStack(stack.getItem(), 64, stack.getItemDamage());
                            } else {
                                tempStack = new ItemStack(stack.getItem(), stack.stackSize, stack.getItemDamage());
                            }
                            int used = Utils.addToRandomInventoryAround(worldObj, xCoord, yCoord, zCoord, tempStack);
                            LogHelper.debug("used: " + used);
                            if (used > 0) {
                                this.decrStackSize(i, used);
                                break;
                            }
                        }
                    }
                    ttu = 0;
                }
            }
        }
    }


    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
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
        LogHelper.debug("Try insering " + itemstack.getDisplayName() + " into Slot " + i);
        if (i == 27 && itemstack.getItem() instanceof IHarvesttool) {
            LogHelper.debug("Returning true");
            return true;
        }
        LogHelper.debug("Returning false");
        return false;
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int side) {
        return null;
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int slot, ItemStack par2ItemStack, int side) {
        LogHelper.debug("Try insering " + par2ItemStack.getDisplayName() + " into Slot " + slot);
        if (slot == 27 && par2ItemStack.getItem() instanceof IHarvesttool) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int slot, ItemStack par2ItemStack, int side) {
        if (slot == 27) {
            return false;
        }
        return false;
    }

//    @Override
//    public Packet getDescriptionPacket() {
//        return PacketTypeHandler.populatePacket(new PacketTileUpdate(xCoord, yCoord, zCoord, orientation, state, customName));
//    }
}
