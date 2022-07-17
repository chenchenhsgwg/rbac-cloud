package com.bosssoft.trainee.base.common.constant;

public class CommonConstant {
    /**
     * 逻辑值：假
     */
    public static final int IS_FALSE = 1;

    /**
     * 逻辑值：真
     */
    public static final int IS_TRUE = 0;

    /**
     * 页容量最大值
     * <p>
     * 最大翻页容量，防止数据量过大
     * </p>
     */
    public static final Integer LIMIT_MAX = 200;

    /**
     * 树根节点
     * <p>
     * 树形结构默认根节点标志
     * </p>
     */
    public static final String ROOT = "root";

    /**
     * 翻页参数常量类
     */
    public class Page {
        /**
         * 页码
         */
        public static final String PAGE = "page";
        /**
         * 页容量（每页数量）
         */
        public static final String LIMIT = "limit";
    }
}