package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.powers.powerRiposte;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;


    public class GuardianRelic extends CustomRelic {

        public static final String ID = DefaultMod.makeID("GuardianRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("GuardianRelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_GuardianRelic.png"));
        public GuardianRelic() {
            super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);

        }
        public void atBattleStart() {
            this.counter = 0;
        }

        public void atTurnStart() {
            if (!this.grayscale) {
                ++this.counter;
            }
            if (this.counter == 3) {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlatedArmorPower(AbstractDungeon.player, 5), 5));
                this.counter = -1;
                this.grayscale = true;
            }
        }

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

    }
