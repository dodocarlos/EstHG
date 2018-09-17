package me.dhg.listeners;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener{

	@EventHandler
	public void falar(AsyncPlayerChatEvent e){
		if(Variaveis.chat == false){
			if(!e.getPlayer().hasPermission("dHG.chat.bypass")){
				e.setCancelled(true);
				e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor + "Chat desabilitado");
				return;
			}
		}
		
		e.setFormat(Metodos.ColoredMsg(e.getPlayer().getDisplayName() + " &a➜&f " + e.getMessage()));
	}
	
}
