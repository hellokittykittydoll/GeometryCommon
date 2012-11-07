package com.fudaowang.graph;

/**
 * Created with IntelliJ IDEA.
 * User: dongxin
 * Date: 10/24/12
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Segment {
    private Point p1;
    private Point p2;

    public Segment(Point p1, Point p2) {
        if (p1 == null) {
            throw new NullPointerException("p1");
        }
        if (p2 == null) {
            throw new NullPointerException("p2");
        }
        if (p1 == p2) {
            throw new IllegalArgumentException("p1 == p2");
        }

        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

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
