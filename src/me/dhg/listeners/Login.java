package me.dhg.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import me.dhg.principal.Main;
import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

public class Login implements Listener{
	
	static Main main = Variaveis.main;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void login(PlayerLoginEvent e){
		final Player p = e.getPlayer();
		
		if(Main.stats.hasBanned(p)){
			e.disallow(Result.KICK_BANNED, Variaveis.tag + Variaveis.GeralColor +  "Você foi banido \nMotivo: " + Variaveis.InfoColor + "" + Main.stats.getBanReason(p.getName()));
		}
		
		if(Bukkit.getOnlinePlayers().length >= Bukkit.getMaxPlayers()){
			for(Player pl : Bukkit.getOnlinePlayers()){
				if(!pl.hasPermission("dHG.vip")){
					pl.kickPlayer(Variaveis.tag + Variaveis.GeralColor +  "Voce foi kickado por um VIP. \nPara isso nao acontecer novamente adquira vip no nosso site !");
					return;
				}
			}
		}
		
		if(e.getResult() == PlayerLoginEvent.Result.KICK_FULL){
			e.setKickMessage(Variaveis.tag + Variaveis.GeralColor +  "Servidor lotado \n Compre VIP e tenha acesso garantido !");
			return;
		}
		
		if(e.getResult() == PlayerLoginEvent.Result.KICK_OTHER){
			e.setKickMessage(Variaveis.tag + Variaveis.GeralColor +  "Erro no servidor \n Tente entrar novamente");
		}
		
		if(e.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST){
			e.setKickMessage(Variaveis.tag + Variaveis.GeralColor +  "Em manutencao !");
		}
		
		if(Variaveis.iniciou){
			if(!Arrays.participando.contains(p.getName())){
				if(Variaveis.iniciou && p.hasPermission("dHG.spectar")){
					Metodos.setEspectador(p);
					return;
				}else{
					Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable(){
						public void run(){
							p.kickPlayer(Variaveis.tag + Variaveis.GeralColor +  "Jogo em Andamento \n Compre vip e tenha 5 minutos a mais :)");
						}
					}, 5);
				}
				if(Variaveis.tempoPartida <= 300 && p.hasPermission("dHG.vip") && Variaveis.invencibilidade == false){

				}else{
					e.disallow(Result.KICK_OTHER, Variaveis.tag + Variaveis.GeralColor +  "Jogo em Andamento \n Compre vip e tenha 5 minutos a mais :)");
				}				
			}else{
				if(Variaveis.invencibilidade == false){
					e.disallow(Result.KICK_OTHER, Variaveis.tag + Variaveis.GeralColor +  "Jogo em Andamento \n Compre vip e tenha 5 minutos a mais :)");
				}
			}
		}
		
	}
	
}
