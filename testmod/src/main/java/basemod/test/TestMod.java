package basemod.test;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.abstracts.CustomSavable;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StorePotion;
import com.megacrit.cardcrawl.shop.StoreRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@SpireInitializer
public class TestMod implements
	EditCardsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber,
	EditStringsSubscriber, PostCampfireSubscriber, PostCreateStartingDeckSubscriber,
	PostCreateStartingRelicsSubscriber, PostCreateShopPotionSubscriber,
	PostCreateShopRelicSubscriber, PostDrawSubscriber,
	PostDungeonInitializeSubscriber, PostEnergyRechargeSubscriber,
	PostInitializeSubscriber, PostRenderSubscriber, PostUpdateSubscriber,
	PreMonsterTurnSubscriber, PreStartGameSubscriber,
	PreUpdateSubscriber, RenderSubscriber, StartActSubscriber,
	StartGameSubscriber {
	public static final Logger logger = LogManager.getLogger(TestMod.class.getName());
	
	private static final String LOG_FILE = "testmod.log";
	
	private static final String MODNAME = "TestMod";
	private static final String AUTHOR = "Test447";
	private static final String DESCRIPTION = "v1.6.4";
	
	//
	// Logging
	//
	
	@SuppressWarnings("unused")
	private static void silentWrite(BufferedWriter writer, String msg) {
		try {
			writer.write(msg + '\n');
			writer.flush();
		} catch (IOException e) {
			logger.error("IOException on writing: " + msg);
			logger.error("ERROR: " + e.toString());
			e.printStackTrace();
		}
	}
	
	private static void loudWrite(BufferedWriter writer, String msg) {
		try {
			writer.write(msg + '\n');
			writer.flush();
		} catch (IOException e) {
			logger.error("IOException on writing: " + msg);
			logger.error("ERROR: " + e.toString());
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	//
	// Error handling
	//
	
	private static void unexpectedCrash(String msg) {
		logger.error("UNEXPECTED BEHAVIOR FOR TESTMOD - EXITING - ERROR BELOW");
		logger.error("ERROR: " + msg);
		Gdx.app.exit();
	}
	
	private static void couldNotCrash(String msg, Exception e) {
		logger.error("COULD NOT " + msg + " FOR TESTMOD - EXITING - ERROR BELOW");
		logger.error("ERROR: " + e.toString());
		e.printStackTrace();
		Gdx.app.exit();
	}
	
	private static void couldNotCrash(String msg, String err) {
		logger.error("COULD NOT " + msg + " FOR TESTMOD - EXITING - ERROR BELOW");
		logger.error("ERROR: " + err);
		Gdx.app.exit();
	}

	public static final String ASSET_FOLDER = "img/test/test";
	public static final String LOCALIZATION_FOLDER = "localization";
	private static final Color PURPLE = CardHelper.getColor(139.0f, 0.0f, 139.0f);
    
	// card strings
	public static final String STRIKE_PURPLE = "cards/strike_purple.png";
	public static final String DEFEND_PURPLE = "cards/defend_purple.png";
	
	// card backgrounds
    private static final String ATTACK_PURPLE = "512/bg_attack_purple.png";
    private static final String SKILL_PURPLE = "512/bg_attack_purple.png";
    private static final String POWER_PURPLE = "512/bg_attack_purple.png";
    private static final String ENERGY_ORB_PURPLE = "512/card_purple_orb.png";
	
    // strings
    private static final String RELIC_STRINGS = "relics.json";
    private static final String CARD_STRINGS = "cards.json";
    
    
    // relic images
    public static final String ARCANOSPHERE_RELIC = "relics/arcanosphere.png";
    
    // purpleclad assets
    private static final String PURPLECLAD_BUTTON = "charSelect/purplecladButton.png";
    private static final String PURPLECLAD_PORTRAIT = "charSelect/purplecladPortrait.jpg";
    
    public static Texture getArcanoSphereTexture() {
    	return ImageMaster.loadImage(makePath(ASSET_FOLDER,ARCANOSPHERE_RELIC));
    }
    
	private static BufferedWriter writer = null;

    /**
     * Makes a full path for a resource path
     * @param resource the resource, must *NOT* have a leading "/"
     * @return the full path
     */
    public static final String makePath(String folder, String resource) {
    	return folder + "/" + resource;
    }
	
	@SuppressWarnings("deprecation")
	public TestMod() {
		loudWrite(writer, "Begin subscribing to hooks");
		BaseMod.subscribe(this);
		loudWrite(writer, "End subscribing to hooks");
		
		loudWrite(writer, "Setup new colors");
		BaseMod.addColor(ColorEnumPatch.PURPLE,
        		PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE,
        		makePath(ASSET_FOLDER, ATTACK_PURPLE), 
        		makePath(ASSET_FOLDER, SKILL_PURPLE),
        		makePath(ASSET_FOLDER, POWER_PURPLE),
        		makePath(ASSET_FOLDER, ENERGY_ORB_PURPLE),
        		makePath(ASSET_FOLDER, ATTACK_PURPLE), 
        		makePath(ASSET_FOLDER, SKILL_PURPLE),
        		makePath(ASSET_FOLDER, POWER_PURPLE),
        		makePath(ASSET_FOLDER, ENERGY_ORB_PURPLE));
		loudWrite(writer, "End setting up new colors");

		saveFieldTest();
	}
	
	public static void initialize() {
		logger.info("========================= TESTMOD INIT =========================");
		
		File logFile = new File(LOG_FILE);
		try {
			if (writer == null) {
				writer = new BufferedWriter(new FileWriter(logFile));
			}
		} catch (IOException e) {
			couldNotCrash("CREATE LOG FILE", e);
		}
		
		@SuppressWarnings("unused")
		TestMod test = new TestMod();
		
		logger.info("================================================================");
	}
	
	//
	// Subscriptions
	//
	
	private static final int STARTING_CARDS_COUNT = 3;
	private static final String CARD_1 = "Transmutation";
	private static final String CARD_2 = "Panacea";
	private static final String CARD_3 = "Panache";

	private static void addCardToStartingDeck(CardGroup cards, String card) {
		loudWrite(writer, "Adding card " + card + " to deck: 1/1");
		cards.addToTop(CardLibrary.getCard(card).makeCopy());
	}
	
	@Override
	public void receivePostCreateStartingDeck(AbstractPlayer.PlayerClass chosenClass, CardGroup cards) {
		// add one copy of Transmutation, Panacea, and Panache
		// to the starting deck
		addCardToStartingDeck(cards, CARD_1);
		addCardToStartingDeck(cards, CARD_2);
		addCardToStartingDeck(cards, CARD_3);
	}
	
	private static void addRelicToStartingSet(ArrayList<String> addRelicsToMe, String relic) {
		loudWrite(writer, "Adding relic " + relic + " to starting relics: 1/1");
		addRelicsToMe.add(relic);
	}

	private static final int STARTING_RELICS_COUNT = 2;
	private static final String RELIC_1 = "Snecko Eye";
	private static final String RELIC_2 = "Velvet Choker";
	
	@Override
	public void receivePostCreateStartingRelics(AbstractPlayer.PlayerClass chosenClass, ArrayList<String> addRelicsToMe) {
		// add one Snecko Eye, and Velvet Choker
		// to the starting relics
		addRelicToStartingSet(addRelicsToMe, RELIC_1);
		addRelicToStartingSet(addRelicsToMe, RELIC_2);
	}

	private static boolean hasCard(String card) {
		for (AbstractCard realCard : AbstractDungeon.player.hand.group) {
			if (realCard.cardID.equals(card)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean hasRelic(String relic) {
		for (AbstractRelic realRelic : AbstractDungeon.player.relics) {
			if (realRelic.relicId.equals(relic)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean receivedStartGame = false;
	
	@Override
	public void receiveStartGame() {
		if (receivedStartGame) {
			//unexpectedCrash("RECEIVED START GAME HOOK MORE THAN ONCE");
		}
		receivedStartGame = true;
		
		// confirm starting loadout
		if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.IRONCLAD ||
			AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
			// cards
			int card_count = AbstractDungeon.player.hand.group.size();
			if (card_count != STARTING_CARDS_COUNT) {
				couldNotCrash("CONFIRM VALID STARTING DECK SIZE", "EXPECTED: " + STARTING_CARDS_COUNT + " ACTUAL: " + card_count);
			}
			if (!hasCard(CARD_1)) {
				couldNotCrash("CONFIRM PLAYER HAD EXPECTED CARDS", "EXPECTED TO HAVE: " + CARD_1 + " BUT ACTUALLY DID NOT");
			}
			if (!hasCard(CARD_2)) {
				couldNotCrash("CONFIRM PLAYER HAD EXPECTED CARDS", "EXPECTED TO HAVE: " + CARD_2 + " BUT ACTUALLY DID NOT");
			}
			if (!hasCard(CARD_3)) {
				couldNotCrash("CONFIRM PLAYER HAD EXPECTED CARDS", "EXPECTED TO HAVE: " + CARD_3 + " BUT ACTUALLY DID NOT");
			}
			
			// relics
			int relic_count = AbstractDungeon.player.relics.size();
			if (relic_count != STARTING_RELICS_COUNT) {
				couldNotCrash("CONFIRM VALID STARTING RELICS SIZE", "EXPECTED: " + STARTING_RELICS_COUNT + " ACTUAL: " + relic_count);
			}
			if (!hasRelic(RELIC_1)) {
				couldNotCrash("CONFIRM PLAYER HAD EXPECTED RELICS", "EXPECTED TO HAVE: " + RELIC_1 + " BUT ACTUALLY DID NOT");
			}
			if (!hasRelic(RELIC_2)) {
				couldNotCrash("CONFIRM PLAYER HAD EXPECTED RELICS", "EXPECTED TO HAVE: " + RELIC_2 + " BUT ACTUALLY DID NOT");
			}
		}
	}
	
	@Override
	public void receiveStartAct() {
		
	}

	@Override
	public void receiveRender(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivePreUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivePreStartGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean receivePreMonsterTurn(AbstractMonster m) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void receivePostUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivePostRender(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void receivePostInitialize() {
        try {
        	// setup ModBadge for TestMod
            Texture badgeTexture = ImageMaster.loadImage("img/BaseModBadge.png");
            ModPanel settingsPanel = new ModPanel();
            settingsPanel.addUIElement(new ModLabel("Test Mod - no settings", 400.0f, 700.0f, settingsPanel, (me) -> {}));
            BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        } catch (Exception e) {
        	couldNotCrash("CREATE MOD BADGE", e);
        }
	}

	@Override
	public void receivePostEnergyRecharge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivePostDungeonInitialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivePostDraw(AbstractCard c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveCreateShopRelics(ArrayList<StoreRelic> relics, ShopScreen screenInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveCreateShopPotions(ArrayList<StorePotion> potions, ShopScreen screenInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean receivePostCampfire() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void receiveEditStrings() {
		loudWrite(writer, "begin editting strings");
		
		// RelicStrings
		String relicStrings = Gdx.files.internal(
				makePath(LOCALIZATION_FOLDER, RELIC_STRINGS)).readString(
				String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
		
		// CardStrings
		String cardStrings = Gdx.files.internal(
				makePath(LOCALIZATION_FOLDER, CARD_STRINGS)).readString(
				String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
		
		loudWrite(writer, "end editting strings");
	}

	@Override
	public void receiveEditRelics() {
		loudWrite(writer, "begin editting relics");
		
		RelicLibrary.add(new Arcanosphere());
		
		loudWrite(writer, "end editting relics");
	}

	@Override
	public void receiveEditCharacters() {
		loudWrite(writer, "begin editting characters");

		BaseMod.addCharacter(new Purpleclad(CardCrawlGame.playerName),
				makePath(ASSET_FOLDER, PURPLECLAD_BUTTON),
				makePath(ASSET_FOLDER, PURPLECLAD_PORTRAIT),
				CharacterEnumPatch.THE_PURPLECLAD);
		
		loudWrite(writer, "done editting characters");
	}

	@Override
	public void receiveEditCards() {
		loudWrite(writer, "begin editting cards");
		
		BaseMod.addCard(new Strike_Purple());
		BaseMod.addCard(new Defend_Purple());
		
		loudWrite(writer, "End editting cards");
	}

	public void saveFieldTest() {
		BaseMod.addSaveField("testmod:savetest", new CustomSavable<String>() {
			public Class<String> savedType() {
				return String.class;
			}
			public String onSave() {
				return "Saved";
			}
			public void onLoad(String x) {
				if (x == null) {
					loudWrite(writer, "loaded savefile, but no custom field");
				} else {
					loudWrite(writer, "loaded savefile, found custom field: " + x);
				}
			}
		});
	}
}
