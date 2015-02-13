package com.aileen.ie2.client.gui;

import com.aileen.ie2.container.ContainerNetController;
import com.aileen.ie2.libs.Numbers;
import com.aileen.ie2.libs.Textures;
import com.aileen.ie2.tileEntity.tileController;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aileen on 12.01.14.
 */
public class GuiNetController extends GuiContainer {
    private tileController tileController;

    public GuiNetController(InventoryPlayer inventoryPlayer, tileController tileController) {
        super(new ContainerNetController(inventoryPlayer, tileController));
        this.tileController = tileController;

        xSize = 176;
        ySize = 187;
    }


    @Override
    public void drawGuiContainerBackgroundLayer(float v, int i, int i2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.GUI_NETCONTROLLER);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        int i1 = this.tileController.getEnergyLevelScaled(56);
        this.drawTexturedModalRect(guiLeft + 8, guiTop + 79 - i1, 176, 69 - i1, 16, i1);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        FontRenderer font = new FontRenderer(mc.gameSettings, Textures.GUI_NETCONTROLLER, mc.getTextureManager(), true);
        font.drawString("Energy Level: " + Numbers.los(tileController.getOverallEnergy()), 27, 25, 65280);
        font.drawString("Energy Free: " + Numbers.los(tileController.getFreeEnergy()), 27, 35, 65280);
        font.drawString("Energy Filled to " + Numbers.los(tileController.getEnergyPercentage()) + "%", 27, 45, 65280);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        int xCoord = x - xStart;
        int yCoord = y - yStart;
        if (xCoord >= 8 && xCoord <= 24 && yCoord >= 24 && yCoord <= 79) {
            List strings = new LinkedList();
            font.FONT_HEIGHT = 4;
            strings.add("Energy Stored: " + Numbers.los(this.tileController.getOverallEnergy()));
            strings.add("Energy Maximum: " + Numbers.los(this.tileController.getOverallMaxEnergy()));
            drawHoveringText(strings, xCoord, yCoord, font);
        }
    }
}
