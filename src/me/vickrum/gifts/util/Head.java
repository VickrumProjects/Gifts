package me.vickrum.gifts.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public enum Head {
	PLAYER((EntityType) null, 3, (String) null), CHICKEN(EntityType.CHICKEN, 3, "MHF_Chicken"),
	PIG_ZOMBIE(EntityType.PIG_ZOMBIE, 3, "MHF_PigZombie"), PIG(EntityType.PIG, 3, "MHF_Pig"),
	COW(EntityType.COW, 3, "MHF_Cow"), SHEEP(EntityType.SHEEP, 3, "MHF_Sheep"),
	MOOSHROOM(EntityType.MUSHROOM_COW, 3, "MHF_MushroomCow"), SPIDER(EntityType.SPIDER, 3, "MHF_Spider"),
	ZOMBIE(EntityType.ZOMBIE, 2, (String) null), SKELETON(EntityType.SKELETON, 0, (String) null),
	VILLAGER(EntityType.VILLAGER, 3, "MHF_Villager"), MAGMA_CUBE(EntityType.MAGMA_CUBE, 3, "MHF_LavaSlime"),
	BLAZE(EntityType.BLAZE, 3, "MHF_Blaze"), CREEPER(EntityType.CREEPER, 4, (String) null),
	ENDERMAN(EntityType.ENDERMAN, 3, "MHF_Enderman"), IRON_GOLEM(EntityType.IRON_GOLEM, 3, "MHF_Golem");

	private EntityType entity;
	private int data;
	private String skin;
	String yellow;
	String gray;
	@SuppressWarnings("unused")
	private String properName;

	private Head(EntityType entity, int data, String skin) {
		this.yellow = ChatColor.YELLOW + "";
		this.gray = ChatColor.GRAY + "";
		this.properName = null;
		this.entity = entity;
		this.data = data;
		this.skin = skin;
	}

	public EntityType getEntityType() {
		return this.entity;
	}

	public short getData() {
		return (short) this.data;
	}

	public String getSkin() {
		return this.skin;
	}

	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, this.getData());
		if (this.getData() == 3 && this.getSkin() != null) {
			SkullMeta meta = (SkullMeta) item.getItemMeta();
			meta.setOwner(this.getSkin());
			item.setItemMeta(meta);
		}

		return item;
	}
}
