package me.dhg.listeners;

import me.dhg.principal.Main;
import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;

public class Death implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void morrer(PlayerDeathEvent e){	
		
		for(ItemStack i : e.getDrops()){
			if(i.getType() == Material.REDSTONE_TORCH_OFF || i.getType() == Material.REDSTONE_TORCH_ON && Arrays.kitPlayer.get(e.getEntity()).equalsIgnoreCase("Flash")){
				i.setType(Material.AIR);
			}
			if(i.getType() == Material.IRON_FENCE && Arrays.kitPlayer.get(e.getEntity()).equalsIgnoreCase("Gladiator")){
				i.setType(Material.AIR);
			}	
			if(i.getType() == Material.SIGN || i.getType() == Material.WOOD_BUTTON && Arrays.kitPlayer.get(e.getEntity()).equalsIgnoreCase("Checkpoint")){
				i.setType(Material.AIR);
			}	
			if(i.getType() == Material.SLIME_BALL && Arrays.kitPlayer.get(e.getEntity()).equalsIgnoreCase("C4")){
				i.setType(Material.AIR);
			}
			if(i.getType() == Material.MAGMA_CREAM && Arrays.kitPlayer.get(e.getEntity()).equalsIgnoreCase("ForceField")){
				i.setType(Material.AIR);
			}
			if(i.getType() == Material.FIREWORK && Arrays.kitPlayer.get(e.getEntity()).equalsIgnoreCase("Kangaroo")){
				i.setType(Material.AIR);
			}
			if(i.getType() == Material.LEASH && Arrays.kitPlayer.get(e.getEntity()).equalsIgnoreCase("Grappler")){
				i.setType(Material.AIR);
			}
		}
		
		if(Variaveis.iniciou == false){
			e.setDroppedExp(0);
			e.getDrops().clear();
			for(Player p : Bukkit.getOnlinePlayers()){
				if(!Arrays.admin.contains(p.getName())){
					e.getEntity().showPlayer(p);
					e.getEntity().getKiller().showPlayer(p);
				}
			}
		}
		
		if(Arrays.em1v1.contains(e.getEntity())){
			Arrays.em1v1.remove(e.getEntity());
		}		
		if(Arrays.em1v1.contains(e.getEntity().getKiller())){
			Arrays.em1v1.remove(e.getEntity().getKiller());
			e.getEntity().getKiller().getInventory().clear();
			e.getEntity().getKiller().getInventory().setArmorContents(null);
			e.getEntity().getKiller().teleport(e.getEntity().getKiller().getWorld().getSpawnLocation());
			Metodos.darItensIniciais(e.getEntity().getKiller());
		}
		
		e.setDeathMessage(null);
		Player morreu = e.getEntity().getPlayer();	
		
		if(Variaveis.iniciou){
			if(Arrays.deaths.get(morreu) != null){
				Arrays.deaths.put(morreu, Arrays.deaths.get(morreu) + 1);
			}else{
				Arrays.deaths.put(morreu, 1);
			}
			
			int mortes;
			if(Arrays.deaths.get(morreu) != null){
				mortes = Arrays.deaths.get(morreu);
			}else{
				mortes = 0;
			}
			
			for(PotionEffect efeito : morreu.getActivePotionEffects()){
				morreu.removePotionEffect(efeito.getType());
			}
			
			
			
			
			if(e.getEntity().getKiller() instanceof Player){
				if(Listener1v1.list1v1.get(morreu) != null){
					Listener1v1.list1v1.get(morreu).finalizar();
				}
				Player matou = e.getEntity().getKiller();
				
		
				
				Main.stats.addDeath(morreu);
				Main.stats.addKill(matou);
				
				if(Variaveis.iniciou){
					if(Arrays.kills.get(matou) != null){
						Arrays.kills.put(matou, Arrays.kills.get(matou) + 1);
					}else{
						Arrays.kills.put(matou, 1);
					}
				}

				if(morreu.hasPermission("dHG.respawn") && Variaveis.tempoPartida <= 300 && mortes < 3){				
					Metodos.respawn(morreu);
					Metodos.darKit(morreu);
				}else{
					if(morreu.getKiller() instanceof Player){
						if(matou.getItemInHand().getType() != Material.AIR){
							Bukkit.broadcastMessage(Variaveis.GeralColor + "O Jogador " + Variaveis.InfoColor + "" + (Arrays.fakes.containsKey(morreu)? Arrays.fakes.get(morreu).getFakeName() : morreu.getName()) + "(" + Arrays.kitPlayer.get(morreu) + ")" + Variaveis.GeralColor + " foi morto por " + Variaveis.InfoColor + "" + matou.getName() + "(" + Arrays.kitPlayer.get(matou) + ")" + Variaveis.GeralColor + " usando " + Variaveis.InfoColor + "" + matou.getItemInHand().getType());
							morreu.playSound(morreu.getLocation(), Sound.FUSE, 1, 1);
							Arrays.participando.remove(morreu.getName());
							Bukkit.broadcastMessage("" + Variaveis.InfoColor + "" + Arrays.participando.size() + Variaveis.GeralColor + " jogadores restantes");
							Arrays.espectador.add(morreu);
							if(morreu.hasPermission("dHG.espectador")){
								Metodos.setEspectador(morreu);
							}else{
								morreu.kickPlayer("§4Voce foi morto por " + Variaveis.InfoColor + "" + matou.getName() + "(" + Arrays.kitPlayer.get(matou) + ")" + " §4usando " + Variaveis.InfoColor + "" + matou.getItemInHand().getType());
							}
						}else{
							Bukkit.broadcastMessage(Variaveis.GeralColor + "O Jogador " + Variaveis.InfoColor + "" + (Arrays.fakes.containsKey(morreu)? Arrays.fakes.get(morreu).getFakeName() : morreu.getName()) + "(" + Arrays.kitPlayer.get(morreu) + ")" + Variaveis.GeralColor + " foi morto por " + Variaveis.InfoColor + "" + matou.getName() + "(" + Arrays.kitPlayer.get(matou) + ")" + Variaveis.GeralColor + " usando " + Variaveis.InfoColor + "seus punhos");
							morreu.playSound(morreu.getLocation(), Sound.FUSE, 1, 1);
							Arrays.participando.remove(morreu.getName());
							Bukkit.broadcastMessage("" + Variaveis.InfoColor + "" + Arrays.participando.size() + Variaveis.GeralColor + " jogadores restantes");
							Arrays.espectador.add(morreu);
							if(morreu.hasPermission("dHG.espectador")){
								Metodos.setEspectador(morreu);
							}else{
								morreu.kickPlayer("§4Voce foi morto por " + Variaveis.InfoColor + "" + matou.getName() + "(" + Arrays.kitPlayer.get(matou) + ")" + " §4usando " + Variaveis.InfoColor + "seus punhos");
							}
						}
					}else{
						//Damages
						if(morreu.getLastDamageCause().getCause() == DamageCause.FALL){
							Bukkit.broadcastMessage(Variaveis.GeralColor + "O Jogador " + Variaveis.InfoColor + "" + (Arrays.fakes.containsKey(morreu)? Arrays.fakes.get(morreu).getFakeName() : morreu.getName()) + "(" + Arrays.kitPlayer.get(morreu) + ")" + Variaveis.GeralColor + " esqueceu como se voa :(");
						}
						if(morreu.getLastDamageCause().getCause() == DamageCause.FIRE){
							Bukkit.broadcastMessage(Variaveis.GeralColor + "O Jogador " + Variaveis.InfoColor + "" + (Arrays.fakes.containsKey(morreu)? Arrays.fakes.get(morreu).getFakeName() : morreu.getName()) + "(" + Arrays.kitPlayer.get(morreu) + ")" + Variaveis.GeralColor + " morreu queimado");
						}
						
					}
				}
			}else{			
				if(morreu.hasPermission("dHG.respawn") && Variaveis.tempoPartida <= 300 && mortes < 3){
					Metodos.respawn(morreu);	
					Metodos.darKit(morreu);
				}else{
					Bukkit.broadcastMessage(Variaveis.GeralColor + "O Jogador " + Variaveis.InfoColor + "" + (Arrays.fakes.containsKey(morreu)? Arrays.fakes.get(morreu).getFakeName() : morreu.getName()) + "(" + Arrays.kitPlayer.get(morreu) + ")" + Variaveis.GeralColor + " foi morto");
					Arrays.participando.remove(morreu.getName());					
					Bukkit.broadcastMessage("" + Variaveis.InfoColor + "" + Arrays.participando.size() + Variaveis.GeralColor + " jogadores restantes");
					if(morreu.hasPermission("dHG.espectador")){
						Metodos.setEspectador(morreu);
					}else{
						morreu.kickPlayer("§aVoce foi morto :P");
					}
				}
			}
		}
		
	}

}
