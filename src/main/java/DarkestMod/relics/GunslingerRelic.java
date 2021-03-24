package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.cards.skillTrackingShot;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;

    public class GunslingerRelic extends CustomRelic {

        public static final String ID = DefaultMod.makeID("GunslingerRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BuckleRelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_BuckleRelic.png"));
        public GunslingerRelic() {
            super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);

        }
        public void atBattleStart() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(
                    new MakeTempCardInHandAction(new skillTrackingShot(), 1));
        }

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

    }