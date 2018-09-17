package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ForceField implements Listener{

	public static ArrayList<String> forcefield = new ArrayList<String> ();
	public static ArrayList<String> ffAtivo = new ArrayList<String>();
	public static HashMap<String, Long> cooldown = new HashMap<>();
	
	public static void darForceField(Player p){
		cooldown.put(p.getName(), 0l);
		forcefield.add(p.getName());
		p.getInventory().addItem(new ItemStack(Material.MAGMA_CREAM));
	}
	
	@EventHandler
	public void usarFF(final PlayerInteractEvent e){
		if(forcefield.contains(e.getPlayer().getName())){
			if(e.getItem() != null && e.getItem().getType() == Material.MAGMA_CREAM){
				if(Variaveis.invencibilidade == false){
				if(Metodos.acabouCooldown(e.getPlayer(), 120, cooldown)){					
						ffAtivo.add(e.getPlayer().getName());
						e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor +   "Voce ativou o " + Variaveis.InfoColor + "Forcefield");
						Bukkit.getScheduler().scheduleSyncDelayedTask(Variaveis.main, new Runnable(){
							public void run(){
								ffAtivo.remove(e.getPlayer().getName());
							}
						}, 600l);
					}else{
						e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Aguarde o cooldown de " + Variaveis.InfoColor + Metodos.getRemainingCooldown(e.getPlayer(), 120, cooldown) + Variaveis.GeralColor + " segundos");
					}
				}else{
					e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Aguarde a invencibilidade acabar");
				}
			}
		}
	}
	
}
