package com.aileen.ie2.libs;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class DigitalItemstack {
    public int stackSize;
    public NBTTagCompound stackTagCompound;
    int itemDamage;
    private int maxStacksize = Integer.MAX_VALUE;
    private Item item;

    private cpw.mods.fml.common.registry.RegistryDelegate<Item> delegate;

    public DigitalItemstack(Block block) {
        this(block, 1);
    }

    public DigitalItemstack(Block block, int stackSize) {
        this(block, stackSize, 0);
    }

    public DigitalItemstack(Block block, int stackSize, int meta) {
        this(Item.getItemFromBlock(block), stackSize, meta);
    }

    public DigitalItemstack(Item item) {
        this(item, 1);
    }

    public DigitalItemstack(Item item, int stackSize) {
        this(item, stackSize, 0);
    }

    public DigitalItemstack(Item item, int stackSize, int meta) {
        setDelegate(item);
        this.stackSize = stackSize;
        this.itemDamage = meta;

        if (this.itemDamage < 0) {
            this.itemDamage = 0;
        }
    }

    public DigitalItemstack() {
    }

    public static DigitalItemstack loadItemStackFromNBT(NBTTagCompound nbtTagCompound) {
        DigitalItemstack itemstack = new DigitalItemstack();
        itemstack.readFromNBT(nbtTagCompound);
        return itemstack.getItem() != null ? itemstack : null;
    }

    public static boolean areItemStackTagsEqual(DigitalItemstack itemStack1, DigitalItemstack itemStack2) {
        return itemStack1 == null && itemStack2 == null ? true : (itemStack1 != null && itemStack2 != null ? (itemStack1.stackTagCompound == null && itemStack2.stackTagCompound != null ? false : itemStack1.stackTagCompound == null || itemStack1.stackTagCompound.equals(itemStack2.stackTagCompound)) : false);
    }

    public static boolean areItemStacksEqual(DigitalItemstack itemStack1, DigitalItemstack itemStack2) {
        return itemStack1 == null && itemStack2 == null ? true : (itemStack1 != null && itemStack2 != null ? itemStack1.isItemStackEqual(itemStack2) : false);
    }

    public Item getItem() {
        return this.delegate != null ? this.delegate.get() : null;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setShort("id", (short) Item.getIdFromItem(this.item));
        nbt.setInteger("Count", this.stackSize);
        nbt.setShort("Damage", (short) this.itemDamage);

        if (this.stackTagCompound != null) {
            nbt.setTag("tag", this.stackTagCompound);
        }

        return nbt;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        setDelegate(Item.getItemById(nbt.getShort("id")));
        this.stackSize = nbt.getInteger("Count");
        this.itemDamage = nbt.getShort("Damage");

        if (this.itemDamage < 0) {
            this.itemDamage = 0;
        }

        if (nbt.hasKey("tag", 10)) {
            this.stackTagCompound = nbt.getCompoundTag("tag");
        }
    }

    public void setDelegate(Item item) {
        this.delegate = item != null ? item.delegate : null;
        this.item = item;
    }

    public int getMaxStackSize() {
        return this.maxStacksize;
    }

    public boolean isStackable() {
        return this.getMaxStackSize() > 1 && (!this.isItemStackDamageable() || !this.isItemDamaged());
    }

    public boolean isItemDamaged() {
        return this.isItemStackDamageable() && getItem().isDamaged(new ItemStack(this.getItem()));
    }

    public boolean isItemStackDamageable() {
        return this.item.getMaxDamage(new ItemStack(this.getItem())) <= 0 ? false : !this.hasTagCompound() || !this.getTagCompound().getBoolean("Unbreakable");
    }

    public boolean hasTagCompound() {
        return this.stackTagCompound != null;
    }

    public NBTTagCompound getTagCompound() {
        return this.stackTagCompound;
    }

    public void setTagCompound(NBTTagCompound nbtTagCompound) {
        this.stackTagCompound = nbtTagCompound;
    }

    public DigitalItemstack copy() {
        DigitalItemstack itemstack = new DigitalItemstack(this.item, this.stackSize, this.itemDamage);

        if (this.stackTagCompound != null) {
            itemstack.stackTagCompound = (NBTTagCompound) this.stackTagCompound.copy();
        }

        return itemstack;
    }

    private boolean isItemStackEqual(DigitalItemstack itemStack) {
        return this.stackSize != itemStack.stackSize ? false : (this.item != itemStack.getItem() ? false : (this.itemDamage != itemStack.getItemDamage() ? false : (this.stackTagCompound == null && itemStack.stackTagCompound != null ? false : this.stackTagCompound == null || this.stackTagCompound.equals(itemStack.stackTagCompound))));
    }

    public boolean isItemEqual(DigitalItemstack itemStack) {
        return this.item == itemStack.getItem() && this.itemDamage == itemStack.getItemDamage();
    }

    public int getItemDamage() {
        return this.itemDamage;
    }

    public String toString() {
        return this.stackSize + "x" + this.item.getUnlocalizedName() + "@" + this.itemDamage;
    }

    public String getDisplayName() {
        String s = this.getItem().getItemStackDisplayName(new ItemStack(this.getItem()));

        if (this.stackTagCompound != null && this.stackTagCompound.hasKey("display", 10)) {
            NBTTagCompound nbttagcompound = this.stackTagCompound.getCompoundTag("display");

            if (nbttagcompound.hasKey("Name", 8)) {
                s = nbttagcompound.getString("Name");
            }
        }

        return s;
    }

    public DigitalItemstack setStackDisplayName(String name) {
        if (this.stackTagCompound == null) {
            this.stackTagCompound = new NBTTagCompound();
        }

        if (!this.stackTagCompound.hasKey("display", 10)) {
            this.stackTagCompound.setTag("display", new NBTTagCompound());
        }

        this.stackTagCompound.getCompoundTag("display").setString("Name", name);
        return this;
    }

}