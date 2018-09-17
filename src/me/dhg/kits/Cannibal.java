package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Cannibal implements Listener{
	
	public static ArrayList<Player> cannibal = new ArrayList<Player>();
	
	public static HashMap<Player, Long> cooldown = new HashMap<>();
	
	public static void darCannibal(Player p){
		cannibal.add(p);
		cooldown.put(p, 0l);
	}
	
	@EventHandler
	public void dano(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			Player levou = (Player) e.getEntity();
			if(e.getDamager() instanceof Player){
				Player bateu = (Player) e.getDamager();
				if(cannibal.contains(bateu)){
					if(System.currentTimeMillis() - cooldown.get(bateu) >= 2 * 1000){
						if(bateu.getFoodLevel() < 20){
							bateu.setFoodLevel((int) (bateu.getFoodLevel() + e.getDamage()));
							levou.setFoodLevel((int) (bateu.getFoodLevel() - e.getDamage() / 2));
							cooldown.put(bateu, System.currentTimeMillis());
						}
					}
				}
			}
		}
	}
	
}
