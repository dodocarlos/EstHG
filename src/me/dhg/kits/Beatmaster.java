package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Beatmaster implements Listener{

	public static ArrayList<Player> beatmaster = new ArrayList<> ();
	
	public static HashMap<String, Long> cooldown = new HashMap<>();
	
	public static void darBeatmaster(Player p){
		beatmaster.add(p);
		p.getInventory().addItem(new ItemStack(Material.BONE));
		cooldown.put(p.getName(), 0l);
	}
	
	@EventHandler
	public void interact(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			Player p = e.getPlayer();
			if(beatmaster.contains(p)){
				if(p.getItemInHand().getType() == Material.BONE){					
					if(Metodos.acabouCooldown(p, 120, cooldown)){
						for(int i = 0; i < 3; i ++){
							Wolf lobo = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
							lobo.setAdult();
							lobo.setAngry(false);
							lobo.setCustomName(Arrays.fakes.containsKey(p) ? "§cLobo de §e" + Arrays.fakes.get(p).getFakeName() : "§cLobo de §e" + p.getName());
							lobo.setCustomNameVisible(true);
							lobo.setOwner(p);
							lobo.setTamed(true);
							lobo.setRemoveWhenFarAway(false);
						}
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Lobos spawnados !");
						cooldown.put(p.getName(), System.currentTimeMillis());
					}else{
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Aguarde o cooldown de " + Variaveis.InfoColor + Metodos.getRemainingCooldown(p, 120, cooldown) + Variaveis.GeralColor + " segundos");
					}
				}
			}
		}
	}
	
}
