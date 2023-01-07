package fr.maliimaloo.bosscustomisation;

import org.bukkit.plugin.java.JavaPlugin;

import fr.maliimaloo.bosscustomisation.event.Spawn_Boss;
import fr.maliimaloo.omegacore.api.OmegaAPI;

public class BossCustomisation extends JavaPlugin {

	private static BossCustomisation instance;
	
	@Override
    public void onEnable() {
		instance = this;

		getServer().getPluginManager().registerEvents(new Spawn_Boss(), this);
		getCommand("coller").setExecutor(new PasteCommand(this));

		if(OmegaAPI.getInstance() == null)
			System.out.println(" [Boss Customisation] L'instance OMEGAAPI est null");
		else 
			System.out.println(" [Boss Customisation] L'instance OMEGAAPI n'est pas null");
	}
	
	@Override
	public void onDisable() {
		
	}

    public static BossCustomisation getInstance() {
        return instance;
    }
}
