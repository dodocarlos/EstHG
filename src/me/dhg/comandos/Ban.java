package me.dhg.comandos;

import me.dhg.principal.Main;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ban implements CommandExecutor{

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

			if(sender.hasPermission("dHG.cmd.ban")){
				if(args.length < 2){
					sender.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Use: /ban " + Variaveis.InfoColor + "nick " + Variaveis.InfoColor + "motivo");	
					return true;
				}
				String nick = args[0];
				StringBuilder motivo = new StringBuilder();
				for(int i = 1; i < args.length; i ++){
					motivo.append(args[i] + " ");
				}
				
				if(sender instanceof Player){
					Player banner = (Player) sender;
					if(!Main.stats.hasBanned(nick)){
						try{
							Player p = Bukkit.getPlayer(nick);
							Variaveis.main.stats.banPlayer(p, banner.getName(), motivo.toString(), "0");
							p.kickPlayer(Variaveis.tag + Variaveis.GeralColor + "Voce foi banido\nMotivo: " + Variaveis.InfoColor + motivo.toString());
						}catch(Exception e){
							Variaveis.main.stats.banPlayer(nick, banner.getName(), motivo.toString(), "0");
						}
					}else{
						sender.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Esse jogador ja esta banido");
						return true;
					}
				}else{
					if(!Main.stats.hasBanned(nick)){
						try{
							Player p = Bukkit.getPlayer(nick);
							Variaveis.main.stats.banPlayer(p, "Console", motivo.toString(), "0");
							p.kickPlayer(Variaveis.tag + Variaveis.GeralColor + "Voce foi banido \nMotivo: " + Variaveis.InfoColor + motivo.toString());
						}catch(Exception e){
							Variaveis.main.stats.banPlayer(nick, "Console", motivo.toString(), "0");
						}
					}else{
						sender.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Esse jogador ja esta banido");
						return true;
					}
				}
				sender.sendMessage(Variaveis.tag + Variaveis.GeralColor + "O jogador " + Variaveis.InfoColor + nick + Variaveis.GeralColor + " foi banido");
			}
		
		return false;
	}

	
	
}
