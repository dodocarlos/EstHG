package me.dhg.comandos;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveKits implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

			if(sender.hasPermission("dHG.cmd.removekits")){
				for(Player p : Bukkit.getOnlinePlayers()){
					Metodos.retirarKit(p);
				}
				Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Todos os kits foram removidos");
			}
		
		return false;
	}

}
