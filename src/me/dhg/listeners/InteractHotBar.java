package me.dhg.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import me.dhg.principal.Main;
import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

public class InteractHotBar implements Listener{
	
	ArrayList<String> kanga = new ArrayList<> ();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void interagir(PlayerInteractEvent e){
		if(e.getItem() != null){
			if(Variaveis.iniciou == false){
				if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
					Player p = e.getPlayer();
					
					if(e.getItem().getType() == Material.CHEST){
						Bukkit.dispatchCommand(p, "kit");
					}
					
					if(e.getItem().getType() == Material.DIAMOND){
						p.sendMessage(Variaveis.tag + "Loja: " + Variaveis.InfoColor + " " + Variaveis.siteLoja);
					}
					
					if(e.getItem().getType() == Material.GOLD_NUGGET){
						//Fazer kits por xp
					}
					
					if(e.getItem().getType() == Material.PAPER){
						if(Variaveis.LinkDoForm != null){
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Link do form: §f" + Variaveis.LinkDoForm);
						}else{
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Sem form no momento");
						}
					}
					
					if(e.getItem().getType() == Material.BLAZE_ROD){
						//1v1
					}
					
					if(e.getItem().getType() == Material.LEASH){
						//Grappler
					}
					
					if(e.getItem().getType() == Material.CARPET && e.getItem().getItemMeta().getDisplayName().equals("§3Hotbar 2 §7(Clique)")){
						
						p.getInventory().clear();
						
						ItemStack carpete = new ItemStack(Material.CARPET, 1, (byte)2);
						ItemMeta carpetemeta = carpete.getItemMeta();
						carpetemeta.setDisplayName("§3Hotbar 1 §7(Clique)");
						carpete.setItemMeta(carpetemeta);
						
						ItemStack relogio = new ItemStack(Material.REDSTONE_TORCH_ON);
						ItemMeta relogiometa = relogio.getItemMeta();
						relogiometa.setDisplayName("§3Youtubers online §7(Clique)");
						relogio.setItemMeta(relogiometa);
						
						ItemStack cama = new ItemStack(Material.BED);
						ItemMeta camameta = cama.getItemMeta();
						camameta.setDisplayName("§3Ativar / Desativar o Fly §7(Clique)");
						cama.setItemMeta(camameta);
						
						ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						ItemMeta headmeta = head.getItemMeta();
						headmeta.setDisplayName("§3Status §7(Clique)");
						head.setItemMeta(headmeta);
						
						ItemStack armaduracavalo = new ItemStack(Material.GOLD_BARDING);
						ItemMeta armaduracavalometa = armaduracavalo.getItemMeta();
						armaduracavalometa.setDisplayName("§3Cavalo §7(Clique)");
						armaduracavalo.setItemMeta(armaduracavalometa);
						
						ItemStack olhoender = new ItemStack(Material.EYE_OF_ENDER);
						ItemMeta olhoenderometa = olhoender.getItemMeta();
						olhoenderometa.setDisplayName("§3Teleportar §7(Clique)");
						olhoender.setItemMeta(olhoenderometa);
						
						ItemStack livro = new ItemStack(Material.WRITTEN_BOOK);
						ItemMeta livrometa = livro.getItemMeta();
						livrometa.setDisplayName("§3Informa§§es §7(Clique)");
						livro.setItemMeta(livrometa);
											
						
						p.getInventory().setItem(0, carpete);
						p.getInventory().setItem(1, relogio);
						p.getInventory().setItem(3, cama);
						p.getInventory().setItem(4, head);
						p.getInventory().setItem(5, armaduracavalo);
						p.getInventory().setItem(7, olhoender);
						p.getInventory().setItem(8, livro);
						
						p.updateInventory();
						
					}
					
					//Hotbar 2					
					
					if(e.getItem().getType() == Material.CARPET && e.getItem().getItemMeta().getDisplayName().equals("§3Hotbar 1 §7(Clique)")){
						p.getInventory().clear();	
						
						ItemStack bau = new ItemStack(Material.CHEST);
						ItemMeta baumeta = bau.getItemMeta();
						baumeta.setDisplayName("§3Escolha seu Kit §7(Clique)");
						bau.setItemMeta(baumeta);
						
						ItemStack buycraft = new ItemStack(Material.DIAMOND);
						ItemMeta buycraftmeta = buycraft.getItemMeta();
						buycraftmeta.setDisplayName("§3Comprar VIP §7(Clique)");
						buycraft.setItemMeta(buycraftmeta);
						
						ItemStack pepita = new ItemStack(Material.GOLD_NUGGET);
						ItemMeta pepitatmeta = pepita.getItemMeta();
						pepitatmeta.setDisplayName("§3Kits por EXP §7(Clique)");
						pepita.setItemMeta(pepitatmeta);
						
						ItemStack papel = new ItemStack(Material.PAPER);
						ItemMeta papelmeta = papel.getItemMeta();
						papelmeta.setDisplayName("§3Form para TRIAL §7(Clique)");
						papel.setItemMeta(papelmeta);
						
						ItemStack blaze = new ItemStack(Material.BLAZE_ROD);
						ItemMeta blazemeta = blaze.getItemMeta();
						blazemeta.setDisplayName("§31v1 §7(Clique)");
						blaze.setItemMeta(blazemeta);
						
						ItemStack corda = new ItemStack(Material.LEASH);
						ItemMeta cordameta = corda.getItemMeta();
						cordameta.setDisplayName("§3Grappler §7(Clique)");
						corda.setItemMeta(cordameta);
						
						ItemStack carpete1 = new ItemStack(Material.CARPET, 1, (byte)5);
						ItemMeta carpete1meta = carpete1.getItemMeta();
						carpete1meta.setDisplayName("§3Hotbar 2 §7(Clique)");
						carpete1.setItemMeta(carpete1meta);
						
						e.getPlayer().getInventory().setItem(0, bau);
						e.getPlayer().getInventory().setItem(1, buycraft);
						e.getPlayer().getInventory().setItem(3, pepita);
						e.getPlayer().getInventory().setItem(4, papel);
						e.getPlayer().getInventory().setItem(6, blaze);
						e.getPlayer().getInventory().setItem(7, corda);
						e.getPlayer().getInventory().setItem(8, carpete1);
						
						p.updateInventory();
						
					}
					
					if(e.getItem().getType() == Material.FIREWORK){
						if(Variaveis.tempoInicio >= 15){
							if(!kanga.contains(p.getName())){								
								if(p.isSneaking()){
									p.setVelocity(p.getLocation().getDirection().multiply(1.2D));
							          p.setVelocity(new Vector(p.getVelocity().getX(), 0.5D, p.getVelocity().getZ()));
								}else{
									p.setVelocity(new Vector(p.getVelocity().getX(), 1.0D, p.getVelocity().getZ()));
								}	
								kanga.add(p.getName());
							}
						}else{
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Partida iniciando");
						}
					}
					
					if(e.getItem().getType() == Material.BED){
						if(p.hasPermission("dHG.fly")){
							if(p.getAllowFlight() == false){
								p.setAllowFlight(true);
								p.setFlying(true);
								p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Agora voce pode voar");
							}else{
								p.setAllowFlight(false);
								p.setFlying(false);
								p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Agora nao pode mais voar");
							}
						}else{
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Sem permissao");
						}
					}
					
					if(e.getItem().getType() == Material.SKULL_ITEM){
						org.bukkit.inventory.Inventory status = Bukkit.createInventory(p, 36, "§3Status");
						
						ItemStack kills = new ItemStack(Material.DIAMOND_SWORD);
						ItemMeta killsmeta = kills.getItemMeta();
						killsmeta.setDisplayName("§aMatou: §f" + Main.stats.getkills(p));
						kills.setItemMeta(killsmeta);
						
						ItemStack deaths = new ItemStack(Material.SKULL_ITEM);
						ItemMeta deathssmeta = deaths.getItemMeta();
						deathssmeta.setDisplayName("§aMorreu: §f" + Main.stats.getDeaths(p));
						deaths.setItemMeta(deathssmeta);
						
						ItemStack wins = new ItemStack(Material.CAKE);
						ItemMeta winsmeta = wins.getItemMeta();
						winsmeta.setDisplayName("§aVenceu: §f" + Main.stats.getWins(p));
						wins.setItemMeta(winsmeta);
						
						ItemStack kdr = new ItemStack(Material.GOLD_INGOT);
						ItemMeta kdrmeta = kdr.getItemMeta();
						kdrmeta.setDisplayName("§aKDR: §f" + Main.stats.getKD(p));
						kdr.setItemMeta(kdrmeta);
						
						ItemStack partidas = new ItemStack(Material.IRON_INGOT);
						ItemMeta partidassmeta = partidas.getItemMeta();
						partidassmeta.setDisplayName("§aPartidas Jogadas: §f" + Main.stats.getPartidas(p));
						partidas.setItemMeta(partidassmeta);
						
						status.setItem(11, kills);
						status.setItem(13, wins);
						status.setItem(15, deaths);
						status.setItem(21, kdr);
						status.setItem(23, partidas);
						
						ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
						ItemMeta im = vidro.getItemMeta();
						im.setDisplayName(Variaveis.NomeDoServidor);
						vidro.setItemMeta(im);
						
						for(int i = 0; i < 36; i++){
							if(status.getItem(i) == null || status.getItem(i).getType() == Material.AIR){
								status.setItem(i, vidro);
							}
						}
						
						p.openInventory(status);

					}
					
					if(e.getItem().getType() == Material.GOLD_BARDING){
						Horse cavalo = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
						cavalo.setAdult();
						cavalo.setOwner(p);
						cavalo.setTamed(true);
						cavalo.setPassenger(p);
						cavalo.getInventory().setSaddle(new ItemStack(Material.SADDLE));
						cavalo.getInventory().setArmor(new ItemStack(Material.GOLD_BARDING));
						Arrays.retirarCavalo.add(p);
					}
					
					if(e.getItem().getType() == Material.EYE_OF_ENDER){
						Block b = p.getTargetBlock(null, 100);
						if(b.getType() != Material.AIR){
							Location local = b.getLocation().clone().add(0, 1, 0);
							local.setPitch(p.getLocation().getPitch());
							local.setYaw(p.getLocation().getYaw());
							p.teleport(local);
						}
					}
					
				}
			}			
		}
	}
	
	@EventHandler
	public void interagir2(PlayerInteractEvent e){
		
	}
	
	@EventHandler
	public void desmontar(VehicleExitEvent e){
		if(e.getExited() instanceof Player){
			Player p = (Player) e.getExited();
			if(Arrays.retirarCavalo.contains(p)){
				e.getVehicle().remove();
				Arrays.retirarCavalo.remove(p);
			}
		}
	}
	
	@EventHandler
	public void inv1(InventoryClickEvent e){
		if(e.getInventory().getName().equals("§3Status")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void inv1(InventoryInteractEvent e){
		if(e.getInventory().getName().equals("§3Status")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void andar(PlayerMoveEvent e){
		if(kanga.contains(e.getPlayer().getName())){
			if(((CraftPlayer)e.getPlayer()).getHandle().onGround){
				kanga.remove(e.getPlayer().getName());
			}
		}
	}
}
