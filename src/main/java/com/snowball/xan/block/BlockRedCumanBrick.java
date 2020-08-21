package com.snowball.xan.block;

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
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

import com.snowball.xan.XanObjects;

@XanObjects.ModElement.Tag
public class BlockRedCumanBrick extends XanObjects.ModElement {
	
	@GameRegistry.ObjectHolder("xan:red_cumanbrick")
	public static final Block block = null;
	public BlockRedCumanBrick(XanObjects instance) {
		super(instance, 6);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("red_cumanbrick"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:red_cumanbrick", "inventory"));
	}
	
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.ROCK);
			setUnlocalizedName("red_cumanbrick");
			setSoundType(SoundType.STONE);
			setHardness(2F);
			setResistance(9F);
			setLightLevel(0F);
			setLightOpacity(255);
			setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		}
	}
}