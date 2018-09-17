package me.dhg.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Utils1v1 {

	private Box arena;
	private Player player1;
	private Player player2;
	private Type type;
	
	public Utils1v1(Box arena, Player player1, Player player2, Type type){
		this.arena = arena;
		this.player1 = player1;
		this.player2 = player2;
		this.type = type;
	}
	
	@SuppressWarnings("deprecation")
	public void iniciar(){		
		
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.getName() != player1.getName()){
				p.showPlayer(player2);
			}
			if(p.getName() != player2.getName()){
				p.showPlayer(player1);
			}
		}
		
		if(type == Type.Normal){
			player1.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			player1.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			player1.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			player1.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

			player2.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			player2.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			player2.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			player2.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
			
			ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta espadameta = espada.getItemMeta();
			espadameta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			espada.setItemMeta(espadameta);
			
			player1.getInventory().setItem(0, espada);
			player2.getInventory().setItem(0, espada);
			
			for(int i = 1; i < 9; i++){
				player1.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
				player2.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
			}
		}
		
		if(type == Type.Refil){
			player1.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			player1.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			player1.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			player1.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

			player2.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			player2.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			player2.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			player2.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
			
			ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta espadameta = espada.getItemMeta();
			espadameta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			espada.setItemMeta(espadameta);
			
			player1.getInventory().setItem(0, espada);
			player2.getInventory().setItem(0, espada);
			
			for(int i = 1; i < 36; i++){
				player1.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
				player2.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
			}
		}
		
		if(type == Type.Buffed){
			player1.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			player1.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			player1.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			player1.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

			player2.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			player2.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			player2.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			player2.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
			
			ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta espadameta = espada.getItemMeta();
			espadameta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			espada.setItemMeta(espadameta);
			
			player1.getInventory().setItem(0, espada);
			player2.getInventory().setItem(0, espada);
			
			for(int i = 1; i < 9; i++){
				player1.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
				player2.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
			}
			
			player1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
			player1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
			
			player2.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
			player2.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
		}
		
		if(type == Type.Custom){
			Inventory custom = Bukkit.createInventory(player1, 45, "§3Custom 1v1");
			
			ItemStack pronto = new ItemStack(Material.WOOL, 1, (short) 2);
			ItemMeta prontometa = pronto.getItemMeta();
			prontometa.setDisplayName("§fPronto");
			pronto.setItemMeta(prontometa);
			
			custom.setItem(0, pronto);
			custom.setItem(9, pronto);
			custom.setItem(18, pronto);
			custom.setItem(27, pronto);
			custom.setItem(36, pronto);
			
			custom.setItem(8, pronto);
			custom.setItem(17, pronto);
			custom.setItem(26, pronto);
			custom.setItem(35, pronto);
			custom.setItem(45, pronto);
			
			ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta espadameta = espada.getItemMeta();
			espadameta.setDisplayName("§bEspada");
			espadameta.setLore(java.util.Arrays.asList("§7Clique esquerdo para mudar", " ", "§aDiamante", "§cFerro", "§cPedra", "§cMadeira", " ", "§7Clique direito para mudar", " ", "§aSem sharpness", "§cSharpness 1", "§cSharpness 2", "§cSharpness 3", "§cSharpness 4", "§cSharpness 5"));
			espada.setItemMeta(espadameta);
			
			ItemStack armadura = new ItemStack(Material.IRON_CHESTPLATE);
			ItemMeta armadurameta = armadura.getItemMeta();
			armadurameta.setDisplayName("§bArmadura");
			armadurameta.setLore(java.util.Arrays.asList("§7Clique esquerdo para mudar", " ", "§cDiamante", "§aFerro", "§cCouro", "§cSem", " ", "§7Clique direito para mudar", "§aSem prote§§o", "§cProtection 1", "§cProtection 2", "§cProtection 3", "§cProtection 4"));
			armadura.setItemMeta(armadurameta);
			
			ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
			ItemMeta sopameta = sopa.getItemMeta();
			sopameta.setDisplayName("§bSopas");
			sopameta.setLore(java.util.Arrays.asList("§7Clique esquerdo para mudar", "Com sopa", "Sem sopa"));
			
			custom.setItem(29, espada);
			custom.setItem(31, armadura);
			custom.setItem(33, sopa);
			
			ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
			ItemMeta im = vidro.getItemMeta();
			im.setDisplayName(Variaveis.NomeDoServidor);
			vidro.setItemMeta(im);
			
			for(int i = 0; i < 45; i ++){
				if(custom.getItem(i) == null || custom.getItem(i).getType() == Material.AIR){
					custom.setItem(i, vidro);
				}
			}
			
			player1.openInventory(custom);
		}
		
		player1.teleport(arena.getLado1());
		player2.teleport(arena.getLado2());
		Arrays.em1v1.add(player1);
		Arrays.em1v1.add(player2);
	}
	
	@SuppressWarnings("deprecation")
	public void finalizar(){
		
		for(PotionEffect efeito : player1.getActivePotionEffects()){
			player1.removePotionEffect(efeito.getType());
		}	
		
		for(PotionEffect efeito : player2.getActivePotionEffects()){
			player2.removePotionEffect(efeito.getType());
		}	
		
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.getName() != player1.getName()){
				p.showPlayer(player1);
			}
			if(p.getName() != player2.getName()){
				p.showPlayer(player2);
			}
		}
		
		player1.getInventory().clear();
		player2.getInventory().clear();
		
		player1.getInventory().setArmorContents(null);
		player2.getInventory().setArmorContents(null);
		
		player1.teleport(arena.getSaida());
		player2.teleport(arena.getSaida());
		Arrays.em1v1.remove(player1);
		Arrays.em1v1.remove(player2);
		
	}
	
	public Player getPlayer1(){
		return player1;
	}
	
	public Player getPlayer2(){
		return player2;
	}
	
	public Box getArena(){
		return arena;
	}
	
	public Type getType(){
		return type;
	}
	
	public enum Type{
		Normal, Buffed, Refil, Custom;
	}
	
}
