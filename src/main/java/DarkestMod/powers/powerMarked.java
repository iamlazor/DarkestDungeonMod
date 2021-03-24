package DarkestMod.powers;

import DarkestMod.DarkestMod;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static DarkestMod.DarkestMod.makePowerPath;

public class powerMarked extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DarkestMod.makeID("PowerMarked");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("marked_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("marked_power32.png"));

    public powerMarked(AbstractCreature owner, AbstractCreature source, int amount){
        this.name = NAME;
        this.ID = "PowerMarked";

        this.owner = owner;
        this.source = source;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }


    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
         return this.owner != null && !this.owner.isPlayer ? damage * 2.0F : damage * 2.0F;
            }
            return damage;
        }

    public int onAttacked(DamageInfo info, int damageAmount) {

      if(this.owner.currentHealth >0  && damageAmount > 0 && info.owner != null && this.amount>=0){
          this.flash();
          AbstractDungeon.actionManager.addToBottom(
                  new ReducePowerAction(this.owner, this.owner, "PowerMarked", 1));
      }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }
    @Override
    public AbstractPower makeCopy() {
        return new powerMarked(owner,source, amount);
    }
}
