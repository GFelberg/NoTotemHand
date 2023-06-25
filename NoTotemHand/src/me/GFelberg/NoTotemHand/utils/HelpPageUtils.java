package me.GFelberg.NoTotemHand.utils;

import me.GFelberg.NoTotemHand.Main;

public class HelpPageUtils {

	public String getHelp_page() {
		return Main.getInstance().getConfig().getString("Help.Page").replace("&", "§");
	}

	public String getHelp_reload() {
		return Main.getInstance().getConfig().getString("Help.Reload").replace("&", "§");
	}
}