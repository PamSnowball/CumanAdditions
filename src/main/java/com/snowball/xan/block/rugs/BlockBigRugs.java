package com.snowball.xan.block.rugs;

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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
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
public class BlockBigRugs extends XanObjects.ModElement {
	
	@GameRegistry.ObjectHolder("xan:bigrugs")
	public static final Block block = null;
	public BlockBigRugs(XanObjects instance) {
		super(instance, 20);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("bigrugs"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:bigrugs", "inventory"));
	}
	
	public static class BlockCustom extends Block {
		public static final PropertyDirection FACING = BlockHorizontal.FACING;
		public BlockCustom() {
			super(Material.CLOTH);
			setUnlocalizedName("bigrugs");
			setSoundType(SoundType.CLOTH);
			setHardness(2F);
			setResistance(9F);
			setLightLevel(0F);
			setLightOpacity(255);
			this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
			this.useNeighborBrightness = true;
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
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(new ItemStack(BlockRugs.block, (int) (0)));
		}
	
		//This giant If is a spot getter, so the rugs can choose a random placement.
		
		@Override
		public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
			super.onBlockAdded(world, pos, state);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			boolean Spot1 = false;
			boolean Spot2 = false;
			boolean Spot3 = false;
			boolean Spot4 = false;
			double Variable1 = 0;
			if (((((!(world.isAirBlock(new BlockPos(x, (y - 1), (z - 1)))))
					&& (world.isAirBlock(new BlockPos(x, y, (z - 1)))))
					&& ((!(world.isAirBlock(new BlockPos((x + 1), (y - 1), (z - 1)))))
							&& (world.isAirBlock(new BlockPos((x + 1), y, (z - 1))))))
					&& ((!(world.isAirBlock(new BlockPos((x + 1), (y - 1), z))))
							&& (world.isAirBlock(new BlockPos((x + 1), y, z)))))) {
				Spot1 = (boolean) (true);
			}
			if (((((!(world.isAirBlock(new BlockPos(x, (y - 1), (z + 1)))))
					&& (world.isAirBlock(new BlockPos(x, y, (z + 1)))))
					&& ((!(world.isAirBlock(new BlockPos((x + 1), (y - 1), (z + 1)))))
							&& (world.isAirBlock(new BlockPos((x + 1), y, (z + 1))))))
					&& ((!(world.isAirBlock(new BlockPos((x + 1), (y - 1), z))))
							&& (world.isAirBlock(new BlockPos((x + 1), y, z)))))) {
				Spot2 = (boolean) (true);
			}
			if (((((!(world.isAirBlock(new BlockPos(x, (y - 1), (z + 1)))))
					&& (world.isAirBlock(new BlockPos(x, y, (z + 1)))))
					&& ((!(world.isAirBlock(new BlockPos((x - 1), (y - 1), (z + 1)))))
							&& (world.isAirBlock(new BlockPos((x - 1), y, (z + 1))))))
					&& ((!(world.isAirBlock(new BlockPos((x - 1), (y - 1), z))))
							&& (world.isAirBlock(new BlockPos((x - 1), y, z)))))) {
				Spot3 = (boolean) (true);
			}
			if (((((!(world.isAirBlock(new BlockPos(x, (y - 1), (z - 1)))))
					&& (world.isAirBlock(new BlockPos(x, y, (z - 1)))))
					&& ((!(world.isAirBlock(new BlockPos((x - 1), (y - 1), (z - 1)))))
							&& (world.isAirBlock(new BlockPos((x - 1), y, (z - 1))))))
					&& ((!(world.isAirBlock(new BlockPos((x - 1), (y - 1), z))))
							&& (world.isAirBlock(new BlockPos((x - 1), y, z)))))) {
				Spot4 = (boolean) (true);
			}
			if (((((Spot4) == (false)) && ((Spot1) == (true))) && (((Spot2) == (false)) && ((Spot3) == (false))))) {
				try {
					IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos(x, y, (z - 1)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos((x + 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z - 1)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((x + 1), y, (z - 1)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((x + 1), y, z),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			if (((((Spot4) == (false)) && ((Spot1) == (false))) && (((Spot2) == (true)) && ((Spot3) == (false))))) {
				try {
					IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos(x, y, (z + 1)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos((x + 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z + 1)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((x + 1), y, (z + 1)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((x + 1), y, z),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			if (((((Spot4) == (false)) && ((Spot1) == (false))) && (((Spot2) == (false)) && ((Spot3) == (true))))) {
				try {
					IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos(x, y, (z + 1)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos((x - 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z + 1)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((x - 1), y, (z + 1)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((x - 1), y, z),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			if (((((Spot4) == (true)) && ((Spot1) == (false))) && (((Spot2) == (false)) && ((Spot3) == (false))))) {
				try {
					IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos(x, y, (z - 1)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos((x - 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z - 1)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((x - 1), y, (z - 1)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
				world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((x - 1), y, z),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			if (((((Spot4) == (false)) && ((Spot1) == (true))) && (((Spot2) == (true)) && ((Spot3) == (false))))) {
				if ((Math.random() < 0.5)) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			if (((((Spot4) == (false)) && ((Spot1) == (true))) && (((Spot2) == (false)) && ((Spot3) == (true))))) {
				if ((Math.random() < 0.5)) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			if (((((Spot4) == (true)) && ((Spot1) == (true))) && (((Spot2) == (false)) && ((Spot3) == (false))))) {
				if ((Math.random() < 0.5)) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				} else {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.EAST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			if (((((Spot4) == (true)) && ((Spot1) == (false))) && (((Spot2) == (true)) && ((Spot3) == (false))))) {
				if ((Math.random() < 0.5)) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.WEST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				} else {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.EAST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			if (((((Spot4) == (true)) && ((Spot1) == (false))) && (((Spot2) == (false)) && ((Spot3) == (true))))) {
				if ((Math.random() < 0.5)) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				} else {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.EAST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			if (((((Spot4) == (false)) && ((Spot1) == (false))) && (((Spot2) == (true)) && ((Spot3) == (true))))) {
				if ((Math.random() < 0.5)) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				} else {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.EAST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			Variable1 = (double) Math.random();
			if (((((Spot4) == (false)) && ((Spot1) == (true))) && (((Spot2) == (true)) && ((Spot3) == (true))))) {
				if ((((Variable1) < (2 / 3)) && ((Variable1) > (1 / 3)))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
				if (((Variable1) < (1 / 3))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.EAST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
				if (((Variable1) > (2 / 3))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			if (((((Spot4) == (true)) && ((Spot1) == (true))) && (((Spot2) == (true)) && ((Spot3) == (false))))) {
				if ((((Variable1) < (2 / 3)) && ((Variable1) > (1 / 3)))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
				if (((Variable1) < (1 / 3))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.EAST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
				if (((Variable1) > (2 / 3))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			if (((((Spot4) == (true)) && ((Spot1) == (true))) && (((Spot2) == (false)) && ((Spot3) == (true))))) {
				if ((((Variable1) < (2 / 3)) && ((Variable1) > (1 / 3)))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
				if (((Variable1) < (1 / 3))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.EAST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
				if (((Variable1) > (2 / 3))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			if (((((Spot4) == (true)) && ((Spot1) == (false))) && (((Spot2) == (true)) && ((Spot3) == (true))))) {
				if ((((Variable1) < (2 / 3)) && ((Variable1) > (1 / 3)))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
				if (((Variable1) < (1 / 3))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.EAST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
				if (((Variable1) > (2 / 3))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.WEST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
			if (((((Spot4) == (true)) && ((Spot1) == (true))) && (((Spot2) == (true)) && ((Spot3) == (true))))) {
				if ((((Variable1) < (2 / 4)) && ((Variable1) > (1 / 4)))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
				if (((Variable1) < (1 / 4))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.EAST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x - 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x - 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x - 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
				if (((Variable1) > (3 / 4))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.WEST),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, (z + 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z + 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, (z + 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
				if ((((Variable1) < (3 / 4)) && ((Variable1) > (2 / 4)))) {
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, z), _bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH),
										3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos(x, y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos(x, y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos(x, y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, (z - 1)), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, (z - 1)));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, (z - 1)),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
					world.setBlockState(new BlockPos((x + 1), y, z), BlockBigDummyRugs.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((x + 1), y, z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((x + 1), y, z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
		}
	
		@Override
		public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer entity, boolean willHarvest) {
			boolean retval = super.removedByPlayer(state, world, pos, entity, willHarvest);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if (((new Object() {
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
			}.getEnumFacing(new BlockPos(x, y, z))) == EnumFacing.NORTH)) {
				world.setBlockToAir(new BlockPos((x - 1), y, (z - 1)));
				world.setBlockToAir(new BlockPos(x, y, (z - 1)));
				world.setBlockToAir(new BlockPos((x - 1), y, z));
			}
			if (((new Object() {
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
			}.getEnumFacing(new BlockPos(x, y, z))) == EnumFacing.SOUTH)) {
				world.setBlockToAir(new BlockPos((x + 1), y, (z + 1)));
				world.setBlockToAir(new BlockPos(x, y, (z + 1)));
				world.setBlockToAir(new BlockPos((x + 1), y, z));
			}
			if (((new Object() {
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
			}.getEnumFacing(new BlockPos(x, y, z))) == EnumFacing.WEST)) {
				world.setBlockToAir(new BlockPos((x - 1), y, (z + 1)));
				world.setBlockToAir(new BlockPos(x, y, (z + 1)));
				world.setBlockToAir(new BlockPos((x - 1), y, z));
			}
			if (((new Object() {
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
			}.getEnumFacing(new BlockPos(x, y, z))) == EnumFacing.EAST)) {
				world.setBlockToAir(new BlockPos((x + 1), y, (z - 1)));
				world.setBlockToAir(new BlockPos(x, y, (z - 1)));
				world.setBlockToAir(new BlockPos((x + 1), y, z));
			}
			if ((!((entity instanceof EntityPlayer) ? (entity).capabilities.isCreativeMode : false)) && !world.isRemote) {
				EntityItem entityToSpawn = new EntityItem(world, x, y, z, new ItemStack(BlockRugs.block, (1)));
				entityToSpawn.setPickupDelay(10);
				world.spawnEntity(entityToSpawn);
			} return retval;
		}
	}
}