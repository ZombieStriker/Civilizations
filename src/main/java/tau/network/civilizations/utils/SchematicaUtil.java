package tau.network.civilizations.utils;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import tau.network.civilizations.Civilizations;

import java.io.File;
import java.io.IOException;

public class SchematicaUtil {

    public static void loadSchematic(Location location, String fileName) {
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(location.getWorld()), 10000);
        File schematic = new File(Civilizations.getInstance().getDataFolder() + File.separator + "/schematics/" + fileName + ".schematic");
        try {
            CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
            clipboard.rotate2D(0);
            clipboard.paste(session, new Vector(location.getX(), location.getY(), location.getZ()), true);
        } catch (MaxChangedBlocksException | DataException | IOException e) {
            e.printStackTrace();
        }
    }
}
