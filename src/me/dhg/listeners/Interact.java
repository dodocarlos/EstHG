package me.dhg.listeners;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interact implements Listener{

	@EventHandler
	public void interagir(PlayerInteractEvent e){
		if(Arrays.admin.contains(e.getPlayer().getName())){
			return;
		}
		if(Variaveis.iniciou == false && !Arrays.admin.contains(e.getPlayer())){
			e.setCancelled(true);
		}
		
		if(Arrays.espectador.contains(e.getPlayer())){
			e.setCancelled(true);
		}
		
		if(e.getPlayer().getItemInHand().getType() == Material.COMPASS){
			bussola(e.getPlayer());
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public void bussola(Player p){	
		Player alvo = null;
		double distancia = Double.MAX_VALUE;		
		for(Player pl : Bukkit.getOnlinePlayers()){
			if(distancia >= pl.getLocation().distance(p.getLocation()) && pl.getLocation().distance(p.getLocation()) >= 20){
				distancia = pl.getLocation().distance(p.getLocation());
				alvo = pl;				
			}
		}
		if(alvo != null && !Arrays.espectador.contains(alvo) && Arrays.participando.contains(alvo.getName())){
			p.setCompassTarget(alvo.getLocation());
			String name = alvo.getName();
			if(Arrays.fakes.containsKey(alvo)){
				name = Arrays.fakes.get(alvo).getFakeName();
			}
			p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Bussola apontando para " + Variaveis.InfoColor + name);
		}else{
			p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Sem Jogadores, Bussola apontando para " + Variaveis.InfoColor + "Spawn");
			p.setCompassTarget(p.getWorld().getSpawnLocation());
		}		
	}		
	
	
}
