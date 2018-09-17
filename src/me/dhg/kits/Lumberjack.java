package me.dhg.kits;

import java.util.ArrayList;

import me.dhg.utils.Variaveis;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Lumberjack implements Listener{

	public static ArrayList<Player> lumberjack = new ArrayList<Player>();

	public static void darLumberjack(Player p){
		lumberjack.add(p);
		p.getInventory().addItem(new ItemStack(Material.WOOD_AXE));
	}
	
	@EventHandler
	public void quebrar(BlockBreakEvent e){
		if(Variaveis.iniciou){
			if(e.getPlayer().getItemInHand().getType() == Material.WOOD_AXE && lumberjack.contains(e.getPlayer()) && e.getPlayer().getItemInHand() != null && e.getBlock().getType().equals(Material.LOG) || e.getBlock().getType().equals(Material.LOG_2)){
				Location loc = e.getBlock().getLocation();
				while(e.getPlayer().getWorld().getBlockAt(loc).getType().equals(Material.LOG) || e.getPlayer().getWorld().getBlockAt(loc).getType().equals(Material.LOG_2)){
					e.getPlayer().getWorld().getBlockAt(loc).breakNaturally();
					loc.add(0, 1, 0);
				}
			}
		}
	}
	
}
