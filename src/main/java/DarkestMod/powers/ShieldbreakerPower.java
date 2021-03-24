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

public class ShieldbreakerPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = DarkestMod.makeID("ShieldbreakerPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("tracking_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("tracking_power32.png"));

    public ShieldbreakerPower(AbstractCreature owner, int afflictionAmount) {
        this.name = NAME;
        this.ID = "ShieldbreakerPower";

        this.owner = owner;
        this.amount = afflictionAmount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }



    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }


    @Override
    public AbstractPower makeCopy() {
        return new powerTracking(owner, amount);
    }

}