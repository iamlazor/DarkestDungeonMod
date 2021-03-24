package DarkestMod.relics;

import DarkestMod.DarkestMod;
import DarkestMod.powers.powerMarked;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static DarkestMod.DarkestMod.makeRelicOutlinePath;
import static DarkestMod.DarkestMod.makeRelicPath;


    public class BandanaRelic extends CustomRelic {

        public static final String ID = DarkestMod.makeID("BandanaRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BandanaRelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_BandanaRelic.png"));
        public BandanaRelic() {
            super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
        }

        public void atBattleStart() {
            this.counter = 0;
        }
        public void atTurnStart() {
                if (!this.grayscale) {
                    ++this.counter;
                }
            if (this.counter == 4) {
                this.flash();
                Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

                while (var1.hasNext()) {
                    AbstractMonster mo = (AbstractMonster) var1.next();
                    AbstractDungeon.actionManager.addToBottom(
                            new RelicAboveCreatureAction(mo, this));
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(mo, AbstractDungeon.player, new powerMarked(mo, AbstractDungeon.player, 1), 1));
                    this.grayscale = true;
                }
            }
        }

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

    }

