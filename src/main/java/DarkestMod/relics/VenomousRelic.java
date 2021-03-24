package DarkestMod.relics;

import DarkestMod.DarkestMod;
import DarkestMod.powers.powerBlight;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static DarkestMod.DarkestMod.makeRelicOutlinePath;
import static DarkestMod.DarkestMod.makeRelicPath;


    public class VenomousRelic extends CustomRelic {

        public static final String ID = DarkestMod.makeID("VenomousRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("VenomousRelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_VenomousRelic.png"));
        public VenomousRelic() {
            super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.FLAT);
        }

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

        public void atBattleStart() {
            this.flash();
            Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

            while (var3.hasNext()) {
                AbstractMonster monster = (AbstractMonster) var3.next();
                if (!monster.isDead && !monster.isDying) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(monster, AbstractDungeon.player, new powerBlight(monster, AbstractDungeon.player, 3), 3));
                }
            }
        }

    }

