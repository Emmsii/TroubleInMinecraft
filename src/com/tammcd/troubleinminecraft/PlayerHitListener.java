package com.tammcd.troubleinminecraft;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerHitListener implements Listener{
	
	public static Main plugin;
	
	public PlayerHitListener(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerDeath(EntityDeathEvent event){
		Entity e = event.getEntity();
		if(e instanceof Player){
			Player player = (Player) e;
			player.setPlayerListName(ChatColor.RED + player.getName() + " [DEAD]");			
		}
	}
 
	@EventHandler(priority = EventPriority.NORMAL)
	public void onDrop(PlayerDropItemEvent event){
		Player player = event.getPlayer();
		Material m = event.getItemDrop().getItemStack().getType();
		if(m.getId() == Material.STICK.getId()){
			event.setCancelled(true);
			player.sendMessage(ChatColor.GOLD + "You cannot drop your Lookin' Stick.");
		}
	}
	
	
	 @EventHandler
	 public void clickWithStick(PlayerInteractEntityEvent event) {
	     if ((event.getRightClicked() instanceof Player) && (event.getPlayer().getItemInHand().getType() == Material.STICK)) {
	         Player player2 = (Player) event.getRightClicked();
	         Player player1 = event.getPlayer();
	         player1.sendMessage(ChatColor.GOLD + "You have arrested " + player2.getName() + ".");
	         player2.sendMessage(ChatColor.RED + player1.getName() + " has arrested you.");
	     }else{
	    	if ((event.getRightClicked() instanceof Entity) && (event.getPlayer().getItemInHand().getType() == Material.STICK)) {
	    		Player player = event.getPlayer();
	    		player.sendMessage(ChatColor.RED + "Watchu doin! You can't arrest that!");
	    	}
	     }
	 }
	 
	 @EventHandler
	 public void clickWithStickTick(PlayerInteractEntityEvent event) {
	     if ((event.getRightClicked() instanceof Player) && (event.getPlayer().getItemInHand().getType() == Material.BONE)) {
	         Player player2 = (Player) event.getRightClicked();
	         Player player1 = event.getPlayer();
	         player1.sendMessage(ChatColor.GOLD + player2.getName() + " has been alive for " + player2.getTicksLived() + " ticks.");
	         player2.sendMessage(ChatColor.RED + player1.getName() + " has seen how many ticks you have been alive.");
	     }else{
	    	 if ((event.getRightClicked() instanceof Entity) && (event.getPlayer().getItemInHand().getType() == Material.BONE)) {
	    		 Player player = event.getPlayer();
	    		 player.sendMessage(ChatColor.RED + "Watchu doin! You can't see how many ticks this has lived!");
	    	}
	     }
	 }
}
