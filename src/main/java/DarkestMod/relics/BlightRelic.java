package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.powers.powerBlight;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

import java.util.Iterator;

import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;

public class BlightRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("BlasphemousVialRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Blightrelic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_Blightrelic.png"));
    public BlightRelic() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != AbstractDungeon.player && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(target, AbstractDungeon.player, new powerBlight(target, AbstractDungeon.player, 1), 1));
                }
            }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
