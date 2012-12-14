package com.fudaowang.common.graph;

import com.fudaowang.common.util.NumberUtil;

/**
 * 描述圆的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/1/12
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Round {
    private Point center;
    private double radius;

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

        this.center = center;
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
        this.center = new Point(x, y);
        this.radius = radius;
    }

    /**
     * 获得点的圆心
     *
     * @return 圆心
     */
    public Point getCenter() {
        return center;
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
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
