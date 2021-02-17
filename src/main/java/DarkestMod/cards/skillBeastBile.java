package DarkestMod.cards;

import DarkestMod.actions.BlightTriggerAction;
import DarkestMod.powers.powerBlight;
import DarkestMod.powers.powerStress;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DarkestMod.DefaultMod;
import DarkestMod.characters.TheDefault;

import static DarkestMod.DefaultMod.makeCardPath;

public class skillBeastBile extends AbstractDynamicCard {

    /*
     * "Hey, I wanna make a bunch of cards now." - You, probably.
     * ok cool my dude no problem here's the most convenient way to do it:
     *
     * Copy all of the code here (Ctrl+A > Ctrl+C)
     * Ctrl+Shift+A and search up "file and code template"
     * Press the + button at the top and name your template whatever it is for - "AttackCard" or "PowerCard" or something up to you.
     * Read up on the instructions at the bottom. Basically replace anywhere you'd put your cards name with attackNailStrike
     * And then you can do custom ones like 6 and  if you want.
     * I'll leave some comments on things you might consider replacing with what.
     *
     * Of course, delete all the comments and add anything you want (For example, if you're making a skill card template you'll
     * likely want to replace that new DamageAction with a gain Block one, and add baseBlock instead, or maybe you want a
     * universal template where you delete everything unnecessary - up to you)
     *
     * You can create templates for anything you ever want to. Cards, relics, powers, orbs, etc. etc. etc.
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID("Beast Bile"); // DefaultMod.makeID("attackNailStrike");

    public static final String IMG = makeCardPath("skillBeastBile.png");// "public static final String IMG = makeCardPath("attackNailStrike.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int BLIGHT= 8;
    private static final int UPGRADE_PLUS_BLIGHT = 4;



    // STAT DECLARATION

    public skillBeastBile() { // public attackNailStrike() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = 3;
        magicNumber = baseMagicNumber = BLIGHT;


    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(m, p, new powerBlight(m, p, this.magicNumber), this.magicNumber));

            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerStress(AbstractDungeon.player, 4), 4));

        } else {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(m, p, new powerBlight(m, p, this.magicNumber), this.magicNumber));

            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerStress(AbstractDungeon.player, 4), 4));
            AbstractDungeon.actionManager.addToBottom(
                    new BlightTriggerAction(m, p));

        }
    }



    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_BLIGHT);
            initializeDescription();
        }
    }
}
