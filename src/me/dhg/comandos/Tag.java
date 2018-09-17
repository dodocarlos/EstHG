package me.dhg.comandos;


import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tag implements CommandExecutor {

	String tags[] = {"Normal", "Vip", "Youtuber", "Coder", "Trial", "Mod", "Admin", "Dono"};

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
		
		
		
		
		if(sender instanceof Player){
			Player p = (Player) sender;
			StringBuilder sb = new StringBuilder();
			sb.append(Variaveis.tag + Variaveis.GeralColor +  "Use: (");
			for(String tag : tags){
				if(p.hasPermission("dHG.tag." + tag)){
					sb.append(tag + " | ");
				}
			}
			sb.setCharAt(sb.length() -1, ')');
			
			if(args.length == 0){
				p.sendMessage(sb.toString());
			}
			
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("Normal")){
					Metodos.setTag(p, "§f", "§f");
				}
				
				if(args[0].equalsIgnoreCase("Vip")){
					if(p.hasPermission("dHG.tag.vip")){
						Metodos.setTag(p, "§a§lVIP §a", "§a");
					}
				}
				
//				if(args[0].equalsIgnoreCase("MvP")){
//					if(p.hasPermission("dHG.tag.mvp")){
//						Metodos.setTag(p, "§1§lMvP §1", "§1");
//					}
//				}
//				
//				if(args[0].equalsIgnoreCase("Pro")){
//					if(p.hasPermission("dHG.tag.pro")){
//						Metodos.setTag(p, "§6§lPRO §6", "§6");
//					}
//				}
				
				if(args[0].equalsIgnoreCase("Youtuber")){
					if(p.hasPermission("dHG.tag.youtuber")){
						Metodos.setTag(p, "§b§lYOUTUBER §b", "§b");
					}
				}
				
				if(args[0].equalsIgnoreCase("Coder")){
					if(p.hasPermission("dHG.tag.coder")){
						Metodos.setTag(p, "§9§lCODER §9", "§9");
					}
				}
				
				if(args[0].equalsIgnoreCase("Trial")){
					if(p.hasPermission("dHG.tag.trial")){
						Metodos.setTag(p, "§d§lTRIAL §d", "§d");
					}
				}
				
				if(args[0].equalsIgnoreCase("Mod")){
					if(p.hasPermission("dHG.tag.mod")){
						Metodos.setTag(p, "§5§lMOD §5", "§5");
					}
				}
				
				if(args[0].equalsIgnoreCase("Admin")){
					if(p.hasPermission("dHG.tag.admin")){
						Metodos.setTag(p, "§c§lADMIN §c", "§c");
					}
				}
				
				if(args[0].equalsIgnoreCase("Dono")){
					if(p.hasPermission("dHG.tag.dono")){
						Metodos.setTag(p, "§4§lDONO §4", "§4");
					}
				}
			}
			
			
			
		}else{
			sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Apenas Jogadores");
		}
		
		return false;
	}

}
