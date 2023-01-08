package fr.maliimaloo.bosscustomisation;

import fr.maliimaloo.omegacore.api.OmegaAPI;
import fr.maliimaloo.omegacore.api.world.WorldAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WorldCommand implements CommandExecutor {

    private final WorldAPI worldAPI;

    public WorldCommand() {
        this.worldAPI = OmegaAPI.getInstance().getWorldAPI();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("Veuillez spécifier une action à réaliser (create, delete, liste)");
            return true;
        }

        String action = args[0];

        switch (action) {
            case "create":
                if (args.length != 3) {
                    sender.sendMessage("[OmegaAPI] Utilisation de la commande : /world create <void:normal:nther:the_end> <nom_du_monde>");
                    return true;
                }

                String environment = args[1];
                String worldName = args[2];

                if (environment.equalsIgnoreCase("void")) {
                    return worldAPI.getWorldGeneratorAPI().generateVoidWorld(BossCustomisation.getInstance(), worldName, sender);
                } else {
                    return worldAPI.getWorldGeneratorAPI().generateWorld(BossCustomisation.getInstance(), worldName, environment, sender);
                }
            case "delete":
                if (args.length != 2) {
                    sender.sendMessage("[OmegaAPI] Utilisation de la commande : /world delete <nom_du_monde>");
                    return true;
                }

                String deleteWorldName = args[1];
                return worldAPI.deleteWorld(deleteWorldName, sender);
            case "liste":
                if (args.length != 1) {
                    sender.sendMessage("[OmegaAPI] Utilisation de la commande : /world liste");
                    return true;
                }

                if(sender != null)
                    sender.sendMessage("---------[Liste des mondes]---------");
                else
                    System.out.println("---------[Liste des mondes]---------");

                for(String existingWorldName : worldAPI.getExistingWorldNames()) {
                    if(sender != null)
                        sender.sendMessage("* " + existingWorldName);
                    else
                        System.out.println("* " + existingWorldName);
                }
                return true;
        }
        return false;
    }
}
