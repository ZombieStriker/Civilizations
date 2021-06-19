package tau.network.civilizations.oregen;

import org.bukkit.Material;

public enum OreType {

    COAL(Material.COAL_ORE, 100),
    IRON(Material.IRON_ORE, 100),
    COPPER(Material.COPPER_ORE, 100),
    REDSTONE(Material.REDSTONE_ORE, 200),
    LAPIS(Material.LAPIS_ORE, 200),
    DIAMOND(Material.DIAMOND_ORE, 500),
    ANCIENT_DEBRIS(Material.ANCIENT_DEBRIS, 1000),
    ;

    private final Material ore;
    private final int rarity;

    OreType(Material coalOre, int rarity) {
        this.ore = coalOre;
        this.rarity = rarity;
    }

    public int getRarity() {
        return rarity;
    }

    public Material getOre() {
        return ore;
    }
}
