package me.dhg.comandos;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SKit implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
				
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(p.hasPermission("dHG.cmd.skit")){
						if(args.length < 1){
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /skit <create | apply (all : nick)> <nome>");
						}else{
							if(args.length == 2){
								if(args[0].equalsIgnoreCase("create")){
									Arrays.skitarmor.put(args[1], p.getInventory().getArmorContents());
									Arrays.skititem.put(args[1], p.getInventory().getContents());
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Kit " + Variaveis.InfoColor + args[1] + Variaveis.GeralColor + "criado !");
								}else{
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /skit <create | apply (all : nick)> <nome>");
								}
							}
							if(args.length == 3){
								if(args[0].equalsIgnoreCase("apply")){
									if(args[1].equalsIgnoreCase("all")){
										if(Arrays.skititem.get(args[2]) == null && Arrays.skitarmor.get(args[2]) == null){
											p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Este kit nao existe");
										}else{
											for(Player pl : Bukkit.getOnlinePlayers()){
												pl.getInventory().setArmorContents(Arrays.skitarmor.get(args[2]));
												pl.getInventory().setContents(Arrays.skititem.get(args[2]));												
											}
											p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Kit " + Variaveis.InfoColor + args[2] + Variaveis.GeralColor + "aplicado a todos os jogadores");
										}										
									}else{
										try{
											Player pl = Bukkit.getPlayer(args[1]);
											if(pl.isOnline()){
												if(Arrays.skititem.get(args[2]) == null && Arrays.skitarmor.get(args[2]) == null){
													p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Este kit nao existe");
												}else{
													pl.getInventory().setArmorContents(Arrays.skitarmor.get(args[2]));
													pl.getInventory().setContents(Arrays.skititem.get(args[2]));
													p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Kit " + Variaveis.InfoColor + args[2] + Variaveis.GeralColor + "aplicado ao player " + Variaveis.InfoColor + args[1]);
												}
											}else{
												p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Player offline");
											}
										}catch(NullPointerException e){
											p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Player offline");
										}
									}
								}else{
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /skit <create | apply (all : nick)> <nome>");
								}
							}
						}
					}else{
						p.sendMessage(Variaveis.SemPerm);
					}
				}
		
		return false;
	}

}
