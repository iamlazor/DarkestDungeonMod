package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.powers.powerLight;
import DarkestMod.powers.powerStress;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;

public class TomeOfHealingRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("TomeRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("HolyHealingRelic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_HolyHealingRelic.png"));
    public TomeOfHealingRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT); this.counter = 2;

    }
    public void onEquip() {
        AbstractDungeon.player.heal(15);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerLight(AbstractDungeon.player, counter), counter));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
