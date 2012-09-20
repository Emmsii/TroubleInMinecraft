package com.tammcd.troubleinminecraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

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
	@EventHandler
	public void onNameTag(PlayerReceiveNameTagEvent event) {
		if (event.getNamedPlayer().getName().equals("Guard_")) {
		event.setTag(ChatColor.BLUE + "Guard_");
		}
	}
	
	
	@EventHandler
	public void onNameTagGreen(PlayerReceiveNameTagEvent event) {
		Player player = event.getPlayer();
		if (event.getPlayer().getHealth() < 20 || event.getPlayer().getHealth() > 16) {
			event.setTag(ChatColor.GREEN + player.getName());
		}else if(event.getPlayer().getHealth() < 15 && event.getPlayer().getHealth() > 11){
			event.setTag(ChatColor.YELLOW + player.getName());
		}else if(event.getPlayer().getHealth() < 10 && event.getPlayer().getHealth() > 6){
			event.setTag(ChatColor.GOLD + player.getName());
		}else if(event.getPlayer().getHealth() < 5 && event.getPlayer().getHealth() > 0){
			event.setTag(ChatColor.RED + player.getName());
		}
	}
	/*
	@EventHandler
	public void onNameTagYellow(PlayerReceiveNameTagEvent event) {
		Player player = event.getPlayer();
		if (event.getPlayer().getHealth() < 15 || event.getPlayer().getHealth() > 11) {
			event.setTag(ChatColor.YELLOW + player.getName());
		}
	}
	@EventHandler
	public void onNameTagGold(PlayerReceiveNameTagEvent event) {
		Player player = event.getPlayer();
		if (event.getPlayer().getHealth() < 10 || event.getPlayer().getHealth() > 6) {
			event.setTag(ChatColor.GOLD + player.getName());
		}
	}
	@EventHandler
	public void onNameTagRed(PlayerReceiveNameTagEvent event) {
		Player player = event.getPlayer();
		if (event.getPlayer().getHealth() < 5 || event.getPlayer().getHealth() > 0) {
			event.setTag(ChatColor.RED + player.getName());
		}
	}
	*/
}
