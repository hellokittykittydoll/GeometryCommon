package com.fudaowang.common.graph;

import org.apache.commons.lang.enums.Enum;

/**
 * 表示两圆关系的枚举类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 12/11/12
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public final class RoundEnum extends Enum {
    public static final RoundEnum CONTAIN = new RoundEnum("包含");
    public static final RoundEnum INTERNAL = new RoundEnum("内切");
    public static final RoundEnum INTERSECT = new RoundEnum("相交");
    public static final RoundEnum EXTERNAL = new RoundEnum("外切");
    public static final RoundEnum SEPARATE = new RoundEnum("相离");

    protected RoundEnum(String name) {
        super(name);
    }
}
