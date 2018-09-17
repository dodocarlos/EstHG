package me.dhg.comandos;

import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetTime implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
				
			if(sender.hasPermission("dHG.cmd.settime")){
				if(args.length < 1){
					sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /settime <tempo>");
				}else if(args.length > 1){
					sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /settime <tempo>");
				}else{
					try{						
						if(Variaveis.iniciou == false){
							Variaveis.tempoInicio = Integer.valueOf(args[0]);
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Tempo de inicio definido para " + Variaveis.InfoColor + args[0] + Variaveis.GeralColor + " segundos");
						}else if(Variaveis.invencibilidade){
							Variaveis.tempoInvencibilidade = Integer.valueOf(args[0]);
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Tempo de invencibilidade definido para " + Variaveis.InfoColor + args[0] + Variaveis.GeralColor + " segundos");
						}else{
							Variaveis.tempoPartida = Integer.valueOf(args[0]);
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Tempo da partida definido para " + Variaveis.InfoColor + args[0] + Variaveis.GeralColor + " segundos");
						}
					}catch(NumberFormatException e){
						sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use apenas numeros !");
					}
				}
			}else{
				sender.sendMessage(Variaveis.SemPerm);
			}
		
		return false;
	}

	
	
}
