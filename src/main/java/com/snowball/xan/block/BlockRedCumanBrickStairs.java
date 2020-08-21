package com.snowball.xan.block;

import com.snowball.xan.XanObjects;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@XanObjects.ModElement.Tag
public class BlockRedCumanBrickStairs extends XanObjects.ModElement {
	@GameRegistry.ObjectHolder("xan:red_cuman_brick_stairs")
	public static final Block block = null;
	public BlockRedCumanBrickStairs(XanObjects instance) {
		super(instance, 3);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("red_cuman_brick_stairs"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:red_cuman_brick_stairs", "inventory"));
	}
	public static class BlockCustom extends BlockStairs {
		public BlockCustom() {
			super(new Block(Material.ROCK).getDefaultState());
			setUnlocalizedName("red_cuman_brick_stairs");
			setSoundType(SoundType.GROUND);
			setHardness(1F);
			setResistance(10F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}
	}
}
