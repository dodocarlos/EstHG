package me.dhg.kits;

import java.util.ArrayList;

import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class Anchor implements Listener{

	public static ArrayList<Player> anchor = new ArrayList<Player> ();
	
	public static void darAnchor(Player p){
		anchor.add(p);
	}
	
	@EventHandler
	public void dano(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			final Player p = (Player) e.getEntity();
			if(anchor.contains(p)){
			      Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Variaveis.main, new Runnable()
			      {
			        public void run()
			        {
			          p.setVelocity(new Vector());
			        }
			      }, 1L);
			}	
		}
		
		if(e.getDamager() instanceof Player){
			Player bateu = (Player) e.getDamager();
			if(e.getEntity() instanceof Player){
				final Player p = (Player) e.getEntity();
				if(anchor.contains(bateu)){
				      Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Variaveis.main, new Runnable()
				      {
				        public void run()
				        {
				          p.setVelocity(new Vector());
				        }
				      }, 1L);
				}
			}
		}
	
	}
	
}
