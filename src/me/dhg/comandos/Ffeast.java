package me.dhg.comandos;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ffeast implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] arg3) {
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(p.hasPermission("dHG.cmd.ffeast")){
						Metodos.generateFeast(p.getLocation());
						Metodos.spawnFeast(p.getLocation());
					}
				}else{
					sender.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Necessario ser um player");
				}
		return false;
	}
	
}
