package myAct.events;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.localization.EventStrings;
import myAct.MyAct;
import myAct.dungeons.Factory;
import myAct.patches.GoToNextDungeonPatch;

public class ForkInTheRoad extends AbstractImageEvent {
    public static final String ID = MyAct.makeID("ForkInTheRoad");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String BASE_IMG = "superResources/images/events/ForkInTheRoad.png";

    public ForkInTheRoad() {
        super(NAME, DESCRIPTIONS[0], BASE_IMG);
        imageEventText.setDialogOption(OPTIONS[1] + TheBeyond.NAME + OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[2] + Factory.NAME + OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[3]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (buttonPressed) {
            case 0:
                CardCrawlGame.nextDungeon = TheBeyond.ID;
                break;
            case 1:
                CardCrawlGame.nextDungeon = Factory.ID;
                MyAct.wentToTheFactory = true;
                break;
            case 3:
                if (AbstractDungeon.cardRandomRng.random(1) == 1) {
                    CardCrawlGame.nextDungeon = TheBeyond.ID;
                } else {
                    CardCrawlGame.nextDungeon = Factory.ID;
                    MyAct.wentToTheFactory = true;
                }
                break;
        }

        AbstractDungeon.rs = AbstractDungeon.RenderScene.NORMAL;
        if (AbstractDungeon.currMapNode.room instanceof GoToNextDungeonPatch.ForkEventRoom) {
            AbstractDungeon.currMapNode.room = ((GoToNextDungeonPatch.ForkEventRoom) AbstractDungeon.currMapNode.room).originalRoom;
        }
        GenericEventDialog.hide();

        AbstractDungeon.fadeOut();
        AbstractDungeon.isDungeonBeaten = true;
    }
}