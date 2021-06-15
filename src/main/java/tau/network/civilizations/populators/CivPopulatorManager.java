package tau.network.civilizations.populators;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.scheduler.BukkitRunnable;
import tau.network.civilizations.CivBiomes;
import tau.network.civilizations.Civilizations;
import tau.network.civilizations.chunk.BlockDataHolder;
import tau.network.civilizations.chunk.ChunkPosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CivPopulatorManager {

    private static HashMap<CivBiomes, List<CivPopulator>> populators = new HashMap<>();

    private static HashMap<ChunkPosition,List<BlockDataHolder>> toBeReplacedWith = new HashMap<>();

    public static void setHoldoverBlock(World world, int x, int y, int z, int chunkx, int chunkz, Material material){
       /* if(x < 0) {
            chunkx--;
            x+=16;
        }
        if(z < 0 ) {
            chunkz--;
            z+=16;
        }
        if(x >= 16){
            chunkx++;
            x-=16;
        }
        if(z >= 16){
            chunkz++;
            z-=16;
        }*/
        if(world.isChunkGenerated(chunkx,chunkz)) {
            int finalZ = z;
            int finalX = x;
            new BukkitRunnable() {
            public void run() {
                world.getBlockAt(finalX, y, finalZ).setType(material);
            }
        }.runTaskLater(Civilizations.getInstance(),2);
        }else{
            ChunkPosition position = ChunkPosition.at(chunkx,chunkz);
            if(toBeReplacedWith.containsKey(position)){
                toBeReplacedWith.get(position).add(new BlockDataHolder(x,y,z,material));
            }else {
                ArrayList<BlockDataHolder> dataholder = new ArrayList<>();
                dataholder.add(new BlockDataHolder(x,y,z,material));
                toBeReplacedWith.put(position, dataholder);
            }
        }
    }
    public static List<BlockDataHolder> getHoldoverBlocks(ChunkPosition location){
        return toBeReplacedWith.get(location);
    }
    public static void removeHoldoverBlocks(ChunkPosition position){
        toBeReplacedWith.remove(position);
    }

    public static void init(){
        List<CivPopulator> forest = new ArrayList<>();
        List<CivPopulator> plains = new ArrayList<>();
        List<CivPopulator> taiga = new ArrayList<>();
        List<CivPopulator> mushroom = new ArrayList<>();
        List<CivPopulator> volcanic = new ArrayList<>();
        List<CivPopulator> icecaps = new ArrayList<>();
        List<CivPopulator> desert = new ArrayList<>();

        forest.add(new WheatPopulator(0.01));
        forest.add(new OakTreePopulator(0.02));
        forest.add(new GrassPopulator(0.5));

        plains.add(new WheatPopulator(0.01));
        plains.add(new GrassPopulator(0.6));

        desert.add(new DeadBushPopulator(0.01));

        volcanic.add(new LavaPoolPopulator(0.01));

        populators.put(CivBiomes.FOREST,forest);
        populators.put(CivBiomes.PLAINS,plains);
        populators.put(CivBiomes.TAIGA,taiga);
        populators.put(CivBiomes.MUSHROOM,mushroom);
        populators.put(CivBiomes.VOLCANIC,volcanic);
        populators.put(CivBiomes.POLAR_ICECAPS,icecaps);
        populators.put(CivBiomes.DESERT,desert);
    }

    public static void populate(World world, CivBiomes biome, ChunkGenerator.ChunkData chunk, int x, int z, int maxheight, int chunkx, int chunkz){
        if(populators.containsKey(biome)){
            for(CivPopulator pop : populators.get(biome)){
                if(Math.random() <= pop.chanceForAppearing()){
                    pop.generate(world,chunk,x,z, maxheight,chunkx,chunkz);
                    return;
                }
            }
        }
    }
}
