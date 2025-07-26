package chenjunfu2.carpetsorteraddition.carpet;

import carpet.api.settings.Rule;
import chenjunfu2.carpetsorteraddition.CarpetSorterAddition;

public class CarpetSorterExtensionSettings
{
	@Rule
	(
		categories = {CarpetSorterAddition.MOD_ABBR}
		//options = {"true", "false"}
		//strict = true
		//validators = {xxx.class}//自定义规则验证器
		//conditions = {xxx.class}//自定义注册验证器
		//appSource = "xxx"//关联xxx.sc的scarpet脚本
	)
	public static boolean fakePlayerSorter = false;//必须要public static，否则"obj" is null爆炸警告
}
