package DarkestMod;

import DarkestMod.cards.*;
import DarkestMod.relics.*;
import basemod.*;
import basemod.eventUtil.AddEventParams;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import DarkestMod.characters.TheDarkest;
import DarkestMod.events.IdentityCrisisEvent;
import DarkestMod.util.IDCheckDontTouchPls;
import DarkestMod.util.TextureLoader;
import DarkestMod.variables.DefaultCustomVariable;
import DarkestMod.variables.DefaultSecondMagicNumber;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
// Please don't just mass replace "theDefault" with "yourMod" everywhere.
// It'll be a bigger pain for you. You only need to replace it in 4 places.
// I comment those places below, under the place where you set your ID.

//TODO: FIRST THINGS FIRST: RENAME YOUR PACKAGE AND ID NAMES FIRST-THING!!!
// Right click the package (Open the project pane on the left. Folder with black dot on it. The name's at the very top) -> Refactor -> Rename, and name it whatever you wanna call your mod.
// Scroll down in this file. Change the ID from "theDefault:" to "yourModName:" or whatever your heart desires (don't use spaces). Dw, you'll see it.
// In the JSON strings (resources>localization>eng>[all them files] make sure they all go "yourModName:" rather than "theDefault", and change to "yourmodname" rather than "thedefault".
// You can ctrl+R to replace in 1 file, or ctrl+shift+r to mass replace in specific files/directories, and press alt+c to make the replace case sensitive (Be careful.).
// Start with the DefaultCommon cards - they are the most commented cards since I don't feel it's necessary to put identical comments on every card.
// After you sorta get the hang of how to make cards, check out the card template which will make your life easier

/*
 * With that out of the way:
 * Welcome to this super over-commented Slay the Spire modding base.
 * Use it to make your own mod of any type. - If you want to add any standard in-game content (character,
 * cards, relics), this is a good starting point.
 * It features 1 character with a minimal set of things: 1 card of each type, 1 debuff, couple of relics, etc.
 * If you're new to modding, you basically *need* the BaseMod wiki for whatever you wish to add
 * https://github.com/daviscook477/BaseMod/wiki - work your way through with this base.
 * Feel free to use this in any way you like, of course. MIT licence applies. Happy modding!
 *
 * And pls. Read the comments.
 */

@SpireInitializer
public class DarkestMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber {
    // Make sure to implement the subscribers *you* are using (read basemod wiki). Editing cards? EditCardsSubscriber.
    // Making relics? EditRelicsSubscriber. etc., etc., for a full list and how to make your own, visit the basemod wiki.
    public static final Logger logger = LogManager.getLogger(DarkestMod.class.getName());
    private static String modID;

    // Mod-settings settings. This is if you want an on/off savable button
    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true; // The boolean we'll be setting on/off (true/false)

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "Default Mod";
    private static final String AUTHOR = "Gremious"; // And pretty soon - You!
    private static final String DESCRIPTION = "A base for Slay the Spire to start your own mod from, feat. the Default.";

    // =============== INPUT TEXTURE LOCATION =================

    // Colors (RGB)
    // Character Color
    public static final Color DARKEST_BLACK = CardHelper.getColor(64.0f, 70.0f, 70.0f);

    // Potion Colors in RGB
    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown

    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!

    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_DARKEST_BLACK = "DarkestModResources/images/512/bg_attack_darkest_black.png";
    private static final String SKILL_DARKEST_BLACK = "DarkestModResources/images/512/bg_skill_darkest_black.png";
    private static final String POWER_DARKEST_BLACK = "DarkestModResources/images/512/bg_power_darkest_black.png";

    private static final String ENERGY_ORB_DARKEST_BLACK = "DarkestModResources/images/512/card_darkest_black_orb.png";
    private static final String CARD_ENERGY_ORB = "DarkestModResources/images/512/card_small_orb.png";

    private static final String ATTACK_DARKEST_BLACK_PORTRAIT = "DarkestModResources/images/1024/bg_attack_darkest_black.png";
    private static final String SKILL_DARKEST_BLACK_PORTRAIT = "DarkestModResources/images/1024/bg_skill_darkest_black.png";
    private static final String POWER_DARKEST_BLACK_PORTRAIT = "DarkestModResources/images/1024/bg_power_darkest_black.png";
    private static final String ENERGY_ORB_DARKEST_BLACK_PORTRAIT = "DarkestModResources/images/1024/card_darkest_black_orb.png";
    // Character assets
    private static final String THE_DARKEST_BUTTON = "DarkestModResources/images/charSelect/DDcharacterButton.png";
    private static final String THE_DARKEST_PORTRAIT = "DarkestModResources/images/charSelect/DDCharacterPortraitBG.jpg";
    public static final String THE_DARKEST_SHOULDER_1 = "DarkestModResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DARKEST_SHOULDER_2 = "DarkestModResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DARKEST_CORPSE = "DarkestModResources/images/char/defaultCharacter/corpse.png";

    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "DarkestModResources/images/Badge.png";

    // Atlas and JSON files for the Animations
    public static final String THE_DEFAULT_SKELETON_ATLAS = "DarkestModResources/images/char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "DarkestModResources/images/char/defaultCharacter/skeleton.json";

    // =============== MAKE IMAGE PATHS =================

    public static String makeCardPath(String resourcePath) {
        return "DarkestModResources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return "DarkestModResources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return "DarkestModResources/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return  "DarkestModResources/images/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return "DarkestModResources/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return "DarkestModResources/images/events/" + resourcePath;
    }

    // =============== /MAKE IMAGE PATHS/ =================

    // =============== /INPUT TEXTURE LOCATION/ =================


    // =============== SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE =================

    public DarkestMod() {
        logger.info("Subscribe to BaseMod hooks");

        BaseMod.subscribe(this);

      /*
           (   ( /(  (     ( /( (            (  `   ( /( )\ )    )\ ))\ )
           )\  )\()) )\    )\()))\ )   (     )\))(  )\()|()/(   (()/(()/(
         (((_)((_)((((_)( ((_)\(()/(   )\   ((_)()\((_)\ /(_))   /(_))(_))
         )\___ _((_)\ _ )\ _((_)/(_))_((_)  (_()((_) ((_|_))_  _(_))(_))_
        ((/ __| || (_)_\(_) \| |/ __| __| |  \/  |/ _ \|   \  |_ _||   (_)
         | (__| __ |/ _ \ | .` | (_ | _|  | |\/| | (_) | |) |  | | | |) |
          \___|_||_/_/ \_\|_|\_|\___|___| |_|  |_|\___/|___/  |___||___(_)
      */

        setModID("DarkestMod");
        // cool
        // TODO: NOW READ THIS!!!!!!!!!!!!!!!:

        // 1. Go to your resources folder in the project panel, and refactor> rename theDefaultResources to
        // yourModIDResources.

        // 2. Click on the localization > eng folder and press ctrl+shift+r, then select "Directory" (rather than in Project) and press alt+c (or mark the match case option)
        // replace all instances of theDefault with yourModID, and all instances of thedefault with yourmodid (the same but all lowercase).
        // Because your mod ID isn't the default. Your cards (and everything else) should have Your mod id. Not mine.
        // It's important that the mod ID prefix for keywords used in the cards descriptions is lowercase!

        // 3. Scroll down (or search for "ADD CARDS") till you reach the ADD CARDS section, and follow the TODO instructions

        // 4. FINALLY and most importantly: Scroll up a bit. You may have noticed the image locations above don't use getModID()
        // Change their locations to reflect your actual ID rather than theDefault. They get loaded before getID is a thing.

        logger.info("Done subscribing");

        logger.info("Creating the color " + TheDarkest.Enums.DARKEST_COLOR.toString());

        BaseMod.addColor(TheDarkest.Enums.DARKEST_COLOR, DARKEST_BLACK, DARKEST_BLACK, DARKEST_BLACK,
                DARKEST_BLACK, DARKEST_BLACK, DARKEST_BLACK, DARKEST_BLACK,
                ATTACK_DARKEST_BLACK, SKILL_DARKEST_BLACK, POWER_DARKEST_BLACK, ENERGY_ORB_DARKEST_BLACK,
                ATTACK_DARKEST_BLACK_PORTRAIT, SKILL_DARKEST_BLACK_PORTRAIT, POWER_DARKEST_BLACK_PORTRAIT,
                ENERGY_ORB_DARKEST_BLACK_PORTRAIT, CARD_ENERGY_ORB);

        logger.info("Done creating the color");


        logger.info("Adding mod settings");
        // This loads the mod settings.
        // The actual mod Button is added below in receivePostInitialize()
        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE"); // This is the default setting. It's actually set...
        try {
            SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings); // ...right here
            // the "fileName" parameter is the name of the file MTS will create where it will save our setting.
            config.load(); // Load the setting and set the boolean to equal it
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");

    }

    // ====== NO EDIT AREA ======
    // DON'T TOUCH THIS STUFF. IT IS HERE FOR STANDARDIZATION BETWEEN MODS AND TO ENSURE GOOD CODE PRACTICES.
    // IF YOU MODIFY THIS I WILL HUNT YOU DOWN AND DOWNVOTE YOUR MOD ON WORKSHOP

    public static void setModID(String ID) { // DON'T EDIT
        Gson coolG = new Gson(); // EY DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i hate u Gdx.files
        InputStream in = DarkestMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THIS ETHER
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // OR THIS, DON'T EDIT IT
        logger.info("You are attempting to set your mod ID as: " + ID); // NO WHY
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { // DO *NOT* CHANGE THIS ESPECIALLY, TO EDIT YOUR MOD ID, SCROLL UP JUST A LITTLE, IT'S JUST ABOVE
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION); // THIS ALSO DON'T EDIT
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { // NO
            modID = EXCEPTION_STRINGS.DEFAULTID; // DON'T
        } else { // NO EDIT AREA
            modID = ID; // DON'T WRITE OR CHANGE THINGS HERE NOT EVEN A LITTLE
        } // NO
        logger.info("Success! ID is " + modID); // WHY WOULD U WANT IT NOT TO LOG?? DON'T EDIT THIS.
    } // NO

    public static String getModID() { // NO
        return modID; // DOUBLE NO
    } // NU-UH

    private static void pathCheck() { // ALSO NO
        Gson coolG = new Gson(); // NOPE DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files
        InputStream in = DarkestMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THISSSSS
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT
        String packageName = DarkestMod.class.getPackage().getName(); // STILL NO EDIT ZONE
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS
            if (!packageName.equals(getModID())) { // NOT HERE ETHER
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO
            } // WHY WOULD U EDIT THIS
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS
            }// NO
        }// NO
    }// NO

    // ====== YOU CAN EDIT AGAIN ======


    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        DarkestMod defaultmod = new DarkestMod();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }

    // ============== /SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE/ =================


    // =============== LOAD THE CHARACTER =================

    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + TheDarkest.Enums.THE_DARKEST.toString());

        BaseMod.addCharacter(new TheDarkest("the Default", TheDarkest.Enums.THE_DARKEST),
                THE_DARKEST_BUTTON, THE_DARKEST_PORTRAIT, TheDarkest.Enums.THE_DARKEST);

        receiveEditPotions();
        logger.info("Added " + TheDarkest.Enums.THE_DARKEST.toString());
    }

    // =============== /LOAD THE CHARACTER/ =================


    // =============== POST-INITIALIZE =================

    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");

        // Load the Mod Badge
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);

        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();

        // Create the on/off button:
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont, // Position (trial and error it), color, font
                enablePlaceholder, // Boolean it uses
                settingsPanel, // The mod panel in which this button will be in
                (label) -> {}, // thing??????? idk
                (button) -> { // The actual button:

                    enablePlaceholder = button.enabled; // The boolean true/false will be whether the button is enabled or not
                    try {
                        // And based on that boolean, set the settings and save them
                        SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
                        config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        settingsPanel.addUIElement(enableNormalsButton); // Add the button to the settings panel. Button is a go.

        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);


        // =============== EVENTS =================
        // https://github.com/daviscook477/BaseMod/wiki/Custom-Events

        // You can add the event like so:
        // BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class, TheCity.ID);
        // Then, this event will be exclusive to the City (act 2), and will show up for all characters.
        // If you want an event that's present at any part of the game, simply don't include the dungeon ID

        // If you want to have more specific event spawning (e.g. character-specific or so)
        // deffo take a look at that basemod wiki link as well, as it explains things very in-depth
        // btw if you don't provide event type, normal is assumed by default

        // Create a new event builder
        // Since this is a builder these method calls (outside of create()) can be skipped/added as necessary
        AddEventParams eventParams = new AddEventParams.Builder(IdentityCrisisEvent.ID, IdentityCrisisEvent.class) // for this specific event
                .dungeonID(TheCity.ID) // The dungeon (act) this event will appear in
                .playerClass(TheDarkest.Enums.THE_DARKEST) // Character specific event
                .create();

        // Add the event
        BaseMod.addEvent(eventParams);

        // =============== /EVENTS/ =================
        logger.info("Done loading badge Image and mod options");
    }

    // =============== / POST-INITIALIZE/ =================

    // ================ ADD POTIONS ===================

    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");

        // Class Specific Potion. If you want your potion to not be class-specific,
        // just remove the player class at the end (in this case the "TheDefaultEnum.THE_DEFAULT".
        // Remember, you can press ctrl+P inside parentheses like addPotions)
        //BaseMod.addPotion(PlaceholderPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, PlaceholderPotion.POTION_ID, TheDefault.Enums.THE_DEFAULT);

        logger.info("Done editing potions");
    }

    // ================ /ADD POTIONS/ ===================


    // ================ ADD RELICS ===================

    @Override
    public void receiveEditRelics() {
        logger.info("Adding mod relics");

        // Take a look at https://github.com/daviscook477/BaseMod/wiki/AutoAdd
        // as well as
        // https://github.com/kiooeht/Bard/blob/e023c4089cc347c60331c78c6415f489d19b6eb9/src/main/java/com/evacipated/cardcrawl/mod/bard/BardMod.java#L319
        // for reference as to how to turn this into an "Auto-Add" rather than having to list every relic individually.
        // Of note is that the bard mod uses it's own custom relic class (not dissimilar to our AbstractDefaultCard class for cards) that adds the 'color' field,
        // in order to automatically differentiate which pool to add the relic too.

        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        //BaseMod.addRelicToCustomPool(new PlaceholderRelic(), TheDefault.Enums.COLOR_GRAY);
       // BaseMod.addRelicToCustomPool(new BottledPlaceholderRelic(), TheDefault.Enums.COLOR_GRAY);
        //BaseMod.addRelicToCustomPool(new DefaultClickableRelic(), TheDefault.Enums.COLOR_GRAY);

        //BaseMod.addRelicToCustomPool(new LightRelic(), TheDefault.Enums.COLOR_GRAY);

        BaseMod.addRelicToCustomPool(new BleedRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new BlightRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new BandanaRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new BeserkRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new CandleRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new DemonsCauldronRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new GuardianRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new GunslingerRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new HairpinRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new HolyRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new LuckyDiceRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new RaiderRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new RestrainingRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new SpikedRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new stressRelic(), TheDarkest.Enums.DARKEST_COLOR);
        //BaseMod.addRelicToCustomPool(new BossStressUpgradeRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new TomeOfHealingRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new VenomousRelic(), TheDarkest.Enums.DARKEST_COLOR);
        BaseMod.addRelicToCustomPool(new WoundingRelic(), TheDarkest.Enums.DARKEST_COLOR);


        // This adds a relic to the Shared pool. Every character can find this relic.
        //BaseMod.addRelic(new PlaceholderRelic2(), RelicType.SHARED);

        // Mark relics as seen - makes it visible in the compendium immediately
        // If you don't have this it won't be visible in the compendium until you see them in game
        // (the others are all starters so they're marked as seen in the character file)
        //UnlockTracker.markRelicAsSeen(BottledPlaceholderRelic.ID);
        UnlockTracker.markRelicAsSeen(BleedRelic.ID);
        UnlockTracker.markRelicAsSeen(BlightRelic.ID);
        UnlockTracker.markRelicAsSeen(BandanaRelic.ID);
        UnlockTracker.markRelicAsSeen(BeserkRelic.ID);
        UnlockTracker.markRelicAsSeen(CandleRelic.ID);
        UnlockTracker.markRelicAsSeen(DemonsCauldronRelic.ID);
        UnlockTracker.markRelicAsSeen(GuardianRelic.ID);
        UnlockTracker.markRelicAsSeen(GunslingerRelic.ID);
        UnlockTracker.markRelicAsSeen(HairpinRelic.ID);
        UnlockTracker.markRelicAsSeen(HolyRelic.ID);
        UnlockTracker.markRelicAsSeen(LuckyDiceRelic.ID);
        UnlockTracker.markRelicAsSeen(RaiderRelic.ID);
        UnlockTracker.markRelicAsSeen(RestrainingRelic.ID);
        UnlockTracker.markRelicAsSeen(SpikedRelic.ID);
        UnlockTracker.markRelicAsSeen(TomeOfHealingRelic.ID);
        UnlockTracker.markRelicAsSeen(VenomousRelic.ID);
        UnlockTracker.markRelicAsSeen(WoundingRelic.ID);

        logger.info("========================= /Relics Added/ =========================");
    }

    // ================ /ADD RELICS/ ===================


    // ================ ADD CARDS ===================

    @Override
    public void receiveEditCards() {
        logger.info("Adding Main variables");
        //Ignore this
        pathCheck();
        // Add the Custom Dynamic Variables
        logger.info("Add Custom Dynamic variables");
        // Add the Custom Dynamic variables
        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());

        logger.info("Adding The Manual cards");
        // Add the cards
        // Don't delete these default cards yet. You need 1 of each type and rarity (technically) for your game not to crash
        // when generating card rewards/shop screen items.

        //Curses
        BaseMod.addCard(new curseRabies());
        BaseMod.addCard(new curseKleptomaniac());
        BaseMod.addCard(new curseCreepingCough());
        BaseMod.addCard(new curseRedPlague());
        BaseMod.addCard(new curseTheRuns());
        //Starters

        BaseMod.addCard(new attackShovel());
        BaseMod.addCard(new skillProvisions());
        BaseMod.addCard(new skillTorch());

        //power
        BaseMod.addCard(new powerAbomination());
        BaseMod.addCard(new powerAntiquarian());
        BaseMod.addCard(new powerArbalest());
        BaseMod.addCard(new powerBountyHunter());
        BaseMod.addCard(new powerCrusader());
        BaseMod.addCard(new powerFlagellant());
        BaseMod.addCard(new powerGraveRobber());
        BaseMod.addCard(new powerHellion());
        BaseMod.addCard(new powerHighwayman());
        BaseMod.addCard(new powerHoundmaster());
        BaseMod.addCard(new powerJester());
        BaseMod.addCard(new powerLeper());
        BaseMod.addCard(new powerManAtArms());
        BaseMod.addCard(new powerOccultist());
        BaseMod.addCard(new powerPlagueDoctor());
        BaseMod.addCard(new powerSheildbreaker());
        BaseMod.addCard(new powerVestal());

        //Attack
        BaseMod.addCard(new attackAbyssalArtillery());
        BaseMod.addCard(new attackAddersKiss());
        BaseMod.addCard(new attackBlindfire());
        BaseMod.addCard(new attackBreakthrough());
        BaseMod.addCard(new attackChop());
        BaseMod.addCard(new attackCollectBounty());
        BaseMod.addCard(new attackDazzlingLight());
        BaseMod.addCard(new attackDirkStab());
        BaseMod.addCard(new attackFinale());
        BaseMod.addCard(new attackFinishHim());
        BaseMod.addCard(new attackHarvest());
        BaseMod.addCard(new attackHoundsHarry());
        BaseMod.addCard(new attackIncision());
        BaseMod.addCard(new attackPickToTheFace());
        BaseMod.addCard(new attackPierce());
        BaseMod.addCard(new attackPointBlankShot());
        BaseMod.addCard(new attackImpale());
        BaseMod.addCard(new attackOpenVein());
        BaseMod.addCard(new attackPunish());
        BaseMod.addCard(new attackRake());
        BaseMod.addCard(new attackRampart());
        BaseMod.addCard(new attackSacrificialStab());
        BaseMod.addCard(new attackStunningBlow());
        BaseMod.addCard(new attackSniperShot());
        BaseMod.addCard(new attackWickedHack());



        //skill
        BaseMod.addCard(new skillAbsolution());
        BaseMod.addCard(new skillBarbaricYawp());
        BaseMod.addCard(new skillBeastBile());
        BaseMod.addCard(new skillBolster());
        BaseMod.addCard(new skillBulwarkOfFaith());
        BaseMod.addCard(new skillDefender());
        BaseMod.addCard(new skillDivineComfort());
        BaseMod.addCard(new skillDuelistsAdvance());
        BaseMod.addCard(new skillEndure());
        BaseMod.addCard(new skillFesteringVapours());
        BaseMod.addCard(new skillFlashingDaggers());
        BaseMod.addCard(new skillGetDown());
        BaseMod.addCard(new skillGuardDog());
        BaseMod.addCard(new skillIllumination());
        BaseMod.addCard(new skillInvigoratingVapours());
        BaseMod.addCard(new skillInspiringCry());
        BaseMod.addCard(new skillMarkForDeath());
        BaseMod.addCard(new skillNoxiousBlast());
        BaseMod.addCard(new skillPlagueGrenade());
        BaseMod.addCard(new skillProtectMe());
        BaseMod.addCard(new skillPurge());
        BaseMod.addCard(new skillRainOfSorrows());
        BaseMod.addCard(new skillRedeem());
        BaseMod.addCard(new skillShadowFade());

        BaseMod.addCard(new skillSnipersMark());
        BaseMod.addCard(new skillTargetWhistle());
        BaseMod.addCard(new skillTrackingShot());
        BaseMod.addCard(new skillWeakeningCurse());
        BaseMod.addCard(new skillWithstand());
        BaseMod.addCard(new skillWyrdReconstruction());
        BaseMod.addCard(new skillZealousAccusation());


        //Defaults & Tests
        //BaseMod.addCard(new afflictFearful());
        //BaseMod.addCard(new afflictAbusive());
        //BaseMod.addCard(new afflictCourageous());
        //BaseMod.addCard(new afflictFearful());
        //BaseMod.addCard(new afflictFocused());
        //BaseMod.addCard(new afflictHopeless());
        //BaseMod.addCard(new afflictIrrational());
        //BaseMod.addCard(new afflictMasochistic());
        //BaseMod.addCard(new afflictParanoid());
        //BaseMod.addCard(new afflictSelfish());
        //BaseMod.addCard(new afflictStalwart());
        //BaseMod.addCard(new afflictVigorous());
        // BaseMod.addCard(new attackPoisonTest());
        //BaseMod.addCard(new DefaultAttackWithVariable());
        //BaseMod.addCard(new attackStrike_s());
        //BaseMod.addCard(new skillBlock_s());

        //BaseMod.addCard(new DefaultSecondMagicNumberSkill());
        //BaseMod.addCard(new DefaultCommonSkill());
        //BaseMod.addCard(new DefaultCommonPower());
        //BaseMod.addCard(new DefaultUncommonSkill());
        //BaseMod.addCard(new DefaultUncommonPower());
        //BaseMod.addCard(new DefaultRareSkill());
        //BaseMod.addCard(new DefaultRarePower());
        //BaseMod.addCard(new OrbSkill());


        logger.info("Making sure the cards are unlocked.");
        // Unlock the cards
        // This is so that they are all "seen" in the library, for people who like to look at the card list
        // before playing your mod.

        //Starters

        UnlockTracker.unlockCard(skillTorch.ID);
        UnlockTracker.unlockCard(attackShovel.ID);
        UnlockTracker.unlockCard(skillProvisions.ID);

        //Power
        UnlockTracker.unlockCard(powerAbomination.ID);
        UnlockTracker.unlockCard(powerArbalest.ID);
        UnlockTracker.unlockCard(powerBountyHunter.ID);
        UnlockTracker.unlockCard(powerAntiquarian.ID);
        UnlockTracker.unlockCard(powerCrusader.ID);
        UnlockTracker.unlockCard(powerFlagellant.ID);
        UnlockTracker.unlockCard(powerGraveRobber.ID);
        UnlockTracker.unlockCard(powerHellion.ID);
        UnlockTracker.unlockCard(powerHighwayman.ID);
        UnlockTracker.unlockCard(powerHoundmaster.ID);
        UnlockTracker.unlockCard(powerJester.ID);
        UnlockTracker.unlockCard(powerLeper.ID);
        UnlockTracker.unlockCard(powerManAtArms.ID);
        UnlockTracker.unlockCard(powerOccultist.ID);
        UnlockTracker.unlockCard(powerPlagueDoctor.ID);
        UnlockTracker.unlockCard(powerSheildbreaker.ID);
        UnlockTracker.unlockCard(powerVestal.ID);

        //Attacks
        UnlockTracker.unlockCard(attackAbyssalArtillery.ID);
        UnlockTracker.unlockCard(attackAddersKiss.ID);
        UnlockTracker.unlockCard(attackBlindfire.ID);
        UnlockTracker.unlockCard(attackBreakthrough.ID);
        UnlockTracker.unlockCard(attackChop.ID);
        UnlockTracker.unlockCard(attackCollectBounty.ID);
        UnlockTracker.unlockCard(attackDazzlingLight.ID);
        UnlockTracker.unlockCard(attackDirkStab.ID);
        UnlockTracker.unlockCard(attackFinale.ID);
        UnlockTracker.unlockCard(attackFinishHim.ID);
        UnlockTracker.unlockCard(attackHarvest.ID);
        UnlockTracker.unlockCard(attackHoundsHarry.ID);
        UnlockTracker.unlockCard(attackIncision.ID);
        UnlockTracker.unlockCard(attackPickToTheFace.ID);
        UnlockTracker.unlockCard(attackPierce.ID);
        UnlockTracker.unlockCard(attackPointBlankShot.ID);
        UnlockTracker.unlockCard(attackImpale.ID);
        UnlockTracker.unlockCard(attackOpenVein.ID);
        UnlockTracker.unlockCard(attackPunish.ID);
        UnlockTracker.unlockCard(attackRake.ID);
        UnlockTracker.unlockCard(attackRampart.ID);
        UnlockTracker.unlockCard(attackSacrificialStab.ID);
        UnlockTracker.unlockCard(attackStunningBlow.ID);
        UnlockTracker.unlockCard(attackSniperShot.ID);
        UnlockTracker.unlockCard(attackWickedHack.ID);



        //Skills
        UnlockTracker.unlockCard(skillAbsolution.ID);
        UnlockTracker.unlockCard(skillBarbaricYawp.ID);
        UnlockTracker.unlockCard(skillBeastBile.ID);
        UnlockTracker.unlockCard(skillBolster.ID);
        UnlockTracker.unlockCard(skillBulwarkOfFaith.ID);
        UnlockTracker.unlockCard(skillDefender.ID);
        UnlockTracker.unlockCard(skillDivineComfort.ID);
        UnlockTracker.unlockCard(skillDuelistsAdvance.ID);
        UnlockTracker.unlockCard(skillEndure.ID);
        UnlockTracker.unlockCard(skillFesteringVapours.ID);
        UnlockTracker.unlockCard(skillFlashingDaggers.ID);
        UnlockTracker.unlockCard(skillGetDown.ID);
        UnlockTracker.unlockCard(skillGuardDog.ID);
        UnlockTracker.unlockCard(skillIllumination.ID);
        UnlockTracker.unlockCard(skillInvigoratingVapours.ID);
        UnlockTracker.unlockCard(skillInspiringCry.ID);
        UnlockTracker.unlockCard(skillMarkForDeath.ID);
        UnlockTracker.unlockCard(skillNoxiousBlast.ID);
        UnlockTracker.unlockCard(skillPlagueGrenade.ID);
        UnlockTracker.unlockCard(skillPurge.ID);
        UnlockTracker.unlockCard(skillProtectMe.ID);
        UnlockTracker.unlockCard(skillRainOfSorrows.ID);
        UnlockTracker.unlockCard(skillRedeem.ID);
        UnlockTracker.unlockCard(skillShadowFade.ID);
        UnlockTracker.unlockCard(skillSnipersMark.ID);
        UnlockTracker.unlockCard(skillTargetWhistle.ID);
        UnlockTracker.unlockCard(skillTrackingShot.ID);
        UnlockTracker.unlockCard(skillWeakeningCurse.ID);
        UnlockTracker.unlockCard(skillWithstand.ID);
        UnlockTracker.unlockCard(skillWyrdReconstruction.ID);
        UnlockTracker.unlockCard(skillZealousAccusation.ID);


        //Defaults & Tests
        //UnlockTracker.unlockCard(AfflictionTemp.ID);
        // UnlockTracker.unlockCard(attackStrike_s.ID);
        // UnlockTracker.unlockCard(skillBlock_s.ID);
        // UnlockTracker.unlockCard(attackPoisonTest.ID);
        // UnlockTracker.unlockCard(DefaultAttackWithVariable.ID);
        // UnlockTracker.unlockCard(DefaultSecondMagicNumberSkill.ID);
        // UnlockTracker.unlockCard(DefaultCommonSkill.ID);
        // UnlockTracker.unlockCard(DefaultCommonPower.ID);
        // UnlockTracker.unlockCard(DefaultUncommonSkill.ID);
        //UnlockTracker.unlockCard(DefaultUncommonPower.ID);
        // UnlockTracker.unlockCard(DefaultRareSkill.ID);
        //UnlockTracker.unlockCard(DefaultRarePower.ID);
        // UnlockTracker.unlockCard(OrbSkill.ID);
        logger.info("========================= /Cards Added/ =========================");
    }

    // ================ /ADD CARDS/ ===================


    // ================ LOAD THE TEXT ===================

    @Override
    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());

        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Card-Strings.json");

        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Power-Strings.json");

        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Relic-Strings.json");

        // Event Strings
        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Event-Strings.json");

        // PotionStrings
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Potion-Strings.json");

        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Character-Strings.json");

        // OrbStrings
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Orb-Strings.json");

        logger.info("Done editing strings");
    }

    // ================ /LOAD THE TEXT/ ===================

    // ================ LOAD THE KEYWORDS ===================

    @Override
    public void receiveEditKeywords() {
        // Keywords on cards are supposed to be Capitalized, while in Keyword-String.json they're lowercase
        //
        // Multiword keywords on cards are done With_Underscores
        //
        // If you're using multiword keywords, the first element in your NAMES array in your keywords-strings.json has to be the same as the PROPER_NAME.
        // That is, in Card-Strings.json you would have #yA_Long_Keyword (#y highlights the keyword in yellow).
        // In Keyword-Strings.json you would have PROPER_NAME as A Long Keyword and the first element in NAMES be a long keyword, and the second element be a_long_keyword

        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/DefaultMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
                //  getModID().toLowerCase() makes your keyword mod specific (it won't show up in other cards that use that word)
            }
        }
    }

    // ================ /LOAD THE KEYWORDS/ ===================

    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}