package DarkestMod.cards;

import DarkestMod.powers.CrusaderPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DarkestMod.DarkestMod;
import DarkestMod.characters.TheDarkest;

import static DarkestMod.DarkestMod.makeCardPath;

public class powerCrusader extends AbstractDynamicCard {

    public static final String ID = DarkestMod.makeID("Crusader");
    public static final String IMG = makeCardPath("powerTheCrusader.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDarkest.Enums.DARKEST_COLOR;

    private static final int COST = 1;
    private static final int BlockAMT = 3;
    private static final int UPGRADE_BlockAMT = 1;

    public powerCrusader() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BlockAMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom
                (new ApplyPowerAction(p, p, new CrusaderPower(p,this.BlockAMT), this.BlockAMT));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BlockAMT);
            initializeDescription();
        }
    }
}