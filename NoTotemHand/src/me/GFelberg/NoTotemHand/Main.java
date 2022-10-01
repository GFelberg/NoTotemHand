package me.GFelberg.NoTotemHand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.GFelberg.NoTotemHand.commands.Totem;
import me.GFelberg.NoTotemHand.events.TotemEvents;
import me.GFelberg.NoTotemHand.utils.TotemUtils;

public class Main extends JavaPlugin {

	private static Main instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		TotemUtils.loadVariables();
		getCommand("totem").setExecutor(new Totem());
		getServer().getPluginManager().registerEvents(new TotemEvents(), this);
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
		Bukkit.getConsoleSender().sendMessage("NoTotemHand Plugin Enabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin develloped by GFelberg");
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
	}

	public static Main getInstance() {
		return instance;
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
		Bukkit.getConsoleSender().sendMessage("NoTotemHand Plugin Disabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin develloped by GFelberg");
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
	}
}