package tau.network.civilizations;

import org.bukkit.block.Biome;
import org.bukkit.util.noise.SimplexNoiseGenerator;

public enum CivBiomes {

    OCEAN(Biome.OCEAN,-1,1, -1),
    DESERT(Biome.DESERT,0.5,1,5325234),
    PLAINS(Biome.PLAINS,-0.5,0.5,64587),
    FOREST(Biome.FOREST,-0.5,0.5,4239865),
    MUSHROOM(Biome.MUSHROOM_FIELDS,-0.25,0.25,76954),
    TAIGA(Biome.TAIGA,-1,-0.5,124389),
    POLAR_ICECAPS(Biome.ICE_SPIKES,-1,-0.5,642380943),
    VOLCANIC(Biome.DESERT,-1,1,9435623),
    MESA(Biome.BADLANDS,0.5,1,124389032),
    ;

    private Biome biome;
    private double minTemp;
    private double maxTemp;
    private SimplexNoiseGenerator noiseGenerator;

    CivBiomes(Biome b,double minTemp, double maxTemp, int seed){
        this.biome = b;
        this.minTemp = minTemp;
        this.maxTemp=maxTemp;
        if(seed!=-1)
        noiseGenerator = new SimplexNoiseGenerator(seed);
    }

    public double getBiomeChanceHeight(double x ,double z){
        if(noiseGenerator==null){
            return -1;
        }
        return noiseGenerator.noise(x/450,z/450);
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public Biome getBiome() {
        return biome;
    }
}
