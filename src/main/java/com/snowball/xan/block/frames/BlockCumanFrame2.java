package com.snowball.xan.block.frames;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

import com.snowball.xan.XanObjects;

@XanObjects.ModElement.Tag
public class BlockCumanFrame2 extends XanObjects.ModElement {
	
	@GameRegistry.ObjectHolder("xan:cumanframe2")
	public static final Block block = null;
	public BlockCumanFrame2(XanObjects instance) {
		super(instance, 14);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("cumanframe2"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:cumanframe2", "inventory"));
	}
	
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.ROCK);
			setUnlocalizedName("cumanframe2");
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
			return false;
		}
		
		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}
	}
}