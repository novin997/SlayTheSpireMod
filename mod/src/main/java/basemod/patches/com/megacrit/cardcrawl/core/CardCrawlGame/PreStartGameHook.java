package basemod.patches.com.megacrit.cardcrawl.core.CardCrawlGame;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
		clz=CardCrawlGame.class,
		method="createCharacter"
)
public class PreStartGameHook
{
	public static void Prefix(AbstractPlayer.PlayerClass selection) {
		AbstractDungeon.victoryScreen = null;
		BaseMod.publishPreStartGame();
	}
}
