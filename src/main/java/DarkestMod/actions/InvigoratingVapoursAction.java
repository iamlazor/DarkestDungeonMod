package DarkestMod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class InvigoratingVapoursAction extends AbstractGameAction {


    public InvigoratingVapoursAction(AbstractCreature source, int amount) {
        this.setValues(AbstractDungeon.player, source, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open("Reduce Its Cost to 0", this.amount, false, true, false, false, true);
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
            this.tickDuration();
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard c;
                for(Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); AbstractDungeon.player.hand.addToTop(c)) {
                    c = (AbstractCard)var1.next();
                    if (c.costForTurn > 0) {
                        c.cost = 0;
                        c.costForTurn = 0;
                        c.isCostModified = true;
                        c.superFlash(Color.GOLD.cpy());
                    }
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }


}
