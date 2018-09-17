package me.dhg.comandos;

import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpall implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
				
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(p.hasPermission("dHG.cmd.tpall")){
						for(Player pl : Bukkit.getOnlinePlayers()){
							pl.teleport(p);
						}
						Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor + "Todos os jogadores foram teleportados para " + Variaveis.InfoColor + p.getName());
					}
				}
		
		return false;
	}
}
