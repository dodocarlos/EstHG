package me.dhg.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Nerf implements Listener{
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		e.setDamage(e.getDamage() * 0.8);
	}
	
}
