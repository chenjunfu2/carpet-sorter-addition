package chenjunfu2.carpetsorteraddition;

import chenjunfu2.carpetsorteraddition.carpet.CarpetSorterExtension;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarpetSorterAddition implements ModInitializer {
	public static final String MOD_ID = "carpet-sorter-addition";
	public static final String MOD_NAME = "Carpet Sorter Addition";
	public static final String MOD_ABBR = "CSA";
	public static String version;
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	@Override
	public void onInitialize()
	{
		version = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
		//注册为地毯附属
		CarpetSorterExtension.init();
	}
}