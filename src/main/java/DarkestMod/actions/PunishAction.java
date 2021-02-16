package DarkestMod.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class PunishAction extends AbstractGameAction {


    public PunishAction(AbstractCreature target, AbstractCreature source) {
        this.actionType = ActionType.DEBUFF;
        this.target = target;
        this.source = source;

    }

    public void update() {
        if (this.target != null && this.target.hasPower("PowerBleed")) {
            AbstractDungeon.actionManager.addToTop(
                    new BleedLoseHPAction(this.target, this.source, this.target.getPower("PowerBleed").amount, AbstractGameAction.AttackEffect.FIRE));
        }
        this.isDone = true;
    }
}


