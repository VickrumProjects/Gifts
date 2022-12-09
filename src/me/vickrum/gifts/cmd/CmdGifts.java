package me.vickrum.gifts.cmd;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.chestgui.ChestGui;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.sender.TypePlayer;
import com.massivecraft.massivecore.util.Txt;

import me.vickrum.gifts.action.ActionCollectAll;
import me.vickrum.gifts.action.ActionCollectGift;
import me.vickrum.gifts.entity.MConf;
import me.vickrum.gifts.entity.MPlayer;
import me.vickrum.gifts.entity.object.ItemData;
import me.vickrum.gifts.util.ItemBuilder;

public class CmdGifts extends MassiveCommand {
	private static final CmdGifts i = new CmdGifts();

	public static CmdGifts get() {
		return i;
	}

	public CmdGifts() {
		addRequirements(RequirementHasPerm.get("gothasy.gifts"));
		setAliases(MConf.get().cmdGiftsAliases);
		setDesc("See your gifts");
		
		addParameter(TypePlayer.get(), "player", "me");
	}

	public void perform() throws MassiveException {
		Player player = this.readArgAt(0, me);
		
		if (me.getName().equals(player.getName())) {
			me.openInventory(this.getGiftInventory(me).getInventory());
			return;
		} else {
			me.openInventory(this.getGiftOtherInventory(player).getInventory());
			return;
		}
	}
	
	public ChestGui getGiftOtherInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, Txt.parse(MConf.get().giftsPlayerInvName.replace("{player}", player.getName())));
		ChestGui chestGui = ChestGui.getCreative(inventory);
		MPlayer mplayer = MPlayer.get(player);
		chestGui.setAutoclosing(false);
		chestGui.setAutoremoving(false);
		chestGui.setSoundOpen(null);
		chestGui.setSoundClose(null);
		int rows = 54 / 9;
		if (rows >= 3) {
			for (int i = 0; i <= 8; i++)
				chestGui.getInventory().setItem(i,
						new ItemBuilder(Material.STAINED_GLASS_PANE).amount(1).name(" ")
								.durability(MConf.get().giftGuiBorderGlassDurabilityId)
								.addGlow(MConf.get().giftBorderGlassGlowEnabled));

			for (int s = 8; s < (54 - 9); s += 9) {
				int lastSlot = s + 1;
				chestGui.getInventory().setItem(s,
						new ItemBuilder(Material.STAINED_GLASS_PANE).amount(1).name(" ")
								.durability(MConf.get().giftGuiBorderGlassDurabilityId)
								.addGlow(MConf.get().giftBorderGlassGlowEnabled));
				chestGui.getInventory().setItem(lastSlot,
						new ItemBuilder(Material.STAINED_GLASS_PANE).amount(1).name(" ")
								.durability(MConf.get().giftGuiBorderGlassDurabilityId)
								.addGlow(MConf.get().giftBorderGlassGlowEnabled));
			}

			for (int lr = (54 - 9); lr < 54; lr++)
				chestGui.getInventory().setItem(lr,
						new ItemBuilder(Material.STAINED_GLASS_PANE).amount(1).name(" ")
								.durability(MConf.get().giftGuiBorderGlassDurabilityId)
								.addGlow(MConf.get().giftBorderGlassGlowEnabled));
		}

		for (ItemData item : mplayer.getGiftItems()) {			
			chestGui.getInventory().addItem(item.getItem());
		}
		
		return chestGui;
	}

	public ChestGui getGiftInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, Txt.parse(MConf.get().giftsInvName));
		ChestGui chestGui = ChestGui.getCreative(inventory);
		MPlayer mplayer = MPlayer.get(me);
		chestGui.setAutoclosing(false);
		chestGui.setAutoremoving(false);
		chestGui.setSoundOpen(null);
		chestGui.setSoundClose(null);
		int rows = 54 / 9;
		if (rows >= 3) {
			for (int i = 0; i <= 8; i++)
				chestGui.getInventory().setItem(i,
						new ItemBuilder(Material.STAINED_GLASS_PANE).amount(1).name(" ")
								.durability(MConf.get().giftGuiBorderGlassDurabilityId)
								.addGlow(MConf.get().giftBorderGlassGlowEnabled));

			for (int s = 8; s < (54 - 9); s += 9) {
				int lastSlot = s + 1;
				chestGui.getInventory().setItem(s,
						new ItemBuilder(Material.STAINED_GLASS_PANE).amount(1).name(" ")
								.durability(MConf.get().giftGuiBorderGlassDurabilityId)
								.addGlow(MConf.get().giftBorderGlassGlowEnabled));
				chestGui.getInventory().setItem(lastSlot,
						new ItemBuilder(Material.STAINED_GLASS_PANE).amount(1).name(" ")
								.durability(MConf.get().giftGuiBorderGlassDurabilityId)
								.addGlow(MConf.get().giftBorderGlassGlowEnabled));
			}

			for (int lr = (54 - 9); lr < 54; lr++)
				chestGui.getInventory().setItem(lr,
						new ItemBuilder(Material.STAINED_GLASS_PANE).amount(1).name(" ")
								.durability(MConf.get().giftGuiBorderGlassDurabilityId)
								.addGlow(MConf.get().giftBorderGlassGlowEnabled));
		}

		if (mplayer.getGiftItems().size() >= 1) {
			for (ItemData item : mplayer.getGiftItems()) {
				chestGui.setAction(chestGui.getInventory().firstEmpty(), new ActionCollectGift(mplayer));
				
				chestGui.getInventory().addItem(item.getItem());
			}
		}

		if (MConf.get().collectAllGiftsEnabled) {
			List<String> lore = new ArrayList<String>();
			for (String s : MConf.get().collectAllGiftsLores)
				lore.add(Txt
						.parse(s.replace("{amount}", NumberFormat.getInstance().format(mplayer.getGiftItems().size()))));

			chestGui.getInventory().setItem(MConf.get().collectAllGiftsSlot,
					new ItemBuilder(MConf.get().collectAllGiftsMaterial).amount(1)
							.name(Txt.parse(MConf.get().collectAllGiftsDisplayName))
							.durability(MConf.get().collectAllGiftsMaterialID).setLore(lore));
			
			chestGui.setAction(MConf.get().collectAllGiftsSlot, new ActionCollectAll(mplayer));
		}
		return chestGui;
	}
}
