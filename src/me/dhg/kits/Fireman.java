package me.dhg.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class Fireman implements Listener{

	public static ArrayList<Player> fireman = new ArrayList<>();
	
	public static void darFireman(Player p){
		fireman.add(p);
		p.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
	}
	
	@EventHandler
	public void dano(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(e.getCause() == DamageCause.FIRE
				|| e.getCause() == DamageCause.FIRE_TICK
				|| e.getCause() == DamageCause.LIGHTNING){
					if(fireman.contains(p)){
						e.setCancelled(true);
					}
				}
		}
	}
	
}
