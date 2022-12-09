package me.vickrum.gifts.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import me.vickrum.gifts.entity.MConf;
import me.vickrum.gifts.entity.MPlayer;
import me.vickrum.gifts.util.ItemNames;

public class CmdGift extends MassiveCommand {
	private static final CmdGift i = new CmdGift();

	public static CmdGift get() {
		return i;
	}

	public CmdGift() {
		addRequirements(RequirementHasPerm.get("gothasy.gift"));
		setAliases(MConf.get().cmdGiftAliases);
		setDesc("Give players the item in your hand");

		addParameter(TypeString.get(), "player/toggle", "me");
	}

	public void perform() throws MassiveException {
		String choice = (String) this.readArgAt(0, "me");

		if (MConf.get().cmdGiftToggle.contains(choice.toLowerCase())) {
			if (MPlayer.get(me).getGiftsStatus() == true) {
				// Enabled so disable
				MPlayer.get(me).setGiftGivenStatus(false);

				if (!MConf.get().msgToggledGiftsOff.isEmpty())
					msg(MConf.get().msgToggledGiftsOff);

				return;
			}

			if (MPlayer.get(me).getGiftsStatus() == false) {
				// Disabled so enable
				MPlayer.get(me).setGiftGivenStatus(true);

				if (!MConf.get().msgToggledGiftsOn.isEmpty())
					msg(MConf.get().msgToggledGiftsOn);

				return;
			}
		}

		if (choice.toLowerCase().equals("me")) {
			if (!MConf.get().msgCantGiftYourself.isEmpty())
				msg(MConf.get().msgCantGiftYourself);
			return;
		}

		if (Bukkit.getPlayer(choice) != null) {
			MPlayer mplayer = MPlayer.get(Bukkit.getPlayer(choice));

			if (mplayer.getName().equals(me.getName())) {
				if (!MConf.get().msgCantGiftYourself.isEmpty())
					msg(MConf.get().msgCantGiftYourself);
				return;
			}

			if (me.getItemInHand() == null || me.getItemInHand().getType().equals(Material.AIR)) {
				if (!MConf.get().msgNothingInHand.isEmpty())
					msg(MConf.get().msgNothingInHand);
				return;
			}
			
			if (mplayer.getGiftsStatus() == false) {
				if (!MConf.get().msgPlayersToggledGiftsOff.isEmpty())
					msg(MConf.get().msgPlayersToggledGiftsOff.replace("{player}", mplayer.getName()));
				return;
			}

			if (mplayer.getGiftItems().size() >= MConf.get().maxGifts) {
				if (!MConf.get().msgMaxGifts.isEmpty())
					msg(MConf.get().msgMaxGifts);
				return;
			}
			
			if (MConf.get().blockItems.contains(me.getItemInHand().getType().name())) {
				if (!MConf.get().msgBlockedItem.isEmpty())
					msg(MConf.get().msgBlockedItem);
				return;
			}

			String lol = ItemNames.lookup(me.getItemInHand().clone());

			if (me.getItemInHand().getItemMeta().getDisplayName() != null)
				lol = me.getItemInHand().getItemMeta().getDisplayName();

			if (!MConf.get().msgGivenGift.isEmpty())
				msg(MConf.get().msgGivenGift.replace("{itemName}", lol).replace("{player}", mplayer.getName()));

			if (mplayer.isOnline() && !MConf.get().msgRecievedGift.isEmpty())
				mplayer.msg(MConf.get().msgRecievedGift.replace("{player}", me.getName()).replace("{itemName}", lol));

			mplayer.logGiftItem(me.getItemInHand().clone(), me.getName());

			me.setItemInHand(null);

			return;
		}
	}
}
