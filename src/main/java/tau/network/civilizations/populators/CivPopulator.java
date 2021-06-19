package tau.network.civilizations.populators;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public abstract class CivPopulator {

    public abstract double chanceForAppearing();

    public abstract void generate(World world, ChunkGenerator.ChunkData chunk, int x, int z, int maxheight, int chunkx, int chunkz);
}
