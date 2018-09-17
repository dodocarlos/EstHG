package me.dhg.comandos;

import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inv implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmds, String label,
			String[] args) {
		
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(p.hasPermission("dHG.cmd.inv")){
						if(args.length == 1){
							try{
								Player target = Bukkit.getPlayer(args[0]);
								if(!target.isOnline()){
									p.sendMessage(Variaveis.tag + "Player offline");
								}else{
									p.openInventory(target.getInventory());
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Voce abriu o inventario de " + Variaveis.InfoColor + target.getName());
								}
							}catch(NullPointerException e){
								p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Player offline");
							}
						}
					}else{
						p.sendMessage(Variaveis.SemPerm);
					}
				}
		
		return false;
	}

}
