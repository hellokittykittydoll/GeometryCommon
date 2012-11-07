package com.fudaowang.graph;

/**
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

    public Angle(Point vertex, Point p1, Point p2) {
        if (vertex == null) {
            throw new NullPointerException("vertex");
        }

        if (p1 == null || p2 == null) {
            throw new NullPointerException("point");
        }

        if (vertex == p1 || vertex == p2) {
            throw new IllegalArgumentException("point equal");
        }

        this.vertex = vertex;
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getVertex() {
        return vertex;
    }

    public Point getP1() {
        return p1;
    }

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
