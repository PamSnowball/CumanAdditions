package com.snowball.xan.block;

import com.snowball.xan.Xan;
import com.snowball.xan.init.ModBlocks;
import com.snowball.xan.init.ModItems;
import com.snowball.xan.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {

	public BlockBase(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Xan.XAN_TAB);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
	}

	@Override
	public void registerModels() {
		Xan.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
	}
}
