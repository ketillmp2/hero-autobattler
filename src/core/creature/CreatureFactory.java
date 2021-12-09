package core.creature;

import core.creature.skills.CreatureSkillFactory;
import core.traits.Trait;

public class CreatureFactory {

    /* Инструкция по созданию нового существа:
    1. Создать скилл в CreatureSkillFactory
    2. Создать трейты в Trait
    3. Прописать баффы трейтов в TraitsController (не забыть добавить StatChangeSource для трейта)
    4. Создать метод для создания существа в CreatureFactoty (тут)
    5. Добавить метод создания существа в CreaturePool.init()
     */

    public static Creature dunkan() {
        return Creature.withStats("Дункан", 100, 14, 4, 1, 0, 100, 100, 1)
                .wrapTrait(Trait.KNIGHT)
                .wrapTrait(Trait.WARRIOR)
                .wrapSkill(CreatureSkillFactory.dunkanSkill());
    }

    public static Creature salvira() {
        Creature salvira = new Creature ("Сальвира", 92, 13, 1, 1, 2, 120, 90, 1, CreatureTag.HAVE_BASIC_ATTACK)
                .wrapTrait(Trait.ASSASSIN)
                .wrapTrait(Trait.POISONOUS)
                .wrapSkill(CreatureSkillFactory.salviraSkill());
        salvira.addTagValue(CreatureTag.POISONOUS, 1);
        return salvira;
    }

    public static Creature ignar() {
        return new Creature ("Игнар", 128, 14, 0, 3, 5, 80, 100, 1, CreatureTag.HAVE_BASIC_ATTACK)
                .wrapTrait(Trait.DEMON)
                .wrapTrait(Trait.EATER)
                .wrapSkill(CreatureSkillFactory.ignarSkill());
    }

    public static Creature warbot() {
        return new Creature ("Варбот", 105, 15, 4, 0, 2, 90, 100, 1, CreatureTag.HAVE_BASIC_ATTACK)
                .wrapTrait(Trait.ROBOT)
                .wrapTrait(Trait.WARRIOR)
                .wrapSkill(CreatureSkillFactory.warbotSkill());
    }

    public static Creature kodji() {
        return new Creature ("Коджи", 92, 12, 2, 4, 7, 105, 30, 1, CreatureTag.HAVE_BASIC_ATTACK)
                .wrapTrait(Trait.FROSTBORN)
                .wrapTrait(Trait.MAGE)
                .wrapSkill(CreatureSkillFactory.kodjiSkill());
    }

    public static Creature mira() {
        return new Creature ("Мира", 97, 14, 2, 3, 4, 108, 40, 1, CreatureTag.HAVE_BASIC_ATTACK)
                .wrapTrait(Trait.ALCHEMIST)
                .wrapTrait(Trait.STUDENT)
                .wrapSkill(CreatureSkillFactory.miraSkill());
    }
}
