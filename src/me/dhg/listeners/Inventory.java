package me.dhg.listeners;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class Inventory implements Listener{
	
	@EventHandler
	public void inv1(InventoryClickEvent e){
		if(Variaveis.iniciou == false && !Arrays.admin.contains(e.getWhoClicked().getName())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void inv2(InventoryInteractEvent e){
		if(Variaveis.iniciou == false && !Arrays.admin.contains(e.getWhoClicked().getName())){
			e.setCancelled(true);
		}
	}
	
}
