package DarkestMod.powers;

import DarkestMod.DefaultMod;
import DarkestMod.cards.AbstractDynamicCard;
import DarkestMod.characters.TheDefault;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static DarkestMod.DefaultMod.makeCardPath;
import static DarkestMod.DefaultMod.makePowerPath;

public class LeperPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = DefaultMod.makeID("LeperPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("leper_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("leper_power32.png"));

    public LeperPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;

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
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,1),1));
        }
        if (AbstractDungeon.player.hasPower("Weak")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,1),1));
        }
        if (AbstractDungeon.player.hasPower("Vulnerable")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,1),1));
        }
        if (AbstractDungeon.player.hasPower("Confusion")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,1),1));
        }
        if (AbstractDungeon.player.hasPower("PowerBlight")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,1),1));
        }
        if (AbstractDungeon.player.hasPower("PowerBleed")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,1),1));
        }
        if (AbstractDungeon.player.hasPower("PowerMarked")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,1),1));
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
        return new CrusaderPower(owner, amount);
    }

}