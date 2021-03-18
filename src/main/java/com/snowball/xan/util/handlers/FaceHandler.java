package com.snowball.xan.util.handlers;

import com.snowball.xan.block.BlockRedFeltFrame;
import com.snowball.xan.block.BlockWhiteFeltFrame;
import com.snowball.xan.util.enums.EnumFaces;
import com.snowball.xan.util.enums.EnumRedColours;
import com.snowball.xan.util.enums.EnumWhiteColours;
import com.snowball.xan.util.interfaces.EditableLoader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FaceHandler {
	public static class CompleteLoader implements EditableLoader {
		public void load(EnumWhiteColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
			//Futurely, you'll be able to add a rope around it
		}

		public void load(EnumRedColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
			//Futurely, you'll be able to add a rope around it
		}
	}

	public static class BaseLoader implements EditableLoader {
		public void load(EnumWhiteColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
			world.setBlockState(pos, state
					.withProperty(BlockWhiteFeltFrame.FACES, EnumFaces.ONE)
					.withProperty(BlockWhiteFeltFrame.FACING, facing.getOpposite())
					.withProperty(BlockWhiteFeltFrame.COLOUR, colours));
		}

		public void load(EnumRedColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
			world.setBlockState(pos, state
					.withProperty(BlockRedFeltFrame.FACES, EnumFaces.ONE)
					.withProperty(BlockRedFeltFrame.FACING, facing.getOpposite())
					.withProperty(BlockRedFeltFrame.COLOUR, colours));
		}
	}

	public static class OneLoader implements EditableLoader {
		public void load(EnumWhiteColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
			EnumFacing blockState = state.getValue(BlockWhiteFeltFrame.FACING);
			int index = blockState.getHorizontalIndex() - facing.getHorizontalIndex();
			if (index == 2 || index == -2)
				world.setBlockState(pos, state
						.withProperty(BlockWhiteFeltFrame.FACES, EnumFaces.TWO_EQUALS)
						.withProperty(BlockWhiteFeltFrame.FACING, facing.getOpposite())
						.withProperty(BlockWhiteFeltFrame.COLOUR, colours));
			if (index == -1 || index == 3)
				world.setBlockState(pos, state
						.withProperty(BlockWhiteFeltFrame.FACES, EnumFaces.TWO)
						.withProperty(BlockWhiteFeltFrame.FACING, facing)
						.withProperty(BlockWhiteFeltFrame.COLOUR, colours));
			if (index == 1) 
				world.setBlockState(pos, state
						.withProperty(BlockWhiteFeltFrame.FACES, EnumFaces.TWO)
						.withProperty(BlockWhiteFeltFrame.FACING, EnumFacing.getHorizontal(facing.getHorizontalIndex() + 1))
						.withProperty(BlockWhiteFeltFrame.COLOUR, colours));
			if (index == -3)
				world.setBlockState(pos, state
						.withProperty(BlockWhiteFeltFrame.FACES, EnumFaces.TWO)
						.withProperty(BlockWhiteFeltFrame.FACING, EnumFacing.SOUTH)
						.withProperty(BlockWhiteFeltFrame.COLOUR, colours));
		}

		public void load(EnumRedColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
			EnumFacing blockState = state.getValue(BlockRedFeltFrame.FACING);
			int index = blockState.getHorizontalIndex() - facing.getHorizontalIndex();
			if (index == 2 || index == -2)
				world.setBlockState(pos, state
						.withProperty(BlockRedFeltFrame.FACES, EnumFaces.TWO_EQUALS)
						.withProperty(BlockRedFeltFrame.FACING, facing.getOpposite())
						.withProperty(BlockRedFeltFrame.COLOUR, colours));
			if (index == -1 || index == 3)
				world.setBlockState(pos, state
						.withProperty(BlockRedFeltFrame.FACES, EnumFaces.TWO)
						.withProperty(BlockRedFeltFrame.FACING, facing)
						.withProperty(BlockRedFeltFrame.COLOUR, colours));
			if (index == 1) 
				world.setBlockState(pos, state
						.withProperty(BlockRedFeltFrame.FACES, EnumFaces.TWO)
						.withProperty(BlockRedFeltFrame.FACING, EnumFacing.getHorizontal(facing.getHorizontalIndex() + 1))
						.withProperty(BlockRedFeltFrame.COLOUR, colours));
			if (index == -3)
				world.setBlockState(pos, state
						.withProperty(BlockRedFeltFrame.FACES, EnumFaces.TWO)
						.withProperty(BlockRedFeltFrame.FACING, EnumFacing.SOUTH)
						.withProperty(BlockRedFeltFrame.COLOUR, colours));
		}
	}
	
	public static class TwoLoader implements EditableLoader {
		public void load(EnumWhiteColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
			EnumFacing blockState = state.getValue(BlockWhiteFeltFrame.FACING);
			int index = blockState.getHorizontalIndex() - facing.getHorizontalIndex() - 3;
			if (state.getValue(BlockWhiteFeltFrame.FACES).equals(EnumFaces.TWO_EQUALS))
				world.setBlockState(pos, state
						.withProperty(BlockWhiteFeltFrame.FACES, EnumFaces.THREE)
						.withProperty(BlockWhiteFeltFrame.FACING, facing.getOpposite())
						.withProperty(BlockWhiteFeltFrame.COLOUR, colours));
			if (state.getValue(BlockWhiteFeltFrame.FACES).equals(EnumFaces.TWO)) {
				if (index == 2 || index == 0)
					world.setBlockState(pos, state
							.withProperty(BlockWhiteFeltFrame.FACES, EnumFaces.THREE)
							.withProperty(BlockWhiteFeltFrame.FACING, EnumFacing.getHorizontal(facing.getHorizontalIndex() + 1))
							.withProperty(BlockWhiteFeltFrame.COLOUR, colours));
				if (index == 1 || index == -1) {
					if (facing.equals(EnumFacing.WEST))
						world.setBlockState(pos, state
								.withProperty(BlockWhiteFeltFrame.FACES, EnumFaces.THREE)
								.withProperty(BlockWhiteFeltFrame.FACING, EnumFacing.WEST)
								.withProperty(BlockWhiteFeltFrame.COLOUR, colours));
					else
						world.setBlockState(pos, state
								.withProperty(BlockWhiteFeltFrame.FACING, EnumFacing.getHorizontal(facing.getHorizontalIndex() - 1))
								.withProperty(BlockWhiteFeltFrame.COLOUR, colours));
				}
				if (index == -2)
					world.setBlockState(pos, state
							.withProperty(BlockWhiteFeltFrame.FACES, EnumFaces.THREE)
							.withProperty(BlockWhiteFeltFrame.FACING, EnumFacing.SOUTH)
							.withProperty(BlockWhiteFeltFrame.COLOUR, colours));
				}
			}

		public void load(EnumRedColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
			EnumFacing blockState = state.getValue(BlockRedFeltFrame.FACING);
			int index = blockState.getHorizontalIndex() - facing.getHorizontalIndex() - 3;
			if (state.getValue(BlockRedFeltFrame.FACES).equals(EnumFaces.TWO_EQUALS))
				world.setBlockState(pos, state
						.withProperty(BlockRedFeltFrame.FACES, EnumFaces.THREE)
						.withProperty(BlockRedFeltFrame.FACING, facing.getOpposite())
						.withProperty(BlockRedFeltFrame.COLOUR, colours));
			if (state.getValue(BlockRedFeltFrame.FACES).equals(EnumFaces.TWO)) {
				if (index == 2 || index == 0)
					world.setBlockState(pos, state
							.withProperty(BlockRedFeltFrame.FACES, EnumFaces.THREE)
							.withProperty(BlockRedFeltFrame.FACING, EnumFacing.getHorizontal(facing.getHorizontalIndex() + 1))
							.withProperty(BlockRedFeltFrame.COLOUR, colours));
				if (index == 1 || index == -1) {
					if (facing.equals(EnumFacing.WEST))
						world.setBlockState(pos, state
								.withProperty(BlockRedFeltFrame.FACES, EnumFaces.THREE)
								.withProperty(BlockRedFeltFrame.FACING, EnumFacing.WEST)
								.withProperty(BlockRedFeltFrame.COLOUR, colours));
					else
						world.setBlockState(pos, state
								.withProperty(BlockRedFeltFrame.FACING, EnumFacing.getHorizontal(facing.getHorizontalIndex() - 1))
								.withProperty(BlockRedFeltFrame.COLOUR, colours));
				}
				
				if (index == -2)
					world.setBlockState(pos, state
							.withProperty(BlockRedFeltFrame.FACES, EnumFaces.THREE)
							.withProperty(BlockRedFeltFrame.FACING, EnumFacing.SOUTH)
							.withProperty(BlockRedFeltFrame.COLOUR, colours));
			}
		}
	}
	
	public static class ThreeLoader implements EditableLoader {
		public void load(EnumWhiteColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
			int blockState = state.getValue(BlockWhiteFeltFrame.FACING).getHorizontalIndex();
			if (blockState == facing.getHorizontalIndex() - 2 || blockState == facing.getHorizontalIndex() + 2)
				world.setBlockState(pos, state
						.withProperty(BlockWhiteFeltFrame.FACES, EnumFaces.COMPLETE)
						.withProperty(BlockWhiteFeltFrame.COLOUR, colours));
		}

		public void load(EnumRedColours colours, EnumFacing facing, World world, BlockPos pos, IBlockState state) {
			int blockState = state.getValue(BlockRedFeltFrame.FACING).getHorizontalIndex();
			if (blockState == facing.getHorizontalIndex() - 2 || blockState == facing.getHorizontalIndex() + 2)
				world.setBlockState(pos, state
						.withProperty(BlockRedFeltFrame.FACES, EnumFaces.COMPLETE)
						.withProperty(BlockRedFeltFrame.COLOUR, colours));
		}
	}
}
