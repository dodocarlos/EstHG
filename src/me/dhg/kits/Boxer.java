package me.dhg.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Boxer implements Listener{

	public static ArrayList<Player> boxer = new ArrayList<> ();
	
	public static void darBoxer(Player p){
		boxer.add(p);
	}
	
	@EventHandler
	public void dano(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			Player levou = (Player) e.getEntity();
			if(boxer.contains(levou)){
				e.setDamage(2D);
			}
		}
		if(e.getDamager() instanceof Player){
			Player bateu = (Player) e.getDamager();
			if(boxer.contains(bateu)){
				if(bateu.getItemInHand().getType() == Material.AIR || bateu.getItemInHand() == null){
					e.setDamage(5D);
				}
			}
		}
	}
	
}
