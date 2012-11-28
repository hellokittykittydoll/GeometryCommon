package com.fudaowang.common.graph;

import com.fudaowang.common.util.PointUtil;

/**
 * 表示三角形的类
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/7/12
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class Triangle {
    private Point p1;
    private Point p2;
    private Point p3;

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
