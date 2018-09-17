package me.dhg.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;

import net.minecraft.util.org.apache.commons.io.FileUtils;

public class AutoUpdater {

	public static void check(String localDaPasta){
		
		File f = new File(localDaPasta);
		File f2 = new File("./plugins");
		
		try {
			if(f.lastModified() > f2.lastModified() + 10){
				FileUtils.copyDirectoryToDirectory(f, new File("./"));
				System.out.println("Plugin atualizados, reiniciando !");
				Bukkit.shutdown();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
