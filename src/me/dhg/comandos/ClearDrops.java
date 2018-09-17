package me.dhg.comandos;

import me.dhg.utils.Variaveis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class ClearDrops implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(p.hasPermission("dHG.cmd.cleardrops")){
						for(Entity en : p.getWorld().getEntities()){
							if(en instanceof Item){
								en.remove();
							}
						}
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Foram removidos todos os itens do mundo !");
					}
				}
		
		return false;
	}

}
