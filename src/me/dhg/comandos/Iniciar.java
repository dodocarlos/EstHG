package me.dhg.comandos;

import me.dhg.principal.Main;
import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Iniciar implements CommandExecutor  {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
			if(sender.hasPermission("dHG.cmd.iniciar")){
				if(Variaveis.iniciou == false){
					Variaveis.tempoInicio = 0;
					Variaveis.iniciou = true;
					Variaveis.invencibilidade = true;							
					for(Player pl : Bukkit.getOnlinePlayers()){
						pl.setAllowFlight(false);
						pl.setFlying(false);
						pl.getInventory().clear();
						pl.getInventory().setArmorContents(null);
						if(pl.getVehicle() != null){
							pl.getVehicle().remove();
						}
						pl.getInventory().addItem(new ItemStack(Material.COMPASS));
						Main.stats.addPartida(pl);
						Metodos.darKit(pl);
						pl.playSound(pl.getLocation(), Sound.ANVIL_LAND, 1f, 1f);
					}
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Torneio iniciado, boa sorte a todos");	
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Temos um total de " + Variaveis.InfoColor + Arrays.participando.size() + Variaveis.GeralColor + " jogadores");
					Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor +  "Invencibilidade acaba em " + Variaveis.InfoColor + Variaveis.tempoInvencibilidade);
					Metodos.abrirPortoes();
					
					
					
					for(World w : Bukkit.getWorlds()){
						w.setDifficulty(Difficulty.NORMAL);
					}
				}else{
					sender.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Torneio ja iniciado");
				}
			}else{
				sender.sendMessage(Variaveis.SemPerm);
			}
		return false;
	}
	
	
	
}
