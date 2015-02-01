package com.aileen.ie2.handler;

import com.aileen.ie2.client.gui.GuiToolExtractor;
import com.aileen.ie2.container.ContainerToolExtractor;
import com.aileen.ie2.helper.LogHelper;
import com.aileen.ie2.libs.GuiIds;
import com.aileen.ie2.tileEntity.tileToolExtractor;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Void Magic
 * <p/>
 * com.aileen.ie2.handler
 *
 * @author Cheriekeks
 *         <p/>
 *         10.01.14 17:14
 */
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if (ID == GuiIds.HIGHSPEED_FURNACE) {
//            TileHighspeedFurnace tileHighspeedFurnace = (TileHighspeedFurnace) world.getBlockTileEntity(x, y, z);
//            return new ContainerHighspeedFurnace(entityPlayer.inventory, tileHighspeedFurnace);
        } else if (ID == GuiIds.NETGENERATOR) {
//            TileNetGenerator tileNetGenerator = (TileNetGenerator) world.getBlockTileEntity(x, y, z);
//            return new ContainerNetGenerator(entityPlayer.inventory, tileNetGenerator);
        } else if (ID == GuiIds.NETCONTROLLER) {
//            TileNetworkBlockController tileNetworkBlockController = (TileNetworkBlockController) world.getBlockTileEntity(x, y, z);
//            return new ContainerNetController(entityPlayer.inventory, tileNetworkBlockController);
        } else if (ID == GuiIds.MACERATOR) {
//            TileMacerator tileMacerator = (TileMacerator) world.getBlockTileEntity(x, y, z);
//            return new ContainerMacerator(entityPlayer.inventory, tileMacerator);
        } else if (ID == GuiIds.TOOLREPAIRSTATION) {
//            TileToolRepairStation tileToolRepairStation = (TileToolRepairStation) world.getBlockTileEntity(x, y, z);
//            return new ContainerToolRepairStation(entityPlayer.inventory, tileToolRepairStation);
        } else if (ID == GuiIds.TOOLEXTRACTOR) {
            tileToolExtractor tileToolExtractor = (tileToolExtractor) world.getTileEntity(x, y, z);
            LogHelper.debug("ServerGuiHandler called -> ID: "+ID);
            return new ContainerToolExtractor(entityPlayer.inventory, tileToolExtractor);
        } else if (ID == GuiIds.NETCHEST) {
//            TileNetworkBlockChest tileNetworkBlockChest = (TileNetworkBlockChest) world.getBlockTileEntity(x, y, z);
//            return new ContainerNetChest(entityPlayer.inventory, tileNetworkBlockChest);
        } else if (ID == GuiIds.SOLARCHARGER) {
//            TileSolarGenerator1 tileSolarGenerator1 = (TileSolarGenerator1) world.getBlockTileEntity(x, y, z);
//            return new ContainerSolarCharger(entityPlayer.inventory, tileSolarGenerator1);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        if (ID == GuiIds.HIGHSPEED_FURNACE) {
//            TileHighspeedFurnace tileHighspeedFurnace = (TileHighspeedFurnace) world.getBlockTileEntity(x, y, z);
//            return new GuiHighspeedFurnace(entityPlayer.inventory, tileHighspeedFurnace);
        } else if (ID == GuiIds.NETGENERATOR) {
//            TileNetGenerator tileNetGenerator = (TileNetGenerator) world.getBlockTileEntity(x, y, z);
//            return new GuiNetGenerator(entityPlayer.inventory, tileNetGenerator);
        } else if (ID == GuiIds.NETCONTROLLER) {
//            TileNetworkBlockController tileNetworkBlockController = (TileNetworkBlockController) world.getBlockTileEntity(x, y, z);
//            return new GuiNetController(entityPlayer.inventory, tileNetworkBlockController);
        } else if (ID == GuiIds.MACERATOR) {
//            TileMacerator tileMacerator = (TileMacerator) world.getBlockTileEntity(x, y, z);
//            return new GuiMacerator(entityPlayer.inventory, tileMacerator);
        } else if (ID == GuiIds.TOOLREPAIRSTATION) {
//            TileToolRepairStation tileToolRepairStation = (TileToolRepairStation) world.getBlockTileEntity(x, y, z);
//            return new GuiToolRepairStation(entityPlayer.inventory, tileToolRepairStation);
        } else if (ID == GuiIds.TOOLEXTRACTOR) {
            LogHelper.debug("ClientGuiHandler called -> ID: "+ID);
            tileToolExtractor tileToolExtractor = (tileToolExtractor) world.getTileEntity(x, y, z);
            return new GuiToolExtractor(entityPlayer.inventory, tileToolExtractor);
        } else if (ID == GuiIds.NETCHEST) {
//            TileNetworkBlockChest tileNetworkBlockChest = (TileNetworkBlockChest) world.getBlockTileEntity(x, y, z);
//            return new GuiNetChest(entityPlayer.inventory, tileNetworkBlockChest);
        } else if (ID == GuiIds.SOLARCHARGER) {
//            TileSolarGenerator1 tileSolarGenerator1 = (TileSolarGenerator1) world.getBlockTileEntity(x, y, z);
//            return new GuiSolarCharger(entityPlayer.inventory, tileSolarGenerator1);
        }

        return null;
    }
}
