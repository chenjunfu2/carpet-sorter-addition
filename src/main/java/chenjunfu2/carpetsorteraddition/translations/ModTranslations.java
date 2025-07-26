package chenjunfu2.carpetsorteraddition.translations;

import carpet.utils.Translations;

import java.util.Map;

public class ModTranslations
{
	static Map<String, String> translations = Map.of();
	
	public static void init()
	{}
	
	public static Map<String, String> updateTranslations(String path)
	{
		Map<String, String> newTranslations = Translations.getTranslationFromResourcePath(path);
		if(!newTranslations.isEmpty())
		{
			translations = newTranslations;
		}
		
		return newTranslations;
	}
	
	public static String tr(String key)
	{
		return translations.getOrDefault(key,key);
	}
	
	public static String tr(String namespace, String key)
	{
		return tr(namespace + "." + key);
	}
	
	public static String format(String key, Object... args)
	{
		return String.format(tr(key), args);
	}
	
	public static String format(String namespace, String key, Object... args)
	{
		return String.format(tr(namespace, key), args);
	}
}
