/*
 * TODO List:
 * - Add admin command to clear all database entries (separate commands for each db?)
 * - Add admin command to set joingame and death spawn locations
 * - Add admin command to set game and jail tp location
 * - Remove player from game.db on disconnect
 * - Add on death event
 * - Get nearest player entity to player on player death
 * - Sheriff Stick, right click on player to arrest (player sent to jail)
 * 		-> if player is roughian, Sheriff gets karma; if player is innocent, Sheriff loses karma
 * 		-> Sheriff Stick gets added damage value on each use
 * - On player join, check if player is in constant db else add player
 * - On player death, player spawns in completely crazy stylised death place
 * - Add player stats (from constant db) command
 * - Change player name in chat according to whether they are in game, dead, in jail, or sheriff.
 * 
 * 
 * EXPLINATIONS:
 * 	- Number 1
 * 		This is where you can start to add new commands in with an else if statement.
 * 		In between the START and the END comment lines.
 *  - Number 2
 *  	This is plugin metrics, a stats feature for MC plugins.
 *  	On the website, mcstats.org you can see the ammount of servers using the plugin and the players on the server.
 *  	Metrics requires another class file.
 */
package com.tammcd.troubleinminecraft;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import lib.PatPeter.SQLibrary.SQLite;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;
	public SQLite db;
	private boolean gameRunning = false;

	//STARTUP
	public void onEnable() {
		this.getDataFolder().mkdir();
		this.saveDefaultConfig();

		sqlConnection();
		sqlTableCheck();

		PluginDescriptionFile pdfFile = this.getDescription();

		this.logger.info(pdfFile.getName() + " (version: " + pdfFile.getVersion() + ") has been enabled!");
		
		//Plugin Metrics (Refer to EXPLINATIONS in the to do list) NUMBER 2
		
		try{
			Metrics metrics = new Metrics(this);
			metrics.start();
		}catch (IOException e){
			//text
		}
		

		getServer().getPluginManager().registerEvents(new Listener() {
			@SuppressWarnings("unused")
			@EventHandler
			public void playerJoin(PlayerJoinEvent event) {
				event.getPlayer().sendMessage(ChatColor.DARK_PURPLE + Main.this.getConfig().getString("motd"));
			}
		}, this);
	}

	//SHUTDOWN
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " (version: " + pdfFile.getVersion() + ") has been disabled!");
		db.close();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		String playerName = player.getName();

		int alreadyPlayer = 0;
		try {
			ResultSet isPlayerInGame = db.query("SELECT EXISTS(SELECT * FROM game WHERE playername='" + playerName + "')");
			while (isPlayerInGame.next()) {
				alreadyPlayer = alreadyPlayer + isPlayerInGame.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//START (Refer to EXPLINATIONS in the to do list) NUMBER 1
		
		//Info Command
		if(label.equalsIgnoreCase("timfo")){
			PluginDescriptionFile pdfFile = this.getDescription();
			player.sendMessage(ChatColor.RED + "Trouble In Minecraft Version " + pdfFile.getVersion());
			player.sendMessage(ChatColor.RED + "Code: Tamfoolery, Emmsii");
		}
		
		//Stats command
		else if(label.equalsIgnoreCase("stats")){
			player.sendMessage(ChatColor.GOLD + "-----Stats-----");
			player.sendMessage(ChatColor.GOLD + "Kills: 0");
			player.sendMessage(ChatColor.GOLD + "Deaths: 0");
			player.sendMessage(ChatColor.GOLD + "Arrests: 0");
			
		}
		//END
		else if (alreadyPlayer != 1) {

			if (label.equalsIgnoreCase("join")) {
				if (gameRunning) {
					player.sendMessage(ChatColor.GOLD + "You have joined a game in progress!");
				} else {
					player.sendMessage(ChatColor.GOLD + "You have joined! The game will start shortly...");
				}
				addPlayer(player, playerName);
				if (gamePlayerCount() == 1) {
					getServer().broadcastMessage("There is now " + gamePlayerCount() + " player in game.");
				} else {
					getServer().broadcastMessage("There are now " + gamePlayerCount() + " players in game.");
				}
			}

		} else{
			player.sendMessage(ChatColor.RED + "You have already joined the game!");
		}
		
		return false;
	}

	public void addPlayer(Player player, String playerName) {
		int playerCount = 0;

		try {
			ResultSet totalPlayers = db.query("SELECT COUNT(*) FROM game");
			while (totalPlayers.next()) {
				playerCount = playerCount + totalPlayers.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		db.query("INSERT INTO game (id, playername, isSheriff, isDead, isRoughian) VALUES(" + (playerCount + 1) + ", '" + playerName + "', 'false', 'false', 'false')");
	}
	
	public int gamePlayerCount() {
		int playerCount = 0;

		try {
			ResultSet totalPlayers = db.query("SELECT COUNT(*) FROM game");
			while (totalPlayers.next()) {
				playerCount = playerCount + totalPlayers.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (playerCount);
	}

	public void sqlConnection() {
		db = new SQLite(this.getLogger(), "Trouble in Minecraft", "game", this.getDataFolder().getPath());

		try {
			db.open();
		} catch (Exception e) {
			this.getLogger().info(e.getMessage());
			getPluginLoader().disablePlugin(plugin);
		}
	}

	public void sqlTableCheck() {
		if (db.checkTable("game") && db.checkTable("constant")) {
			return;
		} else {
			db.query("CREATE TABLE game (id INT PRIMARY KEY, playername VARCHAR(255), isSheriff VARCHAR(5), isDead VARCHAR(5), isRoughian VARCHAR(5))");
			this.getLogger().info("[Trouble in Minecraft] 'game' table has been created!");
			db.query("CREATE TABLE constant (id INT PRIMARY KEY, playername VARCHAR(255), karma INT, playCount INT, sheriffCount INT, roughianCount INT, deathCount INT)");
			this.getLogger().info("[Trouble in Minecraft] 'constant' table has been created!");
		}
	}
}