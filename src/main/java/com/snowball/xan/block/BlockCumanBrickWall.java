package com.snowball.xan.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;

import com.snowball.xan.XanObjects;

@XanObjects.ModElement.Tag
public class BlockCumanBrickWall extends XanObjects.ModElement {
	
	@GameRegistry.ObjectHolder("xan:cumanbrick_wall")
	public static final Block block = null;
	public BlockCumanBrickWall(XanObjects instance) {
		super(instance, 19);
	}
	
	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("cumanbrick_wall"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:cumanbrick_wall", "inventory"));
	 }
	
	public static class BlockCustom extends BlockWall {
		public BlockCustom() {
			super(new Block(Material.ROCK));
			setSoundType(SoundType.STONE);
			setLightOpacity(0);
			setHardness(1.5F);
			setResistance(10F);
			setLightLevel(0);
			setUnlocalizedName("cumanbrick_wall");
			setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
			}
		
		@Override
		public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
			items.add(new ItemStack(this));
		}
		
		@SideOnly(Side.CLIENT)
		@Override
		public BlockRenderLayer getBlockLayer() {
			return BlockRenderLayer.CUTOUT;
		}
	
		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}
	}
}
