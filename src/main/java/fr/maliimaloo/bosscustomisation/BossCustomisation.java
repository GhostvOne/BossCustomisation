package fr.maliimaloo.bosscustomisation;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import fr.maliimaloo.bosscustomisation.event.Spawn_Boss;
import fr.maliimaloo.omegacore.api.OmegaAPI;
import fr.maliimaloo.omegacore.api.fichier.FichierAPI;

public class BossCustomisation extends JavaPlugin {

	private static BossCustomisation instance;
	
	@Override
    public void onEnable() {
		instance = this;

		getServer().getPluginManager().registerEvents(new Spawn_Boss(), this);
		getCommand("coller").setExecutor(new PasteCommand(this));
		
		FichierAPI api = OmegaAPI.getInstance().getFichierAPI();
		api.createFileProject(this, "", "test", "json");
		
		File fileJsonFile = api.getFile(this, "", "test", "json");
		String writeJson = "{ nom: \"warren\", age:  }";
		api.jsonFile().writeFile(fileJsonFile, writeJson);
		
		api.createFolderProject(this, "Schematic");
	}
	
	@Override
	public void onDisable() {
		
	}

    public static BossCustomisation getInstance() {
        return instance;
    }
}
