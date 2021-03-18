package com.snowball.xan.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockStairsBase extends BlockStairs {
	public BlockStairsBase(String name, IBlockState state) {
		super(state);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.useNeighborBrightness = true;
	}
}
