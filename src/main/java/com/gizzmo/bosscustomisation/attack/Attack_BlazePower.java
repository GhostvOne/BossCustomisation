package com.gizzmo.bosscustomisation.attack;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.gizzmo.bosscustomisation.BossCustomisation;

import java.util.ArrayList;
import java.util.List;

public class Attack_BlazePower implements Listener {
    private static final int ATTACK_COOLDOWN_SECONDS = 15;
    
    private static final int radiusAttack = 5;
    private static final int damageBrutAttack = 0;
    
    private static final int widthBlockChange = 5;
    private static final int lengthBlockChange = 5;
    
    private static final int restoreBlockChange = 5;
   

    // Pour stocker les bloques de lave temporaires
    private final List<Block> temporaryLavaBlocks = new ArrayList<>();
    private List<Player> playersNearbyDuringAttack = new ArrayList<>();
    
    public void blazePowerAttack(Creature boss) 
    {
        // Programmez l'attaque toutes les 1 minute
        new BukkitRunnable() {
            @Override
            public void run() {
            	 if (boss.getTarget() != null) {
            		 Bukkit.getServer().getConsoleSender().sendMessage("Je lance l'attaque !");
            		 
                     // Récupérez tous les joueurs devant le Boss
                     List<Player> playersInFront = getPlayersNearby(boss);

                     // Transformez les bloques en lave
                     transformBlocksIntoLava(boss, playersInFront, widthBlockChange, lengthBlockChange);

                     // Faites des dégâts aux joueurs devant le Boss
                     damagePlayers(playersInFront, damageBrutAttack);

                     // Retournez à l'état d'origine les bloques après 20 secondes
                     restoreBlocksAfterDelay(restoreBlockChange);
                 } else {
                	 return;
                 }
            }
        }.runTaskTimer(BossCustomisation.getInstance(), 0, ATTACK_COOLDOWN_SECONDS * 20);
    }

    private List<Player> getPlayersNearby(LivingEntity boss) {
        List<Player> players = new ArrayList<>();
        for (Entity nearbyEntity : boss.getNearbyEntities(20, 20, 20)) {
            if (nearbyEntity instanceof Player) {
                Player player = (Player) nearbyEntity;
                Bukkit.getServer().getConsoleSender().sendMessage("J'ai trouvée le joueur: " + nearbyEntity.getName() + " à 20 block");
                
                if(player.getLocation().distance(boss.getLocation()) < radiusAttack) {
                    players.add(player);
                    playersNearbyDuringAttack.add(player);
                    Bukkit.getServer().getConsoleSender().sendMessage("J'ai trouvée le joueur proche du boss: " + nearbyEntity.getName());
                }
            }
        }
        return players;
    }

    private void transformBlocksIntoLava(LivingEntity entity, List<Player> players, int width, int height) {
    	for (Player player : players) 
    	{
	    	Location playerLoc = player.getLocation();
	    	Block feetBlock = playerLoc.getBlock();
	    	// Transformez les bloques en lave en forme d'exagone
	        for (int x = -width; x <= width; x++) {
	            for (int z = -width; z <= width; z++) {
	                // Ne modifiez pas les bloques en dehors de l'exagone
	                if (Math.abs(x) + Math.abs(z) > width) {
	                    continue;
	                }
	
	                // Modifiez les bloques de l'exagone
	                for (int y = -height; y <= height; y++) {
	                    Block block = feetBlock.getRelative(x, y, z);
	                    // Ne modifiez que les bloques remplacés par de l'air
	                    if (block.getType() != Material.AIR) {
	                        temporaryLavaBlocks.add(block);
	                        block.setType(Material.BLACK_WOOL);
	                    }
	                }
	            }
	        }
        }
    }
    
    private void damagePlayers(List<Player> players, int damage) {
        for (Player player : players) {
            player.damage(damage);
        }
    }

    private void restoreBlocksAfterDelay(int delaySeconds) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Block block : temporaryLavaBlocks) {
                    block.setType(Material.GLOWSTONE);
                }
                temporaryLavaBlocks.clear();
                playersNearbyDuringAttack.clear();
            }
        }.runTaskLater(BossCustomisation.getInstance(), delaySeconds * 20);
    }
}