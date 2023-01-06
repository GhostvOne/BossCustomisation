package com.gizzmo.bosscustomisation;

import org.bukkit.plugin.java.JavaPlugin;

import com.gizzmo.bosscustomisation.event.Spawn_Boss;

public class BossCustomisation extends JavaPlugin {

	private static BossCustomisation instance;
	
	@Override
    public void onEnable() {
		instance = this;

		getServer().getPluginManager().registerEvents(new Spawn_Boss(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
    public static BossCustomisation getInstance() {
        return instance;
    }
}
