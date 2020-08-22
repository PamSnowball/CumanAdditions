package com.snowball.xan.block.rugs;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

import com.snowball.xan.XanObjects;

@XanObjects.ModElement.Tag
public class BlockRugs extends XanObjects.ModElement {
	
	@GameRegistry.ObjectHolder("xan:rugs")
	public static final Block block = null;
	public BlockRugs(XanObjects instance) {
		super(instance, 19);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("rugs"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("xan:rugs", "inventory"));
	}
	
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.ROCK);
			setUnlocalizedName("rugs");
			setSoundType(SoundType.STONE);
			setHardness(2F);
			setResistance(9F);
			setLightLevel(0F);
			setLightOpacity(255);
			setCreativeTab(CreativeTabs.DECORATIONS);
		}
		
		//This giant If is a spot getter, so the rugs can choose a random placement.
		
		@Override
		public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
			super.onBlockAdded(world, pos, state);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			boolean X1 = false;
			boolean Z1 = false;
			boolean X0 = false;
			boolean Z0 = false;
			boolean X1Z1 = false;
			boolean X0Z1 = false;
			boolean X1Z0 = false;
			boolean X0Z0 = false;
			boolean X00Z0 = false;
			boolean X00Z1 = false;
			boolean X0Z00 = false;
			boolean X1Z00 = false;
			boolean X00 = false;
			boolean Z00 = false;
			boolean X2 = false;
			boolean Z2 = false;
			boolean X0Z2 = false;
			boolean X1Z2 = false;
			boolean X2Z0 = false;
			boolean X2Z1 = false;
			boolean Layer0 = false;
			boolean Layer1 = false;
			boolean Layer2 = false;
			boolean Layer3 = false;
			if (((!(world.isAirBlock(new BlockPos(x, (y - 1), (z + 1)))))
					&& (world.isAirBlock(new BlockPos(x, y, (z + 1)))))) {
				Z1 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos(x, (y - 1), (z - 1)))))
					&& (world.isAirBlock(new BlockPos(x, y, (z - 1)))))) {
				Z0 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x - 1), (y - 1), z))))
					&& (world.isAirBlock(new BlockPos((x - 1), y, z))))) {
				X0 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x + 1), (y - 1), z))))
					&& (world.isAirBlock(new BlockPos((x + 1), y, z))))) {
				X1 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x + 1), (y - 1), (z - 1)))))
					&& (world.isAirBlock(new BlockPos((x + 1), y, (z - 1)))))) {
				X1Z0 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x + 1), (y - 1), (z + 1)))))
					&& (world.isAirBlock(new BlockPos((x + 1), y, (z + 1)))))) {
				X1Z1 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x - 1), (y - 1), (z + 1)))))
					&& (world.isAirBlock(new BlockPos((x - 1), y, (z + 1)))))) {
				X0Z1 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x - 1), (y - 1), (z - 1)))))
					&& (world.isAirBlock(new BlockPos((x - 1), y, (z - 1)))))) {
				X0Z0 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x - 2), (y - 1), (z - 1)))))
					&& (world.isAirBlock(new BlockPos((x - 2), y, (z - 1)))))) {
				X00Z0 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x - 2), (y - 1), (z + 1)))))
					&& (world.isAirBlock(new BlockPos((x - 2), y, (z + 1)))))) {
				X00Z1 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x - 1), (y - 1), (z - 2)))))
					&& (world.isAirBlock(new BlockPos((x - 1), y, (z - 2)))))) {
				X0Z00 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x + 1), (y - 1), (z - 2)))))
					&& (world.isAirBlock(new BlockPos((x + 1), y, (z - 2)))))) {
				X1Z00 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x - 2), (y - 1), z))))
					&& (world.isAirBlock(new BlockPos((x - 2), y, z))))) {
				X00 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos(x, (y - 1), (z - 2)))))
					&& (world.isAirBlock(new BlockPos(x, y, (z - 2)))))) {
				Z00 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x + 2), (y - 1), z))))
					&& (world.isAirBlock(new BlockPos((x + 2), y, z))))) {
				X2 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos(x, (y - 1), (z + 2)))))
					&& (world.isAirBlock(new BlockPos(x, y, (z + 2)))))) {
				Z2 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x - 1), (y - 1), (z + 2)))))
					&& (world.isAirBlock(new BlockPos((x - 1), y, (z + 2)))))) {
				X1Z2 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x + 1), (y - 1), (z + 2)))))
					&& (world.isAirBlock(new BlockPos((x + 1), y, (z + 2)))))) {
				X0Z2 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x + 2), (y - 1), (z - 1)))))
					&& (world.isAirBlock(new BlockPos((x + 2), y, (z - 2)))))) {
				X2Z0 = (boolean) (true);
			}
			if (((!(world.isAirBlock(new BlockPos((x - 1), (y - 1), (z + 1)))))
					&& (world.isAirBlock(new BlockPos((x - 1), y, (z + 2)))))) {
				X2Z1 = (boolean) (true);
			}
			if (((((((X1) == (true)) && ((Z1) == (true))) && ((X1Z1) == (true))) || ((((X1) == (true)) && ((Z0) == (true))) && ((X1Z0) == (true))))
					|| (((((X0) == (true)) && ((Z1) == (true))) && ((X0Z1) == (true)))
							|| ((((X0) == (true)) && ((Z0) == (true))) && ((X0Z0) == (true)))))) {
				Layer1 = (boolean) (true);
			}
			if (((((X1) == (true)) || ((Z1) == (true))) || (((X0) == (true)) || ((Z0) == (true))))) {
				Layer0 = (boolean) (true);
			}
			if ((((((X1Z1) == (true)) && ((((Z1) == (true)) && ((X1) == (true))) && (((X1Z2) == (true)) && ((Z2) == (true)))))
					|| (((X1Z0) == (true)) && ((((X1) == (true)) && ((Z0) == (true))) && (((X2) == (true)) && ((X2Z0) == (true))))))
					|| ((((X0Z0) == (true)) && ((((X0) == (true)) && ((Z0) == (true))) && (((X0Z00) == (true)) && ((Z00) == (true)))))
							|| (((X0Z1) == (true)) && ((((Z1) == (true)) && ((X0) == (true))) && (((X00) == (true)) && ((X00Z1) == (true)))))))) {
				Layer2 = (boolean) (true);
			}
			if ((((((X1Z1) == (true)) && ((((X1) == (true)) && ((Z1) == (true))) && (((X2Z1) == (true)) && ((X2) == (true)))))
					|| (((X0Z1) == (true)) && ((((Z1) == (true)) && ((X0) == (true))) && (((X0Z2) == (true)) && ((Z2) == (true))))))
					|| ((((X0Z0) == (true)) && ((((X0) == (true)) && ((Z0) == (true))) && (((X00Z0) == (true)) && ((X00) == (true)))))
							|| (((X1Z0) == (true)) && ((((X1) == (true)) && ((Z0) == (true))) && (((X1Z00) == (true)) && ((Z00) == (true)))))))) {
				Layer3 = (boolean) (true);
			}
			if (((((Layer0) == (true)) && ((Layer3) == (false))) && (((Layer1) == (false)) && ((Layer2) == (false))))) {
				world.setBlockState(new BlockPos(x, y, z), BlockWideRugs.block.getDefaultState(), 3);
			}
			if (((((Layer0) == (true)) && ((Layer3) == (false))) && (((Layer1) == (true)) && ((Layer2) == (false))))) {
				world.setBlockState(new BlockPos(x, y, z), BlockBigRugs.block.getDefaultState(), 3);
			}
			if (((((Layer3) == (true)) && ((Layer2) == (false))) && (((Layer1) == (true)) && ((Layer0) == (true))))) {
				world.setBlockState(new BlockPos(x, y, z), BlockGiantRugs0.block.getDefaultState(), 3);
			}
			if (((((Layer3) == (false)) && ((Layer2) == (true))) && (((Layer1) == (true)) && ((Layer0) == (true))))) {
				world.setBlockState(new BlockPos(x, y, z), BlockGiantRugs1.block.getDefaultState(), 3);
			}
			if (((((Layer3) == (false)) && ((Layer2) == (false))) && (((Layer1) == (false)) && ((Layer0) == (false))))) {
				if ((Math.random() < 0.5)) {
					world.setBlockState(new BlockPos(x, y, z), BlockBlueMiniRugs.block.getDefaultState(), 3);
				} else {
					world.setBlockState(new BlockPos(x, y, z), BlockRedMiniRugs.block.getDefaultState(), 3);
				}
			}
		}
	}
}