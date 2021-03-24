package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.cards.curseTheRuns;
import DarkestMod.cards.skillDogBiscuit;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
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
    public void onEquip() {
        this.counter = 0;
    }

    public void onShuffle() {
        ++this.counter;
        if (this.counter == 2) {
            this.counter = 0;
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new curseTheRuns(), 1, true, true));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
        }

    }



    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    }

