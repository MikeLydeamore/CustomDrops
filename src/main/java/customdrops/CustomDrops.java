package customdrops;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=CustomDrops.MODID, version="0.0.1")
public class CustomDrops {
	
	public static final String MODID = "customdrops";
	
	@Instance(MODID)
	public CustomDrops instance;
	
	private File configFile;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new DropHandler());
		
		configFile = new File(event.getModConfigurationDirectory(), "CustomDrops.json");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		DropHandler.loadJson(configFile);
	}

}
