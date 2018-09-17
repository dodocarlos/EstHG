package me.dhg.listeners;

import me.dhg.utils.Arrays;
import me.dhg.utils.SpecInv;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpectatorListener implements Listener{

	@EventHandler
	public static void interagir(PlayerInteractEvent e){
		if(e.getItem() != null && e.getItem().getType() == Material.CHEST && Arrays.espectador.contains(e.getPlayer())){
			new SpecInv().create(e.getPlayer());
		}
	}
	
	@EventHandler
	public void inv(InventoryInteractEvent e){
		if(e.getInventory().getName().equals(Variaveis.InfoColor + "Jogadores")){
			e.setCancelled(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void inv2(InventoryClickEvent e){
		if(e.getInventory().getName().equals(Variaveis.InfoColor + "Jogadores")){
			e.setCancelled(true);
			if(e.getCurrentItem() != null){
				if(e.getCurrentItem().getItemMeta().getDisplayName() != "§aProxima pagina" || e.getCurrentItem().getItemMeta().getDisplayName() != "§cPagina anterior"){
					try{
						Player target = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
						e.getWhoClicked().teleport(target);
						e.getWhoClicked().closeInventory();
					}catch(Exception ex){
						for(Player pl : Bukkit.getOnlinePlayers()){
							if(Arrays.fakes.containsKey(pl) && Arrays.fakes.get(pl).getFakeName() == e.getCurrentItem().getItemMeta().getDisplayName()){
								e.getWhoClicked().teleport(pl);
								break;
							}
						}
					}
				}else{
					if(e.getCurrentItem().getItemMeta().getDisplayName() == "§aProxima pagina"){
						SpecInv.invs.get(e.getWhoClicked().getName()).openInv2();
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName() == "§cPagina anterior"){
						SpecInv.invs.get(e.getWhoClicked().getName()).openInv1();
					}
				}
			}
			
		}
	}
	
	@EventHandler
	public void inv3(InventoryCloseEvent e){
		if(e.getInventory().getName().equals(Variaveis.InfoColor + "Jogadores")){
			SpecInv.invs.get(e.getPlayer().getName()).remove();
		}
	}
	
}
