package me.dhg.comandos;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Bc implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
				if(sender.hasPermission("dHG.cmd.bc")){
					if(args.length < 1){
						sender.sendMessage(Variaveis.tag + "Use: /bc <mensagem>");
					}else{
						StringBuilder sb = new StringBuilder();
						for(String texto : args){
							sb.append(texto + " ");
						}
						Bukkit.broadcastMessage(Variaveis.tag + Variaveis.InfoColor + Metodos.ColoredMsg(sb.toString()));
					}
				}
		
		return false;
	}

	
	
}
