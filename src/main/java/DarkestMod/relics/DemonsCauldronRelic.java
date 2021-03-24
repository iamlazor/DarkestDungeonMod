package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.GoldenEye;


import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;

public class DemonsCauldronRelic  extends CustomRelic {

    public static final String ID = DefaultMod.makeID("DemonsRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("CauldronRelic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_CauldronRelic.png"));
    public DemonsCauldronRelic() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);

    }



    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    }

