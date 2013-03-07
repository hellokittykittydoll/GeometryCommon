package com.fudaowang.geometry.common.tuple;

import com.fudaowang.geometry.common.graph.Point;

/**
 * 表示三个一组的点集合
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 3/7/13
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class PointTriple {
    protected final Point point1;
    protected final Point point2;
    protected final Point point3;

    /**
     * 利用三个点来构造PointTriple
     *
     * @param point1 第一个点
     * @param point2 第二个点
     * @param point3 第三个点
     */
    public PointTriple(Point point1, Point point2, Point point3) {
        if (point1 == null || point2 == null || point3 == null) {
            throw new NullPointerException("给定的point为null");
        }
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

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
     * 获得第三个点
     *
     * @return 第三个点
     */
    public Point getPoint3() {
        return point3;
    }
}
