package DarkestMod.actions;

import DarkestMod.powers.powerLight;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SacStabAction extends AbstractGameAction {


    public SacStabAction(AbstractCreature target) {
        this.actionType = ActionType.BLOCK;
        this.target = target;

    }
    public void update() {
        if (this.target != null && AbstractDungeon.player.getPower("PowerLight").amount <= -3) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(target, new DamageInfo(target, 9, DamageInfo.DamageType.NORMAL) ));

        }
        this.isDone = true;
    }


}
