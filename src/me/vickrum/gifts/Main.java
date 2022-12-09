package me.vickrum.gifts;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.massivecraft.massivecore.MassivePlugin;

public class Main extends MassivePlugin {
	private static Main i;

	public Main() {
		i = this;
	}

	public static Main get() {
		return i;
	}

	public void onEnableInner() {
		activateAuto();
	}

	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) 
			player.closeInventory();
		
		super.onDisable();
	}

	public boolean isVersionSynchronized() {
		return false;
	}
	
	public String caps(final String string) {
		final String[] list = string.split("_");
		String s = "";
		String[] array;
		for (int length = (array = list).length, i = 0; i < length; ++i) {
			final String st = array[i];
			s = String.valueOf(String.valueOf(s)) + st.substring(0, 1).toUpperCase() + st.substring(1).toLowerCase()
					+ " ";
		}
		return s.substring(0, s.length() - 1);
	}
}
