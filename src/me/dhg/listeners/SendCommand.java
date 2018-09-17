package me.dhg.listeners;

import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class SendCommand implements Listener {
	
	@EventHandler
	public void falar(PlayerCommandPreprocessEvent e){
		
		String cmd = e.getMessage().split(" ")[0].replaceAll("/", "");
			if(cmd.equalsIgnoreCase("?") || cmd.equalsIgnoreCase("help")){
				if(!Arrays.admin.contains(e.getPlayer())){
					e.setCancelled(true);
					if(!Arrays.admin.contains(e.getPlayer())){
							e.setCancelled(true);
							Player p = e.getPlayer();
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Infos:----------------------");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Kit: " + Variaveis.InfoColor + "" + Arrays.kitPlayer.get(p));
							if(Arrays.kills.get(p) == null){
								p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Kills: " + Variaveis.InfoColor + "0");
							}else{
								p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Kills: " + Variaveis.InfoColor + "" + Arrays.kills.get(p));
							}
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Tempo de jogo: " + Variaveis.InfoColor + "" + String.format("%02d:%02d:%02d", Metodos.horaPartida, Metodos.minutoPartida, Metodos.segundoPartida));
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "---------------------------");
						
				}
				}
			}
			
			if(Arrays.em1v1.contains(e.getPlayer())){
				e.setCancelled(true);
				e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Sem comandos no 1v1");
			}
			
			if(cmd.equalsIgnoreCase("version")){
				e.setCancelled(true);
				e.getPlayer().sendMessage("§aPlugin criado por §fdodocarlos §apara o servidor §f" + Variaveis.NomeDoServidor);
				e.getPlayer().sendMessage("§aSkype para contato: §fdodocarlos.pessoal");
			}
			if(cmd.equalsIgnoreCase("ver")){
				e.setCancelled(true);
				e.getPlayer().sendMessage("§aPlugin criado por §fdodocarlos §apara o servidor §f" + Variaveis.NomeDoServidor);
				e.getPlayer().sendMessage("§aSkype para contato: §fdodocarlos.pessoal");
			}
			if(cmd.equalsIgnoreCase("pl")){
				e.setCancelled(true);
				e.getPlayer().sendMessage("§aPlugin criado por §fdodocarlos §apara o servidor §f" + Variaveis.NomeDoServidor);
				e.getPlayer().sendMessage("§aSkype para contato: §fdodocarlos.pessoal");
			}
			if(cmd.equalsIgnoreCase("plugins")){
				e.setCancelled(true);
				e.getPlayer().sendMessage("§aPlugin criado por §fdodocarlos §apara o servidor §f" + Variaveis.NomeDoServidor);
				e.getPlayer().sendMessage("§aSkype para contato: §fdodocarlos.pessoal");
			}
			if(cmd.equalsIgnoreCase("about")){
				e.setCancelled(true);
				e.getPlayer().sendMessage("§aPlugin criado por §fdodocarlos §apara o servidor §f" + Variaveis.NomeDoServidor);
				e.getPlayer().sendMessage("§aSkype para contato: §fdodocarlos.pessoal");
			}
	}
	
	@EventHandler
	public void tab(PlayerChatTabCompleteEvent e){
		String cmd = e.getChatMessage().split(" ")[0].replaceAll("/", "");
		if(cmd.equalsIgnoreCase("ver")){
			e.getTabCompletions().clear();
		}
		if(cmd.equalsIgnoreCase("version")){
			e.getTabCompletions().clear();
		}
		if(cmd.equalsIgnoreCase("about")){
			e.getTabCompletions().clear();
		}
	}
	
}
