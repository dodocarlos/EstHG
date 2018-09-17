package me.dhg.listeners;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class AdminListener implements Listener {

	@EventHandler
	public void interagir(PlayerInteractEntityEvent e){
		if(Arrays.admin.contains(e.getPlayer().getName())){
			if(e.getRightClicked() instanceof Player){
				Player clicado = (Player) e.getRightClicked();
				Player p = e.getPlayer();				
				if(p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR){
					e.getPlayer().openInventory(clicado.getInventory());
					e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce abriu o inventario de " + Variaveis.InfoColor + clicado.getName());
				}
			}
		}
	}
	
}
