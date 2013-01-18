package com.fudaowang.geometry.common.graph;

import org.apache.commons.lang.enums.Enum;

/**
 * 表示两圆关系的枚举类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 12/11/12
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public final class RoundRelation extends Enum {
    public static final RoundRelation CONTAIN = new RoundRelation("包含");
    public static final RoundRelation INTERNAL = new RoundRelation("内切");
    public static final RoundRelation INTERSECT = new RoundRelation("相交");
    public static final RoundRelation EXTERNAL = new RoundRelation("外切");
    public static final RoundRelation SEPARATE = new RoundRelation("相离");

    protected RoundRelation(String name) {
        super(name);
    }
}
