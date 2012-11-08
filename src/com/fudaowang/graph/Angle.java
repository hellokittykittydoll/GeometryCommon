package com.fudaowang.graph;

/**
 * 表示角的类,由顶点和两个点构成
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 11/7/12
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class Angle {
    private Point vertex;
    private Point p1;
    private Point p2;

    /**
     * 利用顶点和其他点构造三角形
     * @param vertex 顶点
     * @param p1 第一个点
     * @param p2 第二个点
     */
    public Angle(Point vertex, Point p1, Point p2) {
        if (vertex == null) {
            throw new NullPointerException("角的顶点为null");
        }

        if (p1 == null || p2 == null) {
            throw new NullPointerException("角的端点为null");
        }

        if (vertex == p1 || vertex == p2) {
            throw new IllegalArgumentException("角的顶点或端点重复");
        }

        this.vertex = vertex;
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * 获得角的顶点
     * @return 角的顶点
     */
    public Point getVertex() {
        return vertex;
    }

    /**
     * 获得角的第一个端点
     * @return 端点
     */
    public Point getP1() {
        return p1;
    }

    /**
     * 获得角的第二个端点
     * @return 端点
     */
    public Point getP2() {
        return p2;
    }

    public String toString() {
        return "Angle{" +
                "vertex=" + vertex +
                ", p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
