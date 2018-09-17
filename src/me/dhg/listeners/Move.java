package me.dhg.listeners;

import me.dhg.principal.Main;
import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Move implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void mover(final PlayerMoveEvent e){
		
		if(Main.borda.isOutOfBorder(e.getPlayer())){
			if(!Arrays.foradaborda.contains(e.getPlayer())){
				Arrays.foradaborda.add(e.getPlayer());
				e.getPlayer().getWorld().strikeLightning(e.getPlayer().getLocation());
				e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor + "Voce esta fora da borda do mundo");
				Bukkit.getScheduler().scheduleAsyncDelayedTask(Variaveis.main, new Runnable(){
					public void run(){
						Arrays.foradaborda.remove(e.getPlayer());
					}
				}, 15);
			}
		}
		
		for(Entity en : e.getPlayer().getNearbyEntities(5D, 5D, 5D)){
			if(en instanceof Player){
				Player p = (Player) en;
				if(Arrays.espectador.contains(p)){
					if(!p.hasPermission("dHG.bypassmoveespectador")){
						p.teleport(p.getLocation().clone().add(0, 7, 0));
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Voce nao pode chegar perto de jogadores");
					}
				}
			}
		}
	}
	
}
