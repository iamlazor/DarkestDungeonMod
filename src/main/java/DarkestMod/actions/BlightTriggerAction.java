package DarkestMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BlightTriggerAction extends AbstractGameAction {


    public BlightTriggerAction(AbstractCreature target, AbstractCreature source) {
        this.actionType = ActionType.DEBUFF;
        this.target = target;
        this.source = source;

    }

    public void update() {
        if (this.target != null && this.target.hasPower("PowerBlight")) {
            AbstractDungeon.actionManager.addToTop(
                    new BlightLoseHPAction(this.target, this.source, this.target.getPower("PowerBlight").amount, AttackEffect.POISON));
        }
        this.isDone = true;
    }
}

