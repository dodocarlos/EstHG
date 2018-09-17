package me.dhg.comandos;

import me.dhg.utils.Box;
import me.dhg.utils.Variaveis;
import me.dhg.utils.Box.Type;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Arena implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(p.hasPermission("dHG.cmd.arena")){
						Box arena = new Box(p.getLocation(), Type.ArenaBedrock);
						for(Player pl : Bukkit.getOnlinePlayers()){
							pl.teleport(arena.getLado1());
						}
						Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor + "Todos os jogadores foram teleportados para a arena");
					}else{
						p.sendMessage(Variaveis.SemPerm);
					}
				}
		
		return false;
	}

	
	
}
