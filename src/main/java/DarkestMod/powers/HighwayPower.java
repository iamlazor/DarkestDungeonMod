package DarkestMod.powers;

import DarkestMod.DarkestMod;
import DarkestMod.cards.attackGrapeshotBlast;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static DarkestMod.DarkestMod.makePowerPath;

public class HighwayPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = DarkestMod.makeID("HighwayPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("highway_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("highway_power32.png"));

    public HighwayPower(AbstractCreature owner, int afflictionAmount) {
        this.name = NAME;
        this.ID = POWER_ID;

        this.owner = owner;
        this.amount = afflictionAmount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            for (int i = 0; i < this.amount; ++i) {
                AbstractDungeon.actionManager.addToBottom(
                        new MakeTempCardInHandAction(new attackGrapeshotBlast(), 1));
            }
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }


    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }
    @Override
    public AbstractPower makeCopy() {
        return new HighwayPower(owner, amount);
    }
}
