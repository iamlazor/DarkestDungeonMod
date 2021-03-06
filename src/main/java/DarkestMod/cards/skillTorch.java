package DarkestMod.cards;

import DarkestMod.DarkestMod;
import DarkestMod.characters.TheDarkest;
import DarkestMod.powers.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DarkestMod.DarkestMod.makeCardPath;

public class skillTorch extends AbstractDynamicCard {

        public static final String ID = DarkestMod.makeID("Torch");
        public static final String IMG = makeCardPath("skillTorch.png");

        private static final CardRarity RARITY = CardRarity.COMMON;
        private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

        private static final CardType TYPE = CardType.SKILL;
        public static final CardColor COLOR = TheDarkest.Enums.DARKEST_COLOR;

        private static final int COST = 1;

        private static final int BLOCK = 3;
        private static final int UPGRADE_PLUS_BLOCK = 3;

        private static final int LIGHT = 3;
        private static final int UPGRADE_PLUS_Light = 2;


        public skillTorch() { // public attackNailStrike() - This one and the one right under the imports are the most important ones, don't forget them
            super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
            baseBlock = BLOCK;
            magicNumber = baseMagicNumber = LIGHT;

    }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            AbstractDungeon.actionManager.addToBottom( new GainBlockAction(p, p, this.block));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new powerLight(AbstractDungeon.player, magicNumber), this.magicNumber));

        }
            @Override
            public void upgrade() {
                if (!upgraded) {
                    upgradeName();
                    upgradeBlock(UPGRADE_PLUS_BLOCK);
                    upgradeMagicNumber(UPGRADE_PLUS_Light);
                    initializeDescription();
                }
            }
    }
