package tau.network.civilizations.chunk;

import java.util.ArrayList;
import java.util.List;

public record ChunkPosition(int x, int z) {

    private final static List<ChunkPosition> positions = new ArrayList<>();

    public static ChunkPosition at(int x1, int z1) {
        for (ChunkPosition position : positions) {
            if (position.getX() == x1 && position.getZ() == z1)
                return position;
        }
        ChunkPosition newpos = new ChunkPosition(x1, z1);
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
