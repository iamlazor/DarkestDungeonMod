package DarkestMod.powers;

import DarkestMod.DarkestMod;
import DarkestMod.cards.*;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static DarkestMod.DarkestMod.makePowerPath;

public class powerAffliction extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = DarkestMod.makeID("PowerAffliction");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("afflict_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("afflict_power32.png"));

    public powerAffliction(AbstractCreature owner, int afflictionAmount) {
        this.name = NAME;
        this.ID = POWER_ID;

        this.owner = owner;
        this.amount = afflictionAmount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
        }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

        int random = AbstractDungeon.miscRng.random(0, 99);

        if (this.amount == 1 && random <= 11) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictFearful(), 1));
            //this.amount -= 1;
        } else if (this.amount == 1 && random <= 23) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictParanoid(), 1));
        } else if (this.amount == 1 && random <= 35) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictSelfish(), 1));
        } else if (this.amount == 1 && random <= 47) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictMasochistic(), 1));

        } else if (this.amount == 1 && random <= 59) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictAbusive(), 1));

        } else if (this.amount == 1 && random <= 70) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictHopeless(), 1));

        }else if (this.amount == 1 && random <= 83) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictIrrational(), 1));
        }
        // Virtues
        else if (this.amount == 1 && random <= 86) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictStalwart(), 1));
        }
        else if (this.amount == 1 && random <= 89) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictCourageous(), 1));
        }
        else if (this.amount == 1 && random <= 92
        ) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictFocused(), 1));
        }
        else if (this.amount == 1 && random <=95 ) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictPowerful(), 1));
        }
        else if (this.amount == 1 && random <= 99) {
            this.flash();
            this.addToBot(
                    new MakeTempCardInHandAction(new afflictVigorous(), 1));
        }

    }
    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

    @Override
    public AbstractPower makeCopy() {
        return new powerAffliction(owner, amount);
    }

}

