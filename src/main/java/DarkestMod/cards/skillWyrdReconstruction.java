package DarkestMod.cards;

import DarkestMod.powers.powerBleed;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DarkestMod.DefaultMod;
import DarkestMod.characters.TheDefault;

import static DarkestMod.DefaultMod.makeCardPath;

public class skillWyrdReconstruction extends AbstractDynamicCard {

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

    public static final String ID = DefaultMod.makeID("Wyrd Reconstruction"); // DefaultMod.makeID("attackNailStrike");

    public static final String IMG = makeCardPath("skillWyrdReconstruction.png");// "public static final String IMG = makeCardPath("attackNailStrike.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;


    // STAT DECLARATION

    public skillWyrdReconstruction() { // public attackNailStrike() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CardTags.HEALING);
        baseMagicNumber = 3;
        magicNumber = baseMagicNumber;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int random = AbstractDungeon.miscRng.random(0, 12);
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new powerBleed(p, p, this.magicNumber), this.magicNumber));

        if ( random == 0) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 0));
        } else if (random == 1) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1));
        } else if (random == 2) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 2));
        }else if (random == 3) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 3));
        }else if (random == 4) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 4));
        }else if (random == 5) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 5));
        }else if (random == 6) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 6));
        }else if (random == 7) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 7));
        }else if (random == 8) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 8));
        }else if (random ==9) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 9));
        }else if (random == 10) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 10));
        }else if (random == 11) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 11));
        }else if (random == 12) {
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, 12));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
