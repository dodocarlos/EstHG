package me.dhg.listeners;

import me.dhg.utils.Arrays;
import me.dhg.utils.BossBar;
import me.dhg.utils.Variaveis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Damage implements Listener{

	@EventHandler
	public void dano(EntityDamageEvent e){
		if(Variaveis.dano){
			e.setCancelled(true);
		}
		if(Variaveis.iniciou == false){
			if(!Arrays.em1v1.contains(e.getEntity())){
				e.setCancelled(true);
			}
		}
		if(Variaveis.invencibilidade){
			if(e.getEntity() instanceof Player){
				e.setCancelled(true);
			}
		}
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(Arrays.espectador.contains(p)){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void dano2(EntityDamageByEntityEvent e){
		
		if(e.getDamager() instanceof Player){
			Player d = (Player) e.getDamager();
			if(Arrays.espectador.contains(d)){
				e.setCancelled(true);
				return;
			}
		}
		
		if(Variaveis.dano){
			e.setCancelled(true);
			return;
		}
		
		if(Variaveis.iniciou == false){
			if(!Arrays.em1v1.contains(e.getEntity())){
				e.setCancelled(true);
				return;
			}
		}
		if(Variaveis.invencibilidade){
			if(e.getEntity() instanceof Player){
				e.setCancelled(true);
				return;
			}
		}
		
		if(!e.isCancelled()){
			if(e.getCause() == DamageCause.ENTITY_ATTACK){
				e.setDamage(e.getDamage() / 2.0);
			}
		}
		
		if(e.getEntity() instanceof Player){
			if(!e.isCancelled()){
				if(e.getDamager() instanceof Player){				
					Player bateu = (Player) e.getDamager();
					Player p = (Player) e.getEntity();
					if(Arrays.espectador.contains(bateu)){
						e.setCancelled(true);
					}
					if(Arrays.fakes.containsKey(p)){
						BossBar.tempBar(bateu, Arrays.fakes.get(p).getFakeName() + " - " + Arrays.kitPlayer.get(p), 5, 1);
					}else{
						BossBar.tempBar(bateu, p.getName() + " - " + Arrays.kitPlayer.get(p), 5, 1);
					}
				}
			}
		}
	}
	
}
