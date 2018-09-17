package me.dhg.comandos;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(p.hasPermission("dHG.cmd.teleport") || Arrays.espectador.contains(p)){
						if(args.length == 1){
							Player target = Bukkit.getPlayer(args[0]);
							if(target.isOnline()){
								p.teleport(target);
							}else{
								p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Jogador Offline");
							}
						}
					}else{
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Apenas espectadores !");
					}
				}
		
		return false;
	}

	
	
}
