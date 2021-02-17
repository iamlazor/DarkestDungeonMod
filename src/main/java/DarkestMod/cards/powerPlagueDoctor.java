package DarkestMod.cards;

import DarkestMod.actions.BlightTriggerAction;
import DarkestMod.powers.PlaguePower;
import DarkestMod.powers.powerBlight;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DarkestMod.DefaultMod;
import DarkestMod.characters.TheDefault;
import DarkestMod.powers.CommonPower;
import com.megacrit.cardcrawl.powers.NoxiousFumesPower;

import java.util.Iterator;

import static DarkestMod.DefaultMod.makeCardPath;

public class powerPlagueDoctor extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID("PlagueDoctor");
    public static final String IMG = makeCardPath("powerThePlagueDoctor.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

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
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

            while (var3.hasNext()) {
                AbstractMonster monster = (AbstractMonster) var3.next();
                if (!monster.isDead && !monster.isDying) {
                    AbstractDungeon.actionManager.addToBottom(
                            new BlightTriggerAction(monster, p));

                }
            }
        }
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