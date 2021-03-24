package DarkestMod.relics;

import DarkestMod.DarkestMod;
import DarkestMod.patches.CardTagEnum;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static DarkestMod.DarkestMod.makeRelicOutlinePath;
import static DarkestMod.DarkestMod.makeRelicPath;

    public class WoundingRelic extends CustomRelic {

        public static final String ID = DarkestMod.makeID("WoundingRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("WoundingRelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_WoundingRelic.png"));
        public WoundingRelic() {
            super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.HEAVY);

        }

        public float atDamageModify(float damage, AbstractCard c) {
            return c.hasTag(CardTagEnum.SHOVEL) ? damage + 4.0F : damage;

        }

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

    }

