package me.dhg.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Weather implements Listener{

	public void chuva(WeatherChangeEvent e){
		e.getWorld().setWeatherDuration(0);
		if(e.toWeatherState()){
			e.setCancelled(true);
		}
	}
	
}
