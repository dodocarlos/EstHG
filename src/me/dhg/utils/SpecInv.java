package me.dhg.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpecInv {
	
	public static HashMap<String, SpecInv> invs = new HashMap<> ();	
	private static Inventory players;
	private static Inventory players2;
	private static Player p;
	
	@SuppressWarnings("deprecation")
	public void create(Player p){
		SpecInv.p = p;
		Inventory players = Bukkit.createInventory(p, 54, Variaveis.InfoColor + "Jogadores");
		Inventory players2 = Bukkit.createInventory(p, 54, Variaveis.InfoColor + "Jogadores");
		
		ItemStack vidro = new ItemStack(Material.getMaterial(160));
		ItemMeta vidrometa = vidro.getItemMeta();
		vidrometa.setDisplayName(" ");
		vidro.setItemMeta(vidrometa);
		
		ItemStack wool = new ItemStack(Material.WOOL);
		ItemMeta woolmeta = vidro.getItemMeta();
		woolmeta.setDisplayName(" ");
		wool.setItemMeta(woolmeta);
		
		ItemStack vidro2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta imv = vidro2.getItemMeta();
		imv.setDisplayName(" ");
		vidro2.setItemMeta(imv);
		
		ItemStack dima = new ItemStack(Material.DIAMOND);
		ItemMeta dimameta = vidro.getItemMeta();
		dimameta.setDisplayName(" ");
		dima.setItemMeta(dimameta);
		
		ItemStack next = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta nextmeta = next.getItemMeta();
		nextmeta.setDisplayName("§aProxima pagina");
		next.setItemMeta(nextmeta);
		
		ItemStack previous = new ItemStack(Material.WOOL, 1, (short) 14);
		ItemMeta previousmeta = previous.getItemMeta();
		previousmeta.setDisplayName("§cPagina anterior");
		previous.setItemMeta(previousmeta);
		
		players.setItem(0, wool);
		players.setItem(1, vidro2);
		players.setItem(2, vidro2);
		players.setItem(3, vidro2);
		players.setItem(4, dima);
		players.setItem(5, vidro2);
		players.setItem(6, vidro2);
		players.setItem(7, vidro2);
		players.setItem(8, next);
		
		players2.setItem(0, previous);
		players2.setItem(1, vidro2);
		players2.setItem(2, vidro2);
		players2.setItem(3, vidro2);
		players2.setItem(4, dima);
		players2.setItem(5, vidro2);
		players2.setItem(6, vidro2);
		players2.setItem(7, vidro2);
		players2.setItem(8, wool);
		
		for(String name : Arrays.participando){
			Player target = Bukkit.getPlayer(name);
			ItemStack item = Arrays.kitMaterial.get(Arrays.kitPlayer.get(target));
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(Arrays.fakes.containsKey(target) ? Arrays.fakes.get(target).getFakeName() : target.getName());
			itemmeta.setLore(java.util.Arrays.asList(Variaveis.GeralColor + "Kit: " + Variaveis.InfoColor + " " + Arrays.kitPlayer.get(target), Variaveis.GeralColor + "Vida: " + Variaveis.InfoColor + " " + (((Damageable)target).getHealth()) / 2 * 10));
			item.setItemMeta(itemmeta);
			
			if(players.getItem(53) != null){
				players.addItem(item);
			}else{
				players2.addItem(item);
			}
			
		}
		
		SpecInv.players = players;
		SpecInv.players2 = players2;
		
		p.openInventory(players);
		
		invs.put(p.getName(), this);
	}
	
	public void remove(){
		invs.remove(p.getName());
	}
	
	public void openInv1(){
		p.closeInventory();
		p.openInventory(players);
	}
	
	public void openInv2(){
		p.closeInventory();
		p.openInventory(players2);
	}
	
}
