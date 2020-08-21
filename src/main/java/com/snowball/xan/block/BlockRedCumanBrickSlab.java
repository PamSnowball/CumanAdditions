package com.snowball.xan.block;

import java.util.Random;

import com.snowball.xan.XanObjects;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@XanObjects.ModElement.Tag
public class BlockRedCumanBrickSlab extends XanObjects.ModElement {
	@GameRegistry.ObjectHolder("xan:red_cuman_brick_double_slab")
	public static final Block block_slab_double = null;
	@GameRegistry.ObjectHolder("xan:red_cuman_brick_slab")
	public static final Block block = null;
	
	public BlockRedCumanBrickSlab(XanObjects instance) {
		super(instance, 5);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("red_cuman_brick_slab"));
		elements.blocks.add(() -> new BlockCustom.Double().setRegistryName("red_cuman_brick_double_slab"));
		elements.items.add(() -> new ItemSlab(block, (BlockSlab) block, (BlockSlab) block_slab_double).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:red_cuman_brick_slab", "inventory"));
	}

	public static class BlockCustom extends BlockSlab {
		public BlockCustom() {
			super(Material.ROCK);
			setUnlocalizedName("red_cuman_brick_slab");
			setSoundType(SoundType.STONE);
			setHardness(2F);
			setResistance(9F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
			IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, BlockCustom.Variant.DEFAULT);
			if (!this.isDouble())
				state = state.withProperty(BlockSlab.HALF, EnumBlockHalf.BOTTOM);
			this.setDefaultState(state);
			this.useNeighborBrightness = !this.isDouble();
		}

		public static final PropertyEnum<BlockCustom.Variant> VARIANT = PropertyEnum.<BlockCustom.Variant>create("variant", BlockCustom.Variant.class);
		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune) {
			return Item.getItemFromBlock(block);
		}

		@Override
		public IProperty<?> getVariantProperty() {
			return VARIANT;
		}

		@Override
		public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
			return new ItemStack(block);
		}

		@Override
		public Comparable<?> getTypeForItem(ItemStack stack) {
			return BlockCustom.Variant.DEFAULT;
		}

		@Override
		public IBlockState getStateFromMeta(int meta) {
			if (this.isDouble()) {
				return this.getDefaultState();
			} else {
				return this.getDefaultState().withProperty(HALF, BlockSlab.EnumBlockHalf.values()[meta % 2]);
			}
		}

		@Override
		public int getMetaFromState(IBlockState state) {
			if (this.isDouble()) {
				return 0;
			} else {
				return state.getValue(HALF).ordinal();
			}
		}

		@Override
		protected net.minecraft.block.state.BlockStateContainer createBlockState() {
			return this.isDouble() 
					? new net.minecraft.block.state.BlockStateContainer(this, new IProperty[] { VARIANT })
					: new net.minecraft.block.state.BlockStateContainer(this, new IProperty[] { HALF, VARIANT });
		}

		public enum Variant implements IStringSerializable {
			DEFAULT;
			public String getName() {
				return "default";
			}
		}

		@Override
		public String getUnlocalizedName(int meta) {
			return super.getUnlocalizedName();
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}

		@Override
		public boolean isDouble() {
			return false;
		}

		@Override
		public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
			if (isDouble())
				return true;
			return super.doesSideBlockRendering(state, world, pos, face);
		}

		public static class Double extends BlockCustom {
			@Override
			public boolean isDouble() {
				return true;
			}
		}

	}
}