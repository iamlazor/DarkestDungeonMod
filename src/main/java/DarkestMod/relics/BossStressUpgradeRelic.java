package DarkestMod.relics;

import DarkestMod.DarkestMod;
import DarkestMod.cards.*;
import DarkestMod.powers.JesterPower;
import DarkestMod.powers.powerStress;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import DarkestMod.DarkestMod;
import DarkestMod.util.TextureLoader;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import java.util.ArrayList;

import static DarkestMod.DarkestMod.makeRelicOutlinePath;
import static DarkestMod.DarkestMod.makeRelicPath;
public class BossStressUpgradeRelic extends CustomRelic {

    public static final String ID = DarkestMod.makeID("BossStressUpgradeRelic");

    private static final Texture IMG = TextureLoader.getTexture(DarkestMod.makeRelicPath("BossStress.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(DarkestMod.makeRelicOutlinePath("Outline_BossStress.png"));

    public BossStressUpgradeRelic() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
        this.counter = 1;}

    public void onEnterRestRoom() {
        this.flash();
        this.counter -=15;
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerStress(AbstractDungeon.player, counter), counter));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));

    }



    @Override
    public void onVictory() {
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
            this.counter = 1;
        }
    }

    @Override
    public String getUpdatedDescription() {return DESCRIPTIONS[0];}

}
