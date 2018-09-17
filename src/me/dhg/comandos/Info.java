package me.dhg.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

public class Info implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
				
				if(arg0 instanceof Player){
					Player p = (Player) arg0;
					if(!Arrays.admin.contains(p)){
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Infos:----------------------");
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Kit: " + Variaveis.InfoColor + Arrays.kitPlayer.get(p));
							if(Arrays.kills.get(p) == null){
								p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Kills: " + Variaveis.InfoColor + " 0");
							}else{
								p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Kills: " + Variaveis.InfoColor + Arrays.kills.get(p));
							}
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "Tempo de jogo: " + Variaveis.InfoColor + String.format("%02d:%02d:%02d", Metodos.horaPartida, Metodos.minutoPartida, Metodos.segundoPartida));
							p.sendMessage(Variaveis.tag + Variaveis.GeralColor +  "---------------------------");
						
				}
				}
		
		return false;
	}

}
