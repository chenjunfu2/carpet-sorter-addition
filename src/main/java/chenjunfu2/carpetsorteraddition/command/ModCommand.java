package chenjunfu2.carpetsorteraddition.command;

import carpet.api.settings.CarpetRule;
import carpet.utils.CommandHelper;
import chenjunfu2.carpetsorteraddition.CarpetSorterAddition;
import chenjunfu2.carpetsorteraddition.carpet.CarpetSorterExtensionSettings;
import chenjunfu2.carpetsorteraddition.translations.ModTranslations;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ModCommand
{
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment)
	{
		dispatcher.register(
				literal("sorter")
				.requires(source -> CommandHelper.canUseCommand(source, CarpetSorterExtensionSettings.fakePlayerSorter))
				.then(argument("start", IntegerArgumentType.integer())
				.executes(context ->
				{
					CarpetSorterAddition.LOGGER.info("cmd sorter");
					
					Integer arg = context.getArgument("start", Integer.class);
					context.getSource().sendFeedback(()-> Text.of(ModTranslations.format(CarpetSorterAddition.MOD_ABBR, "command.sorter.success", arg)), true);
					return 1;
				}))
		);
	}
}
