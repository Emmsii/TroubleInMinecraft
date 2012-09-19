package com.tammcd.troubleinminecraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener{

	public static Main plugin;
	
	public JoinListener(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if(player.isOp() && Main.update){
			player.sendMessage(ChatColor.RED + "An update for Trouble in Minecraft is abvailable: "+ Main.name + " (" + Main.size + " bytes)");
			player.sendMessage(ChatColor.RED + "Type /update if you would like to update.");
		}
	}
}
