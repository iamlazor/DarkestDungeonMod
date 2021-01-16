package DarkestMod.powers;

import DarkestMod.DefaultMod;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static DarkestMod.DefaultMod.makePowerPath;


public class powerLight extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = DefaultMod.makeID("PowerLight");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final int VULNERABLE = 20;
    private static final int DEXTERITYAMT = 10;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("light_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("light_power32.png"));

    public powerLight(AbstractCreature owner, int lightAmount) {
        this.name = NAME;
        this.ID = POWER_ID;

        this.owner = owner;
        this.amount = lightAmount;
        if (this.amount >= 10) {
            this.amount = 10;
        }
        if (this.amount <= -10) {
            this.amount = -10;
        }

        if (this.amount == 0) {
        this.amount = -1;
    }

        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        this.canGoNegative = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public void atStartOfTurn (){
        if (this.amount >= 5) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new DexterityPower(this.owner,DEXTERITYAMT),DEXTERITYAMT));
        }
        if (this.amount <= -5) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new VulnerablePower(this.owner,VULNERABLE,false),VULNERABLE));
        }

    }

    public void atEndOfTurn( final boolean isPlayer) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));

        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(this.owner,this.owner,new DexterityPower(this.owner,3),3));
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
        return new powerLight(owner, amount);
    }





}
