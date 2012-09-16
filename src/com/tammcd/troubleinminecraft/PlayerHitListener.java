package com.tammcd.troubleinminecraft;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
//import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
	/*
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		int itemId = player.getItemInHand().getType().getId();
		if (itemId == 280) {
			player.sendMessage(ChatColor.RED + "You have arrested a player! Shame it doesn't do anything... and that it happens everytime you click.");

		}
	}
*/
	 @EventHandler
	    public void clickWithStick(PlayerInteractEntityEvent event) {
	        if ((event.getRightClicked() instanceof Player) && (event.getPlayer().getItemInHand().getType() == Material.STICK)) {
	            Player player2 = (Player) event.getRightClicked();
	            Player player1 = event.getPlayer();
	            player1.sendMessage(ChatColor.GOLD + "You have arrested " + player2.getName() + ".");
	            player2.sendMessage(ChatColor.RED + player1.getName() + " has arrested you.");
	        }
	    }
	
	
	@SuppressWarnings("unused")
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		Entity e = event.getEntity();
		Entity damager = event.getDamager();
		
		if(e instanceof Player){
			Player player = (Player) e;
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 1));
			event.setDamage(0);
			player.setPlayerListName(ChatColor.RED + player.getName() + " [DEAD]");
		}
	}
	
}
