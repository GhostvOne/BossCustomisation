package com.gizzmo.bosscustomisation.event;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.gizzmo.bosscustomisation.attack.Attack;
import com.gizzmo.bosscustomisation.attack.BlazePower_Attack;
import com.gizzmo.bosscustomisation.boss.Boss;

public class Spawn_Boss implements Listener 
{
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) 
    {
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!item.getType().equals(Material.DIAMOND_AXE)) return;

        List<Attack> attacks = new ArrayList<>();
        attacks.add(new BlazePower_Attack(15, 0, 10));

        Boss zombieBoss = new Boss("Boss Zombie", 20.0, EntityType.ZOMBIE, event.getPlayer().getLocation(), attacks);
        zombieBoss.spawn();
    }

	@EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Creature) {
        	String nameEntity = entity.getName();

        	Boss boss = Boss.getBoss(nameEntity);

        	if(boss != null) {
        		if(!boss.isAFight()) {
        			for(Attack attack : boss.getAttacks()) {
                		if (attack != null) {
                    		attack.startAttack(entity);
                		}
                	}
                	
                	boss.setAfight(true);
        		}
        	}
        }
    }
}
