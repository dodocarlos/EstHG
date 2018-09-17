package me.dhg.utils;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoard {
	
	public static Scoreboard sb;
	
	public static void updateScore(Player p){
	
			if(!Arrays.hasTime.contains(p)){
				SimpleScoreboard sb = new SimpleScoreboard(Variaveis.scoreTitle);
				
				for(String line : Arrays.scoreLines){
					if(Variaveis.iniciou == false){					
						if(Arrays.kills.get(p) == null){
							sb.add(line.replaceAll("%kit%", Arrays.kitPlayer.get(p)).replaceAll("%players%", Arrays.participando.size() + "").replaceAll("%tempo%", String.format("%02d:%02d:%02d", Metodos.horaInicio, Metodos.minutoInicio, Metodos.segundoInicio)).replaceAll("%kills%", "0"));
						}else{
							sb.add(line.replaceAll("%kit%", Arrays.kitPlayer.get(p)).replaceAll("%players%", Arrays.participando.size() + "").replaceAll("%tempo%", String.format("%02d:%02d:%02d", Metodos.horaInicio, Metodos.minutoInicio, Metodos.segundoInicio)).replaceAll("%kills%", Arrays.kills.get(p) + ""));
						}
					}else{
						if(Variaveis.invencibilidade){
							if(Arrays.kills.get(p) == null){
								sb.add(line.replaceAll("%kit%", Arrays.kitPlayer.get(p)).replaceAll("%players%", Arrays.participando.size() + "").replaceAll("%tempo%", String.format("%02d:%02d:%02d", Metodos.horaInvencibilidade, Metodos.minutoInvencibilidade, Metodos.segundoInvencibilidade)).replaceAll("%kills%", "0"));
							}else{
								sb.add(line.replaceAll("%kit%", Arrays.kitPlayer.get(p)).replaceAll("%players%", Arrays.participando.size() + "").replaceAll("%tempo%", String.format("%02d:%02d:%02d", Metodos.horaInvencibilidade, Metodos.minutoInvencibilidade, Metodos.segundoInvencibilidade)).replaceAll("%kills%", Arrays.kills.get(p) + ""));
							}
						}else{
							if(Arrays.kills.get(p) == null){
								sb.add(line.replaceAll("%kit%", Arrays.kitPlayer.get(p)).replaceAll("%players%", Arrays.participando.size() + "").replaceAll("%tempo%", String.format("%02d:%02d:%02d", Metodos.horaPartida, Metodos.minutoPartida, Metodos.segundoPartida)).replaceAll("%kills%", "0"));
							}else{
								sb.add(line.replaceAll("%kit%", Arrays.kitPlayer.get(p)).replaceAll("%players%", Arrays.participando.size() + "").replaceAll("%tempo%", String.format("%02d:%02d:%02d", Metodos.horaPartida, Metodos.minutoPartida, Metodos.segundoPartida)).replaceAll("%kills%", Arrays.kills.get(p) + ""));
							}
						}
					}
				}
				
				
				
				sb.build();
				sb.send(p);
				ScoreBoard.sb = sb.getScoreboard();
			}else{				
				//score do time
				Objective o = sb.registerNewObjective("Kill", "Kills");
				o.setDisplaySlot(DisplaySlot.SIDEBAR);
				
				Time time = Time.getTime(p);
				
				SimpleScoreboard sb2 = new SimpleScoreboard("§a" + time.getName());
				
				for(Player componente : time.getComponentes()){
					Damageable d = (Damageable) componente;
					sb2.add("§b" + componente.getName(), (int) (d.getHealth() / 2 ) * 10);
				}
				
				sb2.build();
				sb2.send(p);
				
				ScoreBoard.sb = sb2.getScoreboard();
			}
			
		}
}
	
