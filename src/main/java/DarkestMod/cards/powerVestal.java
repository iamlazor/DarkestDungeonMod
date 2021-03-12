package DarkestMod.cards;

import DarkestMod.powers.VestalPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DarkestMod.DefaultMod;
import DarkestMod.characters.TheDefault;

import static DarkestMod.DefaultMod.makeCardPath;

public class powerVestal extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID("Vestal");
    public static final String IMG = makeCardPath("powerTheVestal.png");


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int LIGHTAMT = 10;
    private static final int LIGHTAMTUPGRADE = -2;


    public powerVestal() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = LIGHTAMT;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                (new ApplyPowerAction(p, p, new VestalPower(p,1),1)));
        AbstractDungeon.actionManager.addToBottom(
                new ApotheosisAction());
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }else {
            if (AbstractDungeon.player.getPower("PowerLight").amount < LIGHTAMT){
                canUse = false;
        }
    }
        return canUse;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
            upgradeMagicNumber(LIGHTAMTUPGRADE);
        }
    }
}