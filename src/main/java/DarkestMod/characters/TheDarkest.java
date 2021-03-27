package DarkestMod.characters;

import DarkestMod.relics.*;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import DarkestMod.DarkestMod;
import DarkestMod.cards.*;

import java.util.ArrayList;

import static DarkestMod.DarkestMod.*;
import static DarkestMod.characters.TheDarkest.Enums.DARKEST_COLOR;

public class TheDarkest extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(DarkestMod.class.getName());
    
    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_DARKEST;
        @SpireEnum(name = "DARKEST_BLACK_COLOR")
        public static AbstractCard.CardColor DARKEST_COLOR;
        @SpireEnum(name = "DARKEST_BLACK_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
    
    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 6;
    public static final int ORB_SLOTS = 0;
    
    private static final String ID = makeID("DefaultCharacter");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;
    
    public static final String[] orbTextures = {
            "DarkestModResources/images/char/defaultCharacter/orb/layer1.png",
            "DarkestModResources/images/char/defaultCharacter/orb/layer2.png",
            "DarkestModResources/images/char/defaultCharacter/orb/layer3.png",
            "DarkestModResources/images/char/defaultCharacter/orb/layer4.png",
            "DarkestModResources/images/char/defaultCharacter/orb/layer5.png",
            "DarkestModResources/images/char/defaultCharacter/orb/layer6.png",
            "DarkestModResources/images/char/defaultCharacter/orb/layer1d.png",
            "DarkestModResources/images/char/defaultCharacter/orb/layer2d.png",
            "DarkestModResources/images/char/defaultCharacter/orb/layer3d.png",
            "DarkestModResources/images/char/defaultCharacter/orb/layer4d.png",
            "DarkestModResources/images/char/defaultCharacter/orb/layer5d.png",};
    
    public TheDarkest(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "DarkestModResources/images/char/defaultCharacter/orb/vfx.png", null,
                new SpineAnimation(
                        "DarkestModResources/images/char/defaultCharacter/skeleton.atlas", "DarkestModResources/images/char/defaultCharacter/skeleton.json", 1f));

        
        initializeClass(null,
                
                THE_DARKEST_SHOULDER_1,
                THE_DARKEST_SHOULDER_2,
                THE_DARKEST_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
        
        
        loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle_1", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        
        
        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);
    }
    
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }
    
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        
        logger.info("Begin loading starter Deck Strings");
        //starters



        retVal.add(skillProvisions.ID);
        retVal.add(skillProvisions.ID);
        retVal.add(skillProvisions.ID);
        retVal.add(skillProvisions.ID);
        retVal.add(attackShovel.ID);
        retVal.add(attackShovel.ID);
        retVal.add(attackShovel.ID);
        retVal.add(attackShovel.ID);

        retVal.add(attackAbyssalArtillery.ID);
        retVal.add(skillTorch.ID);

        //tests
        //retVal.add(afflictFearful.ID);

        //retVal.add(powerManAtArms.ID);
        //retVal.add(powerLeper.ID);
        //retVal.add(powerGraveRobber.ID);
        //retVal.add(powerFlagellant.ID);
        //retVal.add(powerCrusader.ID);
        //retVal.add(powerAbomination.ID);
        //retVal.add(powerAntiquarian.ID);
        //retVal.add(powerHoundmaster.ID);
        //retVal.add(powerVestal.ID);
        //retVal.add(powerArbalest.ID);
        //retVal.add(powerBountyHunter.ID);
        //retVal.add(powerHighwayman.ID);
        //retVal.add(powerJester.ID);
        //retVal.add(powerHellion.ID);
        //retVal.add(powerPlagueDoctor.ID);


        //retVal.add(AfflictionTemp.ID);
        //retVal.add(attackChop.ID);
        //retVal.add(attackPickToTheFace.ID);
       //retVal.add(attackRampart.ID);
        //retVal.add(attackPunish.ID);
        //retVal.add(attackHarvest.ID);
        // retVal.add(attackHoundsHarry.ID);
        //retVal.add(attackAddersKiss.ID);
        //retVal.add(attackDirkStab.ID);
        //retVal.add(attackPierce.ID);
        //retVal.add(attackPoisonDart.ID);
        //retVal.add(attackRake.ID);
        //retVal.add(attackCollectBounty.ID);
        //retVal.add(attackBlindfire.ID);
        //retVal.add(attackFinishHim.ID);
        //retVal.add(skillSnipersMark.ID);
        //retVal.add(attackBreakthrough.ID);v
        //retVal.add(attackAbyssalArtillery.ID);
        //retVal.add(attackSacrificialStab.ID);
        //retVal.add(attackDazzlingLight.ID);
        //retVal.add(attackHoundsHarry.ID);
        //retVal.add(attackPointBlankShot.ID);
        //retVal.add(attackFinale.ID);
        //retVal.add(attackFinishHim.ID);
        //retVal.add(attackOpenVein.ID);
        //retVal.add(attackStunningBlow.ID);

        //retVal.add(skillTrackingShot.ID);
        //retVal.add(skillBarbaricYawp.ID);
        //retVal.add(skillZealousAccusation.ID);
        //retVal.add(skillBeastBile.ID);
        //retVal.add(skillInspiringCry.ID);
        //retVal.add(skillNoxiousBlast.ID);
        //retVal.add(skillWyrdReconstruction.ID);
        //retVal.add(skillIllumination.ID);
        //retVal.add(skillFesteringVapours.ID);
        //retVal.add(skillDuelistsAdvance.ID);
        //retVal.add(skillBolster.ID);
        //retVal.add(skillBulwarkOfFaith.ID);
        //retVal.add(skillGuardDog.ID);
        //retVal.add(skillGetDown.ID);
        //retVal.add(skillProtectMe.ID);
        //retVal.add(skillPlagueGrenade.ID);
        //retVal.add(skillDefender.ID);
        //retVal.add(skillShadowFade.ID);
        //retVal.add(attackIncision.ID);
        //retVal.add(skillWeakeningCurse.ID);
        //retVal.add(skillTargetWhistle.ID);
        //retVal.add(skillRainOfSorrows.ID);
        //retVal.add(skillFlashingDaggers.ID);
        //retVal.add(skillSerpentSway.ID);
       // retVal.add(skillSnipersMark.ID);
        //retVal.add(skillRedeem.ID);
        //retVal.add(skillPurge.ID);
        //retVal.add(skillDivineComfort.ID);
        //retVal.add(skillInvigoratingVapours.ID);
        //retVal.add(skillWithstand.ID);
        //retVal.add(skillSolemnity.ID);



       //retVal.add(attackPoisonTest.ID);
       //retVal.add(DefaultSecondMagicNumberSkill.ID);

        logger.info("Finished loading starter Deck Strings");

        return retVal;
    }
    
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        

       retVal.add(stressRelic.ID);
       //retVal.add(BossStressUpgradeRelic.ID);
       //retVal.add(BleedRelic.ID);
       //retVal.add(BlightRelic.ID);

        //retVal.add(TomeOfHealingRelic.ID);
        //retVal.add(DemonsCauldronRelic.ID);
        //retVal.add(LuckyDiceRelic.ID);
        //retVal.add(RestrainingRelic.ID);
        //retVal.add(CandleRelic.ID);
        //retVal.add(WoundingRelic.ID);
        //retVal.add(HolyRelic.ID);
        //retVal.add(RaiderRelic.ID);
        //retVal.add(GunslingerRelic.ID);
        //retVal.add(SpikedRelic.ID);
        //retVal.add(GuardianRelic.ID);
        //retVal.add(VenomousRelic.ID);
        //retVal.add(BeserkRelic.ID);


        UnlockTracker.markRelicAsSeen(stressRelic.ID);


        return retVal;
    }
    
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }
    
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }
    
    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }
    
    @Override
    public AbstractCard.CardColor getCardColor() {
        return DARKEST_COLOR;
    }
    
    @Override
    public Color getCardTrailColor() {
        return DarkestMod.DARKEST_BLACK;
    }
    
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }
    
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }
    
    @Override
    public AbstractCard getStartCardForEvent() {
        return new DefaultCommonAttack();
    }
    
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }
    
    @Override
    public AbstractPlayer newInstance() {
        return new TheDarkest(name, chosenClass);
    }
    
    @Override
    public Color getCardRenderColor() {
        return DarkestMod.DARKEST_BLACK;
    }
    
    @Override
    public Color getSlashAttackColor() {
        return DarkestMod.DARKEST_BLACK;
    }
    
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }
    
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }
    
    @Override
    public String getVampireText() {
        return TEXT[2];
    }
}
