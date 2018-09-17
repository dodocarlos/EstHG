package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

public class Thor implements Listener{
	
	public static ArrayList<Player> thor = new ArrayList<Player> ();
	public static HashMap<String, Long> cooldown = new HashMap<>();
	
	public static void darThor(Player p){
		p.getInventory().addItem(new ItemStack(Material.WOOD_AXE));
		p.getInventory().addItem(new ItemStack(Material.NETHERRACK, 5));
		thor.add(p);
		cooldown.put(p.getName(), 0l);
	}
	
	
	@EventHandler
	public void dano(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(thor.contains(p)){
				if(e.getCause() == DamageCause.LIGHTNING || e.getCause() == DamageCause.BLOCK_EXPLOSION || e.getCause() == DamageCause.ENTITY_EXPLOSION && p.getItemInHand().getType() == Material.WOOD_AXE){
					e.setDamage(4D);
				}
			}
		}
	}
	
	@EventHandler
	public void interagir(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(thor.contains(e.getPlayer())){
				if(e.getPlayer().getItemInHand().getType() == Material.WOOD_AXE){
					if(Metodos.acabouCooldown(e.getPlayer(), 10, cooldown)){
						if(e.getClickedBlock().getType() == Material.NETHERRACK){
							e.getPlayer().getWorld().createExplosion(e.getClickedBlock().getLocation(), 5F);
						}else{
							e.getPlayer().getWorld().strikeLightning(e.getClickedBlock().getLocation());
						}
					}else{
						e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Aguarde o cooldown de " + Metodos.getRemainingCooldown(e.getPlayer(), 10, cooldown) + Variaveis.GeralColor + " segundos");
					}
				}
			}
		}
	}
}
