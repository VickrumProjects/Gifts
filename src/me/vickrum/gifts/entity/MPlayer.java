package me.vickrum.gifts.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.collections.MassiveSet;
import com.massivecraft.massivecore.store.SenderEntity;

import me.vickrum.gifts.CoreParticipator;
import me.vickrum.gifts.entity.object.ItemData;

public class MPlayer extends SenderEntity<MPlayer> implements CoreParticipator {

	public static MPlayer get(Object oid) {
		return (MPlayer) MPlayerColl.get().get(oid);
	}

	/////////////////////////////////////////
	// Strings / Boolons / ints
	/////////////////////////////////////////
	private boolean giftsEnabled = true;

	protected Set<UUID> ignoredUUIDs = new HashSet<>();

	public MassiveSet<ItemData> itemsGifts = new MassiveSet<ItemData>();

	/////////////////////////////////////////
	// Loading
	/////////////////////////////////////////
	public MPlayer load(MPlayer that) {
		setGiftGivenStatus(that.giftsEnabled);
		setGiftItems(that.itemsGifts);
		return this;
	}

	/////////////////////////////////////////
	// Gifts Grabbing
	/////////////////////////////////////////
	public MassiveSet<ItemData> getGiftItems() {
		return this.itemsGifts;
	}

	public void setGiftItems(MassiveSet<ItemData> massiveSet) {
		this.itemsGifts = massiveSet;
		changed();
	}

	public void logGiftItem(ItemStack str1, String str2) {
		this.itemsGifts.add(new ItemData(str1, str2));
		changed();
	}
	
	public void removeGift (ItemData item) {
		this.itemsGifts.remove(item);
		changed();
	}

	/////////////////////////////////////////
	// Gifts Grabbing
	/////////////////////////////////////////
	public boolean getGiftsStatus() {
		return this.giftsEnabled;
	}

	public void setGiftGivenStatus(boolean status) {
		this.giftsEnabled = status;
		changed();
	}

	/////////////////////////////////////////
	// Ignore Grabbing
	/////////////////////////////////////////
	public void addIgnoredUUID(UUID uuid) {
		this.ignoredUUIDs.add(uuid);
		changed();
	}

	public boolean isIgnoredUUID(UUID uuid) {
		return this.ignoredUUIDs.contains(uuid);
	}

	public void removeIgnoredUUID(UUID uuid) {
		this.ignoredUUIDs.remove(uuid);
		changed();
	}
}
