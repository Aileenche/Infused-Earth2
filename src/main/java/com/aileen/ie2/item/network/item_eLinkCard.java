package com.aileen.ie2.item.network;

import com.aileen.ie2.api.IEEnergyNet;
import com.aileen.ie2.creativeTab.CreativeTabIE2;
import com.aileen.ie2.libs.Reference;
import com.aileen.ie2.tileEntity.tileController;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class item_eLinkCard extends Item {
    @SideOnly(Side.CLIENT)
    public IIcon active;

    public item_eLinkCard(String unlocalizedName) {
        super();
        setMaxStackSize(1);
        setUnlocalizedName(unlocalizedName);
        setCreativeTab(CreativeTabIE2.blocks);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        this.itemIcon = reg.registerIcon(Reference.MOD_ID + ":item_eLinkCard_inactive");
        this.active = reg.registerIcon(Reference.MOD_ID + ":item_eLinkCard_active");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean useExtraInformation) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("linkCard");
            if (nbt.getBoolean("hasData")) {
                info.add(EnumChatFormatting.GREEN + "Linked Card");
                info.add(EnumChatFormatting.GREEN + "Controller [" + nbt.getInteger("conX") + "|" + nbt.getInteger("conY") + "|" + nbt.getInteger("conZ") + "] WorldID: " + nbt.getInteger("conW"));
            } else {
                info.add(EnumChatFormatting.RED + "Unlinked Card");
            }
        } else {
            info.add(EnumChatFormatting.RED + "Unlinked Card");
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconIndex(ItemStack stack) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("linkCard");
            if (nbt.getBoolean("hasData")) {
                return this.active;
            }
        }
        return this.itemIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("linkCard");
            if (nbt.getBoolean("hasData")) {
                return this.active;
            }
        }
        return this.itemIcon;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te != null) {
                if (itemStack.hasTagCompound() && !itemStack.getTagCompound().hasNoTags()) {
                    if (te instanceof tileController) {
                        NBTTagCompound nbt = new NBTTagCompound();
                        nbt.setTag("linkCard", new NBTTagCompound());
                        nbt.getCompoundTag("linkCard").setInteger("conX", x);
                        nbt.getCompoundTag("linkCard").setInteger("conY", y);
                        nbt.getCompoundTag("linkCard").setInteger("conZ", z);
                        nbt.getCompoundTag("linkCard").setInteger("conW", world.provider.dimensionId);
                        nbt.getCompoundTag("linkCard").setBoolean("hasData", true);
                        itemStack.setTagCompound(nbt);
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Saved Controller Position."));
                    }
                    if (te instanceof IEEnergyNet) {
                        if (itemStack.getTagCompound().getCompoundTag("linkCard").hasKey("conX")) {
                            NBTTagCompound nbt = itemStack.getTagCompound();
                            ((IEEnergyNet) te).setConPos(nbt.getCompoundTag("linkCard").getInteger("conX"), nbt.getCompoundTag("linkCard").getInteger("conY"), nbt.getCompoundTag("linkCard").getInteger("conZ"), nbt.getCompoundTag("linkCard").getInteger("conW"));
                            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Set Controller Position for Use."));
                        } else {
                            NBTTagCompound nbt = itemStack.getTagCompound().getCompoundTag("linkCard");
                            nbt.getCompoundTag("linkCard").setBoolean("hasData", false);
                            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You must Rightclick an Controller First."));
                        }
                    }
                } else {
                    NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setTag("linkCard", new NBTTagCompound());
                    nbt.getCompoundTag("linkCard").setBoolean("hasData", true);

                    if (te instanceof tileController) {
                        nbt.getCompoundTag("linkCard").setInteger("conX", x);
                        nbt.getCompoundTag("linkCard").setInteger("conY", y);
                        nbt.getCompoundTag("linkCard").setInteger("conZ", z);
                        nbt.getCompoundTag("linkCard").setInteger("conW", world.provider.dimensionId);
                        itemStack.setTagCompound(nbt);
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Saved Controller Position."));
                    }
                    if (te instanceof IEEnergyNet) {
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You must Rightclick an Controller First."));
                    }
                }
            }
        }
        return false;
    }
}


