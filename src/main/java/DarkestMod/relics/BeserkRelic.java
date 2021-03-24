package DarkestMod.relics;

import DarkestMod.DarkestMod;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import static DarkestMod.DarkestMod.makeRelicOutlinePath;
import static DarkestMod.DarkestMod.makeRelicPath;

    public class BeserkRelic extends CustomRelic {

        public static final String ID = DarkestMod.makeID("BeserkRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Berserkrelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_Berserkrelic.png"));
        public BeserkRelic() {
            super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.HEAVY);        }

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

    }

