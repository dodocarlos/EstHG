package me.dhg.comandos;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dhg.utils.Arrays;
import me.dhg.utils.Variaveis;

public class Admin implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.hasPermission("dHG.cmd.admin")){
					if(!Arrays.admin.contains(p.getName())){
						Arrays.participando.remove(p.getName());
						Arrays.admin.add(p.getName());
						p.setCanPickupItems(false);
						p.setGameMode(GameMode.CREATIVE);
						p.setAllowFlight(true);
						p.setFlying(true);
						Arrays.adminInv.put(p, p.getInventory().getContents());
						Arrays.adminInvArmor.put(p, p.getInventory().getArmorContents());
						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						if(Arrays.espectador.contains(p)){
							Arrays.espectador.remove(p);
						}
						for(Player pl : Bukkit.getOnlinePlayers()){
							if(!pl.hasPermission("dHG.admin")){
								pl.hidePlayer(p);
							}
						}												
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Voce entrou no modo ADMIN");
					}else{
						Arrays.admin.remove(p.getName());
						p.setGameMode(GameMode.SURVIVAL);
						p.getInventory().clear();
						p.getInventory().setContents(Arrays.adminInv.get(p));
						p.getInventory().setArmorContents(Arrays.adminInvArmor.get(p));
						Arrays.adminInv.remove(p);
						Arrays.adminInvArmor.remove(p);
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Voce saiu do modo ADMIN");
						if(Arrays.addGateBlocks.contains(p.getName())){
							Arrays.addGateBlocks.remove(p.getName());
						}
						if(Arrays.espectador.contains(p)){
							Arrays.espectador.remove(p);
						}
						if(!Arrays.participando.contains(p.getName())){
							Arrays.participando.add(p.getName());
						}				
						p.setCanPickupItems(true);
						p.setFlying(false);
						p.setAllowFlight(false);						
						for(Player pl : Bukkit.getOnlinePlayers()){
							pl.showPlayer(p);
						}
					}
				}else{
					p.sendMessage(Variaveis.SemPerm);
				}
			}else{
				sender.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Apenas Jogadores");
			}
		return false;
	}

}
