package com.snowball.xan.block;

import com.snowball.xan.Xan;
import com.snowball.xan.init.ModBlocks;
import com.snowball.xan.init.ModItems;
import com.snowball.xan.util.enums.EnumFaces;
import com.snowball.xan.util.enums.EnumWhiteColours;
import com.snowball.xan.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWhiteFeltFrame extends Block implements IHasModel {
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	public static final PropertyEnum<EnumFaces> FACES = PropertyEnum.<EnumFaces>create("face", EnumFaces.class);
	public static final PropertyEnum<EnumWhiteColours> COLOUR = PropertyEnum.<EnumWhiteColours>create("colour", EnumWhiteColours.class);
	
	public BlockWhiteFeltFrame(String name) {
		super(Material.CLOTH);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(Xan.XAN_TAB);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(FACES, EnumFaces.BASE).withProperty(COLOUR, EnumWhiteColours.BLUE));
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) { 
		if (world.isRemote)
			return true;
		else {
			EnumFacing playerFacing = player.getHorizontalFacing();
			EnumHand enumHand = player.getActiveHand();
			
			ItemStack stack = player.getHeldItem(enumHand);
			Item item = stack.getItem();
			
			if (EnumWhiteColours.map.containsKey(item)) {
				EnumFaces faces = state.getValue(FACES);
				EnumWhiteColours colours = EnumWhiteColours.valueOf(item);
				
				IBlockState blockState = (IBlockState) ModBlocks.WHITE_FELT_FRAME;
				faces.loadFaces(colours, playerFacing, world, pos, blockState);

                if (!player.capabilities.isCreativeMode) 	
                	stack.shrink(1);
			}
			
			return true;
		}
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)  {
		if (!world.isRemote) {
            IBlockState north = world.getBlockState(pos.north());
            IBlockState south = world.getBlockState(pos.south());
            IBlockState west = world.getBlockState(pos.west());
            IBlockState east = world.getBlockState(pos.east());
            EnumFacing face = state.getValue(FACING);

            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
            world.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)  {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(FACES, EnumFaces.BASE).withProperty(COLOUR, EnumWhiteColours.BLUE);
	}
	
	@Override    
	public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | ((EnumFaces)state.getValue(FACES)).getId();
        i = i | ((EnumWhiteColours)state.getValue(COLOUR)).getId() << 2;
        i = i | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex() << 3;
        return i;
    }

	@Override    
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACES, EnumFaces.fromId(meta & 5)).withProperty(FACING, EnumFacing.getHorizontal((meta & 15) >> 2)).withProperty(COLOUR, EnumWhiteColours.fromId((meta & 20) >> 3));
    }
	
	@Override    
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING, FACES, COLOUR});
    }

	@Override
	public void registerModels() {
		Xan.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
	}
}
