package com.bosssoft.trainee.base.core.util;

import com.bosssoft.trainee.base.common.util.ReflectionUtils;
import com.bosssoft.trainee.base.core.context.BaseContextHandler;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class EntityUtils {
    /**
     * 创建人记录字段
     */
    private static final String[] CRE_USER_INFO = {"createTime", "createUserId", "createUserName", "tenantId"};

    /**
     * 更新人记录字段
     */
    private static final String[] UP_USER_INFO = {"updateTime", "updateUserId", "updateUserName", "tenantId"};

    /**
     * 添加操作人信息（操作记录）
     * <p>
     * 设置创建人记录和更新人记录
     * </p>
     *
     * @param entity 实体（表）信息
     */
    public static <T> void setCreatAndUpdatInfo(T entity) {
        setCreateInfo(entity);
        setUpdatedInfo(entity);
    }

    /**
     * 设置创建人记录
     *
     * @param entity 实体（表）信息
     */
    public static <T> void setCreateInfo(T entity) {
        setOperate(entity, CRE_USER_INFO);
    }

    /**
     * 设置更新人记录
     *
     * @param entity 实体（表）信息
     */
    public static <T> void setUpdatedInfo(T entity) {
        setOperate(entity, UP_USER_INFO);
    }

    /**
     * 设置操作人操作记录
     *
     * @param entity 实体（表）信息
     * @param fields 更新字段集（更新人、创建人记录字段）
     * @param <T>
     */
    public static <T> void setOperate(T entity, String[] fields) {
        String name = "admin";//BaseContextHandler.getName();
        String userId = "1"; //BaseContextHandler.getUserId();
        String tenantId = BaseContextHandler.getTenantId();

        // 判断实体中是否存在updateTime或者createTime字段
        Field field = ReflectionUtils.getAccessibleField(entity, fields[0]);
        Object[] value = null;
        if (field != null && field.getType().equals(LocalDateTime.class)) {
            // value和fields字段顺序必须一致
            value = new Object[]{LocalDateTime.now(), userId, name, tenantId};
        }
        // 设置记录信息
        setDefaultValues(entity, fields, value);
    }

    /**
     * 依据实体的属性数组和值数组对对象的属性进行赋值
     *
     * @param entity 实体（表）信息
     * @param fields 属性数组
     * @param value  值数组
     */
    private static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            if (ReflectionUtils.hasField(entity, field)) {
                ReflectionUtils.invokeSetter(entity, field, value[i]);
            }
        }
    }

    /**
     * 根据主键属性，判断主键是否值为空
     *
     * @param entity 实体（表）信息
     * @param field  字段
     * @return 主键为空，则返回false；主键有值，返回true
     */
    public static <T> boolean isPKNotNull(T entity, String field) {
        if (!ReflectionUtils.hasField(entity, field)) {
            return false;
        }
        Object value = ReflectionUtils.getFieldValue(entity, field);
        return value != null && !"".equals(value);
    }
}