package me.dhg.utils;

import org.bukkit.Bukkit;

public class AutoMessageThread implements Runnable{
	int i = 0;
	@Override
	public void run() {
		if(i >= Arrays.autoMessages.size()){
			i = 0;
		}
		Bukkit.broadcastMessage(Arrays.autoMessages.get(i).replaceAll("%pularlinha%", "\n"));
		i ++;		
	}

}
