package DarkestMod.powers;

import DarkestMod.DefaultMod;
import DarkestMod.cards.*;
import DarkestMod.relics.BeserkRelic;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


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
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.canGoNegative = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();

    }

    public void atEndOfTurn(final boolean isPlayer) {
        int random = AbstractDungeon.miscRng.random(0, 99);

         if (this.amount >= 100 && this.amount <= 200 && !owner.hasPower(AfflictionAbusive.POWER_ID) && !owner.hasPower(AfflictionDeathsDoor.POWER_ID) && !owner.hasPower(AfflictionFearful.POWER_ID) && !owner.hasPower(AfflictionHopeless.POWER_ID) && !owner.hasPower(AfflictionIrrational.POWER_ID)  && !owner.hasPower(AfflictionMasochistic.POWER_ID) && !owner.hasPower(AfflictionParanoid.POWER_ID)  && !owner.hasPower(AfflictionSelfish.POWER_ID) && !owner.hasPower(VirtueCourageous.POWER_ID) && !owner.hasPower(VirtueFocused.POWER_ID)&& !owner.hasPower(VirtuePowerful.POWER_ID)&& !owner.hasPower(VirtueStalwart.POWER_ID) && !owner.hasPower(VirtueVigorous.POWER_ID)) { //makes it a do once
            if (AbstractDungeon.player.hasRelic(BeserkRelic.ID)) {
                 if (random <= 9) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionFearful(AbstractDungeon.player, 1), 1));
                 } else if (random <= 18) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionParanoid(AbstractDungeon.player, 1), 1));
                 } else if (random <= 27) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionSelfish(AbstractDungeon.player, 1), 1));

                 } else if (random <= 36) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionMasochistic(AbstractDungeon.player, 1), 1));
                 } else if (random <= 45) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionAbusive(AbstractDungeon.player, 1), 1));

                 } else if (random <= 54) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionHopeless(AbstractDungeon.player, 1), 1));

                 } else if (random <= 64) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionIrrational(AbstractDungeon.player, 1), 1));
                 }
                 // Virtues
                 else if (random <= 70) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VirtueStalwart(AbstractDungeon.player, 1), 1));

                 } else if (random <= 77) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VirtueCourageous(AbstractDungeon.player, 1), 1));
                 } else if (random <= 84) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VirtueFocused(AbstractDungeon.player, 1), 1));
                 } else if (random <= 91) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VirtuePowerful(AbstractDungeon.player, 1), 1));
                 } else if (random <= 99) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VirtueVigorous(AbstractDungeon.player, 1), 1));
                 }
             } else {
                 if (random <= 11) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionFearful(AbstractDungeon.player, 1), 1));
                 } else if (random <= 23) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionParanoid(AbstractDungeon.player, 1), 1));
                 } else if (random <= 35) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionSelfish(AbstractDungeon.player, 1), 1));

                 } else if (random <= 47) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionMasochistic(AbstractDungeon.player, 1), 1));
                 } else if (random <= 59) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionAbusive(AbstractDungeon.player, 1), 1));

                 } else if (random <= 70) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionHopeless(AbstractDungeon.player, 1), 1));

                 } else if (random <= 84) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionIrrational(AbstractDungeon.player, 1), 1));
                 }
                 // Virtues
                 else if (random <= 87) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VirtueStalwart(AbstractDungeon.player, 1), 1));

                 } else if (random <= 90) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VirtueCourageous(AbstractDungeon.player, 1), 1));
                 } else if (random <= 93                 ) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VirtueFocused(AbstractDungeon.player, 1), 1));
                 } else if (random <= 96) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VirtuePowerful(AbstractDungeon.player, 1), 1));
                 } else if (random <= 99) {
                     this.flash();
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VirtueVigorous(AbstractDungeon.player, 1), 1));
                 }
             }
        }
    }



    public void atStartOfTurn() {
    if (this.amount >= 200 && !owner.hasPower(AfflictionDeathsDoor.POWER_ID)) {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AfflictionDeathsDoor(AbstractDungeon.player, 1)));
    }
    }


    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[2]);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if  (owner.hasPower(JesterPower.POWER_ID) && damageAmount < this.owner.currentHealth && damageAmount > 0 && info.owner != null && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            this.amount += damageAmount -=1 ;
            this.updateDescription();
        } else if
        (damageAmount < this.owner.currentHealth && damageAmount > 0 && info.owner != null && info.type == DamageInfo.DamageType.NORMAL) {
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
