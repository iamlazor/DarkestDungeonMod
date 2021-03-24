package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;

    public class BeserkRelic extends CustomRelic {

        public static final String ID = DefaultMod.makeID("BeserkRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Berserkrelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_Berserkrelic.png"));
        public BeserkRelic() {
            super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.HEAVY);        }

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

    }

