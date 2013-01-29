package com.fudaowang.geometry.common.graph;

import com.fudaowang.geometry.common.util.PointUtil;

/**
 * 表示三角形的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/7/12
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class Triangle {
    protected Point p1;
    protected Point p2;
    protected Point p3;

    /**
     * 利用三个已知点构造三角形
     *
     * @param p1 三角形的第一个点
     * @param p2 三角形的第二个点
     * @param p3 三角形的第三个点
     */
    public Triangle(Point p1, Point p2, Point p3) {
        if (p1 == null || p2 == null || p3 == null) {
            throw new NullPointerException("三角形的端点为null");
        }
        if (PointUtil.coincide(p1, p2) || PointUtil.coincide(p2, p3) || PointUtil.coincide(p1, p3)) {
            throw new IllegalArgumentException("三角形的端点重合");
        }

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    /**
     * 利用三个点的坐标来构造三角形
     *
     * @param x1 三角形的第一个点的横坐标
     * @param y1 三角形的第一个点的纵坐标
     * @param x2 三角形的第二个点的横坐标
     * @param y2 三角形的第二个点的纵坐标
     * @param x3 三角形的第三个点的横坐标
     * @param y3 三角形的第三个点的纵坐标
     */
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (PointUtil.coincide(x1, y1, x2, y2) || PointUtil.coincide(x1, y1, x3, y3) || PointUtil.coincide(x2, y2, x3, y3)) {
            throw new IllegalArgumentException("三角形的端点重合");
        }
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
        p3 = new Point(x3, y3);
    }

    /**
     * 获得三角形的第一个端点
     *
     * @return 三角形的第一个端点
     */
    public Point getP1() {
        return p1;
    }

    /**
     * 获得三角形的第二个端点
     *
     * @return 三角形的第二个端点
     */
    public Point getP2() {
        return p2;
    }

    /**
     * 获得三角形的第三个端点
     *
     * @return 三角形的第三个端点
     */
    public Point getP3() {
        return p3;
    }

    public String toString() {
        return "Triangle{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                '}';
    }
}
