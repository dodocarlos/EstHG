package me.dhg.comandos;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Specs implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
				
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(Arrays.semEspectadores.contains(p)){
						Arrays.semEspectadores.remove(p);
						for(Player pl : Arrays.espectador){
							p.showPlayer(pl);
						}
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Espectadores ativados");
					}else{
						Arrays.semEspectadores.add(p);
						for(Player pl : Arrays.espectador){
							p.hidePlayer(pl);
						}
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Espectadores desativados");
					}
				}
		
		return false;
	}
	
}
