package com.fudaowang.common.graph;

/**
 * 表示平面直角坐标系中的一个点
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/24/12
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Point {
    private double x;
    private double y;

    /**
     * 利用点的横坐标和纵坐标来构造点
     * @param x 点的横坐标
     * @param y 点的纵坐标
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 获得点的横坐标
     * @return 点的横坐标
     */
    public double getX() {
        return x;
    }

    /**
     * 获得点的纵坐标
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
