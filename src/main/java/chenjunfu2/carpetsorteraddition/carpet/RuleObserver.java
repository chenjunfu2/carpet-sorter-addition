package chenjunfu2.carpetsorteraddition.carpet;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

public abstract class RuleObserver<T> extends Validator<T>
{
	@Override
	public T validate(ServerCommandSource source, CarpetRule<T> currentRule, T newValue, String userInput) {
		if (currentRule.value() != newValue) {
			onValueChange(source, currentRule.value(), newValue);
		}
		return newValue;
	}
	
	//规则修改前回调
	abstract public void onValueChange(ServerCommandSource source, T oldValue, T newValue);
}