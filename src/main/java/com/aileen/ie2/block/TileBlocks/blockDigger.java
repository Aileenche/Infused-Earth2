package com.aileen.ie2.block.TileBlocks;

import com.aileen.ie2.creativeTab.CreativeTabIE2;
import com.aileen.ie2.libs.Reference;
import com.aileen.ie2.tileEntity.tileDigger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Aileen on 14.01.14.
 */
public class blockDigger extends BlockContainer implements ITileEntityProvider {
    private String unlocalizedName;
    private IIcon iconside;
    private IIcon icontop;
    private IIcon iconsidea;
    private IIcon icontopa;

    public blockDigger(String unlocalizedName) {
        super(Material.rock);
        this.unlocalizedName = unlocalizedName;
        this.setCreativeTab(CreativeTabIE2.blocks);
        this.blockHardness = 1.4F;
        this.blockResistance = 1000F;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new tileDigger();
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.iconside = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.unlocalizedName);
        this.icontop = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.unlocalizedName + "_top");
        this.iconsidea = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.unlocalizedName + "_active");
        this.icontopa = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.unlocalizedName + "_top_active");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return this.icontop;
        }
        return this.iconside;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        tileDigger te = (tileDigger) world.getTileEntity(x, y, z);
        if (te != null) {
            if (te.hasValidController()) {
                if (side == 1) {
                    return this.icontopa;
                }
                return this.iconsidea;
            }
        }
        if (side == 1) {
            return this.icontop;
        }
        return this.iconside;
    }

    @Override
    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            tileDigger te = (tileDigger) world.getTileEntity(x, y, z);
            if (te != null) {
                if (player.isSneaking()) {
                    if (!te.paused) {
                        te.paused = true;
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Digger paused."));
                    } else {
                        te.paused = false;
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Digger unpaused."));
                    }
                } else {
                    if (te.hasValidController()) {
                        if (te.paused) {
                            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Digger is paused."));
                        } else {
                            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Digger is running. Last Coords: [" + te.last.posX + "|" + te.last.posY + "|" + te.last.posZ + "]"));
                        }
                    } else {
                        player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Controller not found!"));
                    }
                }
            }
            return true;
        }
        return true;
    }
}
