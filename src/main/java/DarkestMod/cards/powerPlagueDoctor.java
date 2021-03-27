package DarkestMod.cards;

import DarkestMod.actions.BlightTriggerAction;
import DarkestMod.powers.PlaguePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DarkestMod.DarkestMod;
import DarkestMod.characters.TheDarkest;

import java.util.Iterator;

import static DarkestMod.DarkestMod.makeCardPath;

public class powerPlagueDoctor extends AbstractDynamicCard {

    public static final String ID = DarkestMod.makeID("PlagueDoctor");
    public static final String IMG = makeCardPath("powerThePlagueDoctor.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDarkest.Enums.DARKEST_COLOR;

    private static final int COST = 1;
    private static final int BLIGHT = 2;
    private static final int UPGRADE_BLIGHT = 1;

    public powerPlagueDoctor() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BLIGHT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new PlaguePower(p, this.magicNumber), this.magicNumber));

        }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BLIGHT);
            initializeDescription();
        }
    }
}