package DarkestMod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DarkestMod.DarkestMod;
import DarkestMod.characters.TheDarkest;
import DarkestMod.powers.RarePower;

import static DarkestMod.DarkestMod.makeCardPath;

public class DefaultRarePower extends AbstractDynamicCard {
    
    public static final String ID = DarkestMod.makeID(DefaultRarePower.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");
    
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDarkest.Enums.DARKEST_COLOR;
    
    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;
    
    private static final int MAGIC = 1;
    
    public DefaultRarePower() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        tags.add(BaseModCardTags.FORM);
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new RarePower(p, p, magicNumber), magicNumber));
    }
    
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}
