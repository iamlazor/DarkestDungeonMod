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
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static DarkestMod.DarkestMod.makePowerPath;

public class FlagPower extends AbstractPower implements CloneablePowerInterface {

        public static final String POWER_ID = DarkestMod.makeID("FlagPower");
        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        public static final String NAME = powerStrings.NAME;
        public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

        private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("flag_power84.png"));
        private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("flag_power32.png"));

        public FlagPower(AbstractCreature owner, int StrengthAMT) {
            this.name = NAME;
            this.ID = POWER_ID;

            this.owner = owner;
            this.amount = StrengthAMT;
            this.type = PowerType.BUFF;
            this.isTurnBased = false;


            this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
            this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

            this.updateDescription();
        }

    public void atStartOfTurn() {
        super.atStartOfTurn();
        if ((float)AbstractDungeon.player.currentHealth <= (float)AbstractDungeon.player.maxHealth * 0.5F && AbstractDungeon.player.currentHealth > 0) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.amount), this.amount));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, this.amount), this.amount));
        }
    }

    @Override
    public void updateDescription() {
        description = (DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]);
    }

        @Override
        public AbstractPower makeCopy() {
            return new FlagPower(owner, amount);
        }

    }


