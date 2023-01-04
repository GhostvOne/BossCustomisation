package com.gizzmo.bosscustomisation.taskManagement;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GetPlayersInFrontTask extends BukkitRunnable {

    private final Entity monster;
    private List<Player> players;

    public GetPlayersInFrontTask(Entity monster) {
        this.monster = monster;
    }

    @Override
    public void run() {
        players = new ArrayList<>();

        // Récupérez tous les joueurs autour du monstre
        for (Entity nearbyEntity : monster.getNearbyEntities(10, 10, 10)) {
        	if (nearbyEntity instanceof Player) {
        		players.add((Player) nearbyEntity);
        	}
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
