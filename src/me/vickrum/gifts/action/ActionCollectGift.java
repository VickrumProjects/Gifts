package me.vickrum.gifts.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.massivecraft.massivecore.chestgui.ChestActionAbstract;

import me.vickrum.gifts.entity.MConf;
import me.vickrum.gifts.entity.MPlayer;
import me.vickrum.gifts.entity.object.ItemData;
import me.vickrum.gifts.util.ItemNames;

public class ActionCollectGift extends ChestActionAbstract {

	private MPlayer mplayer;

	public ActionCollectGift(MPlayer mplayer) {
		this.mplayer = mplayer;
	}

	public boolean onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		event.setCancelled(true);

		for (ItemData item : mplayer.getGiftItems()) {
			if (item.getItem().equals(event.getCurrentItem())) {
				player.getInventory().addItem(item.getItem());
				mplayer.itemsGifts.remove(item);
				mplayer.changed();

				player.closeInventory();

				String lol = ItemNames.lookup(item.getItem());
				
				if (item.getItem().getItemMeta().getDisplayName() != null)
					lol = item.getItem().getItemMeta().getDisplayName();
				
				if (!MConf.get().msgCollectedGift.isEmpty())
					mplayer.msg(MConf.get().msgCollectedGift.replace("{itemName}", lol));
				
				return false;
			}
		}

		return true;
	}

}
