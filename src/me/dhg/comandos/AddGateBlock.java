package me.dhg.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

public class AddGateBlock implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.hasPermission("dHG.cmd.addgateblock")){
					if(!Arrays.addGateBlocks.contains(p.getName())){
						Arrays.addGateBlocks.add(p.getName());
						p.sendMessage(Metodos.ColoredMsg(Variaveis.tag + "&aVoce entrou no addgateblocks"));
					}else{
						Arrays.addGateBlocks.remove(p.getName());
						p.sendMessage(Metodos.ColoredMsg(Variaveis.tag + "&cVoce saiu do addgateblocks"));
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
