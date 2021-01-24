package myAct.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import myAct.monsters.SentinelSpawn;

import static java.lang.Math.min;


public class SpawnSentinelSpawnAction extends AbstractGameAction {
    private int hp;

    public SpawnSentinelSpawnAction(int spawnHP) {
        this.actionType = ActionType.SPECIAL;
        this.hp = spawnHP;
    }

    @Override
    public void update() {
        float offsetX = 0;
        float offsetY = 0;
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            offsetX = min(((m.drawX - ((float) Settings.WIDTH * 0.75F)) / Settings.scale), offsetX);
            offsetY = min(m.drawY, offsetY);
        }

        AbstractDungeon.actionManager.addToTop(new SpawnMonsterAutoPositionAction(new SentinelSpawn(offsetX - (150F * Settings.scale), offsetY - (10F * Settings.scale), hp), false, offsetX - (150F * Settings.scale)));

        this.isDone = true;
    }
}