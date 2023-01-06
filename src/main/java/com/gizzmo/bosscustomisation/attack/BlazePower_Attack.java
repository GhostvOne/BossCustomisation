package com.gizzmo.bosscustomisation.attack;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.gizzmo.bosscustomisation.BossCustomisation;

import java.util.ArrayList;
import java.util.List;

public class BlazePower_Attack extends Attack {
    private static final int restoreCooldown = 5;

    private static final int widthChangeBlock = 5;
    private static final int lengthChangeBlock = 5;
    
    private static final Material materialChangeBlock = Material.BLACK_WOOL;
    private static final Material materialRestoreBlock = Material.BEDROCK;
   
    private BukkitTask attackScheduler;
    
    public BlazePower_Attack(int attackCooldown, int attackDamage, int attackRadius) {
        super(attackCooldown, attackDamage, attackRadius); // L'attaque sera lancée toutes les 15 secondes
    }

    // Pour stocker les bloques de lave temporaires
    private final List<Block> temporaryChangeBlocks = new ArrayList<>();
    private List<Player> playersNearbyDuringAttack = new ArrayList<>();
    
	@Override
	public void startAttack(Entity entity) {
		 // Programmez l'attaque toutes les 1 minute
		attackScheduler = new BukkitRunnable() {
            @Override
            public void run() {
            	if(entity instanceof Creature) {
            		Creature boss = (Creature) entity;
            		
            		 if (boss.getTarget() != null) {
                		 Bukkit.getServer().getConsoleSender().sendMessage("Je lance l'attaque !");
                		 
                         // Récupérez tous les joueurs devant le Boss
                         List<Player> playersInRange = getPlayersNearby(boss);

                         // Transformez les bloques en lave
                         transformBlocksIntoBlocks(boss, playersInRange, widthChangeBlock, lengthChangeBlock);

                         // Faites des dégâts aux joueurs devant le Boss
                         damagePlayers(playersInRange, attackDamage);

                         // Retournez à l'état d'origine les bloques après 20 secondes
                         restoreBlocksAfterDelay(restoreCooldown);
                     } else {
                    	 return;
                     }
            	}
            }
        }.runTaskTimer(BossCustomisation.getInstance(), 0, attackCooldown * 20);
	}
	
	@Override
	public void stopAttack() {
		attackScheduler.cancel();
		Bukkit.getServer().getConsoleSender().sendMessage("[Boss Customisation] J'arrète l'attaque BlazePower");
	}
	
    private List<Player> getPlayersNearby(Creature boss) {
        List<Player> players = new ArrayList<>();
        for (Entity nearbyEntity : boss.getNearbyEntities(20, 20, 20)) {
            if (nearbyEntity instanceof Player) {
                Player player = (Player) nearbyEntity;
                Bukkit.getServer().getConsoleSender().sendMessage("J'ai trouvée le joueur: " + nearbyEntity.getName() + " à 20 block");
                
                if(player.getLocation().distance(boss.getLocation()) < attackRadius) {
                    players.add(player);
                    playersNearbyDuringAttack.add(player);
                    Bukkit.getServer().getConsoleSender().sendMessage("J'ai trouvée le joueur proche du boss: " + nearbyEntity.getName());
                }
            }
        }
        return players;
    }

    private void transformBlocksIntoBlocks(Creature entity, List<Player> players, int width, int height) {
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
	                    	temporaryChangeBlocks.add(block);
	                        block.setType(materialChangeBlock);
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
                for (Block block : temporaryChangeBlocks) {
                    block.setType(materialRestoreBlock);
                }
                temporaryChangeBlocks.clear();
                playersNearbyDuringAttack.clear();
            }
        }.runTaskLater(BossCustomisation.getInstance(), delaySeconds * 20);
    }
}