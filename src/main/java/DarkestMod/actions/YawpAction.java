package DarkestMod.actions;

import DarkestMod.powers.powerStunned;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class YawpAction extends AbstractGameAction {
    private AbstractMonster targetMonster;

    public YawpAction(AbstractMonster m) {
        this.duration = 0.0F;
        this.targetMonster = m;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.targetMonster, AbstractDungeon.player, new powerStunned(this.targetMonster,1),1));
        } else {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player,2),2 ));
        }
        this.isDone = true;
    }
}