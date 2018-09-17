package me.dhg.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import me.dhg.principal.Main;
import me.dhg.utils.Utils1v1;
import me.dhg.utils.Utils1v1.Type;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Listener1v1 implements Listener{

	public static ArrayList<String> convites = new ArrayList<String>();
	public static ArrayList<Player> cooldown = new ArrayList<Player>();
	
	public static HashMap<String, Utils1v1.Type> tipos = new HashMap<String, Utils1v1.Type> ();
	
	public static HashMap<Player, Utils1v1> list1v1 = new HashMap<Player, Utils1v1> ();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void interagir(PlayerInteractEntityEvent e){
		if(e.getRightClicked() instanceof Player){
			Player clicou = e.getPlayer();
			Player clicado = (Player) e.getRightClicked();
			if(e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD && Variaveis.iniciou == false){
				if(!convites.contains(clicado.getName() + "_" + clicou.getName()) || !convites.contains(clicou.getName() + "_" + clicado.getName())){
					if(!cooldown.contains(clicou)){
//						Inventory tipos = Bukkit.createInventory(clicou, 45, "§31v1 - " + clicado.getName());
//						
//						ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
//						ItemMeta im = vidro.getItemMeta();
//						im.setDisplayName(Variaveis.NomeDoServidor);
//						vidro.setItemMeta(im);
//						
//						ItemStack normal = new ItemStack(Material.DIAMOND_SWORD);
//						ItemMeta normalmeta = normal.getItemMeta();
//						normalmeta.setDisplayName("§a1v1 Normal");
//						normal.setItemMeta(normalmeta);
//						
//						ItemStack buffed = new ItemStack(Material.POTION);
//						ItemMeta buffedmeta = buffed.getItemMeta();
//						buffedmeta.setDisplayName("§a1v1 Buffed");
//						buffed.setItemMeta(buffedmeta);
//
//						ItemStack refil = new ItemStack(Material.MUSHROOM_SOUP);
//						ItemMeta refilmeta = refil.getItemMeta();
//						refilmeta.setDisplayName("§a1v1 com Refil");
//						refil.setItemMeta(refilmeta);
//						
//						ItemStack custom = new ItemStack(Material.ANVIL);
//						ItemMeta custommeta = custom.getItemMeta();
//						custommeta.setDisplayName("§a1v1 Custom");
//						custom.setItemMeta(custommeta);
//						
//						tipos.setItem(19, normal);
//						tipos.setItem(21, buffed);
//						tipos.setItem(23, refil);
//						tipos.setItem(25, custom);
//						
//						for(int i = 0; i < 45; i ++){
//							if(tipos.getItem(i) == null || tipos.getItem(i).getType() == Material.AIR){
//								tipos.setItem(i, vidro);
//							}
//						}
//						
//						clicou.openInventory(tipos);
						
						convites.add(clicado.getName() + "_" + clicou.getName());
						clicado.sendMessage(Variaveis.tag + "" + Variaveis.InfoColor + " " + clicou.getName() + Variaveis.GeralColor + "convidou vocÃª para um 1v1");
						clicou.sendMessage(Variaveis.tag + Variaveis.GeralColor + "VocÃª convidou " + Variaveis.InfoColor + " " + clicado.getName() + Variaveis.GeralColor + "para um 1v1");
						
					}else{
						clicou.sendMessage(Variaveis.tag + "Ferramenta em cooldown");
					}
				}else{
					
					Utils1v1 atual1v1 = new Utils1v1(Main.arena1v1, clicado, clicou, Type.Normal);
					list1v1.put(clicado, atual1v1);
					list1v1.put(clicou, atual1v1);
					atual1v1.iniciar();
					for(Player pl : Bukkit.getOnlinePlayers()){
						if(!(pl == clicou)){
							clicado.hidePlayer(pl);
						}
						if(!(pl == clicado)){
							clicou.hidePlayer(pl);
						}
					}
					if(convites.contains(clicado.getName() + "_" + clicou.getName())){
						convites.remove(clicado.getName() + "_" + clicou.getName());
					}
					if(convites.contains(clicou.getName() + "_" + clicado.getName())){
						convites.remove(clicou.getName() + "_" + clicado.getName());
					}
					
//					Utils1v1.Type tipo = tipos.get(clicado.getName() + "_" + clicou.getName());
//					if(tipo == Utils1v1.Type.Normal){
//						Utils1v1 atual1v1 = new Utils1v1(Main.arena1v1, clicado, clicou, Type.Normal);
//						list1v1.put(clicado, atual1v1);
//						list1v1.put(clicou, atual1v1);
//						atual1v1.iniciar();
//						convites.remove(clicado.getName() + "_" + clicou.getName());
//					}
//					if(tipo == Utils1v1.Type.Buffed){
//						Utils1v1 atual1v1 = new Utils1v1(Main.arena1v1, clicado, clicou, Type.Buffed);
//						list1v1.put(clicado, atual1v1);
//						list1v1.put(clicou, atual1v1);
//						atual1v1.iniciar();
//						convites.remove(clicado.getName() + "_" + clicou.getName());
//					}	
//					if(tipo == Utils1v1.Type.Refil){
//						Utils1v1 atual1v1 = new Utils1v1(Main.arena1v1, clicado, clicou, Type.Refil);
//						list1v1.put(clicado, atual1v1);
//						list1v1.put(clicou, atual1v1);
//						atual1v1.iniciar();
//						convites.remove(clicado.getName() + "_" + clicou.getName());
//					}
				}
			}
			}
	}
	
	@EventHandler
	public void clicar(InventoryClickEvent e){
		if(e.getInventory().getName().split(" - ")[0].equals("§31v1")){
			e.setCancelled(true);
			Player clicado = Bukkit.getPlayer(e.getInventory().getName().split(" - ")[1]);
			Player clicou = (Player) e.getWhoClicked();
			if(e.getCurrentItem() != null){
				if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD){
					convites.add(clicou.getName() + "_" + clicado.getName());
					tipos.put(clicou.getName() + "_" + clicado.getName(), Type.Normal);
					clicou.sendMessage(Variaveis.tag + "Voce convidou " + Variaveis.InfoColor + " " + clicado.getName() + Variaveis.GeralColor + " para um 1v1 " + Variaveis.InfoColor + "normal");
					clicado.sendMessage(Variaveis.tag + "Voce foi convidado por " + Variaveis.InfoColor + " " + clicou.getName() + "§a para um 1v1 " + Variaveis.InfoColor + " normal");
					clicou.closeInventory();
				}
				if(e.getCurrentItem().getType() == Material.POTION){
					convites.add(clicou.getName() + "_" + clicado.getName());
					tipos.put(clicou.getName() + "_" + clicado.getName(), Type.Buffed);
					clicou.sendMessage(Variaveis.tag + "Voce convidou " + Variaveis.InfoColor + " " + clicado.getName() + Variaveis.GeralColor + " para um 1v1 " + Variaveis.InfoColor + "buffed");
					clicado.sendMessage(Variaveis.tag + "Voce foi convidado por " + Variaveis.InfoColor + " " + clicou.getName() + Variaveis.GeralColor + " para um 1v1 " + Variaveis.InfoColor + "buffed");
					clicou.closeInventory();
				}
				if(e.getCurrentItem().getType() == Material.MUSHROOM_SOUP){
					convites.add(clicou.getName() + "_" + clicado.getName());
					tipos.put(clicou.getName() + "_" + clicado.getName(), Type.Refil);
					clicou.sendMessage(Variaveis.tag + "Voce convidou " + Variaveis.InfoColor + " " + clicado.getName() + Variaveis.GeralColor + " para um 1v1 " + Variaveis.InfoColor + " com refil");
					clicado.sendMessage(Variaveis.tag + "Voce foi convidado por " + Variaveis.InfoColor + " " + clicou.getName() + Variaveis.GeralColor + " para um 1v1 " + Variaveis.InfoColor + "com refil");
					clicou.closeInventory();
				}
			}
		}
	}
	
}
