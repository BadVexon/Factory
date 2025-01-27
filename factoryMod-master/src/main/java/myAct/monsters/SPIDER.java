package myAct.monsters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import myAct.MyAct;
import myAct.actions.MoveCreatureAction;
import myAct.powers.FlightButForASPIDER;

public class SPIDER extends AbstractMonster {
    public static final String ID = MyAct.makeID("SPIDER");
    private static final MonsterStrings monsterstrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterstrings.NAME;
    public static final String[] DIALOG = monsterstrings.DIALOG;
    private static final int HP_MIN = 444;
    private static final int HP_MAX = 444;
    private static final int A_9_HP_MIN = 468;
    private static final int A_9_HP_MAX = 468;
    private static final float HB_X = 0.0F;
    private static final float HB_Y = 0.0F;
    private static final float HB_W = 472.0F;
    private static final float HB_H = 316.0F;
    private static final int GROUND_BIG_ATK_DAMAGE = 25;
    private static final int GROUND_SMALL_ATK_DAMAGE = 15;
    private static final int GROUND_SMALL_ATK_BLOCK = 10;
    private static final int GROUND_DEBUFF_ATK_DAMAGE = 7;
    private static final int FLIGHT_SMALL_ATK_DAMAGE = 10;
    private static final int FLIGHT_HEAL_ATK_DAMAGE = 20;
    private static final int FLIGHT_HEAL_ATK_HEAL = 5;
    private static final int INVIS_PSN_ATK_DAMAGE = 15;
    private static final int INVIS_SMALL_ATK_DAMAGE = 12;
    private static final int INVIS_FORMSWAP_DAMAGE = 10;
    public boolean removingWahoo = false;
    int groundBigAtkDamage;
    int groundSmallAtkDamage;
    int groundSmallAtkBlock;
    int groundDebuffAtkDamage;
    int flightSmallAtkDamage;
    int flightHealAtkDamage;
    int flightHealAtkHeal;
    int invisPsnAtkDamage;
    int invisSmallAtkDamage;
    int invisFormSwapDamage;
    private String curForm;
    private int turnGoing;

    public SPIDER(float x, float y) {
        super(NAME, ID, HP_MAX, HB_X, HB_Y, HB_W, HB_H, "superResources/images/monsters/SPIDER.png", x, y);

        this.type = EnemyType.BOSS;

        this.curForm = "FLIGHT";
        this.turnGoing = 0;

        if (AbstractDungeon.ascensionLevel >= 9) {
            this.setHp(A_9_HP_MIN, A_9_HP_MAX);
        } else {
            this.setHp(HP_MIN, HP_MAX);
        }

        if (AbstractDungeon.ascensionLevel >= 19) {
            groundBigAtkDamage = GROUND_BIG_ATK_DAMAGE;
            groundSmallAtkDamage = GROUND_SMALL_ATK_DAMAGE;
            groundSmallAtkBlock = GROUND_SMALL_ATK_BLOCK;
            groundDebuffAtkDamage = GROUND_DEBUFF_ATK_DAMAGE;
            flightSmallAtkDamage = FLIGHT_SMALL_ATK_DAMAGE;
            flightHealAtkDamage = FLIGHT_HEAL_ATK_DAMAGE;
            flightHealAtkHeal = FLIGHT_HEAL_ATK_HEAL;
            invisPsnAtkDamage = INVIS_PSN_ATK_DAMAGE;
            invisSmallAtkDamage = INVIS_SMALL_ATK_DAMAGE;
            invisFormSwapDamage = INVIS_FORMSWAP_DAMAGE;
        } else if (AbstractDungeon.ascensionLevel >= 4) {
            groundBigAtkDamage = GROUND_BIG_ATK_DAMAGE;
            groundSmallAtkDamage = GROUND_SMALL_ATK_DAMAGE;
            groundSmallAtkBlock = GROUND_SMALL_ATK_BLOCK;
            groundDebuffAtkDamage = GROUND_DEBUFF_ATK_DAMAGE;
            flightSmallAtkDamage = FLIGHT_SMALL_ATK_DAMAGE;
            flightHealAtkDamage = FLIGHT_HEAL_ATK_DAMAGE;
            flightHealAtkHeal = FLIGHT_HEAL_ATK_HEAL;
            invisPsnAtkDamage = INVIS_PSN_ATK_DAMAGE;
            invisSmallAtkDamage = INVIS_SMALL_ATK_DAMAGE;
            invisFormSwapDamage = INVIS_FORMSWAP_DAMAGE;
        } else {
            groundBigAtkDamage = GROUND_BIG_ATK_DAMAGE;
            groundSmallAtkDamage = GROUND_SMALL_ATK_DAMAGE;
            groundSmallAtkBlock = GROUND_SMALL_ATK_BLOCK;
            groundDebuffAtkDamage = GROUND_DEBUFF_ATK_DAMAGE;
            flightSmallAtkDamage = FLIGHT_SMALL_ATK_DAMAGE;
            flightHealAtkDamage = FLIGHT_HEAL_ATK_DAMAGE;
            flightHealAtkHeal = FLIGHT_HEAL_ATK_HEAL;
            invisPsnAtkDamage = INVIS_PSN_ATK_DAMAGE;
            invisSmallAtkDamage = INVIS_SMALL_ATK_DAMAGE;
            invisFormSwapDamage = INVIS_FORMSWAP_DAMAGE;
        }

        this.damage.add(new DamageInfo(this, groundBigAtkDamage));
        this.damage.add(new DamageInfo(this, groundSmallAtkDamage));
        this.damage.add(new DamageInfo(this, groundDebuffAtkDamage));
        this.damage.add(new DamageInfo(this, flightSmallAtkDamage));
        this.damage.add(new DamageInfo(this, flightHealAtkDamage));
        this.damage.add(new DamageInfo(this, invisPsnAtkDamage));
        this.damage.add(new DamageInfo(this, invisSmallAtkDamage));
        this.damage.add(new DamageInfo(this, invisFormSwapDamage));
    }

    public void usePreBattleAction() {
        if (!Settings.isEndless) {
            AbstractDungeon.getCurrRoom().rewardAllowed = false;
        }
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_FACTORY");
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new FlightButForASPIDER(this, 7), 7));
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new GoldenSlashEffect(AbstractDungeon.player.hb.cX - 60.0F * Settings.scale, AbstractDungeon.player.hb.cY, false), 0.0F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(3), AttackEffect.NONE));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new BiteEffect(AbstractDungeon.player.hb.cX + MathUtils.random(-25.0F, 25.0F) * Settings.scale, AbstractDungeon.player.hb.cY + MathUtils.random(-25.0F, 25.0F) * Settings.scale, Color.LIGHT_GRAY.cpy()), 0.0F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(4), AttackEffect.NONE));
                AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, flightHealAtkHeal));
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Slimed(), 1, true, false, false, (float) Settings.WIDTH * 0.2F, (float) Settings.HEIGHT / 2.0F));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Slimed(), 1, true, false, false, (float) Settings.WIDTH * 0.35F, (float) Settings.HEIGHT / 2.0F));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Slimed(), 1, true, false, false, (float) Settings.WIDTH * 0.5F, (float) Settings.HEIGHT / 2.0F));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Slimed(), 1, true, false, false, (float) Settings.WIDTH * 0.65F, (float) Settings.HEIGHT / 2.0F));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Slimed(), 1, true, false, false, (float) Settings.WIDTH * 0.8F, (float) Settings.HEIGHT / 2.0F));
                break;
            case 4:
                AbstractDungeon.actionManager.addToBottom(new MoveCreatureAction(this, this.drawX, this.drawY + 1000, 1.5F));
                AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                    public void update() {
                        removingWahoo = true;
                        isDone = true;
                    }
                });
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this, this, FlightButForASPIDER.POWER_ID));
                AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                    public void update() {
                        removingWahoo = false;
                        isDone = true;
                    }
                });
                this.curForm = "INVISIBLE";
                this.turnGoing = 0;
                break;
            case 5:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(5), AttackEffect.POISON));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new PoisonPower(AbstractDungeon.player, this, 3), 3));
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(6), AttackEffect.SLASH_HORIZONTAL));
                break;
            case 7:
                AbstractDungeon.actionManager.addToBottom(new MoveCreatureAction(this, this.drawX, this.drawY - 1100, 0.2F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(7), AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(7), AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 3), 3));
                this.curForm = "GROUNDED";
                this.turnGoing = 0;
                break;
            case 8:
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY)));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AttackEffect.NONE));
                break;
            case 9:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AttackEffect.SHIELD));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, groundSmallAtkBlock));
                break;
            case 10:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(2), AttackEffect.FIRE));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(2), AttackEffect.FIRE));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, 2, true), 2));
                break;
            case 11:
                AbstractDungeon.actionManager.addToBottom(new MoveCreatureAction(this, this.drawX, this.drawY + 100, 0.75F));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new FlightButForASPIDER(this, 7), 7));
                this.curForm = "FLIGHT";
                this.turnGoing = 0;
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        switch (this.curForm) {
            case "FLIGHT":
                if (turnGoing == 0) {
                    this.setMove((byte) 1, Intent.ATTACK_DEBUFF, this.damage.get(3).base);
                } else if (turnGoing == 1) {
                    this.setMove((byte) 2, Intent.ATTACK_BUFF, this.damage.get(4).base);
                } else if (turnGoing == 2) {
                    this.setMove((byte) 3, Intent.STRONG_DEBUFF);
                } else if (turnGoing == 3) {
                    this.setMove((byte) 4, Intent.BUFF);
                }
                turnGoing++;
                break;
            case "INVISIBLE":
                if (turnGoing == 0) {
                    this.setMove((byte) 5, Intent.ATTACK_DEBUFF, this.damage.get(5).base);
                } else if (turnGoing == 1) {
                    this.setMove((byte) 6, Intent.ATTACK, this.damage.get(6).base);
                } else if (turnGoing == 2) {
                    this.setMove((byte) 7, Intent.ATTACK_BUFF, this.damage.get(7).base, 2, true);
                }
                turnGoing++;
                break;
            case "GROUNDED":
                if (turnGoing == 0) {
                    this.setMove((byte) 8, Intent.ATTACK, this.damage.get(0).base);
                } else if (turnGoing == 1) {
                    this.setMove((byte) 9, Intent.ATTACK_DEFEND, this.damage.get(1).base);
                } else if (turnGoing == 2) {
                    this.setMove((byte) 10, Intent.ATTACK_DEBUFF, this.damage.get(2).base, 2, true);
                } else if (turnGoing == 3) {
                    this.setMove((byte) 11, Intent.BUFF);
                }
                turnGoing++;
        }
    }

    public void die() {
        if (!AbstractDungeon.getCurrRoom().cannotLose) {
            this.useFastShakeAnimation(5.0F);
            CardCrawlGame.screenShake.rumble(4.0F);
            super.die();
            this.onBossVictoryLogic();
            this.onFinalBossVictoryLogic();
        }
    }

    public void onRemoveFlight() {
        if (!this.removingWahoo) {
            AbstractDungeon.actionManager.addToBottom(new MoveCreatureAction(this, this.drawX, this.drawY - 100, 0.25F));
            this.curForm = "GROUNDED";
            this.turnGoing = 0;
            this.getMove(69);
            this.createIntent();
        }
    }
}
