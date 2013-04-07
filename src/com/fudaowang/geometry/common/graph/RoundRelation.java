package com.fudaowang.geometry.common.graph;


/**
 * 表示两圆关系的枚举类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 12/11/12
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public enum RoundRelation {
    CONTAIN("包含", 1),
    INTERNAL("内切", 2),
    INTERSECT("相交", 3),
    EXTERNAL("外切", 4),
    SEPARATE("相离", 5);

    private String name;
    private int index;

    private RoundRelation(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (RoundRelation roundRelation : RoundRelation.values()) {
            if (index == roundRelation.index) {
                return roundRelation.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
