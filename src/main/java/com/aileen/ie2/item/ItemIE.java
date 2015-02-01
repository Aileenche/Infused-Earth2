package com.aileen.ie2.item;

import com.aileen.ie2.creativeTab.CreativeTabIE2;
import com.aileen.ie2.libs.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * Created by Aileen on 04.01.14.
 */
public class ItemIE extends Item {
    private String unlocalized_Name;

    public ItemIE(String unlocalizedName) {
        super();
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabIE2.blocks);
        this.unlocalized_Name = unlocalizedName;
        this.setUnlocalizedName(unlocalizedName);
    }

    @Override
    public String getUnlocalizedName() {
        return this.unlocalized_Name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("" + Reference.MOD_ID + ":" + this.unlocalized_Name);
    }
}
