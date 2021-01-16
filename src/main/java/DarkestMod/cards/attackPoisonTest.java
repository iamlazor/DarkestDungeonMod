package DarkestMod.cards;

import DarkestMod.DefaultMod;
import DarkestMod.characters.TheDefault;
import DarkestMod.powers.powerBlight;
import DarkestMod.powers.powerStress;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import static DarkestMod.DefaultMod.makeCardPath;

public class attackPoisonTest extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(attackPoisonTest.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 2;
    private static final int STRESS_GEN = 5;


    public attackPoisonTest() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = 4;
        magicNumber = baseMagicNumber;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = STRESS_GEN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new powerBlight(m, p, this.magicNumber), this.magicNumber));

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerStress(AbstractDungeon.player, defaultSecondMagicNumber),defaultSecondMagicNumber));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.initializeDescription();
        }
    }


}
