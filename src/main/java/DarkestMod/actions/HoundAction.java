package DarkestMod.actions;

import DarkestMod.powers.powerBleed;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;


public class HoundAction  extends AbstractGameAction {
    private AbstractMonster targetMonster;
    public HoundAction(AbstractMonster m) {
        this.actionType = ActionType.WAIT;
        this.targetMonster = m;
    }

    public void update() {
        if (this.targetMonster != null && this.targetMonster.hasPower("PowerBleed")) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.targetMonster, AbstractDungeon.player, new VulnerablePower(this.targetMonster, 2, false), 2));
        } else {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.targetMonster, AbstractDungeon.player, new powerBleed(this.targetMonster, AbstractDungeon.player, 5), 5));

        }
        this.isDone = true;
    }
}


