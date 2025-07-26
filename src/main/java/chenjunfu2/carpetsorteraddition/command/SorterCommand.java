package chenjunfu2.carpetsorteraddition.command;

import chenjunfu2.carpetsorteraddition.CarpetSorterAddition;
import chenjunfu2.carpetsorteraddition.carpet.CarpetSorterExtensionSettings;
import chenjunfu2.carpetsorteraddition.translations.ModTranslations;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class SorterCommand
{
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment)
	{
		dispatcher.register(
			CommandManager
				.literal("sorter")
				.requires(source -> isRuleEnabled(source, CarpetSorterExtensionSettings.fakePlayerSorter))
				.executes(context ->
				{
					CarpetSorterAddition.LOGGER.info("cmd sorter");
					context.getSource().sendFeedback(()-> Text.of(ModTranslations.tr(CarpetSorterAddition.MOD_ABBR, "command.sorter.success")), true);
					return 1;
				})
		);
	}
	
	public static boolean isRuleEnabled(ServerCommandSource source, Object rule)
	{
		if (rule instanceof Boolean)
		{
			return (boolean)rule;
		}
		else if (rule instanceof String)
		{
			return switch ((String)rule)
			{
				case "true"-> true;
				case "false"-> false;
				default -> false;
			};
		}
		
		return false;
	}
	
}
