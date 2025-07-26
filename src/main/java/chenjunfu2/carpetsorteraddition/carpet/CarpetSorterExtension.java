package chenjunfu2.carpetsorteraddition.carpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.CarpetRule;
import carpet.api.settings.SettingsManager;
import chenjunfu2.carpetsorteraddition.CarpetSorterAddition;
import net.minecraft.server.command.ServerCommandSource;

public class CarpetSorterExtension implements CarpetExtension
{
	private static final SettingsManager CSESettingsManager = new SettingsManager(CarpetSorterAddition.version, CarpetSorterAddition.MOD_ABBR, CarpetSorterAddition.MOD_NAME);
	private static final CarpetSorterExtension INSTANCE = new CarpetSorterExtension();
	
	public static void init()
	{
		CarpetServer.manageExtension(INSTANCE);
		SettingsManager.registerGlobalRuleObserver(INSTANCE::ruleChanged);
	}
	
	public void ruleChanged(ServerCommandSource source, CarpetRule<?> changedRule, String userInput)
	{
		if (changedRule.settingsManager() != CSESettingsManager)
		{
			return;
		}
		
		CarpetSorterAddition.LOGGER.info("ruleChanged call");
	}
	
	@Override
	public void onGameStarted()
	{
		CSESettingsManager.parseSettingsClass(CarpetSorterExtensionSettings.class);
	}
	
	@Override
	public SettingsManager extensionSettingsManager()//注册命令
	{
		return CSESettingsManager;
	}
	
	@Override
	public String version()
	{
		return CarpetSorterAddition.version;
	}
	
	//在此覆写父类方法以处理carpet事件
	
}

