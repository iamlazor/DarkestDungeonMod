package DarkestMod.powers;


import DarkestMod.DefaultMod;
import DarkestMod.actions.BleedLoseHPAction;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static DarkestMod.DefaultMod.makePowerPath;

public class powerBleed extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String Power_ID = DefaultMod.makeID("PowerBleed");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(Power_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("bleed_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("bleed_power32.png"));

    public powerBleed(AbstractCreature owner, AbstractCreature source, int bleedAmt) {
        this.name = NAME;
        this.ID = "PowerBleed";

        this.owner = owner;
        this.source = source;
        this.amount = bleedAmt;
        if (this.amount >= 9999) {
            this.amount = 9999;
        }

        this.type = PowerType.DEBUFF;
        this.isPostActionPower = true; //option to make it on Draw or Discard here

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.addToBot(new BleedLoseHPAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.FIRE)); // make an attack effect for bleed later
            if (this.amount == 0) {
                AbstractDungeon.actionManager.addToBottom(
                        new RemoveSpecificPowerAction(this.owner, this.owner, "PowerBleed"));

            }
        }
    }
    @Override
    public void updateDescription() {
        description = (DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]);
    }

    @Override
    public AbstractPower makeCopy() {
        return new powerBleed(owner, source, amount);
    }
}
