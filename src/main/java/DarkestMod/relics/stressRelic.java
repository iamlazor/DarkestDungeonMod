package DarkestMod.relics;

import DarkestMod.DarkestMod;
import DarkestMod.cards.*;
import DarkestMod.powers.JesterPower;
import DarkestMod.powers.powerStress;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;

import static DarkestMod.DarkestMod.makeRelicOutlinePath;
import static DarkestMod.DarkestMod.makeRelicPath;

public class stressRelic extends CustomRelic {

    public static final String ID = DarkestMod.makeID("StressRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("StressRelic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_StressRelic.png"));
    private AbstractCreature owner;


    public stressRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
        this.counter = 1;}

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new powerStress(AbstractDungeon.player, counter), counter));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (AbstractDungeon.player.hasPower(JesterPower.POWER_ID) && damageAmount < AbstractDungeon.player.currentHealth && damageAmount > 0 && info.owner != null && info.type == DamageInfo.DamageType.NORMAL){
            this.counter += damageAmount --;

    }   else if (damageAmount < AbstractDungeon.player.currentHealth && damageAmount > 0 && info.owner != null && info.type == DamageInfo.DamageType.NORMAL) {
            this.counter += damageAmount;
         }
        return damageAmount;
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        if (!this.grayscale && AbstractDungeon.player.hasPower("Stalwart")) {
            this.flash();
            this.counter -= 100;
            this.grayscale = true;
        }
    }


    @Override
    public void onVictory() {
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
            this.counter = 1;
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
     if (card.cardID.equals(skillProtectMe.ID)){
         this.counter += 4;
        }
     else if (card.cardID.equals(attackRake.ID)) {
            this.counter += 8;
        }
     else if (card.cardID.equals(skillWeakeningCurse.ID)) {
         this.counter += 5;
        }
     else if (card.cardID.equals(skillBeastBile.ID)) {
         this.counter += 4;
        }
     // stress relief
     else if (card.cardID.equals(afflictFearful.ID)) {
         this.counter += 20;
        }
     else if (card.cardID.equals(skillDogBiscuit.ID)) {
         this.counter -= 5;
     }

     else if (card.cardID.equals(powerJester.ID)) {
         this.counter -= 8;
        }
     else if (!card.upgraded && card.cardID.equals(skillAbsolution.ID)) {
         this.counter -= 8;
        }
     else if (card.upgraded && card.cardID.equals(skillAbsolution.ID)) {
         this.counter -= 12;
     }
     else if (card.cardID.equals(skillEndure.ID)) {
         this.counter -= 3;
     }
     else if (!card.upgraded && card.cardID.equals(skillInspiringCry.ID)) {
         this.counter -= 5;
        }
     else if (card.upgraded && card.cardID.equals(skillInspiringCry.ID)) {
         this.counter -= 8;
        }
     else if (card.cardID.equals(afflictStalwart.ID)) {
         this.counter -= 100;
        }
    }


        @Override
    public String getUpdatedDescription() {return DESCRIPTIONS[0];}

}
