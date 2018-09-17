package me.dhg.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import me.dhg.kits.Anchor;
import me.dhg.kits.Archer;
import me.dhg.kits.Beatmaster;
import me.dhg.kits.Berserker;
import me.dhg.kits.Boxer;
import me.dhg.kits.C4;
import me.dhg.kits.Camel;
import me.dhg.kits.Cannibal;
import me.dhg.kits.Change;
import me.dhg.kits.CheckPoint;
import me.dhg.kits.CookieMonster;
import me.dhg.kits.Cultivator;
import me.dhg.kits.Demoman;
import me.dhg.kits.Digger;
import me.dhg.kits.Endermage;
import me.dhg.kits.Fireman;
import me.dhg.kits.Fisherman;
import me.dhg.kits.Flash;
import me.dhg.kits.ForceField;
import me.dhg.kits.Gladiator;
import me.dhg.kits.Grandpa;
import me.dhg.kits.Grappler;
import me.dhg.kits.Hulk;
import me.dhg.kits.Jackhammer;
import me.dhg.kits.Kangaroo;
import me.dhg.kits.Lumberjack;
import me.dhg.kits.Ninja;
import me.dhg.kits.Stomper;
import me.dhg.kits.Surprise;
import me.dhg.kits.Thor;
import me.dhg.kits.Tower;
import me.dhg.kits.Turtle;
import me.dhg.kits.Urgal;
import me.dhg.kits.Viper;
import me.dhg.kits.Worm;
import me.dhg.principal.Main;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutNamedEntitySpawn;
import net.minecraft.util.com.mojang.authlib.GameProfile;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Difficulty;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Team;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;

public class Metodos {
	
	static Main main;
	
	public Metodos(Main main) {
		Metodos.main = main;
	}
	
	public static String nameGanhou = "";
	
	public static boolean fim = false;
	
	public static int scinicio = 0;
	
	public static int segundoPartida = 0;    
	public static  int minutoPartida = 0;     
	public static int horaPartida = 0;   
	
    public static int segundoInicio = 0;      
    public static  int minutoInicio = 0;     
	public static int horaInicio = 0;   
    
    public static int segundoInvencibilidade = 0;      
    public static  int minutoInvencibilidade = 0;     
	public static int horaInvencibilidade = 0;	    
	public void iniciar(){
		final Random r = new Random();
		scinicio = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable(){
			@SuppressWarnings("deprecation")
			public void run(){	
												
				horaPartida = Variaveis.tempoPartida / 3600;
				minutoPartida = (Variaveis.tempoPartida % 3600) / 60;
				segundoPartida = Variaveis.tempoPartida % 60;
			  
				horaInicio = Variaveis.tempoInicio / 3600;
				minutoInicio = (Variaveis.tempoInicio % 3600) / 60;
				segundoInicio = Variaveis.tempoInicio % 60;	
				
				horaInvencibilidade = Variaveis.tempoInvencibilidade / 3600;
				minutoInvencibilidade = (Variaveis.tempoInvencibilidade % 3600) / 60;
				segundoInvencibilidade = Variaveis.tempoInvencibilidade % 60;	
				
				for(Player pl : Bukkit.getOnlinePlayers()){
					ScoreBoard.updateScore(pl);
				}
				
				if(Variaveis.iniciou == false){
					if(Variaveis.tempoInicio > 0){
						Variaveis.tempoInicio--;
						if(Variaveis.tempoInicio == 15 && Arrays.participando.size() >= Variaveis.MinimumPlayers){
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.setAllowFlight(false);
								pl.setFlying(false);
								pl.getInventory().clear();
								pl.getInventory().setArmorContents(null);;
								if(pl.getVehicle() != null){
									pl.getVehicle().remove();
								}								
							}
						}
					}
					
					if(Variaveis.tempoInicio <= 0){
						if(Arrays.participando.size() >= Variaveis.MinimumPlayers){
							Variaveis.iniciou = true;
							Variaveis.invencibilidade = true;							
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Torneio iniciado, boa sorte a todos");	
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Temos um total de " + Variaveis.InfoColor + Arrays.participando.size() + Variaveis.GeralColor + " jogadores");
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Invencibilidade acaba em " + Variaveis.InfoColor + Variaveis.tempoInvencibilidade);
							BossBar.removeAllStatusBars();
							abrirPortoes();
							
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.getInventory().addItem(new ItemStack(Material.COMPASS));
								Main.stats.addPartida(pl);
								darKit(pl);
								for(PotionEffect effect : pl.getActivePotionEffects()){
									pl.removePotionEffect(effect.getType());
								}
								pl.playSound(pl.getLocation(), Sound.ANVIL_LAND, 1f, 1f);
							}
							
							for(World w : Bukkit.getWorlds()){
								w.setDifficulty(Difficulty.NORMAL);
							}
						}else{
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Poucos Jogadores !");
							Variaveis.tempoInicio = 120;
						}
					}else{
						switch (Variaveis.tempoInicio) {
						case 180:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Iniciando em " + Variaveis.InfoColor + "3" + Variaveis.GeralColor + " minutos");
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 120:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Iniciando em " + Variaveis.InfoColor + "2" + Variaveis.GeralColor + " minutos");
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 60:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Iniciando em " + Variaveis.InfoColor + "1" + Variaveis.GeralColor + " minuto");
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 30:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Iniciando em " + Variaveis.InfoColor + Variaveis.tempoInicio + Variaveis.GeralColor + " segundos");
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 10:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Iniciando em " + Variaveis.InfoColor + Variaveis.tempoInicio + Variaveis.GeralColor + " segundos");
							break;
						case 5:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Iniciando em " + Variaveis.InfoColor + Variaveis.tempoInicio + Variaveis.GeralColor + " segundos");
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 4:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Iniciando em " + Variaveis.InfoColor + Variaveis.tempoInicio + Variaveis.GeralColor + " segundos");
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 3:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Iniciando em " + Variaveis.InfoColor + Variaveis.tempoInicio + Variaveis.GeralColor + " segundos");
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 2:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Iniciando em " + Variaveis.InfoColor + Variaveis.tempoInicio + Variaveis.GeralColor + " segundos");
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 1:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Iniciando em " + Variaveis.InfoColor + Variaveis.tempoInicio + Variaveis.GeralColor + " segundo");
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						}
					}
					
				}
				
				if(Variaveis.invencibilidade){
					if(Variaveis.tempoInvencibilidade > 0){
					    Variaveis.tempoInvencibilidade--;
					    switch (Variaveis.tempoInvencibilidade) {
						case 30:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Invencibilidade acaba em " + Variaveis.InfoColor + Variaveis.tempoInvencibilidade + Variaveis.GeralColor + " segundos");
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 10:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Invencibilidade acaba em " + Variaveis.InfoColor + Variaveis.tempoInvencibilidade + Variaveis.GeralColor + " segundos");							
							break;
						case 5:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Invencibilidade acaba em " + Variaveis.InfoColor + Variaveis.tempoInvencibilidade + Variaveis.GeralColor + " segundos");							
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 4:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Invencibilidade acaba em " + Variaveis.InfoColor + Variaveis.tempoInvencibilidade + Variaveis.GeralColor + " segundos");							
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 3:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Invencibilidade acaba em " + Variaveis.InfoColor + Variaveis.tempoInvencibilidade + Variaveis.GeralColor + " segundos");							
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 2:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Invencibilidade acaba em " + Variaveis.InfoColor + Variaveis.tempoInvencibilidade + Variaveis.GeralColor + " segundos");							
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 1:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Invencibilidade acaba em " + Variaveis.InfoColor + Variaveis.tempoInvencibilidade + Variaveis.GeralColor + " segundo");							
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;
						case 0:
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Invencibilidade acabou");
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.playSound(pl.getLocation(), Sound.CLICK, 1f, 1f);
							}
							break;

						}
					}else{
						Variaveis.invencibilidade = false;
					}
				}
				
				if(Variaveis.iniciou){
					  Variaveis.tempoPartida++;						  
				}
				
				
				switch (Variaveis.tempoPartida) {
				case 300:
					Variaveis.miniFeast = Bukkit.getWorld("world").getSpawnLocation().clone().add(r.nextInt(500) - r.nextInt(500), 0, r.nextInt(500) - r.nextInt(500));
					Variaveis.miniFeast.setY(Bukkit.getWorld("world").getHighestBlockYAt(Variaveis.miniFeast));
					Metodos.spawnMiniFeast(Variaveis.miniFeast);
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Um minifeast deu spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.miniFeast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.miniFeast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.miniFeast.getZ()) + Variaveis.GeralColor + ")");
					break;
				case 700:
					Variaveis.miniFeast = Bukkit.getWorld("world").getSpawnLocation().clone().add(r.nextInt(500) - r.nextInt(500), 0, r.nextInt(500) - r.nextInt(500));
					Variaveis.miniFeast.setY(Bukkit.getWorld("world").getHighestBlockYAt(Variaveis.miniFeast));
					Metodos.spawnMiniFeast(Variaveis.miniFeast);
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Um minifeast deu spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.miniFeast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.miniFeast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.miniFeast.getZ()) + Variaveis.GeralColor + ")");
					break;
				case 540:
					final Random r = new Random();
					Variaveis.feast = Bukkit.getWorld("world").getSpawnLocation().clone().add(r.nextInt(500) - r.nextInt(500), 0, r.nextInt(500) - r.nextInt(500));
					Variaveis.feast.setY(Bukkit.getWorld("world").getHighestBlockYAt(Variaveis.feast));
					Metodos.generateFeast(Variaveis.feast);
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "O feast ira dar spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getZ()) + Variaveis.GeralColor + ") em " + Variaveis.InfoColor + " 5 " + Variaveis.GeralColor + "minutos");
					break;
				case 840:
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "O feast ira dar spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getZ()) + Variaveis.GeralColor + ") em " + Variaveis.InfoColor + " 1 " + Variaveis.GeralColor + "minutos");
					break;
				case 870:
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "O feast ira dar spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getZ()) + Variaveis.GeralColor + ") em " + Variaveis.InfoColor + " 30 " + Variaveis.GeralColor + "segundos");
					break;
				case 890:
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "O feast ira dar spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getZ()) + Variaveis.GeralColor + ") em " + Variaveis.InfoColor + " 10 " + Variaveis.GeralColor + "segundos");
					break;
				case 895:
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "O feast ira dar spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getZ()) + Variaveis.GeralColor + ") em " + Variaveis.InfoColor + " 5 " + Variaveis.GeralColor + "segundos");
					break;
				case 896:
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "O feast ira dar spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getZ()) + Variaveis.GeralColor + ") em " + Variaveis.InfoColor + " 4 " + Variaveis.GeralColor + "segundos");
					break;
				case 897:
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "O feast ira dar spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getZ()) + Variaveis.GeralColor + ") em " + Variaveis.InfoColor + " 3 " + Variaveis.GeralColor + "segundos");
					break;
				case 898:
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "O feast ira dar spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getZ()) + Variaveis.GeralColor + ") em " + Variaveis.InfoColor + " 2 " + Variaveis.GeralColor + "segundos");
					break;
				case 899:
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "O feast ira dar spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getZ()) + Variaveis.GeralColor + ") em " + Variaveis.InfoColor + " 1 " + Variaveis.GeralColor + "segundo");
					break;
				case 900:
					Metodos.spawnFeast(Variaveis.feast);
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "O feast deu spawn em X(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getX()) + Variaveis.GeralColor + ") " + "Y(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getY()) + Variaveis.GeralColor + ") " + "Z(" + Variaveis.InfoColor + String.format("%.2f", Variaveis.feast.getZ()) + Variaveis.GeralColor + ")");
					break;
				}
				
				fim();
			
			}
		}, 0, 20);
	}
	
	@SuppressWarnings("deprecation")
	public static void fim(){
		if(fim == false){
			if(Variaveis.iniciou == true){
				if(Arrays.participando.size() == 1){
					final Player p = Bukkit.getPlayer(Arrays.participando.get(0));
					ganhou(p);
					p.setAllowFlight(true);
					p.setFlying(true);
					Variaveis.invencibilidade = true;
					Bukkit.getScheduler().cancelTask(scinicio);
					nameGanhou = p.getName();
					Bukkit.getScheduler().scheduleAsyncRepeatingTask(main, new Runnable(){
						public void run(){						
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  nameGanhou + Variaveis.GeralColor + " ganhou !");
					}
					}, 0, 20 * 2);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable(){
						public void run(){
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.kickPlayer(Variaveis.tag + Variaveis.GeralColor +  nameGanhou + Variaveis.GeralColor + " ganhou !");				
							}
							Bukkit.shutdown();
						}
					}, 20 * 20);
					
				}else if(Arrays.participando.size() < 1){
					Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable(){
						public void run(){
							for(Player pl : Bukkit.getOnlinePlayers()){
								pl.kickPlayer(Variaveis.tag + Variaveis.GeralColor +  "Ninguem " + Variaveis.GeralColor + " ganhou !");				
							}
							Bukkit.shutdown();
						}
					}, 20 * 20);
					Bukkit.getScheduler().scheduleAsyncRepeatingTask(main, new Runnable(){
						public void run(){						
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor + "Ninguem ganhou !");
					}
					}, 0, 20 * 2);
				}
			}
		}
	}
	
	public static void ganhou(Player p){
		fim = true;
		Main.stats.addWins(p);
		
		p.getInventory().clear();
		
		ItemStack mlg = new ItemStack(Material.WATER_BUCKET);
		ItemMeta mlgmeta = mlg.getItemMeta();
		mlgmeta.setDisplayName(ColoredMsg("&c&lMLG"));
		mlg.setItemMeta(mlgmeta);
		
		p.getInventory().setItem(4, mlg);
		
		int y = p.getWorld().getHighestBlockYAt(p.getLocation());
		
		//Vidros
		Location v1 = p.getLocation().add(-1, y, -1);
		Location v2 = p.getLocation().add(0, y, -1);
		Location v3 = p.getLocation().add(1, y, -1);
		
		Location v4 = p.getLocation().add(-1, y, 0);
		Location v5 = p.getLocation().add(0, y, 0);
		Location v6 = p.getLocation().add(1, y, 0);
		
		Location v7 = p.getLocation().add(-1, y, 1);
		Location v8 = p.getLocation().add(0, y, 1);
		Location v9 = p.getLocation().add(1, y, 1);
		
		v1.getBlock().setType(Material.GLASS);
		v2.getBlock().setType(Material.GLASS);
		v3.getBlock().setType(Material.GLASS);
		v4.getBlock().setType(Material.GLASS);
		v5.getBlock().setType(Material.GLASS);
		v6.getBlock().setType(Material.GLASS);
		v7.getBlock().setType(Material.GLASS);
		v8.getBlock().setType(Material.GLASS);
		v9.getBlock().setType(Material.GLASS);
		
		//Bolos
		Location b1 = p.getLocation().add(-1, y + 1, -1);
		Location b2 = p.getLocation().add(0, y + 1, -1);
		Location b3 = p.getLocation().add(1, y + 1, -1);
		
		Location b4 = p.getLocation().add(-1, y + 1, 0);
		Location b5 = p.getLocation().add(0, y + 1, 0);
		Location b6 = p.getLocation().add(1, y + 1, 0);
		
		Location b7 = p.getLocation().add(-1, y + 1, 1);
		Location b8 = p.getLocation().add(0, y + 1, 1);
		Location b9 = p.getLocation().add(1, y + 1, 1);
		
		b1.getBlock().setType(Material.CAKE_BLOCK);
		b2.getBlock().setType(Material.CAKE_BLOCK);
		b3.getBlock().setType(Material.CAKE_BLOCK);
		b4.getBlock().setType(Material.CAKE_BLOCK);
		b5.getBlock().setType(Material.CAKE_BLOCK);
		b6.getBlock().setType(Material.CAKE_BLOCK);
		b7.getBlock().setType(Material.CAKE_BLOCK);
		b8.getBlock().setType(Material.CAKE_BLOCK);
		b9.getBlock().setType(Material.CAKE_BLOCK);
		
		p.teleport(b5.clone().add(0, 1, 0));
		
		for(int i = -5; i < 5; i ++){
			for(int j = 5; j > -5; j--){
				Firework f = (Firework) p.getWorld().spawn(p.getLocation().clone().add(j, 0, i), Firework.class);
		        
		        FireworkMeta fm = f.getFireworkMeta();
		        fm.addEffect(FireworkEffect.builder()
		                        .flicker(false)
		                        .trail(true)
		                        .with(Type.BALL_LARGE)
		                        .withColor(Color.GREEN, Color.BLUE, Color.AQUA, Color.LIME, Color.ORANGE, Color.RED)
		                        .withFade(Color.BLUE)
		                        .build());
		        fm.setPower(1);
		        f.setFireworkMeta(fm);
			}
		}
	}
	
	public static void generateFeast(Location newFeast){
		Variaveis.feast = newFeast;       
        Metodos.spawnObject(Variaveis.feast, "Feast");	
	}
	
	public static void spawnFeast(Location newFeast){        
		//Mesa de encantamentos
		Location mesa = Variaveis.feast.clone().add(21, 2, 21);
		mesa.getBlock().setType(Material.ENCHANTMENT_TABLE);
		
		//Baus
		Location c1 = mesa.clone().add(1, -1, -1);
		Location c2 = mesa.clone().add(-1, -1, -1);
		Location c9 = mesa.clone().add(-1, -1, 1);
		Location c10 = mesa.clone().add(1, -1, 1);
		
		//Baus torres
		Location c13 = mesa.clone().add(10, 6, -8);
		Location c14 = mesa.clone().add(10, 6, 8);
		Location c15 = mesa.clone().add(10, 6, -10);
		Location c16 = mesa.clone().add(10, 6, 10);
		
		Location c17 = mesa.clone().add(12, 6, -8);
		Location c18 = mesa.clone().add(12, 6, 8);
		Location c19 = mesa.clone().add(12, 6, -10);
		Location c20 = mesa.clone().add(12, 6, 10);
		
		Location c21 = mesa.clone().add(-10, 6, -8);
		Location c22 = mesa.clone().add(-10, 6, 8);
		Location c23 = mesa.clone().add(-10, 6, -10);
		Location c24 = mesa.clone().add(-10, 6, 10);
		
		Location c25 = mesa.clone().add(-12, 6, -8);
		Location c26 = mesa.clone().add(-12, 6, 8);
		Location c27 = mesa.clone().add(-12, 6, -10);
		Location c28 = mesa.clone().add(-12, 6, 10);
		
		c1.getBlock().setType(Material.CHEST);
		c2.getBlock().setType(Material.CHEST);
		c9.getBlock().setType(Material.CHEST);
		c10.getBlock().setType(Material.CHEST);
		c13.getBlock().setType(Material.CHEST);
		c14.getBlock().setType(Material.CHEST);
		c15.getBlock().setType(Material.CHEST);
		c16.getBlock().setType(Material.CHEST);
		c17.getBlock().setType(Material.CHEST);
		c18.getBlock().setType(Material.CHEST);
		c19.getBlock().setType(Material.CHEST);
		c20.getBlock().setType(Material.CHEST);
		c21.getBlock().setType(Material.CHEST);
		c22.getBlock().setType(Material.CHEST);
		c23.getBlock().setType(Material.CHEST);
		c24.getBlock().setType(Material.CHEST);
		c25.getBlock().setType(Material.CHEST);
		c26.getBlock().setType(Material.CHEST);
		c27.getBlock().setType(Material.CHEST);
		c28.getBlock().setType(Material.CHEST);
		
		Chest chest1 = (Chest) c1.getBlock().getState();
		Chest chest2 = (Chest) c2.getBlock().getState();
		Chest chest9 = (Chest) c9.getBlock().getState();
		Chest chest10 = (Chest) c10.getBlock().getState();
		Chest chest13 = (Chest) c13.getBlock().getState();
		Chest chest14 = (Chest) c14.getBlock().getState();
		Chest chest15 = (Chest) c15.getBlock().getState();
		Chest chest16 = (Chest) c16.getBlock().getState();
		Chest chest17 = (Chest) c17.getBlock().getState();
		Chest chest18 = (Chest) c18.getBlock().getState();
		Chest chest19 = (Chest) c19.getBlock().getState();
		Chest chest20 = (Chest) c20.getBlock().getState();
		Chest chest21 = (Chest) c21.getBlock().getState();
		Chest chest22 = (Chest) c22.getBlock().getState();
		Chest chest23 = (Chest) c23.getBlock().getState();
		Chest chest24 = (Chest) c24.getBlock().getState();
		Chest chest25 = (Chest) c25.getBlock().getState();
		Chest chest26 = (Chest) c26.getBlock().getState();
		Chest chest27 = (Chest) c27.getBlock().getState();
		Chest chest28 = (Chest) c28.getBlock().getState();

		
		Random r = new Random();
		
		Chest baus[] = {chest1, chest2, chest9, chest10, chest13, chest14, chest15, 
				chest16, chest17, chest18, chest19, chest20, chest21, chest22, chest23, chest24, chest25, chest26, chest27, chest28};
		for(Chest bau : baus){
			Inventory bauinv = bau.getInventory();
			for(ItemStack i : Variaveis.itemsFeast.keySet()){
				if(r.nextInt(100) <= Variaveis.itemsFeast.get(i)){
					bauinv.addItem(i);
				}
			}
		}
		
	}
	
	public static void spawnMiniFeast(Location miniFeast){
		
        int y = miniFeast.getWorld().getHighestBlockYAt((int) miniFeast.getX(), (int) miniFeast.getZ());
        
        spawnObject(new Location(miniFeast.getWorld(), miniFeast.getX(), y, miniFeast.getZ()), "MiniFeast");
        
        //Mesa de encantamento
        Location enchantTable = miniFeast.clone().add(8, 2, 8);
        enchantTable.getBlock().setType(Material.ENCHANTMENT_TABLE);
        
        //Baus
        Location c1 = enchantTable.clone().add(-1, -1, 1);
        Location c2 = enchantTable.clone().add(-1, -1, -1);
        Location c3 = enchantTable.clone().add(1, -1, 1);
        Location c4 = enchantTable.clone().add(1, -1, -1);
        
        c1.getBlock().setType(Material.CHEST);
        c2.getBlock().setType(Material.CHEST);
        c3.getBlock().setType(Material.CHEST);
        c4.getBlock().setType(Material.CHEST);
        
        Random r = new Random();
        
        Chest chest1 = (Chest) c1.getBlock().getState();
        Chest chest2 = (Chest) c2.getBlock().getState();
        Chest chest3 = (Chest) c3.getBlock().getState();
        Chest chest4 = (Chest) c4.getBlock().getState();
        
        Chest baus[] = {chest1, chest2, chest3, chest4};
		for(Chest bau : baus){
			Inventory bauinv = bau.getInventory();
			for(ItemStack i : Variaveis.itemsMiniFeast.keySet()){
				if(r.nextInt(100) <= Variaveis.itemsMiniFeast.get(i)){
					bauinv.addItem(i);
				}
			}
		}
        
	}
	
	public static void setKitMaterials(){
		Arrays.kitMaterial.put("Stomper", createItem(Material.IRON_BOOTS, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Pisoteie seus inimigos,", Variaveis.GeralColor + "dando a eles o seu dano de queda")));
		Arrays.kitMaterial.put("Viper", createItem(Material.SPIDER_EYE, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Tenha 33% de change de", Variaveis.GeralColor + "envenenar seus inimigos")));
		Arrays.kitMaterial.put("Lumberjack", createItem(Material.WOOD_AXE, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Ao quebrar uma madeira", Variaveis.GeralColor + "todas as outras cairao")));
		Arrays.kitMaterial.put("Gladiator", createItem(Material.IRON_FENCE, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Crie uma arena de vidro aonde", Variaveis.GeralColor + "ir tirar pvp com seu oponente")));
		Arrays.kitMaterial.put("Berserker", createItem(Material.POTION, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Ao matar um jogador ganhe", Variaveis.GeralColor + "força por 30 segundos")));
		Arrays.kitMaterial.put("Thor", createItem(Material.WOOD_AXE, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Ao clicar com o seu machado", Variaveis.GeralColor + "solte um raio ou cause uma explosao")));
		Arrays.kitMaterial.put("Worm", createItem(Material.DIRT, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Quebre terra, mas", Variaveis.GeralColor + "instantaneamente")));
		Arrays.kitMaterial.put("Anchor", createItem(Material.ANVIL, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Nao tome e nem de knockback")));
		Arrays.kitMaterial.put("Archer", createItem(Material.BOW, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Ganhe um arco e 10 flechas", Variaveis.GeralColor + "Ao acertar o oponente, recupera a flecha")));
		Arrays.kitMaterial.put("Cannibal", createItem(Material.ROTTEN_FLESH, 0, java.util.Arrays.asList(Variaveis.GeralColor + "O dano gerado da fome ao seu oponente", Variaveis.GeralColor + "Voce recupera fome ao bater em seu oponente")));
		Arrays.kitMaterial.put("Beatmaster", createItem(Material.BONE, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Spawne lobos que irao te ajudar", Variaveis.GeralColor + "na hora da luta")));
		Arrays.kitMaterial.put("Boxer", createItem(Material.STONE_SWORD, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Sua mao da tanto dano quanto uma espada de pedra", Variaveis.GeralColor + "O dano levado reduzido para 1 coracao")));	
		Arrays.kitMaterial.put("Camel", createItem(Material.SAND, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Faça sopas com cacto e areia", Variaveis.GeralColor + "Ganhe velocidade no deserto")));	
		Arrays.kitMaterial.put("Change", createItem(Material.EGG, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Ao acertar o seu oponente com um ovo, ", Variaveis.GeralColor + "troque a sua armadura pela dele")));	
		Arrays.kitMaterial.put("Checkpoint", createItem(Material.SIGN, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Marque um local no qual", Variaveis.GeralColor + "voce pode voltar depois")));	
		Arrays.kitMaterial.put("Cookiemonster", createItem(Material.COOKIE, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Seu cookie pode regenerar vida, fome e te dar speed", Variaveis.GeralColor + "Para conseguir mais, quebre matos")));	
		Arrays.kitMaterial.put("Cultivator", createItem(Material.SAPLING, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Plante árvores instantaneamente", Variaveis.GeralColor + "Vale também para sementes (Trigo, Melancia, ...)")));	
		Arrays.kitMaterial.put("Demoman", createItem(Material.STONE_PLATE, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Cascalho + placa de pressao de pedra = BOOOOM")));	
		Arrays.kitMaterial.put("Digger", createItem(Material.DRAGON_EGG, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Cave um burraco de 5x5")));	
		Arrays.kitMaterial.put("Fireman", createItem(Material.LAVA_BUCKET, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Inicie com um balde de agua", Variaveis.GeralColor + "Imune a fogo e raios")));	
		Arrays.kitMaterial.put("Fisherman", createItem(Material.FISHING_ROD, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Pesque seus inimigos e", Variaveis.GeralColor + "os teletransporte para o seu local")));	
		Arrays.kitMaterial.put("Flash", createItem(Material.REDSTONE_TORCH_ON, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Clique com a redstone e se teletransporte", Variaveis.GeralColor + "para o local que voce esta olhando")));	
		Arrays.kitMaterial.put("Endermage", createItem(Material.ENDER_PORTAL, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Use o portal para", Variaveis.GeralColor + "puxar jogadores")));	
		Arrays.kitMaterial.put("Forcefield", createItem(Material.MAGMA_CREAM, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Use o creme de magma para ativar seu Forcefield", Variaveis.GeralColor + "Com ele voce ira hitar seu inimigo sem toca-lo")));	
		Arrays.kitMaterial.put("Tower", createItem(Material.DIRT, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Junte Stomper + Worm")));	
		Arrays.kitMaterial.put("C4", createItem(Material.TNT, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Jogue um C4 e o exploda")));	
		Arrays.kitMaterial.put("Urgal", createItem(Material.POTION, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Ganhe 3 poÃ§oes de forÃ§a")));	
		Arrays.kitMaterial.put("Grandpa", createItem(Material.STICK, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Ganhe 1 graveto com repulsao 2")));
		Arrays.kitMaterial.put("Turtle", createItem(Material.BEDROCK, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Segurando o shift nao da e nem recebe dano")));
		Arrays.kitMaterial.put("Ninja", createItem(Material.NETHER_STAR, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Aperte shift e va para o ultimo jogador hitado")));
		Arrays.kitMaterial.put("Kangaroo", createItem(Material.FIREWORK, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Use seu firework para fugir de seus inimigos")));
		Arrays.kitMaterial.put("Grappler", createItem(Material.LEASH, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Use seu laÃ§o para se puxar")));
		Arrays.kitMaterial.put("Surprise", createItem(Material.NAME_TAG, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Escolha um kit surpresa")));
		Arrays.kitMaterial.put("Hulk", createItem(Material.GHAST_TEAR, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Pegue seu inimigo nas costas")));
		Arrays.kitMaterial.put("Jackhammer", createItem(Material.STONE_AXE, 0, java.util.Arrays.asList(Variaveis.GeralColor + "Com seu machado quebre todos", Variaveis.GeralColor + "Todos os blocos acima e abaixo")));
	}
	
	public static void retirarKit(Player p){
		Arrays.kitPlayer.remove(p);
		Arrays.kitPlayer.put(p, "Sem Kit");
		
		Stomper.stomper.remove(p);
		Viper.viper.remove(p);
		Lumberjack.lumberjack.remove(p);
		Gladiator.gladiator.remove(p);
		Berserker.berserker.remove(p);
		Thor.thor.remove(p);
		Worm.worm.remove(p);
		Anchor.anchor.remove(p);
		Archer.archer.remove(p);
		Cannibal.cannibal.remove(p);
		Beatmaster.beatmaster.remove(p);
		Boxer.boxer.remove(p);
		Camel.camel.remove(p);
		Change.change.remove(p);
		CheckPoint.checkpoint.remove(p);
		CookieMonster.cookiemonster.remove(p);
		Cultivator.cultivador.remove(p);
		Demoman.demoman.remove(p);
		Digger.digger.remove(p);
		Fireman.fireman.remove(p);
		Fisherman.fisherman.remove(p);
		Flash.flash.remove(p);
		Endermage.endermage.remove(p);
		ForceField.forcefield.remove(p.getName());
		C4.c4.remove(p.getName());
		Turtle.turtle.remove(p.getName());
		Ninja.ninja.remove(p.getName());
		Kangaroo.kangaroo.remove(p.getName());
		Grappler.grappler.remove(p.getName());
		Hulk.hulk.remove(p.getName());
		Jackhammer.jackhammer.remove(p.getName());
	}
	
	public static void darKit(Player p){
			if(!Arrays.kitPlayer.containsKey(p)){
				return;
			}
			
			if(Arrays.kitPlayer.get(p).equals("Stomper")){
				Stomper.darStomper(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Viper")){
				Viper.darViper(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Lumberjack")){
				Lumberjack.darLumberjack(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Gladiator")){
				Gladiator.darGladiator(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Berserker")){
				Berserker.darBerserker(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Thor")){
				Thor.darThor(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Worm")){
				Worm.darWorm(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Anchor")){
				Anchor.darAnchor(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Archer")){
				Archer.darArcher(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Cannibal")){
				Cannibal.darCannibal(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Beatmaster")){
				Beatmaster.darBeatmaster(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Boxer")){
				Boxer.darBoxer(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Camel")){
				Camel.darCamel(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Change")){
				Change.darChange(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Checkpoint")){
				CheckPoint.darCheckpoint(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Cookiemonster")){
				CookieMonster.darCookiemonster(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Cultivator")){
				Cultivator.darCultivator(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Demoman")){
				Demoman.darDemoman(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Digger")){
				Digger.darDigger(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Fireman")){
				Fireman.darFireman(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Fisherman")){
				Fisherman.darFisherman(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Flash")){
				Flash.darFlash(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Endermage")){
				Endermage.darEndermage(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Forcefield")){
				ForceField.darForceField(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Tower")){
				Tower.darTower(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("C4")){
				C4.darC4(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Urgal")){
				Urgal.darUrgal(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Grandpa")){
				Grandpa.darGrandpa(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Turtle")){
				Turtle.darTurtle(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Ninja")){
				Ninja.darNinja(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Kangaroo")){
				Kangaroo.darKangaroo(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Grappler")){
				Grappler.darGrappler(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Surprise")){
				Surprise.darSurprise(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Hulk")){
				Hulk.darHulk(p);
			}
			
			if(Arrays.kitPlayer.get(p).equals("Jackhammer")){
				Jackhammer.darJackhammer(p);
			}
			
	}
		
	public static String ColoredMsg(String text){
		return text.replaceAll("&", "§");
	}
	
	@SuppressWarnings("deprecation")
	public static void setEspectador(Player p){
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		
		if(Arrays.participando.contains(p.getName())){
			Arrays.participando.remove(p.getName());
		}
		
		Arrays.espectador.add(p);

		ItemStack bau = new ItemStack(Material.CHEST);
		ItemMeta baumeta = bau.getItemMeta();
		baumeta.setDisplayName("" + Variaveis.InfoColor + "Jogadores");
		bau.setItemMeta(baumeta);
		
		p.getInventory().setItem(4, bau);
		
		p.setCanPickupItems(false);
		p.setAllowFlight(true);
		p.setFlying(true);
		
		for(Player pl : Bukkit.getOnlinePlayers()){
			if(pl.hasPermission("dHG.verespectador")){
				if(Arrays.semEspectadores.contains(pl)){
					pl.hidePlayer(p);
				}
			}else{
				pl.hidePlayer(p);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void removeEspectador(Player p){
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		
		if(!Arrays.participando.contains(p.getName())){
			Arrays.participando.add(p.getName());
		}
		
		Arrays.espectador.remove(p);
		p.getInventory().addItem(new ItemStack(Material.COMPASS));
		p.setCanPickupItems(true);
		p.setAllowFlight(false);
		p.setFlying(false);
		
		for(Player pl : Bukkit.getOnlinePlayers()){
			pl.showPlayer(p);
		}
	}
	
	public static int contarSopas(Player p){
		int sopas = 0;
		for(ItemStack i : p.getInventory().getContents()){
			if(i != null && i.getType() == Material.MUSHROOM_SOUP){
				sopas ++;
			}
		}
		return sopas;
	}
	
	@SuppressWarnings("deprecation")
	public static void updatePlayer(Player p, String name){
		
		PacketPlayOutEntityDestroy destoy = new PacketPlayOutEntityDestroy(p.getEntityId());
		PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(((CraftPlayer)p).getHandle());
		
		Field b;
		try {
			b = spawn.getClass().getDeclaredField("b");
			b.setAccessible(true);
			b.set(spawn, new GameProfile(p.getUniqueId(), name));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		
		for(Player pl : Bukkit.getOnlinePlayers()){
			if(pl != p){
				((CraftPlayer)pl).getHandle().playerConnection.sendPacket(destoy);
				((CraftPlayer)pl).getHandle().playerConnection.sendPacket(spawn);
			}
		}
	}
	
	public static void setTag(Player p, String tag, String tabColor){
		String name;	
		String tabName;	
		if(Arrays.fakes.containsKey(p)){
			name = tag + Arrays.fakes.get(p).getFakeName();
			tabName = tabColor + Arrays.fakes.get(p).getFakeName();
		}else{
			 name = tag + p.getName();
			 tabName = tabColor + p.getName();
		}
		
		p.setDisplayName(name);	
		
		if(tabName.length() > 16){
			tabName = tabName.substring(0, 16);							
			p.setPlayerListName(tabName);
		}
		
		p.setPlayerListName(tabName);
		if(!tag.equalsIgnoreCase("§f")){
			p.sendMessage("§aVoce esta usando a tag: " + tag);
		}else{
			p.sendMessage("§aVoce esta usando a tag: §fNormal");
		}
		
		if(ScoreBoard.sb.getTeam(p.getName()) == null){
			Team team = ScoreBoard.sb.registerNewTeam(p.getName());
			team.setAllowFriendlyFire(true);
			team.setPrefix(tag);
			team.addPlayer(p);
			team.setSuffix("");
		}else{
			Team team = ScoreBoard.sb.getTeam(p.getName());
			team.setAllowFriendlyFire(true);
			team.setPrefix(tag);
			team.setSuffix("");
		}
		
	}
	
	public static ItemStack createItem(Material material, int Byte, List<String> lore){
		ItemStack item = new ItemStack(material, 1, (byte)Byte);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
//	@SuppressWarnings("deprecation")
//	public static void cor(){
//		final String cor[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
//		
//		final String corinv[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
//		
//		Bukkit.getScheduler().scheduleAsyncRepeatingTask(Variaveis.main, new Runnable(){
//			public void run(){
//				Variaveis.cor = cor[new Random().nextInt(cor.length)];
//				if(Variaveis.iniciou == false){
//					Short coritem = Short.valueOf(corinv[new Random().nextInt(corinv.length)]);
//					for(Player p : Bukkit.getOnlinePlayers()){
//						if(Kit.inv.containsKey(p)){
//							for(int i = 0; i < 54; i ++){
//								if(Kit.inv.get(p).getItem(i).getType() == Material.STAINED_GLASS_PANE){
//									ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, coritem);
//									ItemMeta im = vidro.getItemMeta();
//									im.setDisplayName(Variaveis.NomeDoServidor);
//									vidro.setItemMeta(im);
//									Kit.inv.get(p).setItem(i, vidro);
//								}
//							}
//						}
//						p.updateInventory();
//					}
//				}
//			}
//		}, 0, 10);
//		
//	}
	
	public static int getRemainingCooldown(Player p, int delay, HashMap<String, Long> cooldown){
		if(!cooldown.containsKey(p.getName())){
			cooldown.put(p.getName(), 0l);
		}
		return (int) (((cooldown.get(p.getName()) + delay * 1000) - System.currentTimeMillis()) / 1000);
	}
	
	public static boolean acabouCooldown(Player p, int delay, HashMap<String, Long> cooldown){
		if(!cooldown.containsKey(p.getName())){
			cooldown.put(p.getName(), 0l);
		}
		
		if(System.currentTimeMillis() - cooldown.get(p.getName()) >= delay * 1000){
			cooldown.put(p.getName(), System.currentTimeMillis());
			return true;			
		}
		
		return false;
	}
	
	public static void darItensIniciais(Player p){
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				
				
				ItemStack bau = new ItemStack(Material.CHEST);
				ItemMeta baumeta = bau.getItemMeta();
				baumeta.setDisplayName("§3Escolha seu Kit " + Variaveis.GeralColor + "(Clique)");
				bau.setItemMeta(baumeta);
				
				ItemStack buycraft = new ItemStack(Material.DIAMOND);
				ItemMeta buycraftmeta = buycraft.getItemMeta();
				buycraftmeta.setDisplayName("§3Comprar VIP " + Variaveis.GeralColor + "(Clique)");
				buycraft.setItemMeta(buycraftmeta);
								
				ItemStack kanga = new ItemStack(Material.FIREWORK);
				ItemMeta kangameta = kanga.getItemMeta();
				kangameta.setDisplayName("§3Kangaroo " + Variaveis.GeralColor + "(Clique)");
				kanga.setItemMeta(kangameta);
				
				ItemStack cama = new ItemStack(Material.BED);
				ItemMeta camameta = cama.getItemMeta();
				camameta.setDisplayName("§3Ativar / Desativar o Fly " + Variaveis.GeralColor + "(Clique)");
				cama.setItemMeta(camameta);
								
				ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				ItemMeta headmeta = head.getItemMeta();
				headmeta.setDisplayName("§3Status " + Variaveis.GeralColor + "(Clique)");
				head.setItemMeta(headmeta);
				
				p.getInventory().setItem(0, bau);
				p.getInventory().setItem(1, buycraft);
				p.getInventory().setItem(4, head);
				p.getInventory().setItem(7, kanga);
				p.getInventory().setItem(8, cama);

	}
	
	public static String toPage(List<String> lines, int i){
		StringBuilder page = new StringBuilder();
		i = 0;
		for(String line : Arrays.lines){
			i ++;
			page.append(line.replaceAll("<space>", "\n").replaceAll("&", "§"));
		}
		return page.toString();
	}

	
	public static void respawn(final Player p){
	    main.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable()
	    {
	        public void run()
	        {
	            if(p.isDead())
	            {
	                try
	                {
	                    Object nmsPlayer = p.getClass().getMethod("getHandle").invoke(p);
	                    Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
	                    Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");

	                    for(Object ob : enumClass.getEnumConstants()){
	                        if(ob.toString().equals("PERFORM_RESPAWN")){
	                            packet = packet.getClass().getConstructor(enumClass).newInstance(ob);
	                        }
	                    }

	                    Object con = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
	                    con.getClass().getMethod("a", packet.getClass()).invoke(con, packet);
	                }
	                catch (Exception e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	    });
	    int x = new Random().nextInt(400) - new Random().nextInt(400);
	    int z = new Random().nextInt(400) - new Random().nextInt(400);
	    p.teleport(new Location(p.getWorld(), x, p.getWorld().getHighestBlockYAt(x, z) + 3, z));
	}
	
	public static boolean hasInvSpace(Player p){
		boolean hasSpace = false;
		for(ItemStack i : p.getInventory().getContents()){
			if(i == null){
				hasSpace = true;
				break;
			}
		}
		return hasSpace;
	}
	
	public static void deleteDir(File dir){
	    if (dir.isDirectory())
	    {
	      String[] children = dir.list();
	      for (int i = 0; i < children.length; i++) {
	        deleteDir(new File(dir, children[i]));
	      }
	    }
	    dir.delete();
	  }
	
	public static void spawnObject(Location l, String objectName){
//		Map<String, CustomObject> objects = TerrainControl.getCustomObjectManager().loadObjects(new File("./plugins/dHG/Objects"));
//		Map<String, CustomObject> objects = TerrainControl.getCustomObjectManager().loadObjects(new File("./plugins/dHG/Objects"));
//		objects.get(objectName).spawnForced(TerrainControl.getWorld(l.getWorld().getName()), new Random(), Rotation.NORTH, (int) l.getX(), (int) l.getY(), (int) l.getZ());
	
		WorldEditPlugin we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        File schematic = new File("./plugins/dHG/Objects/" + objectName + ".schematic");
        EditSession session = we.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(l.getWorld()), 1000000);
        try {
        	 MCEditSchematicFormat.getFormat(schematic).load(schematic).paste(session, new Vector(l.getX(), l.getY(), l.getZ()), false);
        } catch (MaxChangedBlocksException
                | com.sk89q.worldedit.data.DataException | IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        
	}
	
	public static void abrirPortoes(){
		for(String s : Main.blocksConfig.getConfig().getConfigurationSection("GateBlock").getKeys(false)){
				Location bLocation = Main.blocksConfig.loadLocation("GateBlock." + s);
				Block b = bLocation.getBlock();
				b.setType(Material.AIR);
		}
	}
	
}