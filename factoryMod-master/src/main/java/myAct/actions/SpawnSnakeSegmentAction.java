package myAct.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import myAct.monsters.SnakeSegment;
import myAct.monsters.ToyOrb;

import static java.lang.Math.min;


public class SpawnSnakeSegmentAction extends AbstractGameAction {

    public SpawnSnakeSegmentAction() {
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        float offsetX = 0;
        float offsetY = 0;
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            offsetX = min(((m.drawX - ((float) Settings.WIDTH * 0.75F)) / Settings.scale), offsetX);
            offsetY = min(m.drawY, offsetY);
        }

        AbstractDungeon.actionManager.addToTop(new SpawnMonsterAutoPositionAction(new SnakeSegment(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale)), false, offsetX - (150F * Settings.scale)));

        this.isDone = true;
    }
}