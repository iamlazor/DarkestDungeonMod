package DarkestMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class FinishHimAction extends AbstractGameAction {
    private DamageInfo info;

    public FinishHimAction(AbstractCreature target, DamageInfo info) {
        this.actionType = ActionType.DAMAGE;
        this.target = target;
        this.info = info;
    }

    public void update() {
        if (this.target != null && this.target.hasPower("PowerMarked")) {
            AbstractDungeon.actionManager.addToTop(
                    new GainEnergyAction(3));
        }

        this.addToTop(new DamageAction(this.target, this.info, com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.isDone = true;
    }
}



