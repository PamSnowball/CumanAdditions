package com.snowball.xan.init;

import java.util.ArrayList;
import java.util.List;

import com.snowball.xan.block.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block CUMAN_BRICK = new BlockBase("cumanbrick", Material.ROCK);
	public static final Block RED_CUMAN_BRICK = new BlockBase("red_cumanbrick", Material.ROCK);

	public static final Block CUMAN_STAIRS = new BlockStairsBase("cumanbrick_stairs", CUMAN_BRICK.getDefaultState());
	public static final Block RED_CUMAN_STAIRS = new BlockStairsBase("red_cumanbrick_stairs", RED_CUMAN_BRICK.getDefaultState());

	public static final BlockSlabBase CUMAN_SLAB_DOUBLE = new BlockSlabDoubleBase("cumanbrick_slab_double", Material.ROCK, ModBlocks.CUMAN_SLAB_HALF);
	public static final BlockSlabBase CUMAN_SLAB_HALF = new BlockSlabHalfBase("cumanbrick_slab_half", Material.ROCK, ModBlocks.CUMAN_SLAB_HALF, ModBlocks.CUMAN_SLAB_DOUBLE);

	public static final BlockSlabBase RED_CUMAN_SLAB_DOUBLE = new BlockSlabDoubleBase("red_cumanbrick_slab_double", Material.ROCK, ModBlocks.RED_CUMAN_SLAB_HALF);
	public static final BlockSlabBase RED_CUMAN_SLAB_HALF = new BlockSlabHalfBase("red_cumanbrick_slab_half", Material.ROCK, ModBlocks.RED_CUMAN_SLAB_HALF, ModBlocks.RED_CUMAN_SLAB_DOUBLE);
	
	public static final Block CUMAN_WALL = new BlockWallBase("cumanbrick_wall");
	public static final Block RED_CUMAN_WALL = new BlockWallBase("red_cumanbrick_wall");
	
	public static final Block MUD_FRAME = new BlockTallBase("mudbrick_frame");
	public static final Block CUMAN_FRAME = new BlockTallBase("cumanbrick_frame");
	public static final Block RED_CUMAN_FRAME = new BlockTallBase("red_cumanbrick_frame");

	public static final Block WHITE_FELT_FRAME = new BlockWhiteFeltFrame("white_felt_frame");
	public static final Block RED_FELT_FRAME = new BlockRedFeltFrame("red_felt_frame");
}
