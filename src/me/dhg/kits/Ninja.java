package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class Ninja implements Listener{

	public static ArrayList<String> ninja = new ArrayList<String> ();
	public static HashMap<String, String> tpTo = new HashMap<>(); 
	public static HashMap<String, Long> cooldown = new HashMap<>(); 
	
	public static void darNinja(Player p){
		cooldown.put(p.getName(), 0l);
		ninja.add(p.getName());
	}
	
	@EventHandler
	public void ninja(PlayerToggleSneakEvent e){
		if(!e.isSneaking()){
			if(ninja.contains(e.getPlayer().getName())){
				if(tpTo.containsKey(e.getPlayer().getName())){
					if(Metodos.acabouCooldown(e.getPlayer(), 10, cooldown)){
						Player to = Bukkit.getPlayer(tpTo.get(e.getPlayer().getName()));
						if(to.isOnline() && e.getPlayer().getLocation().distance(to.getLocation()) <= 100){
							e.getPlayer().teleport(to.getLocation());
						}else{
							e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor + "O jogador esta muito longe");
						}
					}else{
						e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor + "Aguarde o cooldown de " + Variaveis.InfoColor + Metodos.getRemainingCooldown(e.getPlayer(), 10, cooldown) + Variaveis.GeralColor + " segundos");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void hit(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			Player d = (Player) e.getDamager();
			if(ninja.contains(p.getName())){
				tpTo.put(p.getName(), d.getName());
			}
		}
	}
	
	@EventHandler
	public void death(PlayerDeathEvent e){
		if(e.getEntity().getKiller() instanceof Player){
			Player p = (Player) e.getEntity().getKiller();
			tpTo.remove(p.getName());
		}
	}
	
}
