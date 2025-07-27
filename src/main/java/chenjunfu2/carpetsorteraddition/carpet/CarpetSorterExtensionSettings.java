package chenjunfu2.carpetsorteraddition.carpet;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;
import chenjunfu2.carpetsorteraddition.CarpetSorterAddition;
public class CarpetSorterExtensionSettings
{
	/*
	@Rule
	(
		categories = {"xxx","xxx"}//规则所在的类别的数组，注意，如果是carpet自己的RuleCategory内的规则，可能会有不同的附加效果
		options = {"true", "false"}//选项列表
		strict = true//是否严格取值（仅限于取值列表）
		validators = {xxx.class}//自定义规则验证器
		conditions = {xxx.class}//自定义注册验证器
		appSource = "xxx"//关联xxx.sc的scarpet脚本
	)
	
	public static type name = defaultValue;//必须要public static，否则"obj" is null爆炸警告
	*/
	
	@Rule
	(
		categories = {CarpetSorterAddition.MOD_ABBR, RuleCategory.COMMAND}//带有RuleCategory.COMMAND的命令更新后会同步更新玩家指令树
	)
	public static boolean fakePlayerSorter = false;//该变量更新需要更新指令树
}