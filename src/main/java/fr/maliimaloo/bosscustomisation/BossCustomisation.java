package fr.maliimaloo.bosscustomisation;

import fr.maliimaloo.bosscustomisation.event.WorldCommand;
import fr.maliimaloo.omegacore.api.fichier.FichierAPI;
import org.bukkit.plugin.java.JavaPlugin;

import fr.maliimaloo.bosscustomisation.event.Spawn_Boss;
import fr.maliimaloo.omegacore.api.OmegaAPI;

import java.util.ArrayList;

public class BossCustomisation extends JavaPlugin {

	private static BossCustomisation instance;
	
	@Override
    public void onEnable() {
		instance = this;

		getServer().getPluginManager().registerEvents(new Spawn_Boss(), this);
		getCommand("coller").setExecutor(new PasteCommand(this));
		getCommand("world").setExecutor(new WorldCommand());

		FichierAPI fichierAPI = OmegaAPI.getInstance().getFichierAPI();
		ArrayList<String> listeFichier = new ArrayList<>();
		listeFichier.add("Schematic");
		listeFichier.add("Test/TEST");

		fichierAPI.createFolderProject(this, listeFichier);
	}
	
	@Override
	public void onDisable() {
		
	}

    public static BossCustomisation getInstance() {
        return instance;
    }
}
