package me.dhg.kits;

import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class Urgal {
	
	public static void darUrgal(Player p){
		Potion potion = new Potion(PotionType.STRENGTH, 1);
		p.getInventory().addItem(potion.toItemStack(3));
	}
	
}
