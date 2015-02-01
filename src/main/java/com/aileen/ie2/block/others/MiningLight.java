package com.aileen.ie2.block.others;

import com.aileen.ie2.creativeTab.CreativeTabIE2;
import com.aileen.ie2.entity.Particles;
import com.aileen.ie2.handler.DataHandler;
import com.aileen.ie2.libs.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Aileen on 31.01.2015.
 */
public class MiningLight extends Block {

    private String unlocalizedName;

    public MiningLight(String unlocalizedName) {
        super(Material.rock);
        this.unlocalizedName = unlocalizedName;
        this.setLightLevel(1);
        this.setBlockBounds(0.4F, 0.6F, 0.4F, 0.6F, 1.0F, 0.6F);
        this.setHardness(0);
        this.setResistance(0);
        this.setCreativeTab(null);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon(Reference.MOD_ID + ":" + this.unlocalizedName);
        DataHandler.miningLight = reg.registerIcon(Reference.MOD_ID + ":particle_" + this.unlocalizedName);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 15;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        for (int i = 0; i < 4; i++) {

            float particleX = x + rand.nextFloat();
            float particleY = y + rand.nextFloat();
            float particleZ = z + rand.nextFloat();

            float particleMotionX = -0.5F + rand.nextFloat();
            float particleMotionY = -0.5F + rand.nextFloat();
            float particleMotionZ = -0.5F + rand.nextFloat();

            Particles.LIGHT.spawnParticle(world, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
        }
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
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

    public int getRenderType() {
        return 2;
    }

    @Override
    public int quantityDropped(Random rng) {
        return 0;
    }

}
