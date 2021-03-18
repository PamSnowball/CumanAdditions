package com.snowball.xan.block;

import com.snowball.xan.Xan;
import com.snowball.xan.init.ModItems;
import com.snowball.xan.util.interfaces.IHasModel;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;

public class BlockSlabHalfBase extends BlockSlabBase implements IHasModel {
	public BlockSlabHalfBase(String name, Material material,  BlockSlab half, BlockSlab doubleSlab) {
		super(name, material, half);
		
		ModItems.ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
	}
	
	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public void registerModels() {
		Xan.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
	}
}