package DarkestMod.cards;

import DarkestMod.DarkestMod;
import DarkestMod.characters.TheDarkest;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.curses.Normality;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DarkestMod.DarkestMod.makeCardPath;

public class afflictHopeless extends AbstractDynamicCard {

    public static final String ID = DarkestMod.makeID("Hopeless");
    public static final String IMG = makeCardPath("hopeless.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = TheDarkest.Enums.DARKEST_COLOR;

    private static final int COST = -2;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public afflictHopeless() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Normality(), 5,true, true));
        }
    }



    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
