package com.bosssoft.trainee.base.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    /**
     * object转换为ArrayList
     *
     * @param obj   object对象
     * @param clazz 集合中的对象
     * @return 集合对象, 如何obj为null则返回空集合
     */
    public static <T> List<T> castArrayList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj == null) {
            return result;
        }
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
        }
        return result;
    }
}