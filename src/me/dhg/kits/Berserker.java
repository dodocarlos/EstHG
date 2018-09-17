package me.dhg.kits;

import java.util.ArrayList;

import me.dhg.utils.Variaveis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Berserker implements Listener{
	
	public static ArrayList<Player> berserker = new ArrayList<Player> ();
		
	public static void darBerserker(Player p){
		berserker.add(p);
	}
	
	
	@EventHandler
	public void matar(PlayerDeathEvent e){
		if(Variaveis.iniciou){
			if(e.getEntity().getKiller() instanceof Player){
				Player matou = (Player) e.getEntity().getKiller();
				if(berserker.contains(matou)){
					matou.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 30, 1));
				}
			}
		}
	}
}
