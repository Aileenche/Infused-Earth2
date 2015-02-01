package com.aileen.ie2.block;

import com.aileen.ie2.creativeTab.CreativeTabIE2;
import com.aileen.ie2.handler.DataHandler;
import com.aileen.ie2.helper.LogHelper;
import com.aileen.ie2.libs.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import sun.invoke.empty.Empty;

import java.util.Random;

/**
 * Created by Aileen on 25.01.2015.
 */
public class BlockIE extends Block {
    private String unlocalized_Name;

    public BlockIE(String unlocalized_Name) {
        super(Material.rock);
        this.setHardness(3);
        this.setResistance(10);
        this.setCreativeTab(CreativeTabIE2.blocks);
        this.unlocalized_Name = unlocalized_Name;
    }

    @Override
    public String getUnlocalizedName() {
        return this.unlocalized_Name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon("" + Reference.MOD_ID + ":" + this.unlocalized_Name);
    }
}
