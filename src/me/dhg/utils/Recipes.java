package me.dhg.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipes {
		
	public static ShapelessRecipe camel;
	
	@SuppressWarnings("deprecation")
	public static void soupRecipes(){
		ItemStack cacausopa = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta cacaumeta = cacausopa.getItemMeta();
		cacaumeta.setDisplayName("Sopa de cacau");
		cacausopa.setItemMeta(cacaumeta);
		
		ItemStack florsopa = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta flormeta = florsopa.getItemMeta();
		flormeta.setDisplayName("Sopa de flor");
		florsopa.setItemMeta(flormeta);
		
		ItemStack aboborasopa = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta aboborameta = aboborasopa.getItemMeta();
		aboborameta.setDisplayName("Sopa de abobora");
		aboborasopa.setItemMeta(aboborameta);
		
		ItemStack cactosopa = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta cactometa = cactosopa.getItemMeta();
		cactometa.setDisplayName("Sopa de cacto");
		cactosopa.setItemMeta(cactometa);
		
		ShapelessRecipe cacau = new ShapelessRecipe(cacausopa);
		cacau.addIngredient(1, Material.INK_SACK, (byte)3);
		cacau.addIngredient(1, Material.BOWL);
		
		ShapelessRecipe flor = new ShapelessRecipe(florsopa);
		flor.addIngredient(1, Material.RED_ROSE);
		flor.addIngredient(1, Material.YELLOW_FLOWER);
		flor.addIngredient(1, Material.BOWL);
		
		ShapelessRecipe abobora = new ShapelessRecipe(aboborasopa);
		abobora.addIngredient(1, Material.PUMPKIN_SEEDS);
		abobora.addIngredient(1, Material.PUMPKIN_SEEDS);
		abobora.addIngredient(1, Material.BOWL);
		
		ShapelessRecipe cacto = new ShapelessRecipe(cactosopa);
		cacto.addIngredient(1, Material.CACTUS);
		cacto.addIngredient(1, Material.CACTUS);
		cacto.addIngredient(1, Material.BOWL);
		
		camel = new ShapelessRecipe(cactosopa);
		camel.addIngredient(1, Material.CACTUS);
		camel.addIngredient(1, Material.SAND);
	
		Bukkit.getServer().addRecipe(cacau);
		Bukkit.getServer().addRecipe(flor);
		Bukkit.getServer().addRecipe(abobora);
		Bukkit.getServer().addRecipe(cacto);
	}
	
}
