package DarkestMod.powers;

import DarkestMod.DefaultMod;
import DarkestMod.cards.afflictFearful;
import DarkestMod.cards.attackThrowndagger;
import DarkestMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Normality;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static DarkestMod.DefaultMod.makePowerPath;

public class AfflictionFearful extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = DefaultMod.makeID("Fearful");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("fearful.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("afflict_power32.png"));

    public AfflictionFearful(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Fearful";

        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 168, 168);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -2), -2));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, -2), -2));
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
        return new AfflictionFearful(owner, amount);
    }
}
