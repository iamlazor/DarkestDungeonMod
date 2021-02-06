package DarkestMod.powers;

import DarkestMod.DefaultMod;
import DarkestMod.cards.AfflictionTemp;
import DarkestMod.cards.afflictParanoid;
import DarkestMod.cards.attackChop;
import DarkestMod.patches.CardTagEnum;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static DarkestMod.DefaultMod.makePowerPath;

public class powerAffliction extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = DefaultMod.makeID("PowerAffliction");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("stress_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("stress_power32.png"));

    public powerAffliction(AbstractCreature owner, int afflictionAmount) {
        this.name = NAME;
        this.ID = POWER_ID;

        this.owner = owner;
        this.amount = afflictionAmount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
        }
                //new FetchAction(AbstractDungeon.player.discardPile, (card -> card.hasTag(CardTagEnum.AFFLICTION)), this.amount));

    public void atStartOfTurn() {

        int random = AbstractDungeon.miscRng.random(0, 99);

        if (this.amount == 1 && random >=  50) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new AfflictionTemp(), 1));
            //this.amount -= 1;
        } else {
           if (this.amount == 1 && random < 49);
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictParanoid(), 1));
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

    @Override
    public AbstractPower makeCopy() {
        return new powerAffliction(owner, amount);
    }

}

