package com.fudaowang.geometry.common.graph;

import com.fudaowang.geometry.common.tuple.DoubleTriple;
import com.fudaowang.geometry.common.util.LineUtil;
import com.fudaowang.geometry.common.util.NumberUtil;
import com.fudaowang.geometry.common.util.PointUtil;

/**
 * 表示ax+by+c=0的一般式直线方程
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/24/12
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Line {
    protected final double a;
    protected final double b;
    protected final double c;

    /**
     * 利用直线方程的系数abc来构造直线方程
     * a,b不能同时为0
     *
     * @param a 直线方程的系数a
     * @param b 直线方程的系数b
     * @param c 直线方程的系数c
     */
    public Line(double a, double b, double c) {
        if (NumberUtil.isZero(a) && NumberUtil.isZero(b)) {
            throw new IllegalArgumentException("直线方程的系数a和系数b不能同时为0");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * 利用double参数组来构造直线
     *
     * @param triple double参数组
     */
    public Line(DoubleTriple triple) {
        if (triple == null) {
            throw new NullPointerException("参数为null");
        }

        if (NumberUtil.isZero(triple.getNumber1()) && NumberUtil.isZero(triple.getNumber2())) {
            throw new IllegalArgumentException("直线方程的系数a和系数b不能同时为0");
        }

        this.a = triple.getNumber1();
        this.b = triple.getNumber2();
        this.c = triple.getNumber3();
    }

    /**
     * 利用两点构造直线
     *
     * @param p1 第一个点
     * @param p2 第二个点
     */
    protected Line(Point p1, Point p2) {
        if (p1 == null || p2 == null) {
            throw new NullPointerException("线段端点为null");
        }
        if (PointUtil.coincide(p1, p2)) {
            throw new IllegalArgumentException("线段端点重复");
        }
        Line line = LineUtil.getLine(p1, p2);
        this.a = line.getA();
        this.b = line.getB();
        this.c = line.getC();
    }

    /**
     * 利用两点的横纵坐标来构造直线
     *
     * @param x1 第一个点的横坐标
     * @param y1 第一个点的纵坐标
     * @param x2 第二个点的横坐标
     * @param y2 第二个点的纵坐标
     */
    protected Line(double x1, double y1, double x2, double y2) {
        if (PointUtil.coincide(x1, y1, x2, y2)) {
            throw new IllegalArgumentException("线段端点重复");
        }
        Line line = LineUtil.getLine(x1, y1, x2, y2);
        this.a = line.getA();
        this.b = line.getB();
        this.c = line.getC();
    }

    /**
     * 获得方程的系数a
     *
     * @return 方程的系数a
     */
    public double getA() {
        return a;
    }

    /**
     * 获得方程的系数b
     *
     * @return 方程的系数b
     */
    public double getB() {
        return b;
    }

    /**
     * 获得方程的系数c
     *
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
