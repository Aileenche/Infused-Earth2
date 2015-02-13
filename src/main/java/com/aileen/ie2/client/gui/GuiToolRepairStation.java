package com.aileen.ie2.client.gui;

import com.aileen.ie2.container.ContainerToolRepairStation;
import com.aileen.ie2.libs.Textures;
import com.aileen.ie2.tileEntity.TileToolRepairStation;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sunny on 13.02.14.
 */
public class GuiToolRepairStation extends GuiContainer {
    private TileToolRepairStation tileToolRepairStation;

    public GuiToolRepairStation(InventoryPlayer inventory, TileToolRepairStation tileToolRepairStation) {
        super(new ContainerToolRepairStation(inventory, tileToolRepairStation));
        this.tileToolRepairStation = tileToolRepairStation;

        xSize = 176;
        ySize = 168;
    }

    public void drawItemStack(ItemStack par1ItemStack, int par2, int par3, String p_146982_4_) {
        List list = par1ItemStack.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);

        for (int k = 0; k < list.size(); ++k) {
            if (k == 0) {
                //TODO list.set(k, "\u00a7" + Integer.toHexString(par1ItemStack.getRarity().rarityColor) + (String) list.get(k));
            } else {
                list.set(k, EnumChatFormatting.GRAY + (String) list.get(k));
            }
        }
        this.func_146283_a(list, par2, par3);
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float v, int i, int i2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.GUI_REPAIRSTATION);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        int i1 = this.tileToolRepairStation.getEnergyLevelScaled(56);
        this.drawTexturedModalRect(guiLeft + 8, guiTop + 79 - i1, 176, 69 - i1, 16, i1);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        int xCoord = x - xStart;
        int yCoord = y - yStart;

        if (xCoord >= 8 && xCoord <= 24 && yCoord >= 24 && yCoord <= 79) {
            List strings = new LinkedList();
            FontRenderer font = new FontRenderer(mc.gameSettings, Textures.GUI_HIGHSPEED_FURNACE, mc.getTextureManager(), true);
            font.FONT_HEIGHT = 4;
            strings.add("Energy Stored: " + this.tileToolRepairStation.storage.getEnergyStored());
            strings.add("Energy Maximum: " + this.tileToolRepairStation.storage.getMaxEnergyStored());
            drawHoveringText(strings, xCoord, yCoord, font);
        }

    }
}
