package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Flash implements Listener{

	public static ArrayList<Player> flash = new ArrayList<> ();
	public static HashMap<String, Long> cooldown = new HashMap<>();
	
	public static void darFlash(Player p){
		cooldown.put(p.getName(), 0l);
		flash.add(p);
		p.getInventory().addItem(new ItemStack(Material.REDSTONE_TORCH_ON));
	}
	
	@EventHandler
	public void interagir(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(flash.contains(p)){
			if(e.getItem().getType() == Material.REDSTONE_TORCH_ON || e.getItem().getType() == Material.REDSTONE_TORCH_ON){
				if(Metodos.acabouCooldown(p, 120, cooldown)){
					p.setVelocity(p.getVelocity().multiply(2f));
				}else{
					p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Aguarde o cooldown de " + Variaveis.InfoColor + Metodos.getRemainingCooldown(p, 120, cooldown) + Variaveis.GeralColor + " segundos");
				}
			}
		}
	}
	
}
