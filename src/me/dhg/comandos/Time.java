package me.dhg.comandos;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Time implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
				if(sender instanceof Player){
					Player p = (Player) sender;
					
					if(args.length < 1){
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor +   "Use: /time criar <nome>");
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /time entrar <nome>");
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /time kick <nick>");
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /time ban <nick>");
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /time unban <nick>");
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /time sair");
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Use: /time chat");
					}else {
						if(args.length == 1){
							if(args[0].equalsIgnoreCase("sair")){
								if(Arrays.hasTime.contains(p)){
									Arrays.hasTime.remove(p);
									if(me.dhg.utils.Time.getTime(p).getComponentes().size() < 1){
										me.dhg.utils.Time.getTime(p).remove();
									}
									me.dhg.utils.Time.getTime(p).removePlayer(p);
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce saiu do time");
								}else{
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce nao tem um time");
								}
							}
							
							if(args[0].equalsIgnoreCase("chat")){
								//fazer
							}
							
						}
						if(args.length == 2){
							
							if(args[0].equalsIgnoreCase("criar")){
								if(!Arrays.hasTime.contains(p)){
									if(args[1] != null){
										if(!Arrays.times.containsKey(args[1])){
											me.dhg.utils.Time time = new me.dhg.utils.Time(args[1], p);
											time.addPlayer(p);
											Arrays.hasTime.add(p);
											Arrays.times.put(args[1], time);
											p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce criou o time " + Variaveis.InfoColor + time.getName());
										}else{
											p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Esse time ja existe");
										}
									}else{
										p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Nome invalido");
									}
								}else{
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce ja tem um time");
								}								
							}
							
							if(args[0].equalsIgnoreCase("entrar")){
								if(args[1] != null){
									if(!Arrays.hasTime.contains(p)){
										if(Arrays.times.containsKey(args[1])){
											me.dhg.utils.Time time = Arrays.times.get(args[1]);
											if(!time.isBanned(p)){
												Arrays.hasTime.add(p);
												time.addPlayer(p);
												p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce entrou no time " + Variaveis.InfoColor + time.getName());
											}else{
												p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce esta banido desse time");
											}
										}else{
											p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Esse time nao existe");
										}
									}else{
										p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce ja tem um time");
									}
								}else{
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Nome invalido");
								}
							}
							
							if(args[0].equalsIgnoreCase("kick")){
								if(args[1] != null){
									try{
										Player target = Bukkit.getPlayer(args[1]);
										if(me.dhg.utils.Time.getTime(p) != null){
											if(me.dhg.utils.Time.getTime(p).isOwner(p)){
												if(me.dhg.utils.Time.getTime(p).getComponentes().contains(target)){
													me.dhg.utils.Time.getTime(p).kick(target);
													Arrays.hasTime.remove(target);
													p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "O jogador " + Variaveis.InfoColor + target.getName() + Variaveis.GeralColor + "foi kickado do time");
												}
											}else{
												p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Apenas o dono pode fazer isso");
											}
										}else{
											p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce nao tem um time");
										}
										
									}catch(NullPointerException e){
										p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Jogador nao encontrado");
									}
								}
							}
							
							if(args[0].equalsIgnoreCase("ban")){
								if(args[1] != null){
									try{
										Player target = Bukkit.getPlayer(args[1]);
										if(me.dhg.utils.Time.getTime(p) != null){
											if(me.dhg.utils.Time.getTime(p).isOwner(p)){
												if(me.dhg.utils.Time.getTime(p).getComponentes().contains(target)){
													me.dhg.utils.Time.getTime(p).ban(target);
													Arrays.hasTime.remove(target);
													p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "O jogador " + Variaveis.InfoColor + target.getName() + Variaveis.GeralColor + "foi banido do time");
												}
											}else{
												p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Apenas o dono pode fazer isso");
											}
										}else{
											p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce nao tem um time");
										}
										
									}catch(NullPointerException e){
										p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Jogador nao encontrado");
									}
								}
							}
							
							if(args[0].equalsIgnoreCase("unban")){
								if(args[1] != null){
									try{
										Player target = Bukkit.getPlayer(args[1]);
										if(me.dhg.utils.Time.getTime(p) != null){
											if(me.dhg.utils.Time.getTime(p).isOwner(p)){
												if(me.dhg.utils.Time.getTime(p).getComponentes().contains(target)){
													me.dhg.utils.Time.getTime(p).unban(target);
													p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "O jogador " + Variaveis.InfoColor + target.getName() + Variaveis.GeralColor + "foi desbanido do time");
												}
											}else{
												p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Apenas o dono pode fazer isso");
											}
										}else{
											p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce nao tem um time");
										}
										
									}catch(NullPointerException e){
										p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Jogador nao encontrado");
									}
								}
							}
							
						}
					}
					
				}
		
		return false;
	}
	
}
