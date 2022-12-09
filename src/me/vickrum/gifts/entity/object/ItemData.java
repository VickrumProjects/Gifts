package me.vickrum.gifts.entity.object;

import org.bukkit.inventory.ItemStack;

public class ItemData {
	private ItemStack itemStack;
	
	private String playerName;
	
	private long timeOfExchange;

	public ItemData(ItemStack itemStack, String playerName) {
		this.itemStack = itemStack;
		this.playerName = playerName;
		this.timeOfExchange = System.currentTimeMillis();
	}
	
	public ItemStack getItem() {
		return this.itemStack;
	}
	
	public String getPlayer() {
		return this.playerName;
	}
	
	public long getTimeOfExchange() {
		return this.timeOfExchange;
	}
}
