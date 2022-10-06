package me.GFelberg.NoTotemHand.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.GFelberg.NoTotemHand.Main;

public class TotemUtils {

	public static String prefix, inventoryEnabled, inventoryDisabled, help_page, help_reload;

	public static void loadVariables() {
		prefix = Main.getInstance().getConfig().getString("Totem.Prefix").replace("&", "§");
		inventoryEnabled = Main.getInstance().getConfig().getString("Totem.InventoryEnabled").replace("&", "§");
		inventoryDisabled = Main.getInstance().getConfig().getString("Totem.InventoryDisabled").replace("&", "§");
		help_page = Main.getInstance().getConfig().getString("Help.Page").replace("&", "§");
		help_reload = Main.getInstance().getConfig().getString("Help.Reload").replace("&", "§");
	}

	public void reloadConfig(Player p) {
		Main.getInstance().reloadConfig();
		loadVariables();
		p.sendMessage(prefix + " " + ChatColor.GREEN + "Plugin reloaded successfully!");
		Bukkit.getConsoleSender().sendMessage("=============================================");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "NoTotemHand Plugin reloaded");
		Bukkit.getConsoleSender().sendMessage("=============================================");
	}

	public void helpPage(Player p) {
		p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
		p.sendMessage(ChatColor.AQUA + "NoTotemHand - Help Commands");
		p.sendMessage(ChatColor.YELLOW + "/totem help: " + help_page);
		p.sendMessage(ChatColor.YELLOW + "/totem reload : " + help_reload);
		p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
	}
}