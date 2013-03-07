package com.fudaowang.geometry.common.regex;

import java.util.regex.Pattern;

/**
 * 常用的几何图形信息读取用的正则表达式
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 3/7/13
 * Time: 10:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class GraphPattern {
    /**
     * 组1:点的名称,限定为字母
     * 组2:点的横坐标
     * 组3:点的纵坐标
     *
     * @return 匹配点的正则表达式
     */
    public static Pattern pointPattern() {
        return Pattern.compile("(\\w+)\\((\\-?[\\d\\.]+),(\\-?[\\d\\.]+)[\\-\\d,]+");
    }

    /**
     * 组1:线段的第一个点的名称
     * 组2:线段的第二个点的名称
     *
     * @return 匹配线段的正则表达式
     */
    public static Pattern segmentPattern() {
        return Pattern.compile("\\((\\w+),(\\w+)\\)");
    }

    /**
     * 组1:圆心点的名称,限定为字母
     * 组2:圆的半径
     *
     * @return 匹配圆的正则表达式
     */
    public static Pattern roundPattern() {
        return Pattern.compile("\\((\\w+),([\\d\\.]+)[\\d\\-,]+\\)");
    }
}
