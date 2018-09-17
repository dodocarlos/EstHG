package me.dhg.comandos;

import java.util.Random;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor{
	
	Random r = new Random();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] arg3) {
				
				if(sender instanceof Player){
					Player p = (Player) sender;
					
					if(Arrays.admin.contains(p)){
						p.teleport(p.getWorld().getSpawnLocation());
					}else{
						if(Variaveis.iniciou == false){
							p.teleport(p.getWorld().getSpawnLocation());
						}else{
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "O Torneio ja iniciou !");
						}
					}
					
				}
		
		return false;
	}

}
