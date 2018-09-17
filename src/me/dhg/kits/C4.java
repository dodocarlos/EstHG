package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class C4 implements Listener{

	public static ArrayList<String> c4 = new ArrayList<> ();
	public static HashMap<String, Long> cooldown = new HashMap<>();
	public static HashMap<String, Entity> explosive = new HashMap<>();
	
	public static void darC4(Player p){
		cooldown.put(p.getName(), 0l);
		p.getInventory().addItem(new ItemStack(Material.SLIME_BALL));
		c4.add(p.getName());
	}
	
	@EventHandler
	public void c4(PlayerInteractEvent e){
		if(c4.contains(e.getPlayer().getName())){
			if(e.getItem() != null && e.getItem().getType() == Material.SLIME_BALL){
				if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK && !explosive.containsKey(e.getPlayer().getName())){
					if(Metodos.acabouCooldown(e.getPlayer(), 25, cooldown)){
						if(!explosive.containsKey(e.getPlayer().getName())){
							Entity explosivo = e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), new ItemStack(Material.TNT));
							explosivo.setVelocity(e.getPlayer().getLocation().getDirection().clone());
							explosive.put(e.getPlayer().getName(), explosivo);
						}else{
							e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce ja plantou uma bomba !");
						}
					}else{
						e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Aguarde o cooldown de " + Variaveis.InfoColor + Metodos.getRemainingCooldown(e.getPlayer(), 25, cooldown) + Variaveis.GeralColor + " segundos");
					}				
				}
				
				if(e.getAction() != null && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && explosive.containsKey(e.getPlayer().getName())){
					Entity explosivo = explosive.get(e.getPlayer().getName());
					if(explosivo != null){
						e.getPlayer().getWorld().createExplosion(explosivo.getLocation(), 3f);
						explosivo.remove();
						explosive.remove(e.getPlayer().getName());
					}
				}
			}
		}
	}
	
	@EventHandler
	public void pickUP(PlayerPickupItemEvent e){
		if(explosive.containsValue(e.getItem())){
			e.setCancelled(true);
		}
	}
}
