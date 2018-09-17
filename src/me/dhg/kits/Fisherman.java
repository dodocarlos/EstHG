package me.dhg.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class Fisherman implements Listener{

	public static ArrayList<Player> fisherman = new ArrayList<>();
	
	public static void darFisherman(Player p){
		fisherman.add(p);
		p.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
	}
	
	@EventHandler
	public void pescar(PlayerFishEvent e){
		if(e.getCaught() instanceof Player){
			Player pescado = (Player) e.getCaught();
			Player p = e.getPlayer();
			pescado.teleport(p);
		}
	}
	
}
