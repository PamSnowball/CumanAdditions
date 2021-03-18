package com.snowball.xan.init;

import java.util.ArrayList;
import java.util.List;

import com.snowball.xan.Xan;
import com.snowball.xan.item.*;

import net.minecraft.item.Item;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<Item>();

	public static final Item CUMAN_FRAME = (new ItemTallBase(ModBlocks.CUMAN_FRAME)).setUnlocalizedName("cumanbrick_frame");
	public static final Item RED_CUMAN_FRAME = (new ItemTallBase(ModBlocks.RED_CUMAN_FRAME)).setUnlocalizedName("red_cumanbrick_frame");

	public static final Item WHITE_BLUE_FELT = new ItemBase("white_blue_felt").setCreativeTab(Xan.XAN_TAB);
	public static final Item WHITE_ZIGZAG_FELT = new ItemBase("white_zigzag_felt").setCreativeTab(Xan.XAN_TAB);
	public static final Item YELLOW_FELT = new ItemBase("yellow_felt").setCreativeTab(Xan.XAN_TAB);
	
	public static final Item RED_BLUE_FELT = new ItemBase("red_blue_felt").setCreativeTab(Xan.XAN_TAB);
	public static final Item RED_ZIGZAG_FELT = new ItemBase("red_zigzag_felt").setCreativeTab(Xan.XAN_TAB);
}
