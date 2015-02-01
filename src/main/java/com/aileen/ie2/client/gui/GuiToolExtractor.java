package com.aileen.ie2.client.gui;

import com.aileen.ie2.container.ContainerToolExtractor;
import com.aileen.ie2.helper.LogHelper;
import com.aileen.ie2.libs.Textures;
import com.aileen.ie2.tileEntity.tileToolExtractor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Created by Sunny on 01.02.14.
 */
public class GuiToolExtractor extends GuiContainer {
    private tileToolExtractor tileToolExtractor;

    public GuiToolExtractor(InventoryPlayer inventory, tileToolExtractor tileToolExtractor) {
        super(new ContainerToolExtractor(inventory, tileToolExtractor));
        this.tileToolExtractor = tileToolExtractor;

        xSize = 176;
        ySize = 178;
    }

    public void drawItemStack(ItemStack par1ItemStack, int par2, int par3, String p_146982_4_) {
        List list = par1ItemStack.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);

        for (int k = 0; k < list.size(); ++k) {
            if (k == 0) {
                list.set(k, EnumChatFormatting.GRAY + (String) list.get(k));
                //TODO list.set(k, "\u00a7" + Integer.toHexString(par1ItemStack.getRarity().rarityColor) + (String) list.get(k));
            } else {
                list.set(k, EnumChatFormatting.GRAY + (String) list.get(k));
            }
        }
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        int x = par2 - xStart;
        int y = par3 - yStart;
        int slot = getSlotOnCoords(y, x);
        LogHelper.debug("Slot = " + slot);
        if (slot != -1) {
            list.add("Stacksize: " + tileToolExtractor.getStacksizeInSlot(slot));
        }
        this.func_146283_a(list, par2, par3);
    }

    private int getSlotOnCoords(int x, int y) {


        if (x >= 37 && x <= 54 && y >= 7 && y <= 24) {
            return 0;
        }
        if (x >= 37 && x <= 54 && y >= 25 && y <= 42) {
            return 1;
        }
        if (x >= 37 && x <= 54 && y >= 43 && y <= 60) {
            return 2;
        }
        if (x >= 37 && x <= 54 && y >= 61 && y <= 78) {
            return 3;
        }
        if (x >= 37 && x <= 54 && y >= 79 && y <= 96) {
            return 4;
        }
        if (x >= 37 && x <= 54 && y >= 97 && y <= 114) {
            return 5;
        }
        if (x >= 37 && x <= 54 && y >= 115 && y <= 132) {
            return 6;
        }
        if (x >= 37 && x <= 54 && y >= 133 && y <= 150) {
            return 7;
        }
        if (x >= 37 && x <= 54 && y >= 151 && y <= 168) {
            return 8;
        }
        if (x >= 55 && x <= 72 && y >= 7 && y <= 24) {
            return 9;
        }
        if (x >= 55 && x <= 72 && y >= 25 && y <= 42) {
            return 10;
        }
        if (x >= 55 && x <= 72 && y >= 43 && y <= 60) {
            return 11;
        }
        if (x >= 55 && x <= 72 && y >= 61 && y <= 78) {
            return 12;
        }
        if (x >= 55 && x <= 72 && y >= 79 && y <= 96) {
            return 13;
        }
        if (x >= 55 && x <= 72 && y >= 97 && y <= 114) {
            return 14;
        }
        if (x >= 55 && x <= 72 && y >= 115 && y <= 132) {
            return 15;
        }
        if (x >= 55 && x <= 72 && y >= 133 && y <= 150) {
            return 16;
        }
        if (x >= 55 && x <= 72 && y >= 151 && y <= 168) {
            return 17;
        }
        if (x >= 73 && x <= 91 && y >= 7 && y <= 24) {
            return 9;
        }
        if (x >= 73 && x <= 91 && y >= 25 && y <= 42) {
            return 10;
        }
        if (x >= 73 && x <= 91 && y >= 43 && y <= 60) {
            return 11;
        }
        if (x >= 73 && x <= 91 && y >= 61 && y <= 78) {
            return 12;
        }
        if (x >= 73 && x <= 91 && y >= 79 && y <= 96) {
            return 13;
        }
        if (x >= 73 && x <= 91 && y >= 97 && y <= 114) {
            return 14;
        }
        if (x >= 73 && x <= 91 && y >= 115 && y <= 132) {
            return 15;
        }
        if (x >= 73 && x <= 91 && y >= 133 && y <= 150) {
            return 16;
        }
        if (x >= 73 && x <= 91 && y >= 151 && y <= 168) {
            return 17;
        }
        return -1;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float v, int i, int i2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.GUI_TOOLEXTRACTOR);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRendererObj.drawString(tileToolExtractor.getInventoryName(), 8, 6, 4210752);
    }
}
