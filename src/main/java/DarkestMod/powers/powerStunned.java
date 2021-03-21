package DarkestMod.powers;

import DarkestMod.DefaultMod;
import DarkestMod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.lang.reflect.Field;

import static DarkestMod.DefaultMod.makePowerPath;

public class powerStunned extends AbstractPower {
    public static final String POWER_ID = DefaultMod.makeID("StunnedPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private byte moveByte;
    private AbstractMonster.Intent moveIntent;
    private EnemyMoveInfo move;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("stunned_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("stunned_power32.png"));

    public powerStunned(AbstractMonster owner, int amount) {
        this.name = NAME;
        this.ID = "StunnedPower";

        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }


    public void atEndOfRound() {
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));
        }

    }

    public void onInitialApplication() {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            public void update() {
                if (powerStunned.this.owner instanceof AbstractMonster) {
                    powerStunned.this.moveByte = ((AbstractMonster)powerStunned.this.owner).nextMove;
                    powerStunned.this.moveIntent = ((AbstractMonster)powerStunned.this.owner).intent;

                    try {
                        Field f = AbstractMonster.class.getDeclaredField("move");
                        f.setAccessible(true);
                        powerStunned.this.move = (EnemyMoveInfo)f.get(powerStunned.this.owner);
                        powerStunned.this.move.intent = AbstractMonster.Intent.STUN;
                        ((AbstractMonster)powerStunned.this.owner).createIntent();
                    } catch (NoSuchFieldException | IllegalAccessException var2) {
                        var2.printStackTrace();
                    }
                }

                this.isDone = true;
            }
        });
    }

    public void onRemove() {
        if (this.owner instanceof AbstractMonster) {
            AbstractMonster m = (AbstractMonster)this.owner;
            if (this.move != null) {
                m.setMove(this.moveByte, this.moveIntent, this.move.baseDamage, this.move.multiplier, this.move.isMultiDamage);
            } else {
                m.setMove(this.moveByte, this.moveIntent);
            }

            m.createIntent();
            m.applyPowers();
        }

    }
}
