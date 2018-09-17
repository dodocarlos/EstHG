package me.dhg.kits;

import java.util.ArrayList;

import me.dhg.utils.Variaveis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Worm implements Listener{

	public static ArrayList<Player> worm = new ArrayList<Player> ();
	
	public static void darWorm(Player p){
		worm.add(p);
	}
	
	@EventHandler
	public void worm(PlayerInteractEvent e){
		if(Variaveis.iniciou){
			if(worm.contains(e.getPlayer())){
				if(e.getAction() == Action.LEFT_CLICK_BLOCK){
					if(e.getClickedBlock() != null){
						if(e.getClickedBlock().getType() == Material.DIRT){
							e.getClickedBlock().breakNaturally();
						}
					}
				}
			}
		}
	}
	
}
