package me.dhg.listeners;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Fome implements Listener{

	@EventHandler
	public void fome(FoodLevelChangeEvent e){
		if(Variaveis.iniciou == false){
			e.setCancelled(true);
		}
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(Arrays.espectador.contains(p)){
				e.setCancelled(true);
			}
			p.setSaturation(30f);
		}
	}
	
}
