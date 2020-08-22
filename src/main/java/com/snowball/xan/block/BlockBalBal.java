package com.snowball.xan.block;

import javax.annotation.Nullable;

import com.snowball.xan.XanObjects;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@XanObjects.ModElement.Tag
public class BlockBalBal extends XanObjects.ModElement {
	
	@GameRegistry.ObjectHolder("xan:balbal_body")
	public static final Block block = null;
	public BlockBalBal(XanObjects instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("balbal_body"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:balbal", "inventory"));
	}
	
	public static class BlockCustom extends Block {
		public static final PropertyDirection FACING = BlockHorizontal.FACING;
		public BlockCustom() {
			super(Material.ROCK);
			setUnlocalizedName("balbal_body");
			setSoundType(SoundType.STONE);
			setHardness(3F);
			setResistance(10F);
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

		//The BalBals are made of two blocks, BalBal and BalBalFace, this If is for the placement, BalBal puts BalBalFace above itself.
		
		@Override
		public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
			super.onBlockAdded(world, pos, state);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
				if ((world.isAirBlock(new BlockPos(x, (y + 1), z)))) {
					world.setBlockState(new BlockPos(x, (y + 1), z), BlockBalBalFace.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, (y + 1), z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, (y + 1), z), _bs.withProperty((PropertyDirection) prop, (new Object() {
									public EnumFacing getEnumFacing(BlockPos pos) {
										try {
											IBlockState _bs = world.getBlockState(pos);
											for (IProperty<?> prop : _bs.getProperties().keySet()) {
												if (prop.getName().equals("facing"))
													return _bs.getValue((PropertyDirection) prop);
											}
											return EnumFacing.NORTH;
										} catch (Exception e) {
											return EnumFacing.NORTH;
										  }
									}
								}.getEnumFacing(new BlockPos(x, y, z)))), 3);
								break;
							}
						}
					} catch (Exception e) {
					  }
				} else {
					world.getBlockState(new BlockPos(x, y, z)).getBlock().dropBlockAsItem(world, new BlockPos(x, y, z),
							world.getBlockState(new BlockPos(x, y, z)), 1);
					world.setBlockToAir(new BlockPos(x, y, z));
				  }
		}

		//The BalBals are made of two blocks, BalBal and BalBalFace, this If is for the breaking, BalBal breaks BalBalFace when it is broke by a player and viceversa.
		
		@Override
		public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer entity, boolean willHarvest) {
			boolean retval = super.removedByPlayer(state, world, pos, entity, willHarvest);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if (((world.getBlockState(new BlockPos(x, (y + 1), z))).getBlock() == BlockBalBalFace.block.getDefaultState().getBlock())) {
					world.setBlockToAir(new BlockPos(x, (y + 1), z));
			}
			return retval;
		}
		
		@Nullable
	    public static BlockPos getSafeExitLocation(World worldIn, BlockPos pos, int tries)
	    {
	        EnumFacing enumfacing = worldIn.getBlockState(pos).getValue(FACING);
	        int i = pos.getX();
	        int j = pos.getY();
	        int k = pos.getZ();

	        for (int l = 0; l <= 1; ++l)
	        {
	            int i1 = i - enumfacing.getFrontOffsetX() * l - 1;
	            int j1 = k - enumfacing.getFrontOffsetZ() * l - 1;
	            int k1 = i1 + 2;
	            int l1 = j1 + 2;

	            for (int i2 = i1; i2 <= k1; ++i2)
	            {
	                for (int j2 = j1; j2 <= l1; ++j2)
	                {
	                    BlockPos blockpos = new BlockPos(i2, j, j2);

	                    if (hasRoomForPlayer(worldIn, blockpos))
	                    {
	                        if (tries <= 0)
	                        {
	                            return blockpos;
	                        }

	                        --tries;
	                    }
	                }
	            }
	        }

	        return null;
	    }
		
		@SuppressWarnings("deprecation")
		protected static boolean hasRoomForPlayer(World worldIn, BlockPos pos)
	    {
	        return worldIn.getBlockState(pos.down()).isTopSolid() && !worldIn.getBlockState(pos).getMaterial().isSolid() && !worldIn.getBlockState(pos.up()).getMaterial().isSolid();
	    }
	}
}