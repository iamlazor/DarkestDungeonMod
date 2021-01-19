package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.powers.powerStress;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;

public class stressRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("StressRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("StressRelic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_StressRelic.png"));

    public stressRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerStress(AbstractDungeon.player, 1), 1));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
