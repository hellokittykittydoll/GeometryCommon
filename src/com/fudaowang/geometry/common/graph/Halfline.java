package com.fudaowang.geometry.common.graph;

import org.apache.commons.lang3.tuple.Pair;

/**
 * 表示射线的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 5/15/13
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class HalfLine extends Line {
    /**
     * 射线端点的横坐标
     */
    protected final double x;

    /**
     * 射线端点的纵坐标
     */
    protected final double y;

    /**
     * 射线的方向
     */
    protected final Orientation orientation;

    /**
     * 利用射线所在直线的参数以及射线端点的横纵坐标,倾角来构造射线
     *
     * @param a           所在直线的系数a
     * @param b           所在直线的系数b
     * @param c           所在直线的系数c
     * @param x           端点的横坐标
     * @param y           端点的纵坐标
     * @param orientation 射线的主要增长方向
     */
    public HalfLine(double a, double b, double c, double x, double y, Orientation orientation) {
        super(a, b, c);
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    /**
     * 利用射线所在直线的参数组以及射线端点的横纵坐标组,倾角来构造射线
     *
     * @param triple      所在直线的参数组
     * @param pair        端点坐标参数组
     * @param orientation 射线的主要增长方向
     */
    public HalfLine(Triple<Double, Double, Double> triple, Pair<Double, Double> pair, Orientation orientation) {
        super(triple);
        this.orientation = orientation;
        this.x = pair.getLeft();
        this.y = pair.getRight();
    }

    /**
     * 获得射线端点的横坐标
     *
     * @return 端点的横坐标
     */
    public double getX() {
        return x;
    }

    /**
     * 获得射线端点的纵坐标
     *
     * @return 端点的纵坐标
     */
    public double getY() {
        return y;
    }

    /**
     * 获得射线的主要增长方向
     *
     * @return 射线的主要增长方向
     */
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "HalfLine{" +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + orientation +
                '}';
    }

    /**
     * 射线的主要方向,分为X+,X-,Y+,Y-四大类
     */
    public enum Orientation {
        X, Y, MINUS_X, MINUS_Y;
    }

    /**
     * 获得射线的端点
     *
     * @return 射线的端点
     */
    public Point getPoint() {
        return new Point(x, y);
    }
}
