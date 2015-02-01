package com.aileen.ie2.block.TileBlocks;

import com.aileen.ie2.creativeTab.CreativeTabIE2;
import com.aileen.ie2.helper.LogHelper;
import com.aileen.ie2.libs.Reference;
import com.aileen.ie2.tileEntity.tileTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by Aileen on 27.01.2015.
 */
public class blockTank extends Block implements ITileEntityProvider{

    private String unlocalizedName;

    public blockTank(String unlocalized_Name) {
        super(Material.glass);
        this.setHardness(3);
        this.setResistance(10);
        this.setCreativeTab(CreativeTabIE2.blocks);
        this.unlocalizedName = unlocalized_Name;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!world.isRemote) {
            tileTank tt = (tileTank) world.getTileEntity(x, y, z);
            if (tt != null) {
                player.addChatMessage(new ChatComponentText("Fluid: "+ FluidRegistry.getFluidName(tt.getFluidID())+" -> "+tt.getCurrentAmmount() + "/" + tt.getMaxAmmount()+" -> "+((tt.getCurrentAmmount() / tt.getMaxAmmount()) * 100)+"%"));
                if (player.isSneaking()) {
                } else {
//                    if (tt.powered) {
//                        tt.powered = false;
//                        player.addChatMessage("Digger goes Sleep....");
//                    } else {
//                        tt.powered = true;
//                        player.addChatMessage("[Digger] Im here :D");
//                    }
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon("" + Reference.MOD_ID + ":" + this.unlocalizedName);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new tileTank();
    }
}
