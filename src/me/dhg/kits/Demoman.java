package me.dhg.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class Demoman implements Listener{

	public static ArrayList<Player> demoman = new ArrayList<>();
	
	public static void darDemoman(Player p){
		demoman.add(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_PLATE, 5));
		p.getInventory().addItem(new ItemStack(Material.GRAVEL, 5));
	}
	
	@EventHandler
	public void place(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(!e.isCancelled()){
			if(e.getBlock().getType() == Material.STONE_PLATE){
				if(e.getBlock().getWorld().getBlockAt(e.getBlock().getLocation().clone().add(0, -1, 0)).getType() == Material.GRAVEL){
					if(!demoman.contains(p)){
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void move(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(e.getTo().getBlock().getType() == Material.STONE_PLATE){
			if(e.getTo().getWorld().getBlockAt(e.getTo().getBlock().getLocation().clone().add(0, -1, 0)).getType() == Material.GRAVEL){
				p.getWorld().createExplosion(p.getLocation(), 5f);
			}
		}
	}
}
