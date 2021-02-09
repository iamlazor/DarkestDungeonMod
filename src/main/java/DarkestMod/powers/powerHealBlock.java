package DarkestMod.powers;

import DarkestMod.DefaultMod;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static DarkestMod.DefaultMod.makePowerPath;

public class powerHealBlock extends AbstractPower implements CloneablePowerInterface {
        public static final String POWER_ID = DefaultMod.makeID("NoHeal");
        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        public static final String NAME = powerStrings.NAME;
        public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

        private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("noheal_power84.png"));
        private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("noheal_power32.png"));

        public powerHealBlock() {
            this.name = NAME;
            this.ID = POWER_ID;

            this.type = PowerType.DEBUFF;
            this.isTurnBased = false;

            this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
            this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

            this.updateDescription();
        }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public int onPlayerHeal(int healAmount) {
        this.flash();
        return 0;
    }

    @Override
    public AbstractPower makeCopy() {
        return new powerHealBlock();
    }
}

