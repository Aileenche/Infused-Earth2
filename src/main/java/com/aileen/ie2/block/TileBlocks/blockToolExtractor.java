package com.aileen.ie2.block.TileBlocks;

import com.aileen.ie2.InfusedEarth;
import com.aileen.ie2.creativeTab.CreativeTabIE2;
import com.aileen.ie2.helper.LogHelper;
import com.aileen.ie2.libs.GuiIds;
import com.aileen.ie2.libs.Reference;
import com.aileen.ie2.tileEntity.tileToolExtractor;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
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
 * Created by Aileen on 31.01.2015.
 */
public class blockToolExtractor extends Block implements ITileEntityProvider {

    private final Random random = new Random();
    private String unlocalizedName;
    private IIcon iconside;
    private IIcon icontop;

    public blockToolExtractor(String unlocalized_Name) {
        super(Material.piston);
        this.setHardness(1.4F);
        this.setResistance(1000F);
        this.setCreativeTab(CreativeTabIE2.blocks);
        this.unlocalizedName = unlocalized_Name;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return this.icontop;
        }
        return this.iconside;
    }

    public IIcon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.getBlockMetadata(x, y, z);
        return getIcon(side, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (player.isSneaking()) {
            FMLNetworkHandler.openGui(player, InfusedEarth.instance, GuiIds.TOOLEXTRACTOR, world, x, y, z);
            return true;
        } else {
            if (!world.isRemote) {
                if (world.getTileEntity(x, y, z) instanceof tileToolExtractor) {
                    LogHelper.debug("Open Gui");
                    FMLNetworkHandler.openGui(player, InfusedEarth.instance, GuiIds.TOOLEXTRACTOR, world, x, y, z);
                }
            }
            return true;
        }
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

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
        if (!world.isRemote) {
            tileToolExtractor te = (tileToolExtractor) world.getTileEntity(x, y, z);
            if (te != null) {
                ItemStack itemstack = te.getStackInSlot(27);
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
                te.getWorldObj().removeTileEntity(x, y, z);
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new tileToolExtractor();
    }
}
