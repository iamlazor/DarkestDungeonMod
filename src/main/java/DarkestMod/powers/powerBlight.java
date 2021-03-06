package DarkestMod.powers;

import DarkestMod.DarkestMod;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static DarkestMod.DarkestMod.makePowerPath;

public class powerBlight extends AbstractPower implements CloneablePowerInterface {

    public AbstractCreature source;

    public static final String Power_ID = DarkestMod.makeID("PowerBlight");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(Power_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("blight_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("blight_power32.png"));

public powerBlight(AbstractCreature owner, AbstractCreature source, int blightAmt) {
    this.name =NAME;
    this.ID = "PowerBlight";
    this.owner = owner;
    this.source = source;
    this.amount = blightAmt;
    if (this.amount >= 9999) {
        this.amount = 9999;
    }
    this.type = PowerType.DEBUFF;
    this.isPostActionPower = true; //option to make it on Draw or Discard here
    this.type = PowerType.DEBUFF;
    this.isPostActionPower = true; //option to make it on Draw or Discard here

    this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
    this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

    updateDescription();
}
    @Override
    //public void atStartOfTurn() {
        //if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            //this.addToBot(new BlightLoseHPAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.POISON)); // make an attack effect for bleed later
      //  }    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_POISON", 0.05F);
    }

    @Override
    public void updateDescription() {
            description = (DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]);
        }

    @Override
    public AbstractPower makeCopy() {
        return new powerBlight(owner, source, amount);
    }
}
