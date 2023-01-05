package com.gizzmo.bosscustomisation.boss;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import com.gizzmo.bosscustomisation.attack.Attack;

public class Boss
{
    private String name;
    private double maxHealth;
    private EntityType type;
    private Location spawnLocation;
    private boolean isAlive;
    
    private List<Attack> attacks;

    public Boss(String name, int maxHealth, EntityType entityType, Location spawnLocation, List<Attack> attacks) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.type = entityType;
        this.spawnLocation = spawnLocation;
        this.isAlive = true;
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
    
    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }
}
