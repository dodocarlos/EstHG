package me.dhg.listeners;


import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TomarSopa implements Listener{

	ItemStack tijela = new ItemStack(Material.BOWL);
	ItemMeta tijelameta = tijela.getItemMeta();
	
	@EventHandler	
	public void interagir(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getItemInHand() != null && p.getItemInHand().getType() == Material.MUSHROOM_SOUP){
				Damageable d = (Damageable) p;
				if(d.getHealth() < 20){
					int sopa = 7;					
					p.setHealth(d.getHealth() + sopa > d.getMaxHealth() ? d.getMaxHealth() : d.getHealth() + sopa);
					p.getItemInHand().setItemMeta(tijelameta);
					p.getItemInHand().setType(Material.BOWL);
					return;
				}
				if(p.getFoodLevel() < 20){
					p.setFoodLevel(p.getFoodLevel() + 7);
					p.getItemInHand().setType(Material.BOWL);
					p.getItemInHand().setItemMeta(tijelameta);
					return;
				}
			}
		}
	}
}
