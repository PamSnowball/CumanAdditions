package com.snowball.xan.block;

import java.util.Random;

import com.snowball.xan.Xan;
import com.snowball.xan.init.ModBlocks;
import com.snowball.xan.init.ModItems;
import com.snowball.xan.item.ItemTallBase;
import com.snowball.xan.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTallBase extends Block implements IHasModel {
	public static final PropertyEnum<BlockDoor.EnumDoorHalf> HALF = PropertyEnum.<BlockDoor.EnumDoorHalf>create("half", BlockDoor.EnumDoorHalf.class);
	   
	public BlockTallBase(String name) {
		super(Material.ROCK);
	    this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER));
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Xan.XAN_TAB);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemTallBase(this).setRegistryName(name));
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER) {
            BlockPos posPos = pos.down();
            IBlockState stateState = world.getBlockState(posPos);

            if (stateState.getBlock() != this) {
                world.setBlockToAir(pos);
            }
            
            else if (block != this) {
                stateState.neighborChanged(world, posPos, block, fromPos);
            }
            	
        } else {
        	boolean destroy = false;
        	BlockPos posPOS = pos.up();
        	IBlockState stateSTATE = world.getBlockState(posPOS);

        	if (stateSTATE.getBlock() != this) {
        		world.setBlockToAir(pos);
        		destroy = true;
        	}

        	if (!world.getBlockState(pos.down()).isSideSolid(world,  pos.down(), EnumFacing.UP)) {
        		world.setBlockToAir(pos);
        		destroy = true;
        	
        		if (stateSTATE.getBlock() == this) {
        			world.setBlockToAir(posPOS);
        		}
        	}

        	if (destroy && !world.isRemote) {
        		this.dropBlockAsItem(world, pos, state, 0);
        	}
        }
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : this.getItem();
    }
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        if (pos.getY() >= worldIn.getHeight() - 1) {
            return false;
        } else {
            IBlockState state = worldIn.getBlockState(pos.down());
            return (state.isTopSolid() || state.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID) && super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
        }
    }
	
	@Override
	public EnumPushReaction getMobilityFlag(IBlockState state) {
        return EnumPushReaction.DESTROY;
    }
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this.getItem());
    }
	
	private Item getItem() {
        if (this == ModBlocks.CUMAN_FRAME) {
            return new ItemBlock(ModBlocks.CUMAN_BRICK);
        } else {
            return new ItemBlock(ModBlocks.RED_CUMAN_BRICK);
        }
    }

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        BlockPos posPos = pos.down();
        BlockPos posPOS = pos.up();

        if (player.capabilities.isCreativeMode && state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER && world.getBlockState(posPos).getBlock() == this) {
            world.setBlockToAir(posPos);
        }

        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER && world.getBlockState(posPOS).getBlock() == this) {
            if (player.capabilities.isCreativeMode) {
                world.setBlockToAir(pos);
            }

            world.setBlockToAir(posPOS);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER);
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER) {
        	i = 1;
        }
        return i;
    }
    
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {HALF});
    }
    
    protected static boolean isTop(int meta) {
        return meta != 0;
    }
    
	@Override
	public void registerModels() {
		Xan.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
	}
}
