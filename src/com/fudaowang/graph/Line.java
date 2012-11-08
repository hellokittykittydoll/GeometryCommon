package com.fudaowang.graph;

/**
 * 表示ax+by+c=0的一般式直线方程
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/24/12
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Line {
    private double a;
    private double b;
    private double c;

    /**
     * 利用直线方程的系数abc来构造直线方程
     * a,b不能同时为0
     * @param a 直线方程的系数a
     * @param b 直线方程的系数b
     * @param c 直线方程的系数c
     */
    public Line(double a, double b, double c) {
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("直线方程的系数a和系数b不能同时为0");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * 获得方程的系数a
     * @return 方程的系数a
     */
    public double getA() {
        return a;
    }

    /**
     * 获得方程的系数b
     * @return 方程的系数b
     */
    public double getB() {
        return b;
    }

    /**
     * 获得方程的系数c
     * @return 方程的系数c
     */
    public double getC() {
        return c;
    }

    public String toString() {
        return "Line{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
