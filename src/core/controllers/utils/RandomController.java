package core.controllers.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomController {

    public static int randomInt(int to, boolean inclusive) {
        return randomInt(to + (inclusive ? 1 : 0));
    }

    public static int randomInt(int to) {
        return ThreadLocalRandom.current().nextInt(to);
    }

    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int randomInt(int from, int to, boolean inclusive) {
        return ThreadLocalRandom.current().nextInt(from, to + (inclusive ? 1 : 0));
    }

    public static int randomInt(int from, int to) {
        return randomInt(from, to, true);
    }

    public static <T> T randomElementOf(List<T> list) {
        return list.get(randomInt(0, list.size(), false));
    }

    public static <T> T randomElementOf(T[] list) {
        return list[randomInt(0, list.length, false)];
    }
}
