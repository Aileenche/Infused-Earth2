package com.aileen.ie2.worldgen;

import com.aileen.ie2.handler.DataHandler;
import com.aileen.ie2.helper.LogHelper;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * Created by Aileen on 04.01.14.
 */
public class WorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case -1:
                genNether();
                break;
            default:
            case 0:
                String s = world.getBiomeGenForCoords(chunkX * 16 + 8, chunkZ * 16 + 8).biomeName;
                if (s.startsWith("Infused")) {
                    //Own Biome double the ores... Stage 1
                    genSelenium(world, random, chunkX * 16, chunkZ * 16);
                    genRuby(world, random, chunkX * 16, chunkZ * 16);
                    genAmethyst(world, random, chunkX * 16, chunkZ * 16);
                    genTopas(world, random, chunkX * 16, chunkZ * 16);
                    genSapphire(world, random, chunkX * 16, chunkZ * 16);
                    genTitanium(world, random, chunkX * 16, chunkZ * 16);
                    gen_flowers(world, random, chunkX * 16, chunkZ * 16);
                    //Stage 2
                    genSelenium(world, random, chunkX * 16, chunkZ * 16);
                    genRuby(world, random, chunkX * 16, chunkZ * 16);
                    genAmethyst(world, random, chunkX * 16, chunkZ * 16);
                    genTopas(world, random, chunkX * 16, chunkZ * 16);
                    genSapphire(world, random, chunkX * 16, chunkZ * 16);
                    genTitanium(world, random, chunkX * 16, chunkZ * 16);
                    gen_flowers(world, random, chunkX * 16, chunkZ * 16);
                    gen_natureOres(world, random, chunkX * 16, chunkZ * 16);
                } else {
                    genSelenium(world, random, chunkX * 16, chunkZ * 16);
                    genRuby(world, random, chunkX * 16, chunkZ * 16);
                    genAmethyst(world, random, chunkX * 16, chunkZ * 16);
                    genTopas(world, random, chunkX * 16, chunkZ * 16);
                    genSapphire(world, random, chunkX * 16, chunkZ * 16);
                    genTitanium(world, random, chunkX * 16, chunkZ * 16);
                    gen_flowers(world, random, chunkX * 16, chunkZ * 16);
                }
                break;
            case 1:
                genEnd();
                break;
        }

    }

    private void gen_natureOres(World world, Random rand, int chunkX, int chunkZ) {
        for (int i = 0; i < 15; i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(80);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(Blocks.coal_ore, 4).generate(world, rand, randPosX, randPosY, randPosZ);
        }
        for (int i = 0; i < 15; i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(15);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(Blocks.diamond_ore, 4).generate(world, rand, randPosX, randPosY, randPosZ);
        }
        for (int i = 0; i < 15; i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(15);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(Blocks.emerald_ore, 4).generate(world, rand, randPosX, randPosY, randPosZ);
        }
        for (int i = 0; i < 15; i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(80);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(Blocks.gold_ore, 4).generate(world, rand, randPosX, randPosY, randPosZ);
        }
        for (int i = 0; i < 15; i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(80);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(Blocks.iron_ore, 4).generate(world, rand, randPosX, randPosY, randPosZ);
        }
        for (int i = 0; i < 15; i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(20);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(Blocks.lapis_ore, 4).generate(world, rand, randPosX, randPosY, randPosZ);
        }
        for (int i = 0; i < 15; i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(15);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(Blocks.redstone_ore, 4).generate(world, rand, randPosX, randPosY, randPosZ);
        }
        for (int i = 0; i < 15; i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(10);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(Blocks.glowstone, 4).generate(world, rand, randPosX, randPosY, randPosZ);
        }
    }

    private void genEnd() {

    }

    private void genTopas(World world, Random rand, int chunkX, int chunkZ) {
        for (int i = 0; i < (DataHandler.worldgen_block_Topas_Ore_vpc*DataHandler.worldgen_ore_multiplier); i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(DataHandler.worldgen_block_Topas_Ore_maxheight);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(DataHandler.block_Topas_Ore, DataHandler.worldgen_block_Topas_Ore_vs).generate(world, rand, randPosX, randPosY, randPosZ);
        }
    }
    private void genTitanium(World world, Random rand, int chunkX, int chunkZ) {
        for (int i = 0; i < (DataHandler.worldgen_block_Titanium_Ore_vpc*DataHandler.worldgen_ore_multiplier); i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(DataHandler.worldgen_block_Titanium_Ore_maxheight);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(DataHandler.block_Titanium_Ore, DataHandler.worldgen_block_Titanium_Ore_vs).generate(world, rand, randPosX, randPosY, randPosZ);
        }
    }

    private void genAmethyst(World world, Random rand, int chunkX, int chunkZ) {
        for (int i = 0; i < (DataHandler.worldgen_block_Amethyst_Ore_vpc*DataHandler.worldgen_ore_multiplier); i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(DataHandler.worldgen_block_Amethyst_Ore_maxheight);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(DataHandler.block_Amethyst_Ore, DataHandler.worldgen_block_Amethyst_Ore_vs).generate(world, rand, randPosX, randPosY, randPosZ);
        }
    }

    private void genSapphire(World world, Random rand, int chunkX, int chunkZ) {
        for (int i = 0; i < (DataHandler.worldgen_block_Sapphire_Ore_vpc*DataHandler.worldgen_ore_multiplier); i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(DataHandler.worldgen_block_Sapphire_Ore_maxheight);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(DataHandler.block_Sapphire_Ore, DataHandler.worldgen_block_Sapphire_Ore_vs).generate(world, rand, randPosX, randPosY, randPosZ);
        }
    }

    private void genRuby(World world, Random rand, int chunkX, int chunkZ) {
        for (int i = 0; i < (DataHandler.worldgen_block_Ruby_Ore_vpc*DataHandler.worldgen_ore_multiplier); i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(DataHandler.worldgen_block_Ruby_Ore_maxheight);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(DataHandler.block_Ruby_Ore, DataHandler.worldgen_block_Ruby_Ore_vs).generate(world, rand, randPosX, randPosY, randPosZ);
        }
    }

    private void genSelenium(World world, Random rand, int chunkX, int chunkZ) {
        for (int i = 0; i < (DataHandler.worldgen_block_Selenium_Ore_vpc*DataHandler.worldgen_ore_multiplier); i++) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(DataHandler.worldgen_block_Selenium_Ore_maxheight);
            int randPosZ = chunkZ + rand.nextInt(16);
            new WorldGenMinable(DataHandler.block_Selenium_Ore, DataHandler.worldgen_block_Selenium_Ore_vs).generate(world, rand, randPosX, randPosY, randPosZ);
        }
    }

    private void genNether() {
    }

    public void gen_flowers(World par1World, Random par2Random, int x, int z) {
        for (int tries = 0; tries < 70; tries++) {
            int i1 = x + par2Random.nextInt(16);
            int j1 = 64 + par2Random.nextInt(128);
            int k1 = z + par2Random.nextInt(16);
//            if ((par1World.isAirBlock(i1, j1, k1)) && (Blocks.blockList[ModBlocks.iceFlower.blockID]).canBlockStay(par1World, i1, j1, k1)) {
//                par1World.setBlock(i1, j1, k1, ModBlocks.iceFlower.blockID);
//            }
        }
    }
}