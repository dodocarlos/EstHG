package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public class Archer implements Listener {
	
	public static ArrayList<Player> archer = new ArrayList<Player> ();
	
	public static HashMap<Arrow, Player> flechas = new HashMap<> ();
	
	public static void darArcher(Player p){
		p.getInventory().addItem(new ItemStack(Material.BOW));
		p.getInventory().addItem(new ItemStack(Material.ARROW, 10));
		archer.add(p);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void flecha(ProjectileLaunchEvent e){
		if(e.getEntity().getShooter() instanceof Player){
			Player p = (Player) e.getEntity().getShooter();
			if(archer.contains(p)){
				if(e.getEntity().getType() == EntityType.ARROW){
					flechas.put((Arrow) e.getEntity(), p);
				}
			}
		}
	}
	
	@EventHandler
	public void damage(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			if(e.getDamager() instanceof Arrow){
				Arrow arrow = (Arrow) e.getDamager();
				if(flechas.containsKey(arrow)){
					flechas.get(arrow).getInventory().addItem(new ItemStack(Material.ARROW));
					flechas.remove(arrow);
				}
			}
		}
	}
	
}
