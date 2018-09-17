package me.dhg.utils;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutNamedEntitySpawn;
import net.minecraft.util.com.mojang.authlib.GameProfile;

public class Fake {
	
	private Player p;
	private String fakeName;
	private String originalPlayerName;
	
	@SuppressWarnings("deprecation")
	public Fake(Player p, String originalPlayerName, String fakeName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		this.p = p;
		this.originalPlayerName = originalPlayerName;
		this.fakeName = fakeName;
		
		PacketPlayOutEntityDestroy destoy = new PacketPlayOutEntityDestroy(p.getEntityId());
		PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(((CraftPlayer)p).getHandle());
		
		Field b = spawn.getClass().getDeclaredField("b");
		b.setAccessible(true);
		b.set(spawn, new GameProfile(p.getUniqueId(), fakeName));
		
		p.setCustomName("§f" + fakeName);
		p.setDisplayName("§f" + fakeName);
		if(fakeName.length() > 16){
			p.setDisplayName("§f" + fakeName.substring(0, 16));
		}else{
			p.setPlayerListName("§f" +fakeName);
		}
		
		for(Player pl : Bukkit.getOnlinePlayers()){
			if(pl != p){
				((CraftPlayer)pl).getHandle().playerConnection.sendPacket(destoy);
				((CraftPlayer)pl).getHandle().playerConnection.sendPacket(spawn);
			}
		}
		
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public String getFakeName(){
		return fakeName;
	}
		
	public String getOriginalPlayerName(){
		return originalPlayerName;
	}
	
	@SuppressWarnings("deprecation")
	public void toOriginal() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		PacketPlayOutEntityDestroy destoy = new PacketPlayOutEntityDestroy(p.getEntityId());
		PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(((CraftPlayer)p).getHandle());
		
		Field b = spawn.getClass().getDeclaredField("b");
		b.setAccessible(true);
		b.set(spawn, new GameProfile(p.getUniqueId(), originalPlayerName));
		
		p.setCustomName("§f" + originalPlayerName);
		p.setDisplayName("§f" + originalPlayerName);
		p.setPlayerListName("§f" + originalPlayerName);
		
		for(Player pl : Bukkit.getOnlinePlayers()){
			if(pl != p){
				((CraftPlayer)pl).getHandle().playerConnection.sendPacket(destoy);
				((CraftPlayer)pl).getHandle().playerConnection.sendPacket(spawn);
			}
		}
	}
	
	public static void fakeToPlayer(Player p, Fake fake) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(fake.getPlayer().getEntityId());
		PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(((CraftPlayer)fake.getPlayer()).getHandle());
		
		Field b = spawn.getClass().getDeclaredField("b");
		b.setAccessible(true);
		b.set(spawn, new GameProfile(p.getUniqueId(), fake.getFakeName()));
		
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(destroy);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(spawn);
	}
	
}
