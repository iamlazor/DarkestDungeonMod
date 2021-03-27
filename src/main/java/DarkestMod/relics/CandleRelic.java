package DarkestMod.relics;

import DarkestMod.DarkestMod;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static DarkestMod.DarkestMod.makeRelicOutlinePath;
import static DarkestMod.DarkestMod.makeRelicPath;



    public class CandleRelic extends CustomRelic {

        public static final String ID = DarkestMod.makeID("CandleRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Candlerelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_Candlerelic.png"));
        public CandleRelic() {
            super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);

        }

        public void atBattleStart() {
            this.grayscale = false;
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

