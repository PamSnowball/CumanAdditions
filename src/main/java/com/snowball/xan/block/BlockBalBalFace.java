package com.snowball.xan.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.Rotation;
import net.minecraft.util.Mirror;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.Block;

import com.snowball.xan.XanObjects;

import java.util.Random;

@XanObjects.ModElement.Tag
public class BlockBalBalFace extends XanObjects.ModElement {
	@GameRegistry.ObjectHolder("xan:balbal_face")
	public static final Block block = null;
	public BlockBalBalFace(XanObjects instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("balbal_face"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:balbal_face", "inventory"));
	}
	public static class BlockCustom extends Block {
		public static final PropertyDirection FACING = BlockHorizontal.FACING;
		public BlockCustom() {
			super(Material.ROCK);
			setUnlocalizedName("balbal_face");
			setSoundType(SoundType.STONE);
			setHardness(3F);
			setResistance(10F);
			setLightLevel(0F);
			setLightOpacity(0);
			this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		}

		@SideOnly(Side.CLIENT)
		@Override
		public BlockRenderLayer getBlockLayer() {
			return BlockRenderLayer.CUTOUT;
		}

		@Override
		public boolean isFullCube(IBlockState state) {
			return false;
		}

		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
			switch (state.getValue(BlockHorizontal.FACING)) {
				case UP :
				case DOWN :
				case SOUTH :
					return new AxisAlignedBB(0.031D, 0D, 0.219D, 0.969D, 0.815D, 0.687D);
				case NORTH :
					return new AxisAlignedBB(0.031D, 0D, 0.31D, 0.969D, 0.812D, 0.781D);
				case WEST :
					return new AxisAlignedBB(0.31D, 0D, 0.031D, 0.781D, 0.812D, 0.969D);
				default :
				case EAST :
					return new AxisAlignedBB(0.219D, 0D, 0.031D, 0.687D, 0.812D, 0.969D);
			}
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
		public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
			return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
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
		public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
				EntityLivingBase placer) {
			return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}

		@Override
		public EnumPushReaction getMobilityFlag(IBlockState state) {
			return EnumPushReaction.IGNORE;
		}

		@Override
		public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
			return false;
		}

		@Override
		public int quantityDropped(Random random) {
			return 0;
		}

		@Override
		public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer entity, boolean willHarvest) {
			boolean retval = super.removedByPlayer(state, world, pos, entity, willHarvest);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				if (((world.getBlockState(new BlockPos(x, (y - 1), z))).getBlock() == BlockBalBal.block.getDefaultState().getBlock())) {
					world.setBlockToAir(new BlockPos(x, (y - 1), z));
				}
			}
			return retval;
		}
	}
}