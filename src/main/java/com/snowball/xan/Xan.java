package com.snowball.xan;

import com.snowball.xan.proxy.CommonProxy;
import com.snowball.xan.tab.CreativeTabMillenaireXan;
import com.snowball.xan.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Xan {
	@Instance
	public static Xan instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
  
	public static final CreativeTabs XAN_TAB = new CreativeTabMillenaireXan("millenairexantab");

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {}
}
