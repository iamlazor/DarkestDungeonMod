package DarkestMod.cards;

import DarkestMod.actions.HoundAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DarkestMod.DarkestMod;
import DarkestMod.characters.TheDarkest;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import java.util.Iterator;

import static DarkestMod.DarkestMod.makeCardPath;

public class attackHoundsHarry extends AbstractDynamicCard {

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

    public static final String ID = DarkestMod.makeID("Hound's Harry"); // DefaultMod.makeID("attackNailStrike");

    public static final String IMG = makeCardPath("attackHoundsharry.png");// "public static final String IMG = makeCardPath("attackNailStrike.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDarkest.Enums.DARKEST_COLOR;

    private static final int COST = 1;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int UPGRADE_PLUS_BLEED = 2;

    // STAT DECLARATION

    public attackHoundsHarry() { // public attackNailStrike() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = 3;
        magicNumber = baseMagicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!m.isDeadOrEscaped()) {
            this.addToBot(new VFXAction(new BiteEffect(m.drawX, m.drawY), 0.0F));

            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn)));

            AbstractDungeon.actionManager.addToBottom(
                    new HoundAction(m));
        }
    }

        public void triggerOnGlowCheck() {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while(var1.hasNext()) {
                AbstractMonster m = (AbstractMonster)var1.next();
                if (!m.isDeadOrEscaped() && m.hasPower("PowerBleed")) {
                    this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                    break;
                }
            }
        }

        // Upgraded stats.
        @Override
        public void upgrade() {
            if (!upgraded) {
                upgradeName();
                upgradeDamage(UPGRADE_PLUS_DMG);
                this.upgradeMagicNumber(UPGRADE_PLUS_BLEED);
                initializeDescription();
            }
        }
    }