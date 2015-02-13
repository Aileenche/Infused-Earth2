package com.aileen.ie2.libs;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Sunny on 20.02.14.
 */
public class Coords {
    public int posX;
    public int posY;
    public int posZ;
    public int worldId;

    public Coords() {
    }

    public Coords(int par1, int par2, int par3, int worldId) {
        this.posX = par1;
        this.posY = par2;
        this.posZ = par3;
        this.worldId = worldId;
    }

    public Coords(Coords par1ChunkCoordinates) {
        this.posX = par1ChunkCoordinates.posX;
        this.posY = par1ChunkCoordinates.posY;
        this.posZ = par1ChunkCoordinates.posZ;
        this.worldId = par1ChunkCoordinates.worldId;
    }

    public void set(int par1, int par2, int par3, int worldId) {
        this.posX = par1;
        this.posY = par2;
        this.posZ = par3;
        this.worldId = worldId;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        if (nbtTagCompound.hasKey("x")) {
            this.posX = nbtTagCompound.getInteger("x");
        }
        if (nbtTagCompound.hasKey("y")) {
            this.posY = nbtTagCompound.getInteger("y");
        }
        if (nbtTagCompound.hasKey("z")) {
            this.posZ = nbtTagCompound.getInteger("z");
        }
        if (nbtTagCompound.hasKey("world")) {
            this.worldId = nbtTagCompound.getInteger("world");
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger("x", this.posX);
        nbtTagCompound.setInteger("y", this.posY);
        nbtTagCompound.setInteger("z", this.posZ);
        nbtTagCompound.setInteger("world", this.worldId);
    }
}
