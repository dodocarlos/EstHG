package me.dhg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

public class Hulk implements Listener{

	public static ArrayList<String> hulk = new ArrayList<String> ();
	public static HashMap<String, Long> cooldown = new HashMap<String, Long> ();
	
	public static void darHulk(Player p){
		hulk.add(p.getName());
	}
	
	@EventHandler
	public void interagir(PlayerInteractEntityEvent e){
		if(e.getRightClicked() instanceof Player && hulk.contains(e.getPlayer().getName())){
			Player p = e.getPlayer();
			Player clicado = (Player) e.getRightClicked();
			if(Metodos.acabouCooldown(p, 3, cooldown)){
				p.setPassenger(clicado);
			}else{
				p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Aguarde o cooldown de " + Variaveis.InfoColor + Metodos.getRemainingCooldown(p, 3, cooldown) + Variaveis.GeralColor + " segundos");
			}
		}
	}
	
}
