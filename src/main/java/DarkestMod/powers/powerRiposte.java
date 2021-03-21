package DarkestMod.powers;

import DarkestMod.DefaultMod;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static DarkestMod.DefaultMod.makePowerPath;

public class powerRiposte extends AbstractPower implements CloneablePowerInterface {

        public static final String POWER_ID = DefaultMod.makeID("Riposte");
        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        public static final String NAME = powerStrings.NAME;
        public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

        private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("riposte_power84.png"));
        private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("riposte_power32.png"));

        public powerRiposte(AbstractCreature owner, int thornsDamage) {
            this.name = NAME;
            this.ID = POWER_ID;

            this.owner = owner;
            this.amount = thornsDamage;
            this.type = PowerType.BUFF;
            this.isTurnBased = false;


            this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
            this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

            this.updateDescription();
        }

    public void stackPower(int stackAmount) {
            this.fontScale = 8.0F;
            this.amount += stackAmount;
            this.updateDescription();
        }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            this.addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
        }

        return damageAmount;
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

        @Override
        public AbstractPower makeCopy() {
            return new powerRiposte(owner, amount);
        }

    }
