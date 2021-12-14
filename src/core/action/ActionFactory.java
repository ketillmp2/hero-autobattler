package core.action;

import core.creature.stat.Stat;
import utils.Calculator;
import core.battlefield.BattlefieldCreature;

public class ActionFactory {

    public static Action applyPoisonDamageAction(BattlefieldCreature dealer, BattlefieldCreature target, int amount, int turnsLeft) {
        return new Action(ActionInfo.empty().from(dealer).to(target).withTime(ResolveTime.ON_START_TURN)
                .wrapTag(ActionTag.APPLY_POISON_DAMAGE, amount)
                .overrideTagMax(ActionTag.TURNS_LEFT, turnsLeft));
    }

    public static Action attackAction(BattlefieldCreature attacker, BattlefieldCreature defender) {
        return new Action(ActionInfo.empty().from(attacker).to(defender).withTime(ResolveTime.ON_MAIN_PHASE).wrapTag(ActionTag.BASIC_ATTACK).wrapTag(ActionTag.DELETE_AFTER_RESOLVE));
    }

    public static Action takeBasicAtackDamageAction(BattlefieldCreature target, BattlefieldCreature dealer) {
        int damage = Calculator.calculateBasicAttackDamage(target, dealer);
        return new Action(ActionInfo.empty().to(target).from(dealer).withTime(ResolveTime.ON_TAKING_DAMAGE).wrapTag(ActionTag.TAKE_BASIC_DAMAGE, damage).wrapTag(ActionTag.DELETE_AFTER_RESOLVE));
    }

    public static Action takePhysicalDamageAction(BattlefieldCreature target, int amount) {
        int damage = Calculator.calculatePhysicalDamage(amount, target, false);
        return new Action(ActionInfo.empty().to(target).from(target).withTime(ResolveTime.ON_TAKING_DAMAGE).wrapTag(ActionTag.TAKE_BASIC_DAMAGE, damage).wrapTag(ActionTag.DELETE_AFTER_RESOLVE));
    }

    public static Action takeMagicDamageAction(BattlefieldCreature target, int amount) {
        int damage = Calculator.calculateMagicDamage(amount, target, false);
        return new Action(ActionInfo.empty().to(target).from(target).withTime(ResolveTime.ON_TAKING_DAMAGE).wrapTag(ActionTag.TAKE_BASIC_DAMAGE, damage).wrapTag(ActionTag.DELETE_AFTER_RESOLVE));
    }

    public static Action addManaAction(BattlefieldCreature target, int amount, ResolveTime time) {
        return new Action(ActionInfo.empty().from(target).to(target).withTime(time).wrapTag(ActionTag.ADD_MANA, amount));
    }

    public static Action healingAction(BattlefieldCreature target, int amount) {
        return new Action(ActionInfo.empty().from(target).to(target).wrapTag(ActionTag.HEAL, amount));
    }

    public static Action chooseMainActionAction(BattlefieldCreature creature) {
        return new Action(ActionInfo.empty().from(creature).to(creature).withTime(ResolveTime.BEFORE_MAIN_PHASE).wrapTag(ActionTag.CHOOSE_MAIN_ACTION));
    }

    public static Action useSkillAction(BattlefieldCreature creature) {
        return new Action(ActionInfo.empty().from(creature).to(creature).withTime(ResolveTime.ON_MAIN_PHASE).wrapTag(ActionTag.USE_SKILL).wrapTag(ActionTag.DELETE_AFTER_RESOLVE));
    }

    public static Action dealDamageToAllEnemiesAction(BattlefieldCreature creature, int amount, ResolveTime resolveTime) {
        return new Action(ActionInfo.empty().from(creature).to(creature).withTime(resolveTime).wrapTag(ActionTag.DEAL_DAMAGE_TO_ALL_ENEMIES, amount));
    }

    public static Action undefinedAction() {
        return new Action(ActionInfo.empty().wrapTag(ActionTag.UNDEFINED));
    }

    public static Action addStatAction(BattlefieldCreature creature, Stat stat, int amount, ResolveTime resolveTime) {
        return new Action(ActionInfo.empty().from(creature).to(creature).withTime(resolveTime).wrapTag(ActionTag.ADD_STAT).wrapTag(ActionTag.addStat(stat), amount));
    }
}
