package me.dhg.comandos;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Youtuber implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
				for(String text : Variaveis.main.getConfig().getStringList("YoutuberMSG")){
					sender.sendMessage(Metodos.ColoredMsg(text));					
				}
		
		return false;
	}

}
