package me.dhg.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Grandpa {
	
	public static void darGrandpa(Player p){
		ItemStack graveto = new ItemStack(Material.STICK);
		graveto.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
		p.getInventory().addItem(graveto);
	}
	
}
