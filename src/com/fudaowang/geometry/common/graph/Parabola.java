package com.fudaowang.geometry.common.graph;

import com.fudaowang.geometry.common.util.NumberUtil;

/**
 * 表示抛物线的一般式方程y=ax^2+bx+c
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/25/12
 * Time: 10:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Parabola {
    private double a;
    private double b;
    private double c;

    /**
     * 获得抛物线的系数a
     *
     * @return 抛物线的系数a
     */
    public double getA() {
        return a;
    }

    /**
     * 获得抛物线的系数b
     *
     * @return 抛物线的系数b
     */
    public double getB() {
        return b;
    }

    /**
     * 获得抛物线的系数b
     *
     * @return 抛物线的系数b
     */
    public double getC() {
        return c;
    }

    public String toString() {
        return "Parabola{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    /**
     * 利用抛物线一般式方程的系数构造抛物线
     * a不能为0
     *
     * @param a 抛物线的系数a
     * @param b 抛物线的系数b
     * @param c 抛物线的系数c
     */
    public Parabola(double a, double b, double c) {
        if (NumberUtil.isZero(a)) {
            throw new IllegalArgumentException("抛物线方程系数a不能为0");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
