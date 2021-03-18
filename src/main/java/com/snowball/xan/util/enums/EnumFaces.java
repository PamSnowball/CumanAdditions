package com.snowball.xan.util.enums;

import com.snowball.xan.util.handlers.FaceHandler;
import com.snowball.xan.util.interfaces.EditableLoader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public enum EnumFaces implements IStringSerializable {
	BASE(0, "base", (EditableLoader)new FaceHandler.BaseLoader()),
	ONE(1, "one", (EditableLoader)new FaceHandler.OneLoader()),
	TWO(2, "two", (EditableLoader)new FaceHandler.TwoLoader()),
	TWO_EQUALS(3, "two_equals", (EditableLoader)new FaceHandler.TwoLoader()),
	THREE(4, "three", (EditableLoader)new FaceHandler.ThreeLoader()),
	COMPLETE(5, "full", (EditableLoader)new FaceHandler.CompleteLoader());
	
	private int id;
	
	private String name;	
	
	private EditableLoader loader;
	
	EnumFaces(int id, String name, EditableLoader loader) {
		this.loader = loader;
		this.name = name;
		this.id = id;
	}

	public void loadFaces(EnumWhiteColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
		this.loader.load(colours, facing, world, pos, state);
	}

	public void loadFaces(EnumRedColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
		this.loader.load(colours, facing, world, pos, state);
	}
		
	public int getId() {
		return id;
	}
	
	public String toString() {
		return this.getName();
	}

	public String getName() {
		return name;
	}

	public static EnumFaces fromId(int id) {
		return EnumFaces.values()[MathHelper.abs(id % EnumFaces.values().length)];
	}
}