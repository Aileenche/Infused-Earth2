package com.aileen.ie2.proxy;


import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Admin on 25.01.2015.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(x, y, z);

        if (tileEntity != null) {
            if (tileEntity instanceof TileEntity) {
//                ((TileEntity) tileEntity).setOrientation(orientation);
//                ((TileEntity) tileEntity).setState(state);
//                ((TileEntity) tileEntity).setCustomName(customName);
            }
        }
    }


    @Override
    public void handleTileTextureUpdate(int x, int y, int z, int style, int meta) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(x, y, z);
//        if (tileEntity != null && tileEntity instanceof INetworkBase) {
//            ((INetworkBase) tileEntity).set_Icon(style, meta);
//        }
    }


//    @Override
//    public void registerRenderer() {
//        RenderingRegistry.registerBlockHandler(RenderCable.CableRenderID, new RenderCable());
//    }

}
