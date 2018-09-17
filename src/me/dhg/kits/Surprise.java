package me.dhg.kits;

import java.util.Random;

import me.dhg.comandos.Kit;
import me.dhg.utils.Arrays;
import me.dhg.utils.Metodos;
import me.dhg.utils.Variaveis;

import org.bukkit.entity.Player;

public class Surprise {

	public static void darSurprise(Player p){
		Random r = new Random();
		String kit = Kit.kits[r.nextInt(Kit.kits.length)];
		Arrays.kitPlayer.put(p, kit);
		p.sendMessage(Variaveis.tag + Variaveis.GeralColor + "O Surprise escolheu " + Variaveis.InfoColor + kit);
		Metodos.darKit(p);
	}
	
}
