package me.dhg.kits;

import java.util.ArrayList;
import java.util.Random;

import me.dhg.utils.Variaveis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Viper implements Listener{
	
	Random r = new Random();
	
	public static ArrayList<Player> viper = new ArrayList<Player>();
	
	public static void darViper(Player p){
		viper.add(p);
	}
	
	@EventHandler
	public void bater(EntityDamageByEntityEvent e){
		if(Variaveis.iniciou){
			if(e.getEntity() instanceof Player){
				if(e.getDamager() instanceof Player){
					if(viper.contains(e.getDamager()) && !e.isCancelled()){
						if(r.nextInt(2) == 1){
							Player p = (Player) e.getEntity();
							p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 7, 1));
						}
					}
				}
			}
		}
	}
	
}
