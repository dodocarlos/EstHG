package me.dhg.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

public class Desistir implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
				
				if(sender instanceof Player){
					Player p = (Player) sender;
					if(p.hasPermission("dHG.cmd.desistir")){
						if(!Arrays.espectador.contains(p)){
							Metodos.setEspectador(p);
							Bukkit.broadcastMessage(Variaveis.tag + Variaveis.GeralColor + "O jogador " + Variaveis.InfoColor + p.getName() + Variaveis.GeralColor + " desistiu da partida");
							
						}else{
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Voce ja e um espectador");
						}
					}else{
						p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "Desculpe, mas voce nao possui permissao para fazer isso");
					}
				}
		
		return false;
	}

}
