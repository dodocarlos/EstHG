package me.dhg.configs;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	private File file;
	private FileConfiguration configFile;
	
	public Config(File file){
		this.file = file;
		this.configFile = YamlConfiguration.loadConfiguration(file); 
	}
	
	public void create() {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public FileConfiguration getConfig(){
		return configFile;
	}
	
	public void saveConfig(){
		try {
			configFile.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reloadConfig(){
		try{
			saveConfig();
			this.configFile = YamlConfiguration.loadConfiguration(file);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void saveLocation(String name, Location l){
		this.getConfig().set(name + ".world", l.getWorld().getName());
		this.getConfig().set(name + ".x", l.getX());
		this.getConfig().set(name + ".y", l.getY());
		this.getConfig().set(name + ".z", l.getZ());
		this.getConfig().set(name + ".yaw", l.getYaw());
		this.getConfig().set(name + ".pitch", l.getPitch());
		this.saveConfig();
		
	}
	
	public Location loadLocation(String name){
		World w = Bukkit.getWorld(this.getConfig().getString(name + ".world"));
		Double x = this.getConfig().getDouble(name + ".x");
		Double y = this.getConfig().getDouble(name + ".y");
		Double z = this.getConfig().getDouble(name + ".z");
		Float yaw = (float) this.getConfig().getDouble(name + ".yaw");
		Float pitch = (float) this.getConfig().getDouble(name + ".pitch");
		
		return new Location(w, x, y, z, yaw, pitch);
	}
	
}
