package eu.usrv.yamcl;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import eu.usrv.yamcl.auxiliary.*;
import eu.usrv.yamcl.iface.IPersistedDataBase;
import eu.usrv.yamcl.persisteddata.PersistedDataBase;


/**
 * @author Namikon
 *
 */
@Mod(modid = "yamcl", name = "YAMCL", version = "Beta 0.1")
public class Yamcl {
	private LogHelper _mLogger = new LogHelper("Yamcl");
	
	public LogHelper getLogger()
	{
		return _mLogger;
	}
	
	@Instance("yamcl")
	public static Yamcl instance = new Yamcl();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	
	}
}
