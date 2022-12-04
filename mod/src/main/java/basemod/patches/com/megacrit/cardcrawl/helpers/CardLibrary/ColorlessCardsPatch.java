package basemod.patches.com.megacrit.cardcrawl.helpers.CardLibrary;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;

@SpirePatch(
		clz=CardLibrary.class,
		method="addColorlessCards",
		paramtypez={}
)
public class ColorlessCardsPatch {

    public static void Postfix() {
    	// add new cards
		for (AbstractCard card : BaseMod.getColorlessCardsToAdd()) {
			CardLibrary.add(card);
		}
		
		// remove old cards
		for (String cardID : BaseMod.getColorlessCardsToRemove()) {
			CardLibrary.cards.remove(cardID);
			CardLibrary.colorlessCards--;
			CardLibrary.totalCardCount--;
		}
    }
}
