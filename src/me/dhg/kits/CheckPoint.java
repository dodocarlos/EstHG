package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import me.dhg.utils.Variaveis;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CheckPoint implements Listener {

	public static ArrayList<Player> checkpoint = new ArrayList<> ();
	public static HashMap<Player, Location> local = new HashMap<> ();
		
	public static void darCheckpoint(Player p ){
		checkpoint.add(p);
		p.getInventory().addItem(new ItemStack(Material.SIGN));
	}
	
	@EventHandler
	public void interagir(PlayerInteractEvent e){
		if(Variaveis.iniciou){
			Player p = e.getPlayer();
			if(checkpoint.contains(p)){
				if(e.getItem() != null){
					if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
						if(e.getItem().getType() == Material.SIGN){
							e.setCancelled(true);
							local.put(p, e.getClickedBlock().getLocation().clone().add(0, 2, 0));
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Checkpoint definido !");
							p.getItemInHand().setType(Material.WOOD_BUTTON);
							return;
						}
					}
					if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
						if(e.getItem().getType() == Material.WOOD_BUTTON){
							if(local.get(p) != null){
								e.setCancelled(true);
								p.teleport(local.get(p));
								p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Teletransportado para o local do checkpoint");
								local.remove(p);
								p.getItemInHand().setType(Material.SIGN);
								return;
							}
						}
					}
				}
			}
		}
	}
	
}
