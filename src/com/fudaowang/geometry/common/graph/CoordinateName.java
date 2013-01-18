package com.fudaowang.geometry.common.graph;

/**
 * 表示坐标轴的枚举类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/18/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public final class CoordinateName extends org.apache.commons.lang.enums.Enum {
    /**
     * X坐标轴
     */
    public static final CoordinateName X = new CoordinateName("X");
    /**
     * Y坐标轴
     */
    public static final CoordinateName Y = new CoordinateName("Y");

    /**
     * <p>Constructor to add a new named item to the enumeration.</p>
     *
     * @param name the name of the enum object,
     *             must not be empty or <code>null</code>
     * @throws IllegalArgumentException if the name is <code>null</code>
     *                                  or an empty string
     * @throws IllegalArgumentException if the getEnumClass() method returns
     *                                  a null or invalid Class
     */
    public CoordinateName(String name) {
        super(name);
    }
}
