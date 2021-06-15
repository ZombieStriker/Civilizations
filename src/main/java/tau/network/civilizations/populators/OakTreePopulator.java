package tau.network.civilizations.populators;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class OakTreePopulator extends CivPopulator{
    private final double chance;

    @Override
    public double chanceForAppearing() {
        return chance;
    }

    public OakTreePopulator(double chance){
        this.chance = chance;
    }

    @Override
    public void generate(World world, ChunkGenerator.ChunkData chunk, int x, int z, int maxheight, int chunkx, int chunkz) {
        CivPopulatorManager.setHoldoverBlock(world,x,maxheight+1,z,chunkx,chunkz,Material.OAK_LOG);
        CivPopulatorManager.setHoldoverBlock(world,x,maxheight+2,z,chunkx,chunkz,Material.OAK_LOG);
        CivPopulatorManager.setHoldoverBlock(world,x,maxheight+3,z,chunkx,chunkz,Material.OAK_LOG);
        CivPopulatorManager.setHoldoverBlock(world,x,maxheight+4,z,chunkx,chunkz,Material.OAK_LOG);
        CivPopulatorManager.setHoldoverBlock(world,x,maxheight+5,z,chunkx,chunkz,Material.OAK_LOG);
        CivPopulatorManager.setHoldoverBlock(world,x-1,maxheight+5,z,chunkx,chunkz,Material.OAK_LEAVES);
        CivPopulatorManager.setHoldoverBlock(world,x+1,maxheight+5,z,chunkx,chunkz,Material.OAK_LEAVES);
        CivPopulatorManager.setHoldoverBlock(world,x,maxheight+5,z-1,chunkx,chunkz,Material.OAK_LEAVES);
        CivPopulatorManager.setHoldoverBlock(world,x,maxheight+5,z+1,chunkx,chunkz,Material.OAK_LEAVES);
        CivPopulatorManager.setHoldoverBlock(world,x,maxheight+6,z,chunkx,chunkz,Material.OAK_LEAVES);
        //chunk.setBlock(x,maxheight+1,z,Material.DEAD_BUSH);
    }
}
