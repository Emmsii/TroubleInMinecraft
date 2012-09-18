//https://github.com/GooseMonkey/Death-Certificate/blob/master/DeathCertificate/src/me/goosemonkey/deathcertificate/DeathCertificateListener.java
//https://github.com/GooseMonkey/Death-Certificate/blob/master/DeathCertificate/src/me/goosemonkey/deathcertificate/DeathCertificateWriter.java


package com.tammcd.troubleinminecraft;


import java.util.Calendar;
import java.util.GregorianCalendar;

import java.util.TimeZone;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SignWriter {

		Main plugin;
		
		public SignWriter(Main instance){
			plugin = instance;
		}
		
		
		
		@SuppressWarnings("unused")
		private String getKiller(PlayerDeathEvent event){
			if(event.getEntity().getKiller() != null)
				return ChatColor.BOLD + event.getEntity().getKiller().getName();
			if(event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent){
				EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
				return this.getFriendlyMobName(event, event2);
			}
			if(event.getEntity().getLastDamageCause() != null)
				switch(event.getEntity().getLastDamageCause().getCause()){
				case BLOCK_EXPLOSION: return "Explosion";
				case CONTACT: return "Cactus";
				case DROWNING: return "Drowning";
				case ENTITY_EXPLOSION: return "Creeper";
				case FALL: return "Falling";
				case FIRE: return "Fire";
				case FIRE_TICK: return "Fire";
				case LAVA: return "Lava";
				case LIGHTNING: return "Lightning";
				case MAGIC: return "Magic";
				case MELTING: return "Melting?";
				case POISON: return "Poison";
				case STARVATION: return "Starvation";
				case SUFFOCATION: return "Suffocation";
				case SUICIDE: return "Suicide";
				case VOID: return "Void";
				default: break;
				}
			return "Dying";
		}
		
		
		private String getFriendlyMobName(PlayerDeathEvent event, EntityDamageByEntityEvent event2)
		{
			switch (event2.getDamager().getType())
			{
			case ARROW: 
				if (((Arrow) event2.getDamager()).getShooter() == null)
					return "an Arrow";
				else if (((Arrow) event2.getDamager()).getShooter().getType() == EntityType.SKELETON)
					return "a Skeleton";
				else
					return ((Player)((Arrow) event2.getDamager()).getShooter()).getName();
			case BLAZE: return "a Blaze";
			case CAVE_SPIDER: return "a Cave Spider";
			case CREEPER: return "a Creeper";
			case ENDER_DRAGON: return "the Enderdragon";
			case ENDERMAN: return "an Enderman";
			case FIREBALL:
				if (((Fireball) event2.getDamager()).getShooter() == null)
					return "a Fireball";
				else if (((Fireball) event2.getDamager()).getShooter().getType() == EntityType.GHAST)
					return "a Ghast";
				else if (((Fireball) event2.getDamager()).getShooter().getType() == EntityType.BLAZE)
					return "a Blaze";
				else
					return "a Fireball";
			case FISHING_HOOK: return "a Fishing Pole";
			case GHAST: return "a Ghast";
			case GIANT: return "a Giant";
			case IRON_GOLEM: return "an Iron Golem";
			case LIGHTNING: return "Lightning";
			case MAGMA_CUBE: return "a Magma Cube";
			case PIG_ZOMBIE: return "a Zombie Pigman";
			case PLAYER: return "a Player";
			case PRIMED_TNT: return "TNT";
			case SILVERFISH: return "a Silverfish";
			case SKELETON: return "a Skeleton";
			case SLIME: return "a Slime";
			case SMALL_FIREBALL: return "a Fireball";
			case SNOWBALL: return "a Snowball";
			case SNOWMAN: return "a Snow Golem";
			case SPIDER: return "a Spider";
			case SPLASH_POTION: 
				if (((ThrownPotion) event2.getDamager()).getShooter().getType() == EntityType.PLAYER)
					return ((Player) ((ThrownPotion) event2.getDamager()).getShooter()).getName();
				else
					return "a Splash Potion";
			case WOLF: return "a Wolf";
			case ZOMBIE: return "a Zombie";
			default: return "Herobrine";
			}
		}

		@SuppressWarnings("unused")
		private String getTime(){
			Calendar local = Calendar.getInstance();
			
			String timeZone = this.plugin.getConfig().getString("TimeAndDate.TimeZone");
			
			if(!timeZone.equalsIgnoreCase("Default")){
				Calendar foreign = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
				
				foreign.setTimeInMillis(local.getTimeInMillis());
				
				return (foreign.get(Calendar.HOUR) == 0 ? "12" : foreign.get(Calendar.HOUR)) + ":" +
				(local.get(Calendar.MINUTE) < 10 ? "0" + foreign.get(Calendar.MINUTE) : foreign.get(Calendar.MINUTE)) + " " +
				(foreign.get(Calendar.AM_PM) == Calendar.PM ? "PM" : "AM") + " " +
				foreign.getTimeZone().getDisplayName(false, TimeZone.SHORT);
		
			}
			
			return (local.get(Calendar.HOUR) == 0 ? "12" : local.get(Calendar.HOUR)) + ":" +
			(local.get(Calendar.MINUTE) < 10 ? "0" + local.get(Calendar.MINUTE) : local.get(Calendar.MINUTE)) + " " +
			(local.get(Calendar.AM_PM) == Calendar.PM ? "PM" : "AM") + " " +
			local.getTimeZone().getDisplayName(false, TimeZone.SHORT);
			
}}
