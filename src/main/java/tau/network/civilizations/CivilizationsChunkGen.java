package tau.network.civilizations;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;
import tau.network.civilizations.chunk.BlockDataHolder;
import tau.network.civilizations.chunk.ChunkPosition;
import tau.network.civilizations.populators.CivPopulatorManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CivilizationsChunkGen extends ChunkGenerator {

    private final SimplexNoiseGenerator noise1 = new SimplexNoiseGenerator(1111111111);
    private final SimplexNoiseGenerator noise2 = new SimplexNoiseGenerator(222222222);
    private final SimplexNoiseGenerator noise3 = new SimplexNoiseGenerator(3333333);
    private final SimplexNoiseGenerator noise4 = new SimplexNoiseGenerator(44444444);
    private final SimplexNoiseGenerator noise5 = new SimplexNoiseGenerator(55555555);
    private final SimplexNoiseGenerator noiseA1 = new SimplexNoiseGenerator(43625454);
    private final SimplexNoiseGenerator noiseA2 = new SimplexNoiseGenerator(26435675);

    private final SimplexNoiseGenerator noiseTemperature = new SimplexNoiseGenerator(2133321);
    private final SimplexNoiseGenerator noiseTemperatureA1 = new SimplexNoiseGenerator(3421465);
    private final SimplexNoiseGenerator noiseTemperatureA2 = new SimplexNoiseGenerator(2354324);

    public ChunkData generateChunkData(World world, Random random, int cx, int cz, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        for (int x1 = 0; x1 < 16; x1++) {
            for (int z1 = 0; z1 < 16; z1++) {
                chunkData.setBlock(x1, 0, z1, Material.BEDROCK);
                int x = x1 + (cx * 16);
                int z = z1 + (cz * 16);

                int terrainHeight = getTerrainHeight(x, z);

                CivBiomes civbiome = getBiomeAt(x, z);
                for (int y = 0; y < 255; y++)
                    biome.setBiome(x1, y, z1, civbiome.getBiome());

                for (int y = 1; y < terrainHeight - 3; y++) {
                    chunkData.setBlock(x1, y, z1, Material.STONE);
                }
                if (civbiome == CivBiomes.DESERT) {

                    for (int y = terrainHeight - 3; y <= terrainHeight; y++) {
                        chunkData.setBlock(x1, y, z1, Material.SAND);
                    }
                } else if (civbiome == CivBiomes.MESA) {

                    for (int y = terrainHeight - 3; y <= terrainHeight; y++) {
                        chunkData.setBlock(x1, y, z1, Material.TERRACOTTA);
                    }
                } else if (civbiome == CivBiomes.POLAR_ICECAPS) {
                    for (int y = terrainHeight - 3; y < terrainHeight - 1; y++) {
                        chunkData.setBlock(x1, y, z1, Material.DIRT);
                    }
                    chunkData.setBlock(x1, terrainHeight - 1, z1, Material.GRASS_BLOCK);
                    chunkData.setBlock(x1, terrainHeight, z1, Material.SNOW_BLOCK);
                } else if (civbiome == CivBiomes.VOLCANIC) {
                    for (int y = terrainHeight - 3; y < terrainHeight - 1; y++) {
                        chunkData.setBlock(x1, y, z1, Material.DIRT);
                    }
                    chunkData.setBlock(x1, terrainHeight - 1, z1, Material.BASALT);
                    chunkData.setBlock(x1, terrainHeight, z1, Material.BASALT);
                } else if (civbiome == CivBiomes.TAIGA) {
                    for (int y = terrainHeight - 3; y < terrainHeight - 1; y++) {
                        chunkData.setBlock(x1, y, z1, Material.DIRT);
                    }
                    chunkData.setBlock(x1, terrainHeight - 1, z1, Material.GRASS_BLOCK);
                    chunkData.setBlock(x1, terrainHeight, z1, Material.SNOW_BLOCK);

                } else if (civbiome == CivBiomes.MUSHROOM) {
                    for (int y = terrainHeight - 3; y < terrainHeight; y++) {
                        chunkData.setBlock(x1, y, z1, Material.DIRT);
                    }
                    chunkData.setBlock(x1, terrainHeight, z1, Material.MYCELIUM);

                } else {
                    for (int y = terrainHeight - 3; y < terrainHeight; y++) {
                        chunkData.setBlock(x1, y, z1, Material.DIRT);
                    }
                    chunkData.setBlock(x1, terrainHeight, z1, Material.GRASS_BLOCK);
                }
                if (terrainHeight < 62) {
                    for (int y = terrainHeight; y < 63; y++) {
                        chunkData.setBlock(x1, y, z1, Material.WATER);
                    }
                } else {
                    CivPopulatorManager.populate(world, civbiome, chunkData, x1, z1, terrainHeight, cx, cz);
                    if (CivPopulatorManager.getHoldoverBlocks(ChunkPosition.at(x1, z1)) != null) {
                        for (BlockDataHolder data : CivPopulatorManager.getHoldoverBlocks(ChunkPosition.at(x1, z1))) {
                            chunkData.setBlock(data.getX(), data.getY(), data.getZ(), data.getMaterial());
                        }
                        CivPopulatorManager.removeHoldoverBlocks(ChunkPosition.at(x1, z1));
                    }
                }
            }
        }
        return chunkData;
    }

    public List<BlockPopulator> getDefaultPopulators(World world) {
        return new ArrayList<>();
    }

    private int getTerrainHeight(int x, int z) {
        double terrainNoiseHeight = getTerrainNoise(x, z);
        return (int) (60 + (terrainNoiseHeight > 0 ? terrainNoiseHeight * 50 : terrainNoiseHeight * 10));
    }

    private double getTerrainNoise(double x, double z) {
        return (noise1.noise(x / 160, z / 160)
                + noise2.noise(x / 160, z / 160)
                + (noise3.noise(x / 160, z / 160) * noiseA1.noise(x / 160, z / 160))
                + (noise3.noise(x / 160, z / 160) * noiseA2.noise(x / 32, z / 32))
                + noise4.noise(x / 320, z / 320)
                + noise5.noise(x / 160, z / 160)) / 6;
    }

    public CivBiomes getBiomeAt(int x, int z) {
        int terrainHeight = getTerrainHeight(x, z);
        if (terrainHeight < 62) {
            return CivBiomes.OCEAN;
        }
        CivBiomes currentBiome = null;
        double chanceHeighest = -1;

        double tempnoise = (noiseTemperature.noise(((double) x) / 4000, ((double) z) / 4000) + (noiseTemperatureA1.noise(((double) x) / 400, ((double) z) / 400) * noiseTemperatureA2.noise(((double) x) / 400, ((double) z) / 400))) / 2;
        for (CivBiomes biomes : CivBiomes.values()) {
            if (tempnoise <= biomes.getMaxTemp() && tempnoise >= biomes.getMinTemp()) {
                double height = biomes.getBiomeChanceHeight(x, z);
                if (height > chanceHeighest) {
                    chanceHeighest = height;
                    currentBiome = biomes;
                }
            }
        }
        return currentBiome;
    }
}
