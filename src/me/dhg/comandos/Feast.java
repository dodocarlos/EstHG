package me.dhg.comandos;

import me.dhg.utils.Variaveis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feast implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] arg3) {
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(Variaveis.feast == null){
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Feast nao deu spawn, apontando para o " + Variaveis.InfoColor + "Spawn");
						p.setCompassTarget(p.getWorld().getSpawnLocation());
					}else{
						p.setCompassTarget(Variaveis.feast);
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Bussola apontada para o " + Variaveis.InfoColor + "Feast");
					}
				}
		return false;
	}

}
