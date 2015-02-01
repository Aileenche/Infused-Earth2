package com.aileen.ie2.proxy;

import com.aileen.ie2.tileEntity.tileTank;
import com.aileen.ie2.tileEntity.tileToolExtractor;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Admin on 25.01.2015.
 */
public abstract class CommonProxy implements IProxy {
    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(tileTank.class, "tileTank");
        GameRegistry.registerTileEntity(tileToolExtractor.class, "tileToolExtractor");
    }
}