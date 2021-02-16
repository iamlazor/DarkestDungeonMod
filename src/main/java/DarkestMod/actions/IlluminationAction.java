package DarkestMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class IlluminationAction extends AbstractGameAction {


    public IlluminationAction(AbstractCreature target) {
        this.actionType = ActionType.BLOCK;
        this.target = target;

    }
    public void update() {
        if (this.target != null && this.target.getPower("PowerLight").amount >= 3) {
            AbstractDungeon.actionManager.addToBottom(
                    new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player,7 ));
        }
        this.isDone = true;
    }


}
