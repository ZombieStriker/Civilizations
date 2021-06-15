package tau.network.civilizations.chunk;

import java.util.ArrayList;
import java.util.List;

public class ChunkPosition {

    private static List<ChunkPosition> positions = new ArrayList<>();

    private int x;
    private int z;

    private ChunkPosition(int x , int z){
        this.x=x;this.z=z;
    }

    public static ChunkPosition at(int x1, int z1) {
        for(ChunkPosition position : positions){
            if(position.getX()==x1&&position.getZ()==z1)
                return position;
        }
        ChunkPosition newpos = new ChunkPosition(x1,z1);
        positions.add(newpos);
        return newpos;
    }

    public int getZ() {
        return z;
    }

    public int getX() {
        return x;
    }
}
