package com.aileen.ie2.libs;

/**
 * Created by Sunshine on 02.05.14.
 */
public class Chunk implements Comparable {
    public int posX;

    public int posZ;

    public Chunk() {
    }

    public Chunk(int par1, int par2) {
        this.posX = par1;
        this.posZ = par2;
    }

    public Chunk(Chunk par1ChunkCoordinates) {
        this.posX = par1ChunkCoordinates.posX;
        this.posZ = par1ChunkCoordinates.posZ;
    }

    public boolean equals(Object par1Obj) {
        if (!(par1Obj instanceof Chunk)) {
            return false;
        } else {
            Chunk chunkcoordinates = (Chunk) par1Obj;
            return this.posX == chunkcoordinates.posX && this.posZ == chunkcoordinates.posZ;
        }
    }


    public void set(int par1, int par2) {
        this.posX = par1;
        this.posZ = par2;
    }

    public int compareChunkCoordinate(Chunk par1ChunkCoordinates) {
        return (this.posZ == par1ChunkCoordinates.posZ ? this.posX - par1ChunkCoordinates.posX : this.posZ - par1ChunkCoordinates.posZ);
    }

    @Override
    public int compareTo(Object par1Obj) {
        return this.compareChunkCoordinate((Chunk) par1Obj);
    }
}
