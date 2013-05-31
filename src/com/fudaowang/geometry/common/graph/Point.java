package com.fudaowang.geometry.common.graph;

import org.apache.commons.lang3.tuple.Pair;

/**
 * 表示平面直角坐标系中的一个点
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/24/12
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Point {
    protected final double x;
    protected final double y;

    /**
     * 利用点的横坐标和纵坐标来构造点
     *
     * @param x 点的横坐标
     * @param y 点的纵坐标
     */
    public Point(double x, double y) {
        if (Double.isNaN(x)) {
            throw new IllegalArgumentException("x必须是一个有效值");
        }
        if (Double.isNaN(y)) {
            throw new IllegalArgumentException("y必须是一个有效值");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * 利用参数组来构造点
     *
     * @param tuple 表示坐标数值的参数对
     */
    public Point(Pair<Double, Double> tuple) {
        if (tuple == null) {
            throw new NullPointerException("参数组为null");
        }

        this.x = tuple.getLeft();
        this.y = tuple.getRight();
    }

    /**
     * 获得点的横坐标
     *
     * @return 点的横坐标
     */
    public double getX() {
        return x;
    }

    /**
     * 获得点的纵坐标
     *
     * @return 点的纵坐标
     */
    public double getY() {
        return y;
    }

    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
