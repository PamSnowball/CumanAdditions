package com.snowball.xan.tab;

import com.snowball.xan.init.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabMillenaireXan extends CreativeTabs {
	public CreativeTabMillenaireXan(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.CUMAN_BRICK);
	}
}
