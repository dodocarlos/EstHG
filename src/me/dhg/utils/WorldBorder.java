package me.dhg.utils;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WorldBorder {

	private int xesquerdo;
	private int xdireito;
	private int yalto;
	private int ybaixo;
	private int zesquerdo;
	private int zdireito;
	private World w;
	private Material[] material;
	private Random r = new Random();
	
	private HashMap<Location, Material> blocos = new HashMap<Location, Material> ();
	
	public WorldBorder(Material[] material, World w, int xesquerdo, int xdireito, int yalto, int ybaixo, int zesquerdo, int zdireito){
		
		this.xesquerdo = xesquerdo;
		this.xdireito = xdireito;
		this.yalto = yalto;
		this.ybaixo = ybaixo;
		this.zesquerdo = zesquerdo;
		this.zdireito = zdireito;
		this.w = w;
		this.material = material;
		
	}
	
	public void createWorldBorder(){		
		for(int x = xesquerdo; x <= xdireito; x++){
			if(x == xesquerdo || x == xdireito){
				for(int z = zesquerdo; z <= zdireito; z++){
					for(int y = ybaixo; y <= yalto; y ++){	
						if(!w.getChunkAt(w.getBlockAt(x, y, z)).isLoaded())	{
							w.getChunkAt(w.getBlockAt(x, y, z)).load();							
						}
						blocos.put(new Location(w, x, y, z), w.getBlockAt(x, y, z).getType());
						w.getBlockAt(x, y, z).setType(material[r.nextInt(material.length)]);	
					}
				}
			}
		}
		
		for(int z = xesquerdo; z <= xdireito; z++){
			if(z == xesquerdo || z == xdireito){
				for(int x = zesquerdo; x <= zdireito; x++){
					for(int y = ybaixo; y <= yalto; y ++){	
						if(!w.getChunkAt(w.getBlockAt(x, y, z)).isLoaded())	{
							w.getChunkAt(w.getBlockAt(x, y, z)).load();							
						}
						blocos.put(new Location(w, x, y, z), w.getBlockAt(x, y, z).getType());
						w.getBlockAt(x, y, z).setType(material[r.nextInt(material.length)]);	
					}
				}
			}
		}
		System.out.println("Borda Criada !");
	}
	
	public void deleteWorldBorder(){
		
		for(Location loc : blocos.keySet()){
			w.getBlockAt(loc).setType(blocos.get(loc));
		}
		
	}
	
	public boolean isWorldBorderBlock(Block b){
		boolean is = false;
		if(blocos.containsKey(b.getLocation())){
			is = true;
		}
		return is;
	}
	
	public boolean isOutOfBorder(Player p){
		boolean is = false;		
			if(p.getLocation().getX() < xesquerdo || p.getLocation().getX() > xdireito || p.getLocation().getY() > yalto || p.getLocation().getZ() < zesquerdo || p.getLocation().getZ() > zdireito){
				is = true;
			}		
		return is;
	}
	
}
