package com.aileen.ie2.tileEntity;

import com.aileen.ie2.api.IEEnergyNet;
import com.aileen.ie2.libs.Chunk;
import com.aileen.ie2.libs.Coords;
import com.aileen.ie2.libs.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by Aileen on 14.01.14.
 */
public class tileDigger extends TileEntity implements IEEnergyNet {
    public HashSet<Block> blackList = new HashSet<Block>();
    //    public boolean started = false;
//    public boolean finished = false;
    public boolean paused = false;
    public boolean flatten = false;
    public Coords controller = new Coords(0, 0, 0, 0);
    public ChunkCoordinates last = new ChunkCoordinates(0, 0, 0);
    int energyPerBlock = 20;
    int tempc = 0;
    int startchunkX = -123456;
    int startchunkZ = -123456;
    private ItemStack buffer;
    private int minecount = 0;
    private HashSet<Chunk> finishedChunks = new HashSet<Chunk>();

    public tileDigger() {
        blackList.add(Blocks.bedrock);
        blackList.add(Blocks.dirt);
        blackList.add(Blocks.grass);
        blackList.add(Blocks.tallgrass);
        blackList.add(Blocks.vine);
        blackList.add(Blocks.wooden_door);
        blackList.add(Blocks.iron_door);
        //blackList.add(Blocks.dirt);
//        blackList.add(Blocks.dirt);
//        blackList.add(Blocks.dirt);

    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public boolean hasValidController() {
        if (!worldObj.isRemote) {
            if (controller != null) {
                if (controller.posX == 0 && controller.posY == 0 && controller.posZ == 0) {
                    return false;
                }
                if (controller.posX != this.xCoord && controller.posY != this.yCoord && controller.posZ != this.zCoord) {
                    World world = MinecraftServer.getServer().worldServerForDimension(controller.worldId);
                    TileEntity tileEntity = world.getTileEntity(controller.posX, controller.posY, controller.posZ);
                    if (tileEntity != null) {
                        return true;
                    }
                    controller = new Coords(0, 0, 0, 0);
                    return false;
                }
            }
            controller = new Coords(0, 0, 0, 0);
        }
        return false;
    }

    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (startchunkX == -123456 && startchunkZ == -123456) {
                startchunkX = this.xCoord >> 4;
                startchunkZ = this.zCoord >> 4;
            }
            if (hasValidController()) {
                World world = MinecraftServer.getServer().worldServerForDimension(controller.worldId);
                TileEntity tilecontroller = world.getTileEntity(controller.posX, controller.posY, controller.posZ);
                if (tilecontroller != null && tilecontroller instanceof tileController) {
                    tileController te = (tileController) tilecontroller;
                    if (buffer != null) {
                        te.add2storage(buffer);
                        return;
                    }
                    if (paused) {
                        return;
                    }
                    if (te.getOverallEnergy() > energyPerBlock) {
                        for (int i = 0; i < 50; i++) {
                            if (!mineChunk(startchunkX, startchunkZ, te)) {
                                finishedChunks.add(new Chunk(startchunkX, startchunkZ));
                                selectNewChunk();
                                while (finishedChunks.contains(new Chunk(startchunkX, startchunkZ))) {
                                    selectNewChunk();
                                }
                            }
                        }
                    }
                } else {
                    //controller = new Coords(0, 0, 0, 0);
                }
            }
        }
    }


    public void selectNewChunk() {
        int rand = new Random().nextInt(100);
        if (rand > 0 && rand <= 25) {
            startchunkX++;
        }
        if (rand > 25 && rand <= 50) {
            startchunkX--;
        }
        if (rand > 50 && rand <= 75) {
            startchunkZ++;
        }
        if (rand > 75 && rand <= 100) {
            startchunkZ--;
        }
    }

    public boolean mineChunk(int chunkX, int chunkz, tileController te) {
        ChunkCoordinates next = findNextMineableInChunk(chunkX, chunkz);
        last = next;
        if (next != null) {
            List<ItemStack> stacks = dig(next.posX, next.posY, next.posZ);
            if (stacks != null) {
                te.extractEnergy(ForgeDirection.DOWN, energyPerBlock, false);
                for (ItemStack s : stacks) {
                    if (s != null) {
                        ItemStack returned = te.add2storage(s);
                        if (returned != null) {
                            buffer = returned;
                            return true;
                        }
                    }
                }
            }
            minecount++;
            return true;
        }
        return false;
    }

    private ChunkCoordinates findNextMineableInChunk(int chunkX, int chunkZ) {
        for (int z = chunkZ * 16; z <= (chunkZ * 16) + 16; z++) {
            for (int x = chunkX * 16; x <= (chunkX * 16) + 16; x++) {
                for (int y = 256; y >= 1; y--) {
                    if (worldObj.blockExists(x, y, z)) {
                        if (!worldObj.isAirBlock(x, y, z)) {
                            if (worldObj.getTileEntity(x, y, z) == null) {
                                Block block = worldObj.getBlock(x, y, z);
                                if (!blackList.contains(block)) {
                                    if (block instanceof BlockFluidClassic) {
                                        worldObj.setBlock(x, y, z, Blocks.air);
                                        worldObj.markBlockForUpdate(x, y, z);
                                        continue;
                                    }
                                    if (block instanceof BlockFluidBase) {
                                        worldObj.setBlock(x, y, z, Blocks.air);
                                        worldObj.markBlockForUpdate(x, y, z);
                                        continue;
                                    }
                                    if (block instanceof BlockLiquid) {
                                        worldObj.setBlock(x, y, z, Blocks.air);
                                        worldObj.markBlockForUpdate(x, y, z);
                                        continue;
                                    }
                                    if (block instanceof BlockStaticLiquid) {
                                        worldObj.setBlock(x, y, z, Blocks.air);
                                        worldObj.markBlockForUpdate(x, y, z);
                                        continue;
                                    }
                                    if (block instanceof BlockWood || block instanceof BlockLog) {
                                        continue;
                                    }
                                    if (block instanceof BlockLeaves || block instanceof BlockLeavesBase) {
                                        continue;
                                    }

                                    return new ChunkCoordinates(x, y, z);
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void setOwner(EntityPlayer player) {

    }


    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        controller.readFromNBT(nbtTagCompound);
        paused = nbtTagCompound.getBoolean("paused");
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        controller.writeToNBT(nbtTagCompound);
        nbtTagCompound.setBoolean("paused", paused);
    }


    public List<ItemStack> dig(int x, int y, int z) {
        if (!this.worldObj.isRemote) {
            if (worldObj.blockExists(x, y, z)) {
                if (!worldObj.isAirBlock(x, y, z)) {
                    if (worldObj.getTileEntity(x, y, z) == null) {
                        Block blockid = worldObj.getBlock(x, y, z);
                        if (!blackList.contains(blockid)) {
                            List<ItemStack> stacks = blockid.getDrops(worldObj, x, y, z, blockid.getDamageValue(worldObj, x, y, z), 0);
                            if (flatten) {
                                if (y > 64) {
                                    worldObj.setBlock(x, y, z, Blocks.air);
                                } else {
                                    worldObj.setBlock(x, y, z, Blocks.dirt);
                                }
                            } else {
                                worldObj.setBlock(x, y, z, Blocks.dirt);
                            }
                            worldObj.markBlockForUpdate(x, y, z);
                            return stacks;
                        }
                    }
                }
            }
        }
        return null;
    }

//    private ChunkCoordinates findNextMinable() {
//        if (laststage == -1) {
//            this.laststage = yCoord - 1;
//            this.tempstage = yCoord - 1;
//            return null;
//        }
//        for (int y = laststage; y > 0; y--) {
//            for (int z = zCoord - quarrieRange; z < zCoord + quarrieRange; z++) {
//                for (int x = xCoord - quarrieRange; x < xCoord + quarrieRange; x++) {
//                    if (worldObj.blockExists(x, y, z)) {
//                        if (!worldObj.isAirBlock(x, y, z)) {
//                            if (!worldObj.blockHasTileEntity(x, y, z)) {
//                                int blockid = worldObj.getBlockId(x, y, z);
//                                if (!blackList.contains(blockid)) {
//                                    Block block = Block.blocksList[blockid];
//                                    if (block instanceof BlockFluidClassic) {
//                                        continue;
//                                    }
//                                    if (block instanceof BlockFluid) {
//                                        continue;
//                                    }
//                                    if (block instanceof BlockFluidBase) {
//                                        continue;
//                                    }
//                                    return new ChunkCoordinates(x, y, z);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            laststage--;
//        }
//
//        this.powered = false;
//        return null;
//    }

    public void setConPos(int x, int y, int z, int w) {
        this.controller = new Coords(x, y, z, w);
    }

    private void mineStack(ItemStack stack) {
        stack.stackSize -= Utils.addToRandomInventoryAround(worldObj, xCoord, yCoord, zCoord, stack);
        if (stack.stackSize > 0) {
            float f = worldObj.rand.nextFloat() * 0.8F + 0.1F;
            float f1 = worldObj.rand.nextFloat() * 0.8F + 0.1F;
            float f2 = worldObj.rand.nextFloat() * 0.8F + 0.1F;
            EntityItem entityitem = new EntityItem(worldObj, xCoord + f, yCoord + f1 + 0.5F, zCoord + f2, stack);
            entityitem.lifespan = 1200;
            entityitem.delayBeforeCanPickup = 10;
            float f3 = 0.05F;
            entityitem.motionX = (float) worldObj.rand.nextGaussian() * f3;
            entityitem.motionY = (float) worldObj.rand.nextGaussian() * f3 + 1.0F;
            entityitem.motionZ = (float) worldObj.rand.nextGaussian() * f3;
            worldObj.spawnEntityInWorld(entityitem);
        }
    }
}

