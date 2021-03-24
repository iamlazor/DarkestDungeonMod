package DarkestMod.relics;

import DarkestMod.DarkestMod;
import DarkestMod.powers.powerRiposte;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static DarkestMod.DarkestMod.makeRelicOutlinePath;
import static DarkestMod.DarkestMod.makeRelicPath;



    public class SpikedRelic extends CustomRelic {

        public static final String ID = DarkestMod.makeID("SpikedRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("CollarRelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_CollarRelic.png"));
        public SpikedRelic() {
            super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);

        }
        public void atBattleStart() {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerRiposte(AbstractDungeon.player, 1), 1));
        }

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

    }
