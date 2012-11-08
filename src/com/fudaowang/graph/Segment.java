package com.fudaowang.graph;

/**
 * 使用两个点表示的线段
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/24/12
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Segment {
    private Point p1;
    private Point p2;

    /**
     * 利用两个已知点构造线段
     * @param p1 线段的第一个端点
     * @param p2 线段的第二个端点
     */
    public Segment(Point p1, Point p2) {
        if (p1 == null || p2 == null) {
            throw new NullPointerException("线段端点为null");
        }
        if (p1 == p2) {
            throw new IllegalArgumentException("线段端点重复");
        }

        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * 获得线段的第一个端点
     * @return 第一个端点
     */
    public Point getP1() {
        return p1;
    }

    /**
     * 获得线段的第二个端点
     * @return 第二个端点
     */
    public Point getP2() {
        return p2;
    }

    public String toString() {
        return "Segment{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
