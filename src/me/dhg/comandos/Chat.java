package me.dhg.comandos;

import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Chat implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
				if(sender.hasPermission("dHG.cmd.chat")){
					if(Variaveis.chat){
						Variaveis.chat = false;
						Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor + "O chat foi desabilitado");
					}else{
						Variaveis.chat = true;
						Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor + "O chat foi habilitado");
					}
				}
		
		return false;
	}

}
