package me.dhg.listeners;

import me.dhg.utils.Arrays;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class CancelKitItems implements Listener{

	@EventHandler
	public void place(BlockPlaceEvent e){
		if(e.getBlock().getType() == Material.REDSTONE_TORCH_OFF || e.getBlock().getType() == Material.REDSTONE_TORCH_ON && Arrays.kitPlayer.get(e.getPlayer()).equalsIgnoreCase("Flash")){
			e.setCancelled(true);
		}
		if(e.getBlock().getType() == Material.IRON_FENCE && Arrays.kitPlayer.get(e.getPlayer()).equalsIgnoreCase("Gladiator")){
			e.setCancelled(true);
		}	
		if(e.getBlock().getType() == Material.SIGN || e.getBlock().getType() == Material.WOOD_BUTTON && Arrays.kitPlayer.get(e.getPlayer()).equalsIgnoreCase("Checkpoint")){
			e.setCancelled(true);
		}		
	}
	
	@EventHandler
	public void drop(PlayerDropItemEvent e){
		if(e.getItemDrop().getItemStack().getType() == Material.REDSTONE_TORCH_OFF || e.getItemDrop().getItemStack().getType() == Material.REDSTONE_TORCH_ON && Arrays.kitPlayer.get(e.getPlayer()).equalsIgnoreCase("Flash")){
			e.setCancelled(true);
		}
		if(e.getItemDrop().getItemStack().getType() == Material.IRON_FENCE && Arrays.kitPlayer.get(e.getPlayer()).equalsIgnoreCase("Gladiator")){
			e.setCancelled(true);
		}	
		if(e.getItemDrop().getItemStack().getType() == Material.SIGN || e.getItemDrop().getItemStack().getType() == Material.WOOD_BUTTON && Arrays.kitPlayer.get(e.getPlayer()).equalsIgnoreCase("Checkpoint")){
			e.setCancelled(true);
		}	
		if(e.getItemDrop().getItemStack().getType() == Material.SLIME_BALL && Arrays.kitPlayer.get(e.getPlayer()).equalsIgnoreCase("C4")){
			e.setCancelled(true);
		}
		if(e.getItemDrop().getItemStack().getType() == Material.MAGMA_CREAM && Arrays.kitPlayer.get(e.getPlayer()).equalsIgnoreCase("ForceField")){
			e.setCancelled(true);
		}
		if(e.getItemDrop().getItemStack().getType() == Material.FIREWORK && Arrays.kitPlayer.get(e.getPlayer()).equalsIgnoreCase("Kangaroo")){
			e.setCancelled(true);
		}
		if(e.getItemDrop().getItemStack().getType() == Material.LEASH && Arrays.kitPlayer.get(e.getPlayer()).equalsIgnoreCase("Grappler")){
			e.setCancelled(true);
		}
	}
	
}
