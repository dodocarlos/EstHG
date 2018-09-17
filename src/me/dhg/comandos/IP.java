package me.dhg.comandos;

import me.dhg.utils.Variaveis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class IP implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "IP: " + Variaveis.InfoColor + Variaveis.serverIP);	
		
		return false;
	}

	
	
}
