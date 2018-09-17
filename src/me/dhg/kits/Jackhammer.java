package me.dhg.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Jackhammer implements Listener{

	public static ArrayList<String> jackhammer = new ArrayList<String> ();
	
	public static void darJackhammer(Player p){
		jackhammer.add(p.getName());
		p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
	}
	
	@EventHandler
	public void breakblock(BlockBreakEvent e){
		if(jackhammer.contains(e.getPlayer().getName()) && e.getPlayer().getItemInHand().getType() == Material.STONE_AXE){
			for(int i = 0; i < e.getPlayer().getWorld().getMaxHeight(); i ++){
				if(e.getPlayer().getWorld().getBlockAt(e.getBlock().getX(), i, e.getBlock().getZ()).getType() != Material.AIR){
					e.getPlayer().getWorld().getBlockAt(e.getBlock().getX(), i, e.getBlock().getZ()).setType(Material.AIR);
				}else{
					break;
				}
			}
		}
	}
	
}
