package com.snowball.xan.item;

import com.snowball.xan.Xan;
import com.snowball.xan.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTallBase extends Item implements IHasModel {

	private final Block block;
	
	public ItemTallBase(Block block) {
		this.block = block;
		this.setCreativeTab(Xan.XAN_TAB);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (facing != EnumFacing.UP) {
            return EnumActionResult.FAIL;
        } else {
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            if (!block.isReplaceable(world, pos)) {
                pos = pos.offset(facing);
            }

            ItemStack stack = player.getHeldItem(hand);

            if (player.canPlayerEdit(pos, facing, stack) && this.block.canPlaceBlockAt(world, pos)) {
                EnumFacing enumfacing = EnumFacing.fromAngle((double)player.rotationYaw);
                int i = enumfacing.getFrontOffsetX();
                int j = enumfacing.getFrontOffsetZ();
                boolean flag = i < 0 && hitZ < 0.5F || i > 0 && hitZ > 0.5F || j < 0 && hitX > 0.5F || j > 0 && hitX < 0.5F;
                placeTallBlock(world, pos, enumfacing, this.block, flag);
                SoundType soundtype = world.getBlockState(pos).getBlock().getSoundType(world.getBlockState(pos), world, pos, player);
                world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                stack.shrink(1);
                return EnumActionResult.SUCCESS;
            } else {
                return EnumActionResult.FAIL;
            }
        }
    }
	
	public static void placeTallBlock(World world, BlockPos pos, EnumFacing facing, Block block, boolean isRightHinge) {
        BlockPos posPos = pos.offset(facing.rotateY());
        BlockPos posPOS = pos.offset(facing.rotateYCCW());
        int i = (world.getBlockState(posPOS).isNormalCube() ? 1 : 0) + (world.getBlockState(posPOS.up()).isNormalCube() ? 1 : 0);
        int j = (world.getBlockState(posPos).isNormalCube() ? 1 : 0) + (world.getBlockState(posPos.up()).isNormalCube() ? 1 : 0);
        boolean flag = world.getBlockState(posPOS).getBlock() == block || world.getBlockState(posPOS.up()).getBlock() == block;
        boolean flag1 = world.getBlockState(posPos).getBlock() == block || world.getBlockState(posPos.up()).getBlock() == block;

        if (((!flag || flag1) && j <= i) && (flag1 && !flag || j < i)) {
        	isRightHinge = false;
        } else {
            isRightHinge = true;
        }

        BlockPos posPosPOS = pos.up();
        IBlockState state = block.getDefaultState();
        world.setBlockState(pos, state.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER), 2);
        world.setBlockState(posPosPOS, state.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 2);
        world.notifyNeighborsOfStateChange(pos, block, false);
        world.notifyNeighborsOfStateChange(posPosPOS, block, false);
    }

	@Override
	public void registerModels() {
		Xan.proxy.registerItemRenderer(this, 0);
	}
}
