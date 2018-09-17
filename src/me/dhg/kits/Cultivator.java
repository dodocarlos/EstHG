package me.dhg.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Cultivator implements Listener{

	public static ArrayList<Player> cultivador = new ArrayList<> ();
	
	public static void darCultivator(Player p){
		cultivador.add(p);
	}
	
	@EventHandler
	public void plant(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(cultivador.contains(p)){
			if(!e.isCancelled()){
				if(e.getBlock().getType() == Material.SAPLING){
					e.getBlock().setType(Material.AIR);
					e.getBlock().getWorld().generateTree(e.getBlock().getLocation(), TreeType.TREE);
					return;
				}
				if(e.getBlock().getType() == Material.SEEDS){
					e.getBlock().setType(Material.WHEAT);
					return;
				}
				if(e.getBlock().getType() == Material.MELON_SEEDS){
					e.getBlock().setType(Material.MELON_BLOCK);
					return;
				}
				if(e.getBlock().getType() == Material.PUMPKIN_SEEDS){
					e.getBlock().setType(Material.PUMPKIN);
					return;
				}
			}
		}
	}
	
}
