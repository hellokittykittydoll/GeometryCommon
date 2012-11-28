package com.fudaowang.common.util;

/**
 * 数值计算的相关工具类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/27/12
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class NumberUtil {
    /**
     * 系统默认最小精度值,为1*10^-12
     */
    public static final double MIN_VALUE = 1E-12;

    /**
     * 判断两个浮点数是否在最小精度的范围内相等
     *
     * @param d1 第一个浮点数
     * @param d2 第二个浮点数
     * @return 若两个浮点数之差小于最小精度, 则返回true
     */
    public static boolean equal(double d1, double d2) {
        return Math.abs(d1 - d2) < MIN_VALUE;
    }

    /**
     * 判断两个数值之差是否小于给定的精度值
     *
     * @param d1        第一个数值
     * @param d2        第二个数值
     * @param precision 给定的精度值
     * @return 若两数值之差小于精度, 则返回true
     */
    public static boolean equal(double d1, double d2, double precision) {
        if (precision < MIN_VALUE) {
            precision = MIN_VALUE;
        }
        return Math.abs(d1 - d2) < precision;
    }

    /**
     * 判断给定的数值在最小精度范围内是否为0
     *
     * @param d 给定的数值
     * @return 若数值与0之差的绝对值小于最小精度, 则返回true
     */
    public static boolean isZero(double d) {
        return Math.abs(d) < MIN_VALUE;
    }

    /**
     * 判断给定的数值在最小精度范围内是否小于0
     *
     * @param d 给定的数值
     * @return 若数值小于负的最小精度, 则返回true
     */
    public static boolean isLessThanZero(double d) {
        return d < -MIN_VALUE;
    }

    /**
     * 判断给定的数值在最小精度范围内是否大于0
     *
     * @param d 给定的数值
     * @return 若数值大于最小精度, 则返回true
     */
    public static boolean isMoreThanZero(double d) {
        return d > MIN_VALUE;
    }

    /**
     * 判断给定的数值大小是否在给定的范围内
     *
     * @param d1 数值的上限或者下限
     * @param d2 数值的上限或者下限
     * @param d  需要判断的数值
     * @return 若给定的数值在范围之内, 则返回true
     */
    public static boolean valueInRange(double d1, double d2, double d) {
        return d > Math.min(d1, d2) && d < Math.max(d1, d2);
    }
}
