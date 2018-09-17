package me.dhg.utils;

import java.util.HashMap;

import me.dhg.principal.Main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class Variaveis {
	
	public static int MinimumPlayers;
	
	public static int tempoInicio;
	public static int tempoPartida = 0;
	public static int tempoInvencibilidade;
	
	public static boolean iniciou = false;
	public static boolean invencibilidade = false;
	
	public static Location feast = null;
	public static Location miniFeast = null;
	
	public static String NomeDoServidor = "";
	public static String LinkDoForm = "";
	
	public static String motdNaoIniciou = "";
	public static String motdIniciou = "";
	
	public static String cor = "6";
	
	public static String SemPerm = Variaveis.tag + Variaveis.GeralColor + "Desculpe, mas voce nao possui permissao para fazer isso";
	public static String tag;
	public static String GeralColor;
	public static String InfoColor;
	
	public static String server;
	public static String pass;
	public static String user;
	public static String db;
	
	public static String serverIP;
	
	public static String BarText = "IP: ";
	
	public static String scoreTitle;
	
	public static String siteLoja = "";
	
	public static BookMeta bookmeta;
	
	public static Main main;
	
	public static boolean useUUIDSQL = false;
	
	public static boolean chat = true;
	public static boolean dano = false;
	
	public static HashMap<ItemStack, Integer> itemsFeast = new HashMap<>();
	public static HashMap<ItemStack, Integer> itemsMiniFeast = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	public static void setupVariaveis(Main main){
		Variaveis.main = main;
		
		for(String name : main.getConfig().getStringList("FakeBlackList")){
			Arrays.blackListFake.add(name);
		}
		
		for(String item : main.getConfig().getStringList("feastItems")){
			String itemData[] = item.split(",");
			itemsFeast.put(new ItemStack(Material.getMaterial(Integer.valueOf(itemData[0]))), Integer.valueOf(itemData[1]));			
		}
		for(String item : main.getConfig().getStringList("miniFeastItems")){
			String itemData[] = item.split(",");
			itemsMiniFeast.put(new ItemStack(Material.getMaterial(Integer.valueOf(itemData[0]))), Integer.valueOf(itemData[1]));			
		}
		
		NomeDoServidor = main.getConfig().getString("NomeDoServidor").replaceAll("&", "§");
		LinkDoForm = main.getConfig().getString("LinkDoForm");
		
		tempoInicio = main.getConfig().getInt("tempoInicio");
		tempoInvencibilidade = main.getConfig().getInt("tempoInvencibilidade");
		MinimumPlayers = main.getConfig().getInt("MinimumPlayers");
		
		motdIniciou = main.getConfig().getString("motdIniciou").replaceAll("&", "§");
		motdNaoIniciou = main.getConfig().getString("motdNaoIniciou").replaceAll("&", "§");
		
		siteLoja = main.getConfig().getString("siteLoja");
		
		for(String line : main.getConfig().getStringList("bookText")){
			Arrays.lines.add(line.replaceAll("&", "§"));
		}
		
		server = main.getConfig().getString("MySql.host");
		pass = main.getConfig().getString("MySql.pass");
		user = main.getConfig().getString("MySql.user");
		db = main.getConfig().getString("MySql.database");
		
		BarText = main.getConfig().getString("BarText").replaceAll("&", "§");
		
		for(String msg : main.getConfig().getStringList("AutoMessages")){
			Arrays.autoMessages.add(msg.replaceAll("&", "§"));
		}
				
		useUUIDSQL = main.getConfig().getBoolean("useUUIDSQL");
		
		serverIP = main.getConfig().getString("ServerIP");
		
		for(String line : main.getConfig().getStringList("ScoreBoard")){
			Arrays.scoreLines.add(line.replaceAll("&", "§"));
		}
		
		tag = main.getConfig().getString("Tag").replaceAll("&", "§");
		GeralColor = main.getConfig().getString("GeralColor").replaceAll("&", "§");
		InfoColor = main.getConfig().getString("InfoColor").replaceAll("&", "§");
		
		scoreTitle = main.getConfig().getString("ScoreTitle").replaceAll("&", "§");
	}
	
}

