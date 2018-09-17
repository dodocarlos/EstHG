package me.dhg.utils;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Time {

	private String name;
	private ArrayList<Player> jogadores = new ArrayList<Player>();
	private ArrayList<Player> banidos = new ArrayList<Player>();
	private Player owner;
	
	public Time(String name, Player owner){
		this.name = name;
		this.owner = owner;
	}
	
	public void addPlayer(Player p){
		jogadores.add(p);
	}
	
	public void removePlayer(Player p){
		jogadores.remove(p);
	}
	
	public void ban(Player p){
		removePlayer(p);
		banidos.add(p);		
		p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce foi banido do time " + Variaveis.InfoColor + name);
	}
	
	public void unban(Player p){
		banidos.remove(p);		
		p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce foi desbanido do time " + Variaveis.InfoColor + name);
	}
	
	public void kick(Player p){
		removePlayer(p);
		p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Voce foi kickado do time " + Variaveis.InfoColor + name);
	}
	
	public void remove(){
		Arrays.times.remove(name);
	}
	
	public boolean isBanned(Player p){
		return banidos.contains(p);
	}
	
	public boolean isOwner(Player p){
		return p.getName() == owner.getName();
	}
	
	public ArrayList<Player> getComponentes(){
		return jogadores;
	}
	
	public Player getOwner(){
		return owner;
	}
	
	public String getName(){
		return name;
	}
	
	public static Time getTime(Player p){
		for(Time time : Arrays.times.values()){
			if(time.getComponentes().contains(p)){
				return time;
			}
		}
		return null;
	}
}
