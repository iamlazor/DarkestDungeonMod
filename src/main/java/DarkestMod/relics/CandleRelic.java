package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;



    public class CandleRelic extends CustomRelic {

        public static final String ID = DefaultMod.makeID("CandleRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Candlerelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_Candlerelic.png"));
        public CandleRelic() {
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
                        new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 10));
                AbstractDungeon.player.increaseMaxHp(2, false);
                this.counter = -1;
                this.grayscale = true;
            }

        }

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

    }

