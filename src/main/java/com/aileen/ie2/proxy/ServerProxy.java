package com.aileen.ie2.proxy;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Admin on 25.01.2015.
 */
public class ServerProxy extends CommonProxy {
    @Override
    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName) {
        // NOOP
    }

    @Override
    public void handleTileTextureUpdate(int x, int y, int z, int style, int meta) {
        //NOOP
    }

//    @Override
//    public void registerRenderer() {
//
//    }
}