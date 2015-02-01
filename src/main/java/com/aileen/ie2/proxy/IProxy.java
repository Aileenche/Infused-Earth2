package com.aileen.ie2.proxy;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Admin on 25.01.2015.
 */

public interface IProxy
{
    public abstract void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName);

    public abstract void handleTileTextureUpdate(int x, int y, int z, int style, int meta);
}