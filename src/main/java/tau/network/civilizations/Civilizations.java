package tau.network.civilizations;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import tau.network.civilizations.populators.CivPopulatorManager;

public final class Civilizations extends JavaPlugin {


    private static Civilizations instance;
    public static Civilizations getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        CivPopulatorManager.init();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new CivilizationsChunkGen();
    }
}
