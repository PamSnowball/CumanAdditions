package com.snowball.xan.block.rugs;

import com.snowball.xan.XanObjects;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@XanObjects.ModElement.Tag
public class BlockBlueMiniRugs extends XanObjects.ModElement {
	
	@GameRegistry.ObjectHolder("xan:blueminirugs")
	public static final Block block = null;
	public BlockBlueMiniRugs(XanObjects instance) {
		super(instance, 20);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("blueminirugs"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:blueminirugs", "inventory"));
	}
	
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.CLOTH);
			setUnlocalizedName("blueminirugs");
			setSoundType(SoundType.CLOTH);
			setHardness(2F);
			setResistance(9F);
			setLightLevel(0F);
			setLightOpacity(255);
			this.useNeighborBrightness = true;
		}

		@Override
		public boolean isFullCube(IBlockState state) {
			return false;
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(new ItemStack(BlockRugs.block, (1)));
		}

		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
			return new AxisAlignedBB(0D, 0D, 0D, 1D, 0.062D, 1D);
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}
	}
}