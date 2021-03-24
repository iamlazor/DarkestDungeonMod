package DarkestMod.cards;

import DarkestMod.DarkestMod;
import DarkestMod.characters.TheDarkest;
import DarkestMod.powers.JesterPower;
import DarkestMod.powers.powerStress;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DarkestMod.DarkestMod.makeCardPath;

public class powerJester extends AbstractDynamicCard{

    public static final String ID = DarkestMod.makeID("Jester");
    public static final String IMG = makeCardPath("powerTheJester.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDarkest.Enums.DARKEST_COLOR;

    private static final int COST = 1;
    private static final int UPGRADEDCOST = 0;
    private static final int STRESS_GEN = -8;
    private static final int UPGRADE_STRESS = -4;

    public powerJester(){
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = STRESS_GEN;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerStress(AbstractDungeon.player,magicNumber),magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new JesterPower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADEDCOST);
            upgradeMagicNumber(UPGRADE_STRESS);
            initializeDescription();
        }
    }
}
