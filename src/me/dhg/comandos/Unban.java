package me.dhg.comandos;

import me.dhg.utils.Variaveis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Unban implements CommandExecutor{

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

			if(sender.hasPermission("dHG.cmd.unban")){
				if(args.length < 1){
					sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /unban " + Variaveis.InfoColor + " nick");	
					return true;
				}
				String nick = args[0];
				
				if(sender instanceof Player){
					if(!Variaveis.main.stats.hasBanned(nick)){
						sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Este jogador nao esta banido");
						return true;
					}
					Variaveis.main.stats.unbanPlayer(nick);
				}else{
					if(!Variaveis.main.stats.hasBanned(nick)){
						sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Este jogador nao esta banido");
						return true;
					}
					Variaveis.main.stats.unbanPlayer(nick);
				}
				sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "O jogador " + Variaveis.InfoColor + nick + Variaveis.GeralColor + " foi desbanido");
			}
		
		return false;
	}

	
	
}
