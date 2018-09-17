package me.dhg.listeners;

import me.dhg.principal.Main;
import me.dhg.utils.Arrays;
import me.dhg.utils.BossBar;
import me.dhg.utils.Fake;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class Join implements Listener{
	Main main;
	public Join(Main main){
		this.main = main;
	}
	@SuppressWarnings({ "deprecation"})
	@EventHandler
	public void Entrar(final PlayerJoinEvent e) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		BossBar.removeStatusBar(e.getPlayer());
		BossBar.setStatusBar(e.getPlayer(), Variaveis.BarText, 1f);
		
		Arrays.semEspectadores.add(e.getPlayer());
		
		for(String name : Arrays.admin){
			Player pl = Bukkit.getPlayer(name);
			if(!e.getPlayer().hasPermission("dHG.cmd.admin")){
				e.getPlayer().hidePlayer(pl);
			}
		}
		
		for(Player pl : Arrays.fakes.keySet()){
			Fake.fakeToPlayer(e.getPlayer(), Arrays.fakes.get(pl));
		}
		
		for(Player pl : Arrays.espectador){
			e.getPlayer().hidePlayer(pl);
		}
		
		if(!Arrays.participando.contains(e.getPlayer().getName())){
			Arrays.kitPlayer.put(e.getPlayer(), "Sem Kit");			
			
			e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
			e.getPlayer().setFoodLevel(20);
			e.getPlayer().setHealth(20);
			e.getPlayer().setGameMode(GameMode.SURVIVAL);
			e.getPlayer().getInventory().clear();
			
			
			e.getPlayer().setExp(0);
			
			for(PotionEffect efeito : e.getPlayer().getActivePotionEffects()){
					e.getPlayer().removePotionEffect(efeito.getType());
			}
			
			Bukkit.getScheduler().scheduleAsyncDelayedTask(main, new Runnable(){
				public void run(){
					if(e.getPlayer().getVehicle() != null){
						e.getPlayer().getVehicle().remove();
					}
				}
			}, 20);
		
		}
		
		e.setJoinMessage(null);
		final Player p = e.getPlayer();
		if(Variaveis.iniciou == false){
			
			Metodos.darItensIniciais(e.getPlayer());

			if(!Arrays.participando.contains(p.getName())){
				Arrays.participando.add(p.getName());
			}
			
		}else{		
			if(!Arrays.participando.contains(p.getName())){
				if(Variaveis.iniciou && p.hasPermission("dHG.spectar")){
					Metodos.setEspectador(p);
					return;
				}else{
					Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable(){
						public void run(){
							p.kickPlayer(Variaveis.tag + Variaveis.GeralColor +  "Jogo em Andamento \n Compre vip e tenha 5 minutos a mais :)");
						}
					}, 5);
				}
				if(Variaveis.tempoPartida <= 300 && p.hasPermission("dHG.vip") && Variaveis.invencibilidade == false){
						Arrays.participando.add(p.getName());
						p.getInventory().addItem(new ItemStack(Material.COMPASS));
					e.setJoinMessage(Variaveis.tag + Variaveis.GeralColor +  "O jogador " + Variaveis.InfoColor + p.getName() + Variaveis.GeralColor + "entrou na partida");
				}else{
					Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable(){
						public void run(){
							p.kickPlayer(Variaveis.tag + Variaveis.GeralColor +  "Jogo em Andamento \n Compre vip e tenha 5 minutos a mais :)");
						}
					}, 5);
				}				
			}else{
				if(Variaveis.iniciou && p.hasPermission("dHG.spectar")){
					Metodos.setEspectador(p);
					return;
				}else{
					Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable(){
						public void run(){
							p.kickPlayer(Variaveis.tag + Variaveis.GeralColor +  "Jogo em Andamento \n Compre vip e tenha 5 minutos a mais :)");
						}
					}, 5);
				}
				
				if(Variaveis.invencibilidade == false){
					Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable(){
						public void run(){
							p.kickPlayer(Variaveis.tag + Variaveis.GeralColor +  "Jogo em Andamento \n Compre vip e tenha 5 minutos a mais :)");
						}
					}, 5);
				}else{
					p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Bem vindo novamente :)");
				}
			}			
		}
		
		if(e.getPlayer().hasPermission("dhg.cmd.admin")){
			e.getPlayer().chat("/admin");
		}
	}
	
}
