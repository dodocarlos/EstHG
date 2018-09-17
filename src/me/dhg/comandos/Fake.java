package me.dhg.comandos;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fake implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(p.hasPermission("dHG.cmd.fake")){
						if(args.length != 1){
							p.sendMessage("§cUse: /fake <nick>");
						}else if(args.length == 1){
							if(args[0].equalsIgnoreCase("off")){
								if(Arrays.fakes.containsKey(p)){
									try {
										Arrays.fakes.get(p).toOriginal();
									} catch (NoSuchFieldException
											| SecurityException
											| IllegalArgumentException
											| IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									Arrays.fakes.remove(p);
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Fake removido !");
								}else{
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce nao tem um fake");
								}
							}else{
								me.dhg.utils.Fake fake;
								try {
									try{
										if(!Arrays.blackListFake.contains(args[0])){
											fake = new me.dhg.utils.Fake(p, p.getName(), args[0]);
											Arrays.fakes.put(p, fake);
											p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Seu nome agora e " + Variaveis.InfoColor + args[0]);
										}else{
											p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce nao pode user este fake !");
										}
									}catch(NullPointerException e){
										p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "O nome nao pode ser nulo !");
									}
								} catch (NoSuchFieldException
										| SecurityException
										| IllegalArgumentException
										| IllegalAccessException e) {
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Ja existe um fake com esse nome !");
								}								
							}
						}
					}
				}else{
					sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Apenas jogadores");
				}
		
		return false;
	}
	
}
