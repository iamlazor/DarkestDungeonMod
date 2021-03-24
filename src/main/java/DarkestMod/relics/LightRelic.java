package DarkestMod.relics;

import DarkestMod.DarkestMod;
import DarkestMod.powers.powerLight;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static DarkestMod.DarkestMod.makeRelicOutlinePath;
import static DarkestMod.DarkestMod.makeRelicPath;

public class LightRelic extends CustomRelic {

    public static final String ID = DarkestMod.makeID("TorchLightRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TorchRelic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_TorchRelic.png"));
    public LightRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerLight(AbstractDungeon.player, 2), 2));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
