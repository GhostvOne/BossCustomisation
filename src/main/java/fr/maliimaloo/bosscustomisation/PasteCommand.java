package fr.maliimaloo.bosscustomisation;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maliimaloo.omegacore.api.OmegaAPI;
import fr.maliimaloo.omegacore.api.fichier.FichierAPI;
import fr.maliimaloo.omegacore.api.schematic.Schematic;

public class PasteCommand implements CommandExecutor {
	private BossCustomisation plugin;
	private FichierAPI fichierAPI;
	
    public PasteCommand(BossCustomisation plugin) {
		this.plugin = plugin;
		fichierAPI = OmegaAPI.getInstance().getFichierAPI();
	}

	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                String schematicName = args[0];
                // Récupérer le fichier schematic à l'aide de la méthode getFile de votre plugin API Fichier
                File schematicFile = fichierAPI.getFile(plugin, "Schematic", schematicName, "schem");
                if (schematicFile != null) {
                	
                    Schematic.get().pasteSchematic(player.getWorld(), schematicFile, player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
                    player.sendMessage("Le fichier schematic a été collé à votre emplacement actuel.");
                } else {
                    player.sendMessage("Le fichier schematic n'a pas été trouvé.");
                }
            } else {
                player.sendMessage("Utilisation de la commande : /coller <nom_du_fichier_schematic>");
            }
        } else {
            sender.sendMessage("Cette commande ne peut être utilisée que par un joueur.");
        }
        return true;
    }
}
