package com.fudaowang.geometry.common.graph;

import com.fudaowang.geometry.common.util.NumberUtil;

/**
 * 描述圆的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/1/12
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Round {
    protected final double x;
    protected final double y;
    protected final double radius;

    /**
     * 利用圆心和半径长来构造圆
     *
     * @param center 圆心点
     * @param radius 半径长
     */
    public Round(Point center, double radius) {
        if (center == null) {
            throw new NullPointerException("圆心为null");
        }

        if (!NumberUtil.isMoreThanZero(radius)) {
            throw new IllegalArgumentException("半径长必须大于0");
        }

        this.x = center.getX();
        this.y = center.getY();
        this.radius = radius;
    }

    /**
     * 利用圆心的横坐标和纵坐标以及半径长来构造圆
     *
     * @param x      圆心的横坐标
     * @param y      圆心的纵坐标
     * @param radius 半径长
     */
    public Round(double x, double y, double radius) {
        if (!NumberUtil.isMoreThanZero(radius)) {
            throw new IllegalArgumentException("半径长必须大于0");
        }
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * 获得圆心的横坐标
     *
     * @return 圆心的横坐标
     */
    public double getX() {
        return x;
    }

    /**
     * 获得圆心的纵坐标
     *
     * @return 圆心的纵坐标
     */
    public double getY() {
        return y;
    }

    /**
     * 获得圆的半径长
     *
     * @return 半径长
     */
    public double getRadius() {
        return radius;
    }

    public String toString() {
        return "Round{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }
}
