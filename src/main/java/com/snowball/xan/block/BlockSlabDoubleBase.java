package com.snowball.xan.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class BlockSlabDoubleBase extends BlockSlabBase {
	public BlockSlabDoubleBase(String name, Material material, BlockSlab half) {
		super(name, material, half);
	}

	@Override
	public boolean isDouble() {	
		return true;
	}
}
