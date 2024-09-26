package ru.panyukovnn.multithreadingmentoring.patterns.context.threadlocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadContextHolder {

    private static final ThreadLocal<Map<String, String>> threadLocalContext = ThreadLocal.withInitial(HashMap::new);

    public static void put(String key, String value) {
        threadLocalContext.get().put(key, value);
    }

    public static String get(String key) {
        return threadLocalContext.get().get(key);
    }
}
