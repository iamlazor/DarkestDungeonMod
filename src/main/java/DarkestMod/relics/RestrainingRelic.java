package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;

    public class RestrainingRelic extends CustomRelic {

        public static final String ID = DefaultMod.makeID("RestrainingRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PadlockRelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_PadlockRelic.png"));
        public RestrainingRelic() {
            super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);

        }

        public void atBattleStart() {
            this.counter = 0;
        }

        public void atTurnStart() {
            if (!this.grayscale) {
                ++this.counter;
            }
            if (this.counter == 2) {
                this.flash();
                AbstractDungeon.actionManager.addToBottom(
                        new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, 2), 2));
                this.counter = -1;
                this.grayscale = true;
            }

        }

            @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

    }

