package me.dhg.listeners;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class Respawn implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void respawn(final PlayerRespawnEvent e){
		if(Variaveis.iniciou == false){
			Bukkit.getScheduler().scheduleAsyncDelayedTask(Variaveis.main, new Runnable(){
				public void run(){
					Metodos.darItensIniciais(e.getPlayer());
				}
			}, 1);
		}else{
			Bukkit.getScheduler().scheduleAsyncDelayedTask(Variaveis.main, new Runnable(){
				public void run(){
					e.getPlayer().getInventory().addItem(new ItemStack(Material.COMPASS));
					e.getPlayer().setNoDamageTicks(260);
				}
			}, 1);
		}
	}
	
}
