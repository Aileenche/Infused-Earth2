package com.aileen.ie2.worldgen.Biome;

import com.aileen.ie2.configuration.ConfigurationHandler;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Aileen on 05.01.14.
 */
public class ModBiome {
    public static InfusedBiome infusedBiome;
    public static void init() {
        infusedBiome = new InfusedBiome(ConfigurationHandler.getBiomeValue("infusedBiomeID"));
        //GameRegistry.addBiome(infusedBiome);

    }
}
