package tau.network.civilizations.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class LavaPoolPopulator extends CivPopulator{
    private final double chance;

    @Override
    public double chanceForAppearing() {
        return chance;
    }

    public LavaPoolPopulator(double chance){
        this.chance = chance;
    }

    @Override
    public void generate(World world, ChunkGenerator.ChunkData chunk, int x, int z, int maxheight, int chunkx, int chunkz) {
        chunk.setBlock(x,maxheight,z,Material.LAVA);
    }
}
