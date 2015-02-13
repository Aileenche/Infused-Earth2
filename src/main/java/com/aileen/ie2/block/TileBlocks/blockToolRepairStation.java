package com.aileen.ie2.block.TileBlocks;

import com.aileen.ie2.InfusedEarth;
import com.aileen.ie2.creativeTab.CreativeTabIE2;
import com.aileen.ie2.libs.GuiIds;
import com.aileen.ie2.libs.Reference;
import com.aileen.ie2.tileEntity.TileToolRepairStation;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Aileen on 07.02.2015.
 */
public class blockToolRepairStation extends BlockContainer {
    private final Random random = new Random();
    private String unlocalizedName;
    private IIcon iconside;
    private IIcon icontop;

    public blockToolRepairStation(String unlocalizedName) {
        super(Material.rock);
        this.blockHardness = 1.4F;
        this.blockResistance = 1000F;
        this.setCreativeTab(CreativeTabIE2.blocks);
        this.unlocalizedName = unlocalizedName;
    }

    @Override
    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.iconside = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.unlocalizedName);
        this.icontop = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.unlocalizedName + "_top");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return this.icontop;
        }
        return this.iconside;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (player.isSneaking()) {
            TileToolRepairStation te = (TileToolRepairStation) world.getTileEntity(x, y, z);
            if (te != null) {
            }
            return true;
        } else {
            if (!world.isRemote) {
                if (world.getTileEntity(x, y, z) instanceof TileToolRepairStation) {
                    FMLNetworkHandler.openGui(player, InfusedEarth.instance, GuiIds.TOOLREPAIRSTATION, world, x, y, z);
                }
            }
            return true;
        }
    }

    public IIcon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.getBlockMetadata(x, y, z);
        return getIcon(side, meta);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
        if (!world.isRemote) {
            TileToolRepairStation te = (TileToolRepairStation) world.getTileEntity(x, y, z);
            if (te != null) {
                for (int i = 0; i < te.internalItems.length; i++) {
                    ItemStack itemstack = te.getStackInSlot(i);
                    if (itemstack != null) {
                        float f = this.random.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                        EntityItem entityitem;

                        for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
                            int k1 = this.random.nextInt(21) + 10;

                            if (k1 > itemstack.stackSize) {
                                k1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= k1;
                            entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));
                            float f3 = 0.05F;
                            entityitem.motionX = (double) ((float) this.random.nextGaussian() * f3);
                            entityitem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double) ((float) this.random.nextGaussian() * f3);

                            if (itemstack.hasTagCompound()) {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                            }
                        }
                    }
                }
                te.getWorldObj().removeTileEntity(x, y, z);
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileToolRepairStation();
    }
}
