package DarkestMod.cards;

import DarkestMod.powers.HoundPower;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DarkestMod.DarkestMod;
import DarkestMod.characters.TheDarkest;

import static DarkestMod.DarkestMod.makeCardPath;

public class powerHoundmaster extends AbstractDynamicCard {

    public static final String ID = DarkestMod.makeID("Houndmaster");
    public static final String IMG = makeCardPath("powerTheHoundmaster.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDarkest.Enums.DARKEST_COLOR;

    private static final int COST = 1;
    private static final int MAGIC = 8;
    private static final int UPGRADE_MAGIC = 4;

    public powerHoundmaster() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        this.cardsToPreview = new skillDogBiscuit();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HoundPower(AbstractDungeon.player, 1), 1));
        AbstractDungeon.actionManager.addToBottom(
                new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
             initializeDescription();
        }
    }
}