package com.snowball.xan.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;

import com.snowball.xan.Xan;
import com.snowball.xan.init.ModBlocks;
import com.snowball.xan.init.ModItems;
import com.snowball.xan.util.interfaces.IHasModel;

public class BlockTimberWall extends Block implements IHasModel {
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	public BlockTimberWall(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Xan.XAN_TAB);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
		
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	protected net.minecraft.block.state.BlockStateContainer createBlockState() {
		return new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{FACING});
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	    
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(BlockDirectional.FACING)) {
		case DOWN :
			return new AxisAlignedBB(0D, 0.812D, 0D, 1D, 1D, 1D);
		case SOUTH :
			return new AxisAlignedBB(0D, 0D, 0D, 1D, 0.188, 1D);
		case WEST :
			return new AxisAlignedBB(0.812D, 0D, 0D, 1D, 1D, 1D);
		case EAST :
			return new AxisAlignedBB(0D, 0D, 0D, 0.188, 1D, 1D);
		case UP :
			return new AxisAlignedBB(0D, 0D, 0D, 1D, 0.188D, 1D);
		default :
		case NORTH :
			return new AxisAlignedBB(0D, 0D, 0.812, 1D, 1D, 1D);
		}
	}	

	@Override
	public void registerModels() {
		Xan.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
	}
}
