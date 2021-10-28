package core.action;

import core.utils.Tag;

public enum ActionTag implements Tag {
    UNDEFINED("UNDEFINED", 0),
    BASIC_ATTACK("BASIC_ATTACK", 1),
    HAS_VALUE("HAS_VALUE", 2),
    TAKE_BASIC_DAMAGE("TAKE_BASIC_DAMAGE", 3),
    DELETE_AFTER_RESOLVE("DELETE_AFTER_RESOLVE", 4),
    GENERATE_BASIC_ATTACK("GENERATE_BASIC_ATTACK", 5),
    GENERATE_ADD_POISON_AFTER_ATTACK("GENERATE_ADD_POISON_AFTER_ATTACK", 6),
    ADD_POISON_AFTER_ATTACK("ADD_POISON_AFTER_ATTACK", 7),
    APPLY_POISON_DAMAGE("APPLY_POISON_DAMAGE", 8),
    TURNS_LEFT("TURNS_LEFT", 9);

    private String name;
    private int id;

    ActionTag(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static ActionTag byName(String name) {
        for (ActionTag mark : ActionTag.values()) {
            if (mark.name.equals(name)) {
                return mark;
            }
        }
        return UNDEFINED;
    }

    public static ActionTag byId(int id) {
        for (ActionTag mark : ActionTag.values()) {
            if (mark.id == id) {
                return mark;
            }
        }
        return UNDEFINED;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}