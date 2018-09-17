package me.dhg.listeners;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void sair(PlayerQuitEvent e){
		
		if(Arrays.espectador.contains(e.getPlayer())){
			Arrays.espectador.remove(e.getPlayer());
		}
		
		if(Arrays.addGateBlocks.contains(e.getPlayer().getName())){
			Arrays.addGateBlocks.remove(e.getPlayer().getName());
		}
		
		if(Arrays.admin.contains(e.getPlayer().getName())){
			Player p = e.getPlayer();
			Arrays.admin.remove(p.getName());
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			p.getInventory().setContents(Arrays.adminInv.get(p));
			p.getInventory().setArmorContents(Arrays.adminInvArmor.get(p));
			Arrays.adminInv.remove(p);
			Arrays.adminInvArmor.remove(p);
			p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Voce saiu do modo ADMIN");
			if(Arrays.espectador.contains(p)){
				Arrays.espectador.remove(p);
			}
			if(!Arrays.participando.contains(p.getName())){
				Arrays.participando.add(p.getName());
			}				
			p.setCanPickupItems(true);
			p.setFlying(false);
			p.setAllowFlight(false);						
			for(Player pl : Bukkit.getOnlinePlayers()){
				pl.showPlayer(p);
			}
		}
		
		e.setQuitMessage(null);
		final Player p = e.getPlayer();
		
		if(Variaveis.iniciou == false){
			if(Arrays.participando.contains(p.getName())){
				Arrays.participando.remove(p.getName());
			}
		}else{
			if(Arrays.participando.contains(p.getName()) && Variaveis.invencibilidade == false){
				Arrays.participando.remove(p.getName());
				e.setQuitMessage(Variaveis.tag + Variaveis.GeralColor + "O jogador " + Variaveis.InfoColor + p.getName() + Variaveis.GeralColor + " saiu da partida");
				return;
			}
			if(Arrays.participando.contains(p.getName()) && Variaveis.invencibilidade == true){
				Bukkit.getScheduler().scheduleSyncDelayedTask(Variaveis.main, new Runnable(){
					public void run(){
						if(!p.isOnline() && Arrays.participando.contains(p.getName())){
							Arrays.participando.remove(p.getName());
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor + "O jogador " + Variaveis.InfoColor + p.getName() + "(" + Arrays.kitPlayer.get(p) + ") " + Variaveis.GeralColor + " demorou para relogar e foi desclassificado");
							Bukkit.broadcastMessage("" + Variaveis.InfoColor + Arrays.participando.size() + Variaveis.GeralColor + " jogadores restantes");	
						}
					}
				}, 120);
			}
		}
	}
		
}
