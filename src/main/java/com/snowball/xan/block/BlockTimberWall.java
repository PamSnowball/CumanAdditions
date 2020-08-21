package com.snowball.xan.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;

import com.snowball.xan.XanObjects;

@XanObjects.ModElement.Tag
public class BlockTimberWall extends XanObjects.ModElement {
	
	@GameRegistry.ObjectHolder("xan:timber_wall")
	public static final Block block = null;
	public BlockTimberWall(XanObjects instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() { 
		elements.blocks.add(() -> new BlockCustom().setRegistryName("timber_wall"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:timber_wall", "inventory"));
	}
	
	public static class BlockCustom extends Block {
		public static final PropertyDirection FACING = BlockDirectional.FACING;
		public BlockCustom() {
			super(Material.WOOD);
			setUnlocalizedName("timber_wall");
			setSoundType(SoundType.WOOD);
			setHardness(3.5F);
			setResistance(9F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
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
	}
}

