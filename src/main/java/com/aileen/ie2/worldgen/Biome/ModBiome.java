package com.aileen.ie2.worldgen.Biome;

import com.aileen.ie2.configuration.ConfigurationHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

/**
 * Created by Aileen on 05.01.14.
 */
public class ModBiome {
    public static InfusedBiome infusedBiome;
    public static void init() {
        infusedBiome = new InfusedBiome(ConfigurationHandler.getBiomeValue("infusedBiomeID"));
        //BiomeManager.addSpawnBiome(infusedBiome);
    }
}
