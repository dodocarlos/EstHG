package me.dhg.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Arrays {
	
	public static ArrayList<String> participando = new ArrayList<>();
	public static ArrayList<String> admin = new ArrayList<String>();
	public static ArrayList<Player> foradaborda = new ArrayList<Player>();	
	public static ArrayList<Player> retirarCavalo = new ArrayList<Player>();
	public static ArrayList<Player> em1v1 = new ArrayList<Player>();
	public static ArrayList<Player> espectador = new ArrayList<Player>();
	public static ArrayList<Player> semEspectadores = new ArrayList<Player>();
	public static ArrayList<String> addGateBlocks = new ArrayList<String>();
	
	public static ArrayList<String> blackListFake = new ArrayList<>();
	
	public static HashMap<Player, Fake> fakes= new HashMap<Player, Fake>();
	
	public static HashMap<String, Time> times = new HashMap<String, Time>();
	public static ArrayList<Player> hasTime = new ArrayList<Player>();
	
	public static HashMap<Player, String> kitPlayer = new HashMap<Player, String>();
	public static HashMap<Player, Integer> kills = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> deaths = new HashMap<Player, Integer>();
	
	public static HashMap<Player, ItemStack[]> adminInv = new HashMap<Player, ItemStack[]>();
	public static HashMap<Player, ItemStack[]> adminInvArmor = new HashMap<Player, ItemStack[]>();
	
	public static HashMap<String, ItemStack[]> skititem = new HashMap<String, ItemStack[]>();
	public static HashMap<String, ItemStack[]> skitarmor = new HashMap<String, ItemStack[]>();
	
	public static HashMap<String, ItemStack> kitMaterial = new HashMap<String, ItemStack>();
	
	public static ArrayList<String> lines = new ArrayList<>();
	
	public static ArrayList<String> autoMessages = new ArrayList<>();
	
	public static ArrayList<String> scoreLines = new ArrayList<>();
}
