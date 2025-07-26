package chenjunfu2.carpetsorteraddition.carpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.utils.Translations;
import chenjunfu2.carpetsorteraddition.CarpetSorterAddition;

import java.util.Map;

public class CarpetSorterExtension implements CarpetExtension
{
	//private static final SettingsManager CSESettingsManager = new SettingsManager(CarpetSorterAddition.version, CarpetSorterAddition.MOD_ABBR, CarpetSorterAddition.MOD_NAME);
	private static final CarpetSorterExtension INSTANCE = new CarpetSorterExtension();
	
	public static void init()//初始化（注册自身）
	{
		CarpetServer.manageExtension(INSTANCE);
		//SettingsManager.registerGlobalRuleObserver(INSTANCE::ruleChanged);
	}
	
	//public void ruleChanged(ServerCommandSource source, CarpetRule<?> changedRule, String userInput)//规则修改回调
	//{
	//	//if (changedRule.settingsManager() != CSESettingsManager)
	//	//{
	//	//	return;
	//	//}
	//
	//	CarpetSorterAddition.LOGGER.info("ruleChanged call");
	//}
	
	@Override
	public Map<String, String> canHasTranslations(String lang)//注册语言
	{
		return Translations.getTranslationFromResourcePath("assets/carpet-sorter-addition/lang/%s.json".formatted(lang));//gradlew插件会把yml转换到json
	}
	
	@Override
	public void onGameStarted()//注册规则
	{
		//CSESettingsManager.parseSettingsClass(CarpetSorterExtensionSettings.class);//注册自定义命令
		CarpetServer.settingsManager.parseSettingsClass(CarpetSorterExtensionSettings.class);//与/carpet注册到一起
	}
	
	//@Override
	//public SettingsManager extensionSettingsManager()//注册命令
	//{
	//	return CSESettingsManager;
	//}
	
	@Override
	public String version()//注册版本
	{
		return CarpetSorterAddition.version;
	}
	
	//在此覆写父类方法以处理carpet事件
	
}

