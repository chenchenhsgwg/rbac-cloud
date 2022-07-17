package com.bosssoft.trainee.base.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class TreeNodeVO<T> implements Serializable {

    private static final long serialVersionUID = -3844932113013781822L;
    /**
     * 节点id
     */
    protected Object id;

    /**
     * 节点标签
     */
    protected String label;

    /**
     * 父节点id
     */
    protected Object parentId;

    /**
     * 子节点集
     */
    protected List<T> children;


    public TreeNodeVO() {
        children = new ArrayList<>();
    }

    /**
     * 添加节点
     */
    public void add(T node) {
        children.add(node);
    }
}