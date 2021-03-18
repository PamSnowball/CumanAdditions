package com.snowball.xan.util.interfaces;

import com.snowball.xan.util.enums.EnumRedColours;
import com.snowball.xan.util.enums.EnumWhiteColours;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface EditableLoader {
	void load(EnumWhiteColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state);

	void load(EnumRedColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state);
}
