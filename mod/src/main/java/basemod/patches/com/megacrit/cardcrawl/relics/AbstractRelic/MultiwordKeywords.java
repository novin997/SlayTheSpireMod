package basemod.patches.com.megacrit.cardcrawl.relics.AbstractRelic;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@SpirePatch(
		clz=AbstractRelic.class,
		method="initializeTips"
)
public class MultiwordKeywords
{
	public static void Postfix(AbstractRelic __instance)
	{
		if (__instance.tips.size() > 0 && __instance.tips.get(0).header.toLowerCase().equals(__instance.name.toLowerCase())) {
			__instance.tips.get(0).body = replaceMultiWordKeywords(__instance.tips.get(0).body);
		}
	}

	public static String replaceMultiWordKeywords(String input)
	{
		StringBuilder builder = new StringBuilder();
		for (String word : input.split(" ")) {
			String keyword = word;
			String color = null;
			if (!word.isEmpty() && word.charAt(0) == '#') {
				color = word.substring(1, 2);
				keyword = word.substring(2);
			}
			if (GameDictionary.keywords.containsKey(keyword.replace(',', ' ').replace('.', ' ').trim().toLowerCase())) {
				if (color != null) {
					builder.append(' ').append(FontHelper.colorString(keyword.replace('_', ' '), color));
					continue;
				}
			}
			builder.append(' ').append(word);
		}
		// Remove leading space
		return builder.toString().substring(1);
	}
}
