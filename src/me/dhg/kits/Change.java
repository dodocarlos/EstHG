package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class Change implements Listener{

	public static ArrayList<Player> change = new ArrayList<> ();
	
	public static HashMap<Player, ItemStack[]> armor = new HashMap<>();
	
	public static HashMap<Egg, Player> eggs = new HashMap<>();
	
	public static void darChange(Player p){
		change.add(p);
		p.getInventory().addItem(new ItemStack(Material.EGG, 10));
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void projectHit(ProjectileLaunchEvent e){
		if(e.getEntity() instanceof Egg){
			if(e.getEntity().getShooter() instanceof Player){
				Player p = (Player) e.getEntity().getShooter();
				if(change.contains(p)){
					Egg egg = (Egg) e.getEntity();
					eggs.put(egg, p);
				}
			}
		}
	}
	
	@EventHandler
	public void dano(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			if(e.getDamager() instanceof Egg){
				
				if(eggs.containsKey((Egg) e.getDamager())){
					final Player atirou = eggs.get((Egg) e.getDamager());
					final Player levou = (Player) e.getEntity();
					
					armor.put(atirou, atirou.getInventory().getArmorContents());
					armor.put(levou, levou.getInventory().getArmorContents());
					
					atirou.getInventory().setArmorContents(armor.get(levou));
					levou.getInventory().setArmorContents(armor.get(atirou));
					
					String name = levou.getName();
					
					if(Arrays.fakes.containsKey(levou)){
						name = Arrays.fakes.get(levou).getFakeName();
					}
					
					atirou.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce trocu sua armadura de " + Variaveis.InfoColor + name + Variaveis.GeralColor + "aproveite ela por " + Variaveis.InfoColor + "15 " + Variaveis.InfoColor + "segundos");
					levou.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Alguem trocou de armadura com voce");
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Variaveis.main, new Runnable(){
						public void run(){
							if(armor.get(atirou) != null){
								atirou.getInventory().setArmorContents(armor.get(atirou));
								armor.remove(atirou);
							}else{
								atirou.getInventory().setArmorContents(null);
							}
							if(armor.get(levou) != null){
								levou.getInventory().setArmorContents(armor.get(levou));
								armor.remove(levou);
							}else{
								levou.getInventory().setArmorContents(null);
							}
							atirou.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Sua armadura voltou a ser a de antes novamente");
							levou.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Sua armadura voltou a ser a de antes novamente");
						}
					}, 15 * 20);
				}
				
			}
		}
	}
	
	@EventHandler
	public void click(InventoryClickEvent e){
		if(e.getWhoClicked() instanceof Player){
			Player p = (Player) e.getWhoClicked();
			if(armor.containsKey(p)){
				for(ItemStack i : armor.get(p)){
					if(e.getCurrentItem() == i);
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void drop(PlayerDropItemEvent e){
			Player p = (Player) e.getPlayer();
			if(armor.containsKey(p)){
				for(ItemStack i : armor.get(p)){
					if(e.getItemDrop() == i);
					e.setCancelled(true);
				}
			}		
	}
		
}
