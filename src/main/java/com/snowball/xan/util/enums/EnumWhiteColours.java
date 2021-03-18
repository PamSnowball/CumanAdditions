package com.snowball.xan.util.enums;

import java.util.HashMap;
import java.util.Map;

import com.snowball.xan.init.ModItems;

import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;

public enum EnumWhiteColours implements IStringSerializable {
	ZIGZAG(0, "white_zigzag", ModItems.WHITE_ZIGZAG_FELT),
	BLUE(1, "white_blue", ModItems.WHITE_BLUE_FELT),
	YELLOW(2, "yellow", ModItems.YELLOW_FELT);

	private String name;
	private Item item;
	private int id;
	
	public static Map<Item, EnumWhiteColours> map = new HashMap<>();
	
	EnumWhiteColours(int id, String name, Item item) {
		this.item = item;
		this.id = id;
		this.name = name;
	}
	
	static {
		for (EnumWhiteColours colours : EnumWhiteColours.values())
			map.put(colours.item, colours);
	}
	
	public static EnumWhiteColours valueOf(Item item) {
		return map.get(item);
	}
	
	public int getId() {
		return id;
	}
	
	public Item getItem() {
		return item;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public static EnumWhiteColours fromId(int id) {
		return EnumWhiteColours.values()[MathHelper.abs(id % EnumWhiteColours.values().length)];
	}
}