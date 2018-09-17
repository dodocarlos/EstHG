package me.dhg.comandos;

import me.dhg.principal.Main;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Report implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(args.length < 2){
			sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: " + Variaveis.InfoColor + " /report <nick> <motivo>");
			return true;
		}
		String nick = args[0];
		StringBuilder motivo = new StringBuilder();
		for(int i = 1; i < args.length; i ++){
			motivo.append(args[i] + " ");
		}
		
		if(sender instanceof Player){
			Player p = (Player) sender;
			Main.stats.addReport(nick, p.getName(), motivo.toString(), Variaveis.serverIP);
			p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Seu report foi enviado");
			for(Player pl : Bukkit.getOnlinePlayers()){
				if(pl.hasPermission("dhg.verreport")){
					pl.sendMessage(Variaveis.GeralColor + "§l-----------------------------------");
					pl.sendMessage(Variaveis.GeralColor + "Nick: " + Variaveis.InfoColor + nick);
					pl.sendMessage(Variaveis.GeralColor + "Reportado por: " + Variaveis.InfoColor + p.getName());
					pl.sendMessage(Variaveis.GeralColor + "Motivo: " + Variaveis.InfoColor + motivo.toString());
					pl.sendMessage(Variaveis.GeralColor + "§l-----------------------------------");
				}
			}
		}else{
			Main.stats.addReport(nick, "Console", motivo.toString(), Variaveis.serverIP);
			sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Seu report foi enviado");
			for(Player pl : Bukkit.getOnlinePlayers()){
				if(pl.hasPermission("dhg.verreport")){
					pl.sendMessage(Variaveis.GeralColor + "§l-----------------------------------");
					pl.sendMessage(Variaveis.GeralColor + "Nick: " + Variaveis.InfoColor + nick);
					pl.sendMessage(Variaveis.GeralColor + "Reportado por: " + Variaveis.InfoColor + " Console");
					pl.sendMessage(Variaveis.GeralColor + "Motivo: " + Variaveis.InfoColor + motivo.toString());
					pl.sendMessage(Variaveis.GeralColor + "§l-----------------------------------");
				}
			}
		}

		
		return false;
	}

}
