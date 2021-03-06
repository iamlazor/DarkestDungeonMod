package DarkestMod.powers;

import DarkestMod.DarkestMod;
import DarkestMod.relics.HairpinRelic;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;

import static DarkestMod.DarkestMod.makePowerPath;


public class powerLight extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = DarkestMod.makeID("PowerLight");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final int VULNERABLE = 1;
    private static final int DEXTERITYAMT = 1;
    private static final int STRENGTH = 1;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("light_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("light_power32.png"));

    public powerLight(AbstractCreature owner, int lightAmount) {
        this.name = NAME;
        this.ID = "PowerLight";

        this.owner = owner;
        this.amount = lightAmount;
        if (this.amount >= 10) {
            this.amount = 10;
        }

        if (this.amount <= -10) {
            this.amount = -10;
        }

        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.canGoNegative = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public void atStartOfTurn (){
        if (this.amount >= 7) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new DexterityPower(this.owner,DEXTERITYAMT),DEXTERITYAMT));
        }
        if (this.amount <= -5 && AbstractDungeon.player.hasRelic(HairpinRelic.ID)) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
             new GainEnergyAction(1));
        }

        if (this.amount <= -7) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new VulnerablePower(this.owner,VULNERABLE,false),VULNERABLE));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,STRENGTH),STRENGTH));
        }
    }


    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[2]);;
    }

    @Override
    public AbstractPower makeCopy() {
        return new powerLight(owner, amount);
    }

}
