package myAct.monsters;

import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import myAct.MyAct;
import myAct.actions.SpawnSnakeSegmentAction;
import myAct.intents.IntentEnums;

public class SnakeTail extends AbstractMonster {
    public static final String ID = MyAct.makeID("SnakeTail");
    private static final MonsterStrings monsterstrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterstrings.NAME;
    public static final String[] DIALOG = monsterstrings.DIALOG;
    private static final int HP_MIN = 333;
    private static final int HP_MAX = 333;
    private static final int A_8_HP_MIN = 350;
    private static final int A_8_HP_MAX = 350;
    private static final float HB_X = 0.0F;
    private static final float HB_Y = 0.0F;
    private static final float HB_W = 333.0F;
    private static final float HB_H = 225.0F;
    private int turnNum = 1;

    public SnakeTail(float x, float y) {
        super(NAME, ID, HP_MAX, HB_X, HB_Y, HB_W, HB_H, "superResources/images/monsters/SnakeTail.png", x, y);

        this.type = EnemyType.BOSS;

        if (AbstractDungeon.ascensionLevel >= 8) {
            this.setHp(A_8_HP_MIN, A_8_HP_MAX);
        } else {
            this.setHp(HP_MIN, HP_MAX);
        }

    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new SpawnSnakeSegmentAction());
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        this.setMove((byte) 1, IntentEnums.SUMMON_MINI_BOT_INTENT);
    }

    public void die() {
        super.die();
    }

}
