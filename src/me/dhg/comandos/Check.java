package me.dhg.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;

import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

public class Check implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
				
				if(sender.hasPermission("dHG.cmd.check")){
					
					if(args.length != 1){
						sender.sendMessage("§cUse: /check <nick>");
					}else{
						try{
							Player target = Bukkit.getPlayer(args[0]);
							if(!target.isOnline()){
								sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Player offline");
							}else{
								sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Player: " + Variaveis.InfoColor + target.getName() + Variaveis.GeralColor + "----------");
								sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "IP: " + Variaveis.InfoColor + target.getAddress().toString().split("/")[1].split(":")[0]);
								sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Ping: " + Variaveis.InfoColor + ((CraftPlayer)target).getHandle().ping);
								sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Kit: " + Variaveis.InfoColor + Arrays.kitPlayer.get(target));
								Damageable d = (Damageable) target;
								sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "HP: " + Variaveis.InfoColor + (int) d.getHealth() + " / 20");
								sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Fome: " + Variaveis.InfoColor + target.getFoodLevel() + " / 20");
								sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Localizacao: " + Variaveis.InfoColor + " X(" + String.format("%.2f", target.getLocation().getX()) + ") Y(" +  String.format("%.2f", target.getLocation().getY()) + ") Z("  + String.format("%.2f", target.getLocation().getZ()) + ")");
								sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "GM: " + Variaveis.InfoColor + target.getGameMode());
								sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Sopas: " + Variaveis.InfoColor + Metodos.contarSopas(target));
								sender.sendMessage(Variaveis.GeralColor + "----------------------");
							}
						}catch(NullPointerException e){
							sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Player offline");
						}
					}
					
				}else{
					sender.sendMessage(Variaveis.SemPerm);
				}
		
		return false;
	}
	
}
