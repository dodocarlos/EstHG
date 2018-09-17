package me.dhg.kits;

import java.util.ArrayList;
import java.util.List;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Endermage implements Listener{

	public static ArrayList<Player> endermage = new ArrayList<>();
	
	public static void darEndermage(Player p){
		endermage.add(p);
		p.getInventory().addItem(new ItemStack(Material.NETHER_BRICK_ITEM));
	}
	
	@EventHandler
	public void puxar(final PlayerInteractEvent e){
		final Player p = e.getPlayer();
		if(e.isCancelled()){
			return;
		}
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK){
			return;
		}
		if(e.getItem() == null){
			return;
		}
		if(e.getItem().getType() != Material.NETHER_BRICK_ITEM){
			return;
		}
		if(!endermage.contains(p)){
			return;
		}	
		
		p.setItemInHand(null);
		
		List<Entity> entidades = p.getNearbyEntities(3, 3, 3);
		
		e.getClickedBlock().setType(Material.ENDER_PORTAL_FRAME);
		
		if(entidades.size() >= 1){
			for(Entity en : entidades){
				if(en instanceof Player){
					en.teleport(e.getClickedBlock().getLocation());
					((Player) en).setNoDamageTicks(100);
					((Player) en).sendMessage(Metodos.ColoredMsg(Variaveis.tag + Variaveis.GeralColor + "Voce foi puxado e tem " + Variaveis.InfoColor + "5 " + Variaveis.GeralColor + "segundos de invencibilidade !"));
					p.teleport(e.getClickedBlock().getLocation());
					p.setNoDamageTicks(100);
					p.sendMessage(Metodos.ColoredMsg(Variaveis.tag + Variaveis.GeralColor + "Voce foi puxado e tem " + Variaveis.InfoColor + "5 " + Variaveis.GeralColor + "segundos de invencibilidade !"));
				}
			}
			e.getClickedBlock().setType(Material.ENDER_STONE);
			if(Metodos.hasInvSpace(p)){
				p.getInventory().addItem(new ItemStack(Material.NETHER_BRICK_ITEM));
			}else{
				p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.PORTAL));
			}
		}else{
			Bukkit.getScheduler().scheduleSyncDelayedTask(Variaveis.main, new Runnable(){
				public void run(){
					List<Entity> entidades = p.getNearbyEntities(3, 3, 3);
					if(entidades.size() >= 1){
						for(Entity en : entidades){
							if(en instanceof Player){
								en.teleport(e.getClickedBlock().getLocation());
								((Player) en).setNoDamageTicks(100);
								((Player) en).sendMessage(Metodos.ColoredMsg(Variaveis.tag + Variaveis.GeralColor + "Voce foi puxado e tem " + Variaveis.InfoColor + "5 " + Variaveis.GeralColor + "segundos de invencibilidade !"));
								p.teleport(e.getClickedBlock().getLocation());
								p.setNoDamageTicks(100);
								p.sendMessage(Metodos.ColoredMsg(Variaveis.tag + Variaveis.GeralColor + "Voce foi puxado e tem " + Variaveis.InfoColor + "5 " + Variaveis.GeralColor + "segundos de invencibilidade !"));
							}
						}						
					}
					e.getClickedBlock().setType(Material.ENDER_STONE);
					if(Metodos.hasInvSpace(p)){
						p.getInventory().addItem(new ItemStack(Material.NETHER_BRICK_ITEM));
					}else{
						p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.PORTAL));
					}
				}
			}, 60);
		}
		
	}
	
}
