package DarkestMod.powers;

import DarkestMod.DefaultMod;
import DarkestMod.actions.AfflictedAction;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;


import static DarkestMod.DefaultMod.makePowerPath;


public class powerStress extends AbstractPower implements CloneablePowerInterface {


    public static final String POWER_ID = DefaultMod.makeID("PowerStress");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int basePower;
    public static final int REDUCTION_FACTOR = 3;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("stress_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("stress_power32.png"));


    public powerStress(AbstractCreature owner, int stressAmount) {
        this.name = NAME;
        this.ID = POWER_ID;

        this.owner = owner;

        this.amount = stressAmount;
        this.basePower = stressAmount;
        if (this.amount >= 200) {
            this.amount = 200;
        }
        if (this.amount <= 0) {
            this.amount = 0;
        }
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();

    }
    public void atEndOfTurn(final boolean isPlayer) {
        if (!this.owner.isPlayer) {
            this.amount = this.basePower;
            this.updateDescription();
        }
    }

    public void atStartOfTurn() {
        if (this.amount >= 100) {
            this.flash();
            this.addToBot(new AfflictedAction(1));
        }
    }


    @Override
    public void updateDescription() {
        if (this.owner != null && !this.owner.isPlayer) {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount < this.owner.currentHealth && damageAmount > 0 && info.owner != null) {
            this.flash();
            this.amount += damageAmount;
            this.updateDescription();
        }
        return damageAmount;
    }
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        this.basePower += stackAmount;

    }

    @Override
        public AbstractPower makeCopy() {
            return new powerStress(owner, amount);
        }

}
