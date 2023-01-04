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
                     transformBlocksIntoLava(boss, playersInFront, 4, 4);

                     // Faites des dégâts aux joueurs devant le Boss
                     damagePlayers(playersInFront, 0);

                     // Retournez à l'état d'origine les bloques après 20 secondes
                     restoreBlocksAfterDelay(5);
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
                Bukkit.getServer().getConsoleSender().sendMessage("J'ai trouvée le joueur: " + nearbyEntity.getName() + " à 15 block");
                
                if(player.getLocation().distance(boss.getLocation()) < 10) {
                    players.add(player);
                    playersNearbyDuringAttack.add(player);
                    Bukkit.getServer().getConsoleSender().sendMessage("J'ai trouvée le joueur proche du boss: " + nearbyEntity.getName());
                }
            }
        }
        return players;
    }

    private void transformBlocksIntoLava(LivingEntity entity, List<Player> players, int width, int length) {
        for (Player player : players) {
            Location loc = player.getLocation();
            for (int x = -width; x <= width; x++) {
                for (int z = -length; z <= length; z++) {
                    // Obtenez le bloque sous le joueur
                    Block block = loc.add(x, -1, z).getBlock();

                    // Vérifiez que le bloque n'est pas de l'air
                    if (block.getType() != Material.AIR) {
                        temporaryLavaBlocks.add(block);
                        block.setType(Material.MAGMA_BLOCK);
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