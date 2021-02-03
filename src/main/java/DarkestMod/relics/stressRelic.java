package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.cards.*;
import DarkestMod.powers.JesterPower;
import DarkestMod.powers.powerStress;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.StartActSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;

public class stressRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("StressRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("StressRelic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_StressRelic.png"));
    private AbstractCreature owner;


    public stressRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
        this.counter = 1;

    }


    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerStress(AbstractDungeon.player, counter), counter));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
         this.counter += damageAmount;
         return damageAmount;
    }


    public void onUseCard(AbstractCard card, UseCardAction action) {
     if (card.cardID.equals(skillProtectMe.ID)){
         this.counter += 4;
     } else if (card.cardID.equals(attackRake.ID)) {
            this.counter += 8;
        }
     else if (card.cardID.equals(powerJester.ID)) {
         this.counter -= 8;
     }     else if (card.cardID.equals(skillAbsolution.ID)) {
         this.counter -= 8;
     }
    }



        @Override
    public String getUpdatedDescription() {return DESCRIPTIONS[0];}

}
