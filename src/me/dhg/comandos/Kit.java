package me.dhg.comandos;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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
import me.dhg.utils.Arrays;
import me.dhg.utils.KitInv;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

public class Kit implements CommandExecutor, Listener{
	
	public static HashMap<String, KitInv> inv = new HashMap<>();
	
	public static String kits[] = {"Stomper", "Viper", "Lumberjack", "Gladiator", "Berserker", "Thor", "Worm", "Anchor", "Archer", "Cannibal", "Boxer", "Beatmaster",
		"Camel", "Change", "Checkpoint", "Cookiemonster", "Cultivator", "Demoman", "Digger", "Endermage", "Fireman", "Fisherman", "Flash", "Forcefield", "Tower",
		"C4", "Urgal", "Grandpa", "Turtle", "Ninja", "Kangaroo", "Grappler", "Surprise", "Hulk", "Jackhammer"};
		
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
				if(sender instanceof Player){
					Player p = (Player) sender;				
					if((Variaveis.iniciou == false || p.hasPermission("dHG.vip") && Variaveis.tempoPartida <= 300 && Arrays.kitPlayer.get(p).equals("Sem Kit")) || p.hasPermission("dHG.trocarkit")){
						if(args.length < 1){
							
							KitInv kitinv = new KitInv(p, kits);
							inv.put(p.getName(), kitinv);
							
						}else{
							String kit = args[0];
							for(String kitn : kits){
								if(kit.equalsIgnoreCase(kitn) && p.hasPermission("dHG.kit." + kitn)){
									Arrays.kitPlayer.put(p, kitn);
									if(Variaveis.iniciou){
										Metodos.darKit(p);
									}
									p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + kitn);
									break;
								}
							}
						}
					}else{
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "O Torneio ja iniciou !"); 
					}
				}
				
		return false;
	}
		
	@EventHandler
	public void invClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory() != null){
			if(e.getInventory().getName().equals("§a§lKits") || e.getInventory().getName().equals("§a§lKits 2")){
				e.setCancelled(true);
				
				if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR){
					
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aProxima pagina") || e.getCurrentItem().getItemMeta().getDisplayName().equals("§aPagina anterior")){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aProxima pagina")){
							p.openInventory(inv.get(p.getName()).getInv2());
						}						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aPagina anterior")){
							p.openInventory(inv.get(p.getName()).getInv1());
						}
					}else{
						if(inv.containsKey(p.getName())){
							inv.remove(p.getName());
						}
						p.closeInventory();
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aStomper")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Stomper");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Stomper");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aStomper")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Stomper");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Stomper");
							p.closeInventory();
							Stomper.darStomper(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aViper")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Viper");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Viper");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aViper")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Viper");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Viper");
							p.closeInventory();
							Viper.darViper(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLumberjack")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Lumberjack");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Lumberjack");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLumberjack")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Lumberjack");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Lumberjack");
							p.closeInventory();
							Lumberjack.darLumberjack(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aGladiator")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Gladiator");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Gladiator");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aGladiator")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Gladiator");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Gladiator");
							p.closeInventory();
							Gladiator.darGladiator(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBerserker")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Berserker");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Berserker");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBerserker")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Berserker");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Berserker");
							p.closeInventory();
							Berserker.darBerserker(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aThor")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Thor");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Thor");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aThor")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Thor");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Thor");
							p.closeInventory();
							Thor.darThor(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aWorm")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Worm");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Worm");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aWorm")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Worm");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Worm");
							p.closeInventory();
							Worm.darWorm(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAnchor")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Anchor");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Anchor");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAnchor")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Anchor");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Anchor");
							p.closeInventory();
							Anchor.darAnchor(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aArcher")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Archer");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Archer");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aArcher")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Archer");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Archer");
							p.closeInventory();
							Archer.darArcher(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCannibal")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Cannibal");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Cannibal");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCannibal")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Cannibal");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Cannibal");
							p.closeInventory();
							Cannibal.darCannibal(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBeatmaster")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Beatmaster");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Beatmaster");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBeatmaster")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Beatmaster");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Beatmaster");
							p.closeInventory();
							Beatmaster.darBeatmaster(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBoxer")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Boxer");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Boxer");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBoxer")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Boxer");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Boxer");
							p.closeInventory();
							Boxer.darBoxer(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCamel")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Camel");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Camel");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCamel")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Camel");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Camel");
							p.closeInventory();
							Camel.darCamel(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aChange")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Change");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Change");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aChange")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Change");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Change");
							p.closeInventory();
							Change.darChange(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCheckpoint")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Checkpoint");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Checkpoint");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCheckpoint")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Checkpoint");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Checkpoint");
							p.closeInventory();
							CheckPoint.darCheckpoint(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCookiemonster")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Cookiemonster");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Cookiemonster");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCookiemonster")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Cookiemonster");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Cookiemonster");
							p.closeInventory();
							CookieMonster.darCookiemonster(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCultivator")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Cultivator");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Cultivator");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCultivator")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Cultivator");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Cultivator");
							p.closeInventory();
							Cultivator.darCultivator(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aDemoman")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Demoman");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Demoman");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aDemoman")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Demoman");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Demoman");
							p.closeInventory();
							Demoman.darDemoman(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aDigger")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Digger");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Digger");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aDigger")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Digger");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Digger");
							p.closeInventory();
							Digger.darDigger(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFireman")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Fireman");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Fireman");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFireman")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Fireman");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Fireman");
							p.closeInventory();
							Fireman.darFireman(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFisherman")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Fisherman");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Fisherman");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFisherman")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Fisherman");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Fisherman");
							p.closeInventory();
							Fisherman.darFisherman(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFlash")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Flash");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Flash");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFlash")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Flash");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Flash");
							p.closeInventory();
							Flash.darFlash(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aEndermage")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Endermage");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Endermage");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aEndermage")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Endermage");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Endermage");
							p.closeInventory();
							Endermage.darEndermage(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aForcefield")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Forcefield");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Forcefield");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aForcefield")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Forcefield");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Forcefield");
							p.closeInventory();
							ForceField.darForceField(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aTower")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Tower");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Tower");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aTower")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Tower");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Tower");
							p.closeInventory();
							Tower.darTower(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aC4")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "C4");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "C4");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aC4")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "C4");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "C4");
							p.closeInventory();
							C4.darC4(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aUrgal")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Urgal");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Urgal");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aUrgal")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Urgal");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Urgal");
							p.closeInventory();
							Urgal.darUrgal(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aGrandpa")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Grandpa");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Grandpa");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aGrandpa")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Grandpa");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Grandpa");
							p.closeInventory();
							Grandpa.darGrandpa(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aTurtle")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Turtle");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Turtle");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aTurtle")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Turtle");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Turtle");
							p.closeInventory();
							Turtle.darTurtle(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aNinja")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Ninja");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Ninja");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aNinja")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Ninja");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Ninja");
							p.closeInventory();
							Ninja.darNinja(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aKangaroo")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Kangaroo");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Kangaroo");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aKangaroo")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Kangaroo");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Kangaroo");
							p.closeInventory();
							Kangaroo.darKangaroo(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aGrappler")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Grappler");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Grappler");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aGrappler")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Grappler");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Grappler");
							p.closeInventory();
							Grappler.darGrappler(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSurprise")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Surprise");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Surprise");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSurprise")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Surprise");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Surprise");
							p.closeInventory();
							Surprise.darSurprise(p);
						}
					}

					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aHulk")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Hulk");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Hulk");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aHulk")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Hulk");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Hulk");
							p.closeInventory();
							Hulk.darHulk(p);
						}
					}
					
					//Kit
					if(Variaveis.iniciou == false){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aJackhammer")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Jackhammer");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Jackhammer");
							p.closeInventory();
						}
					}else{
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aJackhammer")){						
							e.setCancelled(true);
							Metodos.retirarKit(p);
							Arrays.kitPlayer.put(p, "Jackhammer");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce escolheu o kit " + Variaveis.InfoColor + "Jackhammer");
							p.closeInventory();
							Hulk.darHulk(p);
						}
					}
					
					
					
					
					
					
				}
				
			}
		}
	}
		
}
