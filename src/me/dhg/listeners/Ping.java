package me.dhg.listeners;

import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class Ping implements Listener{

	@EventHandler
	public void ping(ServerListPingEvent e){
		if(Variaveis.iniciou){
			e.setMotd(Variaveis.motdIniciou.replaceAll("%pulalinha%", "\n").replaceAll("%tempo%", String.format("%02d:%02d:%02d", Metodos.horaPartida, Metodos.minutoPartida, Metodos.segundoPartida)));
			e.setMaxPlayers(Arrays.participando.size());
		}else{
			e.setMotd(Variaveis.motdNaoIniciou.replaceAll("%pulalinha%", "\n").replaceAll("%tempo%", String.format("%02d:%02d:%02d", Metodos.horaInicio, Metodos.minutoInicio, Metodos.segundoInicio)));
		}
	}
	
}
