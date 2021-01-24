package myAct.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import myAct.monsters.*;

import static java.lang.Math.min;

public class SummonMiniBotAction extends AbstractGameAction {

    public SummonMiniBotAction() {
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

        int bruh = AbstractDungeon.cardRandomRng.random(4);
        switch (bruh) {
            case 0:
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAutoPositionAction(new MiniBotBeamer(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale)), false, offsetX - (150F * Settings.scale)));
                break;
            case 1:
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAutoPositionAction(new MiniBotBuilderBuilder(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale)), false, offsetX - (150F * Settings.scale)));
                break;
            case 2:
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAutoPositionAction(new MiniBotDebuff(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale)), false, offsetX - (150F * Settings.scale)));
                break;
            case 3:
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAutoPositionAction(new MiniBotRepair(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale)), false, offsetX - (150F * Settings.scale)));
                break;
            case 4:
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAutoPositionAction(new MiniBotVirus(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale)), false, offsetX - (150F * Settings.scale)));
                break;
        }


        this.isDone = true;
    }
}