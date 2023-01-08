package fr.maliimaloo.bosscustomisation.event;

import fr.maliimaloo.omegacore.api.OmegaAPI;
import fr.maliimaloo.omegacore.api.worldgenerator.WorldGeneratorAPI;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nullable;

public class WorldCommand implements CommandExecutor {

    private final WorldGeneratorAPI worldGeneratorAPI;

    public WorldCommand() {
        this.worldGeneratorAPI = OmegaAPI.getInstance().getWorldGeneratorAPI();
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

                action = args[1];
                String worldName = args[2];

                switch (action){
                    case "void":
                        worldGeneratorAPI.generateVoidWorld(worldName, sender);
                        break;
                    default:
                        try {
                            World.Environment environment = World.Environment.valueOf(action.toUpperCase());
                            worldGeneratorAPI.generateWorld(worldName, environment, sender);
                        } catch(Exception e) {
                            if(sender != null)
                                sender.sendMessage("[OmegaAPI] Utilisation de la commande : /world create <void:normal:nther:the_end> <nom_du_monde>");
                            else
                                System.out.println("[OmegaAPI] Utilisation de la commande : /world create <void:normal:nther:the_end> <nom_du_monde>");
                        }
                        break;
                }
            case "delete":
                break;
            case "liste":
                break;
        }
        return true;
    }
}
