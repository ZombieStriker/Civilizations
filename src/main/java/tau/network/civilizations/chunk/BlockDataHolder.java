package tau.network.civilizations.chunk;

import org.bukkit.Material;

public record BlockDataHolder(int x, int y, int z, Material material) {

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public int getY() {
        return y;
    }

    public Material getMaterial() {
        return material;
    }
}
