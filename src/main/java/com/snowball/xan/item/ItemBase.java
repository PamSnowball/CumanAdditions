package com.snowball.xan.item;

import com.snowball.xan.Xan;
import com.snowball.xan.init.ModItems;
import com.snowball.xan.util.interfaces.IHasModel;

import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Xan.XAN_TAB);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Xan.proxy.registerItemRenderer(this, 0);
	}
}
