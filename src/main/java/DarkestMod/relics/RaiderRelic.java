package DarkestMod.relics;

import DarkestMod.DefaultMod;
import DarkestMod.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import java.util.Iterator;

import static DarkestMod.DefaultMod.makeRelicOutlinePath;
import static DarkestMod.DefaultMod.makeRelicPath;


    public class RaiderRelic extends CustomRelic {

        public static final String ID = DefaultMod.makeID("RaiderRelic");

        private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TalismanRelic.png"));
        private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Outline_TalismanRelic.png"));

        public RaiderRelic() {super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK);}

        public void atBattleStart() {this.counter = 0;}

        public void onUseCard(AbstractCard card, UseCardAction action) {
                if (card.type == AbstractCard.CardType.ATTACK) {
                    ++this.counter;
                    if (this.counter == 2) {
                        this.counter = 0;
                        this.flash();
                        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                        AbstractMonster mo;
                         while (var3.hasNext()) {
                            mo = (AbstractMonster) var3.next();
                            AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(mo, AbstractDungeon.player));
                    }
                }
            }
        }

        public void onVictory() {
            this.counter = -1;
        }

        @Override
        public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
        }

    }

