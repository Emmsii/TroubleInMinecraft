package com.tammcd.troubleinminecraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener{

	public static Main plugin;
	
	public SignListener(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onSignCreateKill(SignChangeEvent sign){
		Player player = sign.getPlayer();
		if(sign.getLine(0).equalsIgnoreCase("Lead Kills")){
			player.sendMessage(ChatColor.GOLD + "The sign has been turned into a leaderboard for kills.");
			sign.setLine(0, ChatColor.UNDERLINE + "Top Kills:");
			sign.setLine(1, "Need config");
			}
		}
	
	@EventHandler
	public void onSignCreateDeath(SignChangeEvent sign){
		Player player = sign.getPlayer();
		if(sign.getLine(0).equalsIgnoreCase("Lead Deaths")){
			player.sendMessage(ChatColor.GOLD + "The sign has been turned into a leaderboard for deaths.");
			sign.setLine(0, ChatColor.UNDERLINE + "Top Deaths:");
			sign.setLine(1, "Get from data-");
			sign.setLine(2, "base?");
			}
		}
		
	@EventHandler
	public void onSignCreateArrest(SignChangeEvent sign){
		Player player = sign.getPlayer();
		if(sign.getLine(0).equalsIgnoreCase("Lead Arrests")){
			player.sendMessage(ChatColor.GOLD + "The sign has been turned into a leaderboard for arrests.");
			sign.setLine(0, ChatColor.UNDERLINE + "Top Arrests:");
			sign.setLine(1, "Haha! Poo");
			sign.setLine(2,ChatColor.LIGHT_PURPLE + "Look, colours!");
		}
	}
}
