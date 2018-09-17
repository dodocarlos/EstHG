package me.dhg.principal;

import java.io.File;

import me.dhg.comandos.AddGateBlock;
import me.dhg.comandos.Admin;
import me.dhg.comandos.Arena;
import me.dhg.comandos.Ban;
import me.dhg.comandos.Bc;
import me.dhg.comandos.Chat;
import me.dhg.comandos.Check;
import me.dhg.comandos.ClearDrops;
import me.dhg.comandos.Dano;
import me.dhg.comandos.Desistir;
import me.dhg.comandos.Fake;
import me.dhg.comandos.Feast;
import me.dhg.comandos.Ffeast;
import me.dhg.comandos.IP;
import me.dhg.comandos.Info;
import me.dhg.comandos.Iniciar;
import me.dhg.comandos.Inv;
import me.dhg.comandos.Kit;
import me.dhg.comandos.RemoveKits;
import me.dhg.comandos.Report;
import me.dhg.comandos.SKit;
import me.dhg.comandos.SetTime;
import me.dhg.comandos.Spawn;
import me.dhg.comandos.Specs;
import me.dhg.comandos.Tag;
import me.dhg.comandos.Teleport;
import me.dhg.comandos.Time;
import me.dhg.comandos.Tpall;
import me.dhg.comandos.Unban;
import me.dhg.comandos.Ver;
import me.dhg.comandos.Youtuber;
import me.dhg.comandos.fmfeast;
import me.dhg.configs.Config;
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
import me.dhg.kits.ForceField;
import me.dhg.kits.Gladiator;
import me.dhg.kits.Grappler;
import me.dhg.kits.Hulk;
import me.dhg.kits.Jackhammer;
import me.dhg.kits.Kangaroo;
import me.dhg.kits.Lumberjack;
import me.dhg.kits.Ninja;
import me.dhg.kits.Stomper;
import me.dhg.kits.Thor;
import me.dhg.kits.Turtle;
import me.dhg.kits.Viper;
import me.dhg.kits.Worm;
import me.dhg.listeners.AdminListener;
import me.dhg.listeners.AsyncPlayerChat;
import me.dhg.listeners.Break;
import me.dhg.listeners.CancelKitItems;
import me.dhg.listeners.Damage;
import me.dhg.listeners.Death;
import me.dhg.listeners.Fome;
import me.dhg.listeners.Interact;
import me.dhg.listeners.InteractHotBar;
import me.dhg.listeners.Inventory;
import me.dhg.listeners.Items;
import me.dhg.listeners.Join;
import me.dhg.listeners.Listener1v1;
import me.dhg.listeners.Login;
import me.dhg.listeners.Mobs;
import me.dhg.listeners.Move;
import me.dhg.listeners.Nerf;
import me.dhg.listeners.Ping;
import me.dhg.listeners.Place;
import me.dhg.listeners.Quit;
import me.dhg.listeners.Respawn;
import me.dhg.listeners.SendCommand;
import me.dhg.listeners.SpectatorListener;
import me.dhg.listeners.TomarSopa;
import me.dhg.listeners.Weather;
import me.dhg.utils.AutoMessageThread;
import me.dhg.utils.Box;
import me.dhg.utils.DB;
import me.dhg.utils.Metodos;
import me.dhg.utils.Recipes;
import me.dhg.utils.Scroller;
import me.dhg.utils.Variaveis;
import me.dhg.utils.WorldBorder;
import net.minecraft.server.v1_7_R4.DamageSource;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
		
	Material[] material = {Material.GLASS, Material.MELON_BLOCK};
	
	public static WorldBorder borda;
		
	public static DB stats;
	
	public static Scroller scroller;
	
	public static Box arena1v1;
	
	public static Config blocksConfig = new Config(new File("./plugins/dHG/BlocksConfig.yml"));
	
	@Override
	public void onLoad() {		
		Bukkit.getServer().unloadWorld("world", false);
		Metodos.deleteDir(new File("world"));		
	}
	
	public void onEnable(){
		Variaveis.setupVariaveis(this);
		
		File objects = new File("./plugins/dHG/Objects");
		if(!objects.exists()){
			saveResource("Objects/Coliseu.schematic", true);
			saveResource("Objects/Feast.schematic", true);
			saveResource("Objects/MiniFeast.schematic", true);
		}
		
//		scroller = new Scroller("§3Est§cHG", 9, 4, '§');

		stats = new DB();
		
		//Configs
		config();
				
		for(World w : Bukkit.getWorlds()){
			w.setDifficulty(Difficulty.PEACEFUL);
			w.setSpawnLocation(15, 183, 15);
//			w.setSpawnLocation(0, Bukkit.getWorld("world").getHighestBlockYAt(0, 0) + 78, 0);					
		}	
		
		//Borda
		borda = new WorldBorder(material, Bukkit.getWorld("world"), -500, 500, 250, 0, -500, 500);
		borda.createWorldBorder();
		
		//Recipes
		Recipes.soupRecipes();
		
		//Registro de eventos gerais
		getServer().getPluginManager().registerEvents(new Join(this), this);
		getServer().getPluginManager().registerEvents(new Quit(), this);
		getServer().getPluginManager().registerEvents(new Break(), this);
		getServer().getPluginManager().registerEvents(new Place(), this);
		getServer().getPluginManager().registerEvents(new Damage(), this);
		getServer().getPluginManager().registerEvents(new Death(), this);
		getServer().getPluginManager().registerEvents(new Interact(), this);
		getServer().getPluginManager().registerEvents(new Fome(), this);
		getServer().getPluginManager().registerEvents(new Items(), this);
		getServer().getPluginManager().registerEvents(new Ping(), this);
		getServer().getPluginManager().registerEvents(new TomarSopa(), this);
		getServer().getPluginManager().registerEvents(new Kit(), this);
		getServer().getPluginManager().registerEvents(new Login(), this);
		getServer().getPluginManager().registerEvents(new Move(), this);
		getServer().getPluginManager().registerEvents(new InteractHotBar(), this);
		getServer().getPluginManager().registerEvents(new Listener1v1(), this);
		getServer().getPluginManager().registerEvents(new SendCommand(), this);
		getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
		getServer().getPluginManager().registerEvents(new Inventory(), this);		
		getServer().getPluginManager().registerEvents(new AdminListener(), this);	
		getServer().getPluginManager().registerEvents(new Respawn(), this);	
		getServer().getPluginManager().registerEvents(new CancelKitItems(), this);
		getServer().getPluginManager().registerEvents(new Mobs(), this);
		getServer().getPluginManager().registerEvents(new Weather(), this);
		getServer().getPluginManager().registerEvents(new SpectatorListener(), this);
		getServer().getPluginManager().registerEvents(new Nerf(), this);
		
		//Registro de eventos de kits
		getServer().getPluginManager().registerEvents(new Stomper(), this);
		getServer().getPluginManager().registerEvents(new Viper(), this);
		getServer().getPluginManager().registerEvents(new Lumberjack(), this);
		getServer().getPluginManager().registerEvents(new Gladiator(), this);
		getServer().getPluginManager().registerEvents(new Berserker(), this);
		getServer().getPluginManager().registerEvents(new Thor(), this);
		getServer().getPluginManager().registerEvents(new Worm(), this);
		getServer().getPluginManager().registerEvents(new Anchor(), this);
		getServer().getPluginManager().registerEvents(new Archer(), this);
		getServer().getPluginManager().registerEvents(new Cannibal(), this);
		getServer().getPluginManager().registerEvents(new Beatmaster(), this);
		getServer().getPluginManager().registerEvents(new Boxer(), this);
		getServer().getPluginManager().registerEvents(new Camel(), this);
		getServer().getPluginManager().registerEvents(new Change(), this);
		getServer().getPluginManager().registerEvents(new CheckPoint(), this);
		getServer().getPluginManager().registerEvents(new CookieMonster(), this);
		getServer().getPluginManager().registerEvents(new Cultivator(), this);
		getServer().getPluginManager().registerEvents(new Demoman(), this);
		getServer().getPluginManager().registerEvents(new Digger(), this);
		getServer().getPluginManager().registerEvents(new Fireman(), this);
		getServer().getPluginManager().registerEvents(new Fisherman(), this);
		getServer().getPluginManager().registerEvents(new Endermage(), this);
		getServer().getPluginManager().registerEvents(new ForceField(), this);
		getServer().getPluginManager().registerEvents(new C4(), this);
		getServer().getPluginManager().registerEvents(new Turtle(), this);
		getServer().getPluginManager().registerEvents(new Ninja(), this);
		getServer().getPluginManager().registerEvents(new Kangaroo(), this);
		getServer().getPluginManager().registerEvents(new Grappler(), this);
		getServer().getPluginManager().registerEvents(new Hulk(), this);
		getServer().getPluginManager().registerEvents(new Jackhammer(), this);
		
		getCommand("iniciar").setExecutor(new Iniciar());
		getCommand("admin").setExecutor(new Admin());
		getCommand("tag").setExecutor(new Tag());
		getCommand("ffeast").setExecutor(new Ffeast());
		getCommand("feast").setExecutor(new Feast());
		getCommand("fmfeast").setExecutor(new fmfeast());
		getCommand("kit").setExecutor(new Kit());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("settime").setExecutor(new SetTime());
		getCommand("skit").setExecutor(new SKit());
		getCommand("youtuber").setExecutor(new Youtuber());
		getCommand("info").setExecutor(new Info());
		getCommand("check").setExecutor(new Check());
		getCommand("teleport").setExecutor(new Teleport());
		getCommand("cleardrops").setExecutor(new ClearDrops());
		getCommand("dano").setExecutor(new Dano());
		getCommand("arena").setExecutor(new Arena());
		getCommand("ver").setExecutor(new Ver());
		getCommand("about").setExecutor(new Ver());
		getCommand("chat").setExecutor(new Chat());
		getCommand("bc").setExecutor(new Bc());
		getCommand("inv").setExecutor(new Inv());
		getCommand("specs").setExecutor(new Specs());
		getCommand("desistir").setExecutor(new Desistir());
		getCommand("time").setExecutor(new Time());
		getCommand("tpall").setExecutor(new Tpall());
		getCommand("fake").setExecutor(new Fake());
		getCommand("removerkits").setExecutor(new RemoveKits());
		getCommand("ban").setExecutor(new Ban());		
		getCommand("unban").setExecutor(new Unban());
		getCommand("ip").setExecutor(new IP());
		getCommand("report").setExecutor(new Report());
		getCommand("addgateblocks").setExecutor(new AddGateBlock());
		
		getLogger().info("dHg2 foi habilitado!");
		Metodos metodos = new Metodos(this);
		metodos.iniciar();
		Metodos.setKitMaterials();		
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run(){				
				for(String name : ForceField.ffAtivo){
					Player p = Bukkit.getPlayer(name);
					for(Entity en : p.getNearbyEntities(4, 4, 4)){
						((CraftEntity)en).getHandle().damageEntity(DamageSource.GENERIC, 3f);
					}
				}
				
			}
		}, 0, 10l);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new AutoMessageThread(), 0l, 2400l);
		
		Metodos.spawnObject(new Location(Bukkit.getWorld("world"), 0, 180, 0), "Coliseu");		
			
		System.gc();
		Bukkit.getConsoleSender().sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Memoria limpa. Restante: " + Runtime.getRuntime().freeMemory() / 1000 + "MB de " + Runtime.getRuntime().maxMemory() / 1000 + "MB");
		Bukkit.getConsoleSender().sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Excluindo mundos !");
		
	}
	
	public void onDisable() {
		getLogger().info("dHg2 foi desabilitado!");
	}
	
	public void config(){
		File configf = new File(getDataFolder(), "config.yml");
		if(!configf.exists()){
				saveDefaultConfig();		
		}	
	}
	
	
	
}
