package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class Digger implements Listener{

	public static ArrayList<Player> digger = new ArrayList<> ();
	public static HashMap<String, Long> cooldown = new HashMap<> ();
	
	public static void darDigger(Player p){
		digger.add(p);
		p.getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 10));
		cooldown.put(p.getName(), 0l);
	}
	
	@EventHandler
	public void place(BlockPlaceEvent e){
		final Player p = e.getPlayer();
		if(!e.isCancelled()){
			if(digger.contains(p)){
				if(e.getBlock().getType() == Material.DRAGON_EGG){
					if(Metodos.acabouCooldown(p, 20, cooldown)){
						e.getBlock().setType(Material.AIR);
						final int x1 = (int) e.getBlock().getLocation().clone().add(-2, 0, 0).getX();
						final int y1 = (int) e.getBlock().getLocation().clone().clone().add(0, -1, 0).getY();
						final int z1 = (int) e.getBlock().getLocation().clone().add(0, 0, -2).getZ();
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(Variaveis.main, new Runnable(){
							public void run(){
								for(int x = x1; x < x1 + 5; x ++){
									for(int z = z1; z < z1 + 5; z ++){
										for(int y = y1; y > y1 - 5; y --){
											p.getWorld().getBlockAt(x, y, z).setType(Material.AIR);								
										}
									}
								}
							}
						}, 40);
						
						cooldown.put(p.getName(), System.currentTimeMillis());
					}else{
						e.setCancelled(true);
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Aguarde o cooldown de " + Variaveis.InfoColor + Metodos.getRemainingCooldown(p, 20, cooldown) + Variaveis.GeralColor + " segundos");
					}
					
				}
			}
		}
	}
	
}
