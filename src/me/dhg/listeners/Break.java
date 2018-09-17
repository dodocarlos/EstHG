package me.dhg.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.dhg.kits.Gladiator;
import me.dhg.principal.Main;
import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

public class Break implements Listener{
	
	@EventHandler
	public void quebrar(BlockBreakEvent e){
		
		if(Arrays.addGateBlocks.contains(e.getPlayer().getName())){
			int id = 0;
			try{
				for(int i = 0; i < Main.blocksConfig.getConfig().getConfigurationSection("GateBlock").getKeys(false).size(); i ++){
					id ++;
			}
			}catch(Exception ex){
				id = 0;
			}
			Main.blocksConfig.saveLocation("GateBlock." + id, e.getBlock().getLocation());
			e.setCancelled(true);
			e.getPlayer().sendMessage(Metodos.ColoredMsg(Variaveis.tag + Variaveis.GeralColor + "Voce adicionou este bloco na lista de gates! ID: " + id));
			return;			
		}
		
		if(Arrays.admin.contains(e.getPlayer().getName())){
			return;
		}
		if(Variaveis.iniciou == false && !Arrays.admin.contains(e.getPlayer().getName())){
			e.setCancelled(true);
			return;
		}
		if(!Arrays.admin.contains(e.getPlayer().getName()) && Main.borda.isWorldBorderBlock(e.getBlock())){
			e.setCancelled(true);
			e.getPlayer().sendMessage(Variaveis.tag + Variaveis.GeralColor + "Impossivel quebrar a borda !");
			return;
		}	
		if(Gladiator.boxlist.get(e.getPlayer()) != null){
			if(!Arrays.admin.contains(e.getPlayer()) && Gladiator.boxlist.get(e.getPlayer()).isBoxBlock(e.getBlock())){
				e.setCancelled(true);
				return;
			}
		}
		if(Arrays.espectador.contains(e.getPlayer()) && !Arrays.admin.contains(e.getPlayer().getName())){
			e.setCancelled(true);
			return;
		}
	}
	
}
