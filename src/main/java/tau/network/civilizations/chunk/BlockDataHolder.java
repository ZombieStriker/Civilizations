package tau.network.civilizations.chunk;

import org.bukkit.Material;

public class BlockDataHolder {

    private int x;
    private int y;
    private int z;
    private Material material;

    public BlockDataHolder(int x, int y, int z, Material material) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.material = material;
    }

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
