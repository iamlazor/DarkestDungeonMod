package DarkestMod.powers;

import DarkestMod.DarkestMod;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static DarkestMod.DarkestMod.makePowerPath;

public class LeperPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = DarkestMod.makeID("LeperPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("leper_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("leper_power32.png"));

    public LeperPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.amount = amount;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        this.isTurnBased = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }



    @Override
    public void atStartOfTurn(){
        if (AbstractDungeon.player.hasPower("Frail")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,this.amount),this.amount));
        }
        else if (AbstractDungeon.player.hasPower("Weakened")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,this.amount),this.amount));
        }
        else if (AbstractDungeon.player.hasPower("Vulnerable")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,this.amount),this.amount));
        }
        else if (AbstractDungeon.player.hasPower("Confusion")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,this.amount),1));
        }
        
        else if (AbstractDungeon.player.hasPower("PowerBlight")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,this.amount),this.amount));
        }
         else if (AbstractDungeon.player.hasPower("PowerBleed")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,this.amount),this.amount));
        }
        else if (AbstractDungeon.player.hasPower("PowerMarked")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,this.amount),this.amount));
        }
    }

    @Override
    public void updateDescription() {
        description = (DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]);
    }

    @Override
    public AbstractPower makeCopy() {
        return new CrusaderPower(owner, amount);
    }

}