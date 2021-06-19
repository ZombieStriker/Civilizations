package tau.network.civilizations.oregen;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.concurrent.ThreadLocalRandom;

public class OreGenerator implements Listener {

    private static final BlockFace[] faces = new BlockFace[]{BlockFace.UP, BlockFace.DOWN, BlockFace.EAST, BlockFace.WEST, BlockFace.NORTH, BlockFace.SOUTH};

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.STONE) {
            for (BlockFace blockFace : faces) {
                Block nextFace = event.getBlock().getRelative(blockFace);
                if (nextFace.getType() == Material.STONE) {
                    for (OreType type : OreType.values()) {
                        if (ThreadLocalRandom.current().nextInt(type.getRarity()) == 0) {
                            nextFace.setType(type.getOre());
                        }
                    }
                }
            }
        }
    }
}
