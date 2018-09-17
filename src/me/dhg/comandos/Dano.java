package me.dhg.comandos;

import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Dano implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
					if(sender.hasPermission("dHG.cmd.dano")){
						if(Variaveis.dano){
							Variaveis.dano = false;
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor + "Dano global ativado");
						}else{
							Variaveis.dano = true;
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor + "Dano global desativado");
						}	
					}
							
		return false;
	}
	
}
