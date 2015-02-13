package com.aileen.ie2.worldgen.Biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by Aileen on 05.01.14.
 */
public class InfusedBiome extends BiomeGenBase {
    public InfusedBiome(int par1) {
        super(par1);
        setBiomeName("InfusedBiome");
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        this.theBiomeDecorator.treesPerChunk = 3;
        this.theBiomeDecorator.generateLakes = false;
    }

    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor() {
        return 10682207;
    }

    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor() {
        return 3866368;
    }

    public int getSkyColorByTemp(float par1) {
        return 65535;
    }

    public int getWaterColorMultiplier() {
        return 16777215;
    }


}
