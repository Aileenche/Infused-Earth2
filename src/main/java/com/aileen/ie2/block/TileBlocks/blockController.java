package com.aileen.ie2.block.TileBlocks;

import com.aileen.ie2.InfusedEarth;
import com.aileen.ie2.creativeTab.CreativeTabIE2;
import com.aileen.ie2.entity.CustomDamageSource;
import com.aileen.ie2.helper.LogHelper;
import com.aileen.ie2.libs.GuiIds;
import com.aileen.ie2.libs.Reference;
import com.aileen.ie2.tileEntity.tileController;
import com.sun.media.jfxmedia.events.PlayerStateEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.Random;

/**
 * Created by Aileen on 10.02.2015.
 */
public class blockController extends BlockContainer implements ITileEntityProvider {
    private final Random random = new Random();
    private String unlocalizedName;
    private IIcon iconside;
    private IIcon icontop;

    public blockController(String unlocalizedName) {
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
            //return this.icontop;
        }
        return this.iconside;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        tileController te = (tileController) world.getTileEntity(x, y, z);
        if (te != null) {
            if (!world.isRemote) {
                if(te.getOwner() == null){
                    te.setOwner(player.getPersistentID());
                    player.addChatComponentMessage(new ChatComponentText("Controller Configured to "+player.getCommandSenderName()+"."));
                }
                if(te.getOwner().getMostSignificantBits() == player.getPersistentID().getMostSignificantBits()){
                    if (world.getTileEntity(x, y, z) instanceof tileController) {
                        FMLNetworkHandler.openGui(player, InfusedEarth.instance, GuiIds.NETCONTROLLER, world, x, y, z);
                    }
                } else {
                    player.addChatComponentMessage(new ChatComponentText("This Controller does not belong to You!"));
                }
            }
        }
        return true;
    }

    public IIcon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.getBlockMetadata(x, y, z);
        return getIcon(side, meta);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new tileController();
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        tileController te = (tileController) world.getTileEntity(x, y, z);
        if (te != null) {
            if (!world.isRemote) {
                if(te.getOwner().getMostSignificantBits() != player.getPersistentID().getMostSignificantBits()){
                    player.addChatComponentMessage(new ChatComponentText("This Controller does not belong to You!"));
                    player.attackEntityFrom(new CustomDamageSource("controller"),100000000F);
                }
            }
        }
    }
}
