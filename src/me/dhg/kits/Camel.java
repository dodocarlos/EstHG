package me.dhg.kits;

import java.util.ArrayList;

import me.dhg.utils.Recipes;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Camel implements Listener{
		
	public static ArrayList<Player> camel = new ArrayList<Player> ();
	
	public static void darCamel(Player p){
		camel.add(p);
	}
	
	@EventHandler
	public void craft(CraftItemEvent e){
		if(e.getRecipe().equals(Recipes.camel)){
			if(!camel.contains(e.getWhoClicked())){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void andar(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(camel.contains(p)){
			if(e.getFrom().getBlock().getBiome() == Biome.DESERT){
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
			}
		}
	}
	
}
