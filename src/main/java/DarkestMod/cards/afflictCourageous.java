package DarkestMod.cards;

import DarkestMod.DarkestMod;
import DarkestMod.characters.TheDarkest;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static DarkestMod.DarkestMod.makeCardPath;

public class afflictCourageous extends AbstractDynamicCard {

    public static final String ID = DarkestMod.makeID("Courageous");
    public static final String IMG = makeCardPath("courageous.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.CURSE;
    public static final CardColor COLOR = TheDarkest.Enums.DARKEST_COLOR;

    private static final int COST = -2;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public afflictCourageous() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        this.exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player,3),3));

            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player,3),3));
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
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
