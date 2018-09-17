package me.dhg.comandos;

import me.dhg.utils.Variaveis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Ver implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
				sender.sendMessage("§aPlugin criado por §fdodocarlos §apara o servidor §f" + Variaveis.NomeDoServidor);
				sender.sendMessage("§aSkype para contato: §fdodocarlos.pessoal");
				
		return false;
	}

}
