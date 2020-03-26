package pulsepvp_.BackToServer_Spigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import pulsepvp_.BackToServer_Spigot.listeners.Auth;
import pulsepvp_.BackToServer_Spigot.utils.MySQL;

public class BackToServer_Spigot extends JavaPlugin {
	public static ConsoleCommandSender console = Bukkit.getConsoleSender();
	private static BackToServer_Spigot instance;
	public static String prefix = ("" + ChatColor.GOLD + ChatColor.BOLD + "BackToServer - Spigot " + ChatColor.RESET
			+ ChatColor.DARK_GRAY + "» " + ChatColor.RESET);
	public static String console_prefix = "" + ChatColor.GOLD + ChatColor.BOLD + "[" + ChatColor.RESET
			+ ChatColor.YELLOW + "BackToServer - Spigot" + ChatColor.BOLD + ChatColor.GOLD + "]" + " ";
	public static MySQL database;

	@Override
	public void onEnable() {
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		database = new MySQL(getConfig().getString("host"), getConfig().getString("database"),
				getConfig().getString("username"), getConfig().getString("password"));
		database.Connect();
		instance = this;
		console.sendMessage(console_prefix + ChatColor.GREEN + "Enabling...");
		getServer().getPluginManager().registerEvents(new Auth(), this);
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}

	@Override
	public void onDisable() {
		this.saveConfig();
		database.Disconnect();
		console.sendMessage(console_prefix + ChatColor.RED + "Disabling...");
	}

	public static ConsoleCommandSender getConsole() {
		return console;
	}

	public static BackToServer_Spigot getInstance() {
		return instance;
	}
}
