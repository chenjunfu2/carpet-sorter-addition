package chenjunfu2.carpetsorteraddition.carpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.CarpetRule;
import carpet.api.settings.SettingsManager;
import chenjunfu2.carpetsorteraddition.CarpetSorterAddition;
import chenjunfu2.carpetsorteraddition.command.ModCommand;
import chenjunfu2.carpetsorteraddition.translations.ModTranslations;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Map;

public class CarpetSorterExtension implements CarpetExtension
{
	//private static final SettingsManager CSESettingsManager = new SettingsManager(CarpetSorterAddition.version, CarpetSorterAddition.MOD_ABBR, CarpetSorterAddition.MOD_NAME);
	private static final CarpetSorterExtension INSTANCE = new CarpetSorterExtension();
	
	public static void init()//初始化（注册自身）
	{
		CarpetServer.manageExtension(INSTANCE);
		SettingsManager.registerGlobalRuleObserver(INSTANCE::ruleChanged);
	}
	
	public void ruleChanged(ServerCommandSource source, CarpetRule<?> changedRule, String userInput)//规则修改回调
	{
		if (!changedRule.categories().contains(CarpetSorterAddition.MOD_ABBR))//如果当前规则集不属于本mod则忽略
		{
			return;
		}
	
		CarpetSorterAddition.LOGGER.info("CarpetSorterExtensionSettings ruleChanged call");
		ModCommand.ruleChanged(source, changedRule, userInput);
	}
	
	@Override
	public Map<String, String> canHasTranslations(String lang)//注册语言
	{
		return ModTranslations.updateTranslations("assets/carpet-sorter-addition/lang/%s.json".formatted(lang));//gradlew插件会把yml转换到json
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

