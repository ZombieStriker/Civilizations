package tau.network.civilizations.populators;

import org.bukkit.Chunk;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.material.MaterialData;

public class WheatPopulator extends CivPopulator{
    private final double chance;

    @Override
    public double chanceForAppearing() {
        return chance;
    }

    public WheatPopulator(double chance){
        this.chance = chance;
    }

    @Override
    public void generate(World world, ChunkGenerator.ChunkData chunk, int x, int z, int maxheight, int chunkx, int chunkz) {
        chunk.setBlock(x,maxheight+1,z,Material.WHEAT);
        chunk.setBlock(x,maxheight,z,Material.FARMLAND);
    }
}
