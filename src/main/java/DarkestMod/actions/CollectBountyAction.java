package DarkestMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class CollectBountyAction extends AbstractGameAction {
    private DamageInfo info;

    public CollectBountyAction(AbstractCreature target, DamageInfo info) {
        this.actionType = ActionType.DAMAGE;
        this.target = target;
        this.info = info;
    }

    public void update() {
        if (this.target != null && this.target.hasPower("PowerMarked")) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
            AbstractDungeon.actionManager.addToTop(
                    new GainEnergyAction(2));
        }

        this.addToTop(new DamageAction(this.target, this.info, com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.isDone = true;
    }
}