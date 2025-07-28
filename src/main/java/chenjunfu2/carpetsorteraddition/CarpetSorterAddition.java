package chenjunfu2.carpetsorteraddition;

import chenjunfu2.carpetsorteraddition.carpet.CarpetSorterExtension;
import chenjunfu2.carpetsorteraddition.command.ModCommand;
import chenjunfu2.carpetsorteraddition.translations.ModTranslations;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarpetSorterAddition implements ModInitializer
{
	public static final String MOD_ID = "carpet-sorter-addition";
	public static final String MOD_ABBR = "csa";
	public static String version;
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	@Override
	public void onInitialize()
	{
		//设置版本信息
		version = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
		//初始化翻译
		ModTranslations.init();//一定要先调用这个初始化静态成员
		
		//注册为地毯附属（注册carpet规则、命令、翻译等）
		CarpetSorterExtension.init();
		//注册自定义命令
		CommandRegistrationCallback.EVENT.register(ModCommand::register);
		
		
	}
}