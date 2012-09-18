package com.tammcd.troubleinminecraft;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDeathEvent;

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
			sign.setLine(1, "Need config");//Need to add functionality to these lines.
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
	
	@EventHandler
	public void onSignCreateExample(SignChangeEvent sign){
		Player player = sign.getPlayer();
		if(sign.getLine(0).equalsIgnoreCase("Lead example")){
			player.sendMessage(ChatColor.GOLD + "The sign has been turned into an EXAMPLE leaderboard");
			sign.setLine(0, ChatColor.UNDERLINE + "Top Kills:");
			sign.setLine(1, "Tamfoolery" + ":" + "2");
		}
	}
	
	@SuppressWarnings("unused") 
    @EventHandler 
    public void onPlayerDeathBlock(EntityDeathEvent event){ 
        Entity e = event.getEntity(); 
        Location pl = e.getLocation(); 
        World world = e.getWorld(); 
        Location bl = new Location(world, pl.getX(), pl.getY(), pl.getZ()); 
        if(e instanceof Player){ 
            Player player = (Player) e; 
            bl.getBlock().setType(Material.SIGN_POST); 
        } 
    }
}
