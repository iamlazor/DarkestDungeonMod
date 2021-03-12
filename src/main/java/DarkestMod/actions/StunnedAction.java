package DarkestMod.actions;

import DarkestMod.powers.powerStunned;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class StunnedAction extends AbstractGameAction {
    private AbstractMonster targetMonster;

    public StunnedAction(AbstractMonster m) {
        this.duration = 0.0F;
        this.targetMonster = m;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.targetMonster, AbstractDungeon.player, new StunMonsterPower(this.targetMonster,1),1));

        }
        this.isDone = true;
    }
}