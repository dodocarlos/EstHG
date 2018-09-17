package me.dhg.listeners;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Items implements Listener{

	@EventHandler
	public void dropar(PlayerDropItemEvent e){
		if(Variaveis.iniciou == false){
			e.setCancelled(true);
		}
		if(Arrays.espectador.contains(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void pegar(PlayerPickupItemEvent e){
		if(Variaveis.iniciou == false){
			e.setCancelled(true);
		}
		if(Arrays.espectador.contains(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	
}
