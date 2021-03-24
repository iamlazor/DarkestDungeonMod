package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;

    public class HairpinRelic extends CustomRelic {

        public static final String ID = DefaultMod.makeID("HairpinRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("HairpinRelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_HairpinRelic.png"));

        public HairpinRelic() {
            super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);

        }

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

    }
