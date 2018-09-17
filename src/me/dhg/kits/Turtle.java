package me.dhg.kits;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Turtle implements Listener{

	public static ArrayList<String> turtle = new ArrayList<>();
	
	public static void darTurtle(Player p){
		turtle.add(p.getName());
	}
	
	@EventHandler
	public void damage(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(turtle.contains(p.getName())){
				if(p.isSneaking()){
					e.setDamage(e.getDamage() / 5);
				}
			}
		}
		if(e.getDamager() instanceof Player){
			Player p = (Player) e.getDamager();
			if(turtle.contains(p.getName())){
				if(p.isSneaking()){
					e.setCancelled(true);
				}
			}
		}
	}
	
}
