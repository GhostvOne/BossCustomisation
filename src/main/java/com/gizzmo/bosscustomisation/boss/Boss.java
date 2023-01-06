package com.gizzmo.bosscustomisation.boss;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.gizzmo.bosscustomisation.attack.Attack;

public class Boss
{
	private static List<Boss> allBosses = new ArrayList<>();
	
    private String name;
    private double maxHealth;
    private EntityType type;
    private Location spawnLocation;
    private boolean isAlive;
    private boolean isAfight;
    
    private List<Attack> attacks;
   
    public Boss(String name, double maxHealth, EntityType entityType, Location spawnLocation, List<Attack> attacks) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.type = entityType;
        this.spawnLocation = spawnLocation;
        this.isAlive = true;
        this.isAfight = false;
        this.attacks = attacks;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name_) {
    	this.name = name_;
    }

    public double getMaxHealth() {
        return maxHealth;
    }
    
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public EntityType getType() {
        return type;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }
    
    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    
    public boolean isAFight() {
    	return isAfight;
    }
    
    public void setAfight(boolean afight) {
    	isAfight = afight;
    }
    
    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }
    
    public static boolean exists(String name) {
        for (Boss boss : allBosses) {
            if (boss.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public static Boss getBoss(String name) {
    	for(Boss boss : allBosses) {
    		if(boss.getName().equals(name)) {
    			return boss;
    		}
    	}
    	
    	return null;
    }
    
    public void spawn() {
        // Vérifiez que le Boss est toujours en vie avant de le faire spawn
        if (!isAlive()) {
            return;
        }
        
        if(!Boss.exists(name)) {
            // Créez une instance du monstre au niveau du spawn
            Entity entity = spawnLocation.getWorld().spawnEntity(spawnLocation, type);
            
            // Configurez le monstre selon vos préférences
            ((Creature)entity).setCustomName(name);
            ((Creature) entity).setHealth(maxHealth);
            
            allBosses.add(this);
        }
    }
}
