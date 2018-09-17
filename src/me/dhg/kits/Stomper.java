package me.dhg.kits;

import java.util.ArrayList;
import java.util.List;

import me.dhg.utils.Variaveis;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Stomper implements Listener {

	public static ArrayList<Player> stomper = new ArrayList<Player>();

	public static void darStomper(Player p) {
		stomper.add(p);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void dano(EntityDamageEvent e){
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(Variaveis.iniciou && Variaveis.invencibilidade == false){
				if(stomper.contains(p)) {
					if (e.getCause() == DamageCause.FALL) {
						e.setDamage(4);
						List<Entity> stompados = p.getNearbyEntities(5D, 5D, 5D);

						for (Entity en : stompados) {
							if (en instanceof Player) {
								if (((Player) en).isSneaking()) {
									((Player) en)
											.damage((int) p.getFallDistance() / 7);
									e.setDamage((int) p.getFallDistance() / 8);
								} else {
									((Player) en)
											.damage((int) p.getFallDistance() / 2);
									e.setDamage((int) p.getFallDistance() / 8);
								}
							}
						}

					}

				}
			}
		}
	}

}
