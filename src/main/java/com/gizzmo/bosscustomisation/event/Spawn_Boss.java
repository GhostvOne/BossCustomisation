package com.gizzmo.bosscustomisation.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.gizzmo.bosscustomisation.attack.BlazePower_Attack;

public class Spawn_Boss implements Listener 
{
	BlazePower_Attack attack = new BlazePower_Attack();
	
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) 
    {
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!item.getType().equals(Material.DIAMOND_AXE)) return;

        Location location = event.getPlayer().getLocation();
        LivingEntity boss = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        boss.setCustomName("Boss Zombie");
        boss.setHealth(20.0);

        Zombie zombie = (Zombie) boss;
        zombie.setCanPickupItems(false);

    }
    
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Creature && entity.getName().equals("Boss Zombie")) {
            Creature boss = (Creature) entity;
            attack.blazePowerAttack(boss);
        }
    }
}
