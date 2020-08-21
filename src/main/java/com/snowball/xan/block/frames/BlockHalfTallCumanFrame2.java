package com.snowball.xan.block.frames;

import com.snowball.xan.XanObjects;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@XanObjects.ModElement.Tag
public class BlockHalfTallCumanFrame2 extends XanObjects.ModElement {
	
	@GameRegistry.ObjectHolder("xan:half_tall_cuman_brick_frame2")
	public static final Block block = null;
	public BlockHalfTallCumanFrame2(XanObjects instance) {
		super(instance, 17);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("half_tall_cuman_brick_frame2"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:half_tall_cuman_brick_frame2", "inventory"));
	}
	
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.ROCK);
			setUnlocalizedName("half_tall_cuman_brick_frame2");
			setSoundType(SoundType.STONE);
			setHardness(1.25F);
			setResistance(9F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
			this.useNeighborBrightness = true;
		}
		
		@Override
		public boolean isFullCube(IBlockState state) {
			return false;}
		
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
		public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
			super.onBlockAdded(world, pos, state);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if ((world.isAirBlock(new BlockPos(x, (y + 1), z)))) {
				world.setBlockState(new BlockPos(x, (y + 1), z), BlockUpperTallCumanFrame2.block.getDefaultState(), 3);
			} else {
				world.getBlockState(new BlockPos(x, y, z)).getBlock().dropBlockAsItem(world, new BlockPos(x, y, z),
						world.getBlockState(new BlockPos(x, y, z)), 1);
				world.setBlockToAir(new BlockPos(x, y, z));
			}
		}

		@Override
		public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer entity, boolean willHarvest) {
			boolean retval = super.removedByPlayer(state, world, pos, entity, willHarvest);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if (((world.getBlockState(new BlockPos(x, (y + 1), z))).getBlock() == BlockUpperTallCumanFrame2.block.getDefaultState().getBlock())) {
				world.setBlockToAir(new BlockPos(x, (y + 1), z));
				}
			return retval;
		}
		
		@Deprecated
		protected static boolean hasRoomForPlayer(World worldIn, BlockPos pos)
	    {
	        return worldIn.getBlockState(pos.down()).isTopSolid() && !worldIn.getBlockState(pos).getMaterial().isSolid() && !worldIn.getBlockState(pos.up()).getMaterial().isSolid();
	    }
	}
}
