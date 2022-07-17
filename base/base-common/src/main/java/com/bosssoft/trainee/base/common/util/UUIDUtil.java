package com.bosssoft.trainee.base.common.util;

import java.util.UUID;

public class UUIDUtil {
    /**
     * 剔除"-"字符,生成32位UUID字符串
     *
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}