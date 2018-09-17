package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import me.dhg.utils.Box;
import me.dhg.utils.Variaveis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Gladiator implements Listener{
	
	public static ArrayList<Player> gladiator = new ArrayList<Player> ();
	public static HashMap<Player, Box> boxlist = new HashMap<Player, Box>();
	
	public static void darGladiator(Player p){
		gladiator.add(p);
		p.getInventory().addItem(new ItemStack(Material.IRON_FENCE));
	}
	
	@EventHandler
	public void interagir(PlayerInteractEntityEvent e){
		if(Variaveis.iniciou){
			if(gladiator.contains(e.getPlayer())){
				if(e.getRightClicked() instanceof Player){
					Player p = e.getPlayer();
					Player p2 = (Player) e.getRightClicked();
					if(!boxlist.containsKey(p) && !boxlist.containsValue(p2)){						
						if(e.getPlayer().getItemInHand().getType() == Material.IRON_FENCE && !Variaveis.invencibilidade){
							Box gladbox = new Box(e.getPlayer().getLocation(), Box.Type.Gladiator);
							gladbox.teleportToBox(p, p2);
							boxlist.put(p, gladbox);
							boxlist.put(p2, gladbox);
						}
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void morrer(PlayerDeathEvent e){
		if(boxlist.containsKey(e.getEntity().getKiller())){
			Box gladbox = boxlist.get(e.getEntity().getKiller());
			if(gladbox.isInBox(e.getEntity())){
				e.getEntity().getKiller().teleport(gladbox.getSaida());
				gladbox.removeBox();
				boxlist.remove(e.getEntity().getKiller());
			}
		}
	}
	
}
