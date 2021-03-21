package DarkestMod.powers;

import DarkestMod.DefaultMod;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static DarkestMod.DefaultMod.makePowerPath;

public class powerHeartAttack extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("HeartAttack");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("ddoor_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("ddoor_power32.png"));



    public powerHeartAttack(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;

        this.owner = owner;
        this.source = source;
        this.amount = 1;

        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        if (this.amount == 1) {
            this.amount = this.owner.currentHealth - 1;
            this.flash();
            this.owner.damage(new DamageInfo(this.source, this.amount, DamageInfo.DamageType.HP_LOSS));
            this.amount = 0;
        }
    }



    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

    @Override
    public AbstractPower makeCopy() {
        return new powerHeartAttack(owner, source, amount);
    }
}
