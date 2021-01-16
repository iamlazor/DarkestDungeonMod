package DarkestMod.actions;

import DarkestMod.cards.AbstractDefaultCard;
import DarkestMod.patches.CardTagEnum;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class AfflictedAction extends AbstractGameAction {
    public AfflictedAction(int amount) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
        this.duration = this.startDuration;
    }
    public void update() {
        if (this.duration == this.startDuration) {
            for (int i = 0; i < this.amount; ++i) {
                AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeStatEquivalentCopy();
                UnlockTracker.markCardAsSeen(c.cardID);
                if (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS && (c.hasTag(CardTagEnum.AFFLICTION))){
                                                          //c.hasTag(AbstractCard.CardTags.STRIKE)) this.tags.add(CardTags.STRIKE) this.tags.add(CardTagEnum.AFFLICTION){
                    c.upgrade();
                }

                AbstractDungeon.player.drawPile.addToBottom(c);
            }

            this.duration -= Gdx.graphics.getDeltaTime();
        }

        this.tickDuration();
    }
}
