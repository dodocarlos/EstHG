package me.dhg.listeners;

import me.dhg.kits.Gladiator;
import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Place implements Listener{

	@EventHandler
	public void colocar(BlockPlaceEvent e){
		if(Arrays.admin.contains(e.getPlayer().getName())){
			return;
		}
		if(Variaveis.iniciou == false && !Arrays.admin.contains(e.getPlayer().getName())){
			e.setCancelled(true);
		}
		if(!Arrays.admin.contains(e.getPlayer()) && Gladiator.boxlist.containsKey(e.getPlayer()) && Gladiator.boxlist.get(e.getPlayer()).isInBox(e.getPlayer())){
			e.setCancelled(true);
		}
		if(Arrays.espectador.contains(e.getPlayer()) && !Arrays.admin.contains(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	
}
