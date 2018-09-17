package me.dhg.listeners;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

public class Mobs implements Listener{

	@EventHandler
	public void teste(EntityTargetLivingEntityEvent e){
		if(e.getTarget() instanceof Player){
			Player p = (Player) e.getTarget();
			if(Arrays.admin.contains(p.getName()) || Arrays.espectador.contains(p)){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void spawnMonster(EntitySpawnEvent e){
		if(Variaveis.iniciou == false){
			if(e.getEntity() instanceof Monster){
				e.setCancelled(true);
			}
		}
	}
	
}
