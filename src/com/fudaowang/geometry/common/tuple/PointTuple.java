package com.fudaowang.geometry.common.tuple;

import com.fudaowang.geometry.common.graph.Point;

/**
 * 表示成对的两个点
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 1/16/13
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
public final class PointTuple {
    private final Point point1;
    private final Point point2;

    /**
     * 获得第一个点
     *
     * @return 第一个点
     */
    public Point getPoint1() {
        return point1;
    }

    /**
     * 获得第二个点
     *
     * @return 第二个点
     */
    public Point getPoint2() {
        return point2;
    }

    /**
     * 利用两个点构造一对点
     *
     * @param point1 第一个点
     * @param point2 第二个点
     */
    public PointTuple(Point point1, Point point2) {
        if (point1 == null || point2 == null) {
            throw new NullPointerException("point为null");
        }
        this.point1 = point1;
        this.point2 = point2;
    }
}
