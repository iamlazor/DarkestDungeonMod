package DarkestMod.cards;

import DarkestMod.powers.powerRiposte;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DarkestMod.DefaultMod;
import DarkestMod.characters.TheDefault;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static DarkestMod.DefaultMod.makeCardPath;

public class powerManAtArms extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID("ManAtArms");
    public static final String IMG = makeCardPath("powerTheManAtArms.png");


    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int COUNTER = 3;
    private static final int UPGRADE_COUNTER = 2;



    public powerManAtArms() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = COUNTER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerRiposte(AbstractDungeon.player, COUNTER), COUNTER));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlatedArmorPower(AbstractDungeon.player, COUNTER), COUNTER));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_COUNTER);
            initializeDescription();
        }
    }
}