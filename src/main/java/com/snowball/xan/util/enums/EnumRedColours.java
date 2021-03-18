package com.snowball.xan.util.enums;

import java.util.HashMap;
import java.util.Map;

import com.snowball.xan.init.ModItems;

import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;

public enum EnumRedColours implements IStringSerializable {
	ZIGZAG(0, "red_zigzag", ModItems.RED_ZIGZAG_FELT),
	BLUE(1, "red_blue", ModItems.RED_BLUE_FELT);

	private String name;
	private Item item;
	private int id;
	
	public static Map<Item, EnumRedColours> map = new HashMap<>();
	
	EnumRedColours(int id, String name, Item item) {
		this.item = item;
		this.id = id;
		this.name = name;
	}
	
	static {
		for (EnumRedColours colours : EnumRedColours.values())
			map.put(colours.item, colours);
	}
	
	public static EnumRedColours valueOf(Item item) {
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
	
	public static EnumRedColours fromId(int id) {
		return EnumRedColours.values()[MathHelper.abs(id % EnumRedColours.values().length)];
	}
}
