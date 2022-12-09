package me.vickrum.gifts.entity;

import java.util.List;

import org.bukkit.Material;

import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

public class MConf extends Entity<MConf> {

	protected static transient MConf i;

	///////////////////////////////////////////
	// Commands
	///////////////////////////////////////////
	public List<String> cmdGiftAliases = MUtil.list("gift");
	public List<String> cmdGiftToggle = MUtil.list("toggle");

	public List<String> cmdGiftsAliases = MUtil.list("gifts");

	///////////////////////////////////////////
	// Messages
	///////////////////////////////////////////
	public String msgCantGiftYourself = "&b&lGifts &8➸ &cYou cannot send yourself a gift.";
	public String msgNothingInHand = "&b&lGifts &8➸ &cYou must be holding an item to gift!";
	public String msgGivenGift = "&b&lGifts &8➸ &7You have sent {player} a gift - {itemName}&7!";
	public String msgRecievedGift = "&b&lGifts &8➸ &b{player} &7has sent you a gift - '{itemName}&7'! (/gifts)";
	public String msgMaxGifts = "&b&lGifts &8➸ &b{player} &7has max gifts amount (21)!";
	public String msgNoGiftsTooCollect = "&b&lGifts &8➸ &cYou seem to have no gifts to collect!";
	public String msgCollectedAllGifts = "&b&lGifts &8➸ &7You have collected all your gifts!";
	public String msgCollectedGift = "&b&lGifts &8➸ &7You have collected '{itemName}&7'!";
	public String msgToggledGiftsOff = "&b&lGifts &8➸ &7You will no longer receive gifts from players!";
	public String msgToggledGiftsOn = "&b&lGifts &8➸ &7You will now be able to receive gifts from players!";
	public String msgPlayersToggledGiftsOff = "&b&lGifts &8➸ &b{player} &7has their gifts disabled!";

	public List<String> blockItems = MUtil.list("MOB_SPAWNER");
	public String msgBlockedItem = "&b&lGifts &8➸ &7This is a blocked item!";
	///////////////////////////////////////////
	// Settings
	///////////////////////////////////////////
	public int maxGifts = 21;
	public String giftsInvName = "&b&lGift Inventory";
	public String giftsPlayerInvName = "&b&l{player}'s Gift Inventory";
	public int giftGuiBorderGlassDurabilityId = 7;
	public boolean giftBorderGlassGlowEnabled = false;
	
	public boolean collectAllGiftsEnabled = true;
	public String collectAllGiftsDisplayName = "&b&lCollect All";
	public List<String> collectAllGiftsLores = MUtil.list("&7", "&7Total Items: {amount}");
	public Material collectAllGiftsMaterial = Material.PAPER;
	public int collectAllGiftsMaterialID = 0;
	public int collectAllGiftsSlot = 4;
	///////////////////////////////////////////
	public static MConf get() {
		return i;
	}

	public MConf load(MConf that) {
		super.load(that);
		return this;
	}

}
