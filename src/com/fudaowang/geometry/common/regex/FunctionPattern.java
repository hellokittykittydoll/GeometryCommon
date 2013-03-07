package com.fudaowang.geometry.common.regex;

import java.util.regex.Pattern;

/**
 * 解析几何图形信息读取用正则表达式
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 3/7/13
 * Time: 10:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class FunctionPattern {
    /**
     * 组1:直线的系数A
     * 组2:直线的系数B
     * 组3:直线的系数C
     *
     * @return 匹配直线的正则表达式
     */
    public static Pattern linePattern() {
        return Pattern.compile("SimpleFunction\\((\\-?[\\d\\.]+),(\\-?[\\d\\.]+),(\\-?[\\d\\.]+)[\\-\\d,]+\\)");
    }

    /**
     * 组1:抛物线的系数A
     * 组2:抛物线的系数B
     * 组3:抛物线的系数C
     *
     * @return 匹配抛物线的正则表达式
     */
    public static Pattern parabolaPattern() {
        return Pattern.compile("Parabola\\((\\-?[\\d\\.]+),(\\-?[\\d\\.]+),(\\-?[\\d\\.]+),([\\-\\d,]+)\\)");
    }

    /**
     * 组1:双曲线的系数K
     * 组2:双曲线原点的横坐标
     * 组3:双曲线原点的纵坐标
     *
     * @return 匹配双曲线的正则表达式
     */
    public static Pattern hyperbolaPattern() {
        return Pattern.compile("InverseFunction\\((\\-?\\d+\\.?\\d*),(\\-?\\d+\\.?\\d*),(\\-?\\d+\\.?\\d*)[\\d\\-,]+\\)\n");
    }

    /**
     * 组1:横坐标单位间距
     * 组2:纵坐标单位间距
     * 组3:坐标系Y轴下限
     * 组4:坐标系Y轴上限
     * 组5:坐标系X轴下限
     * 组6:坐标系X轴上限
     *
     * @return 匹配坐标系的正则表达式
     */
    public static Pattern coordinatePattern() {
        return Pattern.compile("\\[X:([\\d\\.]+),Y:([\\d\\.]+),up:([\\d\\.]+),down:([\\d\\.]+),left:([\\d\\.]+),right:([\\d\\.]+)");
    }

    /**
     * 组1:坐标系原点的横坐标
     * 组2:坐标系原点的纵坐标
     *
     * @return 匹配坐标系原点的正则表达式
     */
    public static Pattern originPointPattern() {
        return Pattern.compile("YUANDIAN,([\\d\\.]+),([\\d\\.]+)");
    }
}
