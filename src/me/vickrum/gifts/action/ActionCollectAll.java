package me.vickrum.gifts.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.massivecraft.massivecore.chestgui.ChestActionAbstract;
import com.massivecraft.massivecore.util.Txt;

import me.vickrum.gifts.entity.MConf;
import me.vickrum.gifts.entity.MPlayer;
import me.vickrum.gifts.entity.object.ItemData;

public class ActionCollectAll extends ChestActionAbstract {

	private MPlayer mplayer;

	public ActionCollectAll(MPlayer mplayer) {
		this.mplayer = mplayer;
	}

	public boolean onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		event.setCancelled(true);

		if (mplayer.getGiftItems().size() <= 0) {
			if (!MConf.get().msgNoGiftsTooCollect.isEmpty())
				mplayer.msg(MConf.get().msgNoGiftsTooCollect);
			return true;
		}
		
		for (ItemData item : mplayer.getGiftItems()) {
			mplayer.getPlayer().getInventory().addItem(item.getItem());
		}

		mplayer.itemsGifts.clear();

		player.closeInventory();

		if (!MConf.get().msgCollectedAllGifts.isEmpty())
			player.sendMessage(Txt.parse(MConf.get().msgCollectedAllGifts));
		
		return false;
	}
}
